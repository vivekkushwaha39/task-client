/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package taskclient;

import Contract.Task;
import Contract.TaskList;
import Contract.TaskObject;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Class for making connection requests AKA client
 *
 */
public class NetworkClient {

    NotificationListener notificationListener;
    Socket cliSock = null;
    String hostname = "localhost";
    private int taskPort = 1234;
    private int filePort = 1235;
    ObjectInputStream ois;
    ObjectOutputStream oout;

    public void setTaskPort(int taskPort) {
        this.taskPort = taskPort;
    }

    public void setFilePort(int filePort) {
        this.filePort = filePort;
    }

    public void setHostname(String hostname) {
        this.hostname = hostname;
    }

    public NetworkClient(NotificationListener notificationListener) {
        this.notificationListener = notificationListener;
    }

    /**
     * Function to create socket 
     * @return bool
     */
    public boolean init() {
        boolean ret = true;
        try {
            System.out.println("creating socket");
            cliSock = new Socket(hostname, taskPort);
            System.out.println("end creating socket");
            oout = new ObjectOutputStream(cliSock.getOutputStream());
            ois = new ObjectInputStream(cliSock.getInputStream());

            System.out.println("end creating socket");
        } catch (Exception ex) {
            ex.printStackTrace();
            notificationListener.OnError(ex);
            ret = false;
        }
        
        return ret;
    }
    /***
     * Function to get Task List
     */
    public void getTaskList() {
        System.out.println("Getting task list");

        try {

            System.out.println("Getting output");
            System.out.println("Getting task list");
            oout.writeObject("GET_TASKS_LIST");
            oout.flush();

            System.out.println("wating for tasklist obj");
            Object taskList = ois.readObject();
            TaskList tl = (TaskList) taskList;
            notificationListener.OnTaskList(tl);
        } catch (IOException | ClassNotFoundException ex) {
            ex.printStackTrace();
        }
    }

    /**
     * Function to download task class file from file server.
     * @param className name of the class to download.
     */
    public boolean getTask(String className) {
        FileClient fileClient = new FileClient(notificationListener);
        fileClient.setHostName(this.hostname);
        fileClient.setPort(this.filePort);
        if (fileClient.connect() == true) {
            fileClient.getTask(className);
            fileClient.close();
            return true;
        }
        else{
            return false;
        }

    }

    /**
     * Request server to fill the TaskObject.
     * Server will identify using task id.
     * @param to 
     */
    public void fillTaskObject(TaskObject to) {
        TaskObject filledObject = null;

        try {
            oout.writeObject("FILL_TASK");
            oout.writeObject(to);
            oout.flush();
            filledObject = (TaskObject) ois.readObject();
        } catch (Exception ex) {
            ex.printStackTrace();
        }

        notificationListener.OnTask(filledObject);
    }

    /**
     * Send calculated result to server and receive credits for it.
     * @param res 
     */
    public void sendResult(TaskObject res) {
        System.out.println("Sending result to server");
        try {

            oout.writeObject("RESULT");
            oout.flush();
            Thread.sleep(500);
            System.out.println("Writing object");
            oout.writeObject(res);
            oout.flush();
            Thread.sleep(1000);
            TaskObject to = (TaskObject) ois.readObject();
            notificationListener.OnTask(to);

        } catch (IOException ex) {
            ex.printStackTrace();
        } catch (InterruptedException ex) {
            ex.printStackTrace();
        } catch (ClassNotFoundException ex) {
            ex.printStackTrace();
        }

    }

    /**
     * close current connection with server.
     */
    public void close() {
        System.out.println("Closing Network client");
        try {
            this.cliSock.close();
        } catch (IOException ex) {
            Logger.getLogger(NetworkClient.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

}
