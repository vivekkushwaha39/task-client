/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package taskclient;

import Contract.TaskObject;
import java.io.BufferedOutputStream;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.UnknownHostException;
import Contract.PlaceHolder;

/**
 *
 * @author vfi
 */
public class FileClient {

    private NotificationListener notificationListener;
    private String hostName = "localhost";
    private int port = 1235;
    //
    private BufferedWriter bw;
    private ObjectInputStream ois;
    private Socket cliSock;

    public FileClient(NotificationListener notificationListener) {
        this.notificationListener = notificationListener;

    }

    public void setHostName(String hostName) {
        this.hostName = hostName;
    }

    public void setPort(int port) {
        this.port = port;
    }

    public boolean connect() {
        boolean ret = true;
        try {
            this.cliSock = new Socket(hostName, port);
            Socket s = new Socket(hostName, port);
            bw = new BufferedWriter(new PrintWriter(s.getOutputStream()));
            ois = new ObjectInputStream(s.getInputStream());
        } catch (UnknownHostException ex) {
            ex.printStackTrace();
            ret = false;
        } catch (IOException ex) {
            ex.printStackTrace();
            ret = false;
        }

        return ret;
    }

    private boolean getTaskClassFile(String className) {
        boolean ret = true;
        try {
            bw.write(className);
            bw.newLine();
            bw.flush();
            Long fsize = (Long) ois.readObject();
            System.out.println("File size is " + fsize);
            bw.write("OK");
            bw.newLine();
            bw.flush();

            PlaceHolder ph = new PlaceHolder();
            notificationListener.OnMessage("writing class here "
                    + ph.getClass().getResource(".").getPath()
                    + "/" + className + ".class");

            File f = new File(ph.getClass().getResource(".").getPath()
                    + "/" + className + ".class");
            if (f.exists()) {
                f.delete();
            }
            FileOutputStream fos = new FileOutputStream(f);
            byte[] buff = new byte[1024];
            int read = 0;
            long totalRead = 0;
            long remaining = fsize;
            while ((read = ois.read(buff, 0, (int) Math.min(buff.length, remaining))) > 0) {
                totalRead += read;
                remaining -= read;
                fos.write(buff, 0, read);
                System.out.println("total read " + totalRead);
            }
            notificationListener.OnMessage("Class file recieived");
            fos.flush();
            fos.close();

        } catch (ClassNotFoundException ex) {
            ex.printStackTrace();
            ret = false;
        } catch (IOException ex) {
            ex.printStackTrace();
            ret = false;
        }

        return ret;
    }

    public TaskObject getTask(String taskName) {
        TaskObject to = null;
        try {
            bw.write("GET_TASK");
            bw.newLine();
            bw.flush();
            notificationListener.OnMessage("Downloading task object");
            boolean isDownloaded = getTaskClassFile(taskName);
            if (isDownloaded == false) {
                cliSock.close();
                return to;
            }
            bw.write("OK");
            bw.newLine();
            bw.flush();
            to = (TaskObject) ois.readObject();

            if (to == null) {
                System.out.println("NULL is recieved");
            }

        } catch (Exception ex) {
            ex.printStackTrace();
        }

        return to;

    }

}