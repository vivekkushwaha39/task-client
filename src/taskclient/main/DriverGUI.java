/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package taskclient.main;

import Contract.TaskList;
import Contract.TaskObject;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Arrays;
import java.util.LinkedList;
import taskclient.NetworkClient;
import taskclient.NotificationListener;
import taskclient.gui.ClientUI;

public class DriverGUI extends ClientUI implements NotificationListener, ActionListener {

    private TaskList tl = null;
    private TaskObject to = null;
    private boolean busy = false;

    public DriverGUI() {
        step1();
        btnAvTask.addActionListener(this);
        btnDwnldTsk.addActionListener(this);
    }

    @Override
    public void OnTaskList(TaskList tl) {
        this.tl = tl;
    }

    @Override
    public void OnTask(TaskObject t) {
        System.out.println("Ontask");
        this.to = t;
    }

    @Override
    public void OnError(Exception ex) {
        ex.printStackTrace();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (tryAsyncAction() == false) {
            //it means we are busy
            System.out.println("Client is busy");
            return;
        }

        System.out.println("actionPerformed");
        try {
            new Thread(new Runnable() {
                @Override
                public void run() {
                    if (e.getSource().equals(btnAvTask)) {
                        populateTaskList();

                    } else if (e.getSource().equals(btnDwnldTsk)) {
                        performTask();
                    }

                }
            }).start();
        } catch (Exception ex) {
            ex.printStackTrace();
        } finally {
            setFree();
        }
    }

    /**
     * Function to get task list And download all class files
     */
    private void populateTaskList() {

        btnAvTask.setEnabled(false);
        NetworkClient localclient = new NetworkClient(this);
        localclient.setHostname(txtHost.getText());
        localclient.init();
        addLog("Getting task list from master");
        localclient.getTaskList();
        if (tl == null) {
            addLog("TaskList not avaialable");
            localclient.close();
            return;
        }

        System.out.println("Available Tasks");
        String[] tasks = tl.getTaskClassName();
        String[] descs = tl.getAvailableTasks();
        for (String s : tasks) {
            System.out.println(s);
            addLog("Downloading task class " + s);
            localclient.getTask(s);
        }
        setTasks(new LinkedList<>(Arrays.asList(descs)));
        btnAvTask.setEnabled(true);
        addLog("Downloaded all available task list");
        localclient.close();
        step2();
    }

    /**
     * Function to download task and class file
     */
    private void performTask() {
        NetworkClient client = new NetworkClient(this);
        client.init();
        addLog("Getting task");
        int selItem = cmbTaskList.getSelectedIndex();
        if (selItem == -1) {
            addLog("Please Select task from Dropdown");
            System.out.println("No task selected");
            addLog("No task selected");
            return;
        }

        TaskObject taskObject = new TaskObject();
        taskObject.setTaskID(selItem);
        addLog("Filling task with id" + selItem);
        client.fillTaskObject(taskObject);
        if (to == null) {
            addLog("Selected task is null aborting");
            System.out.println("task is null");
            addLog("task is null");
            client.close();
            step1();
            return;
        }
        addLog("Executing task");
        to.getTObject().executeTask();
        addLog("Result is '" + to.getTObject().getResult().toString() + "'");
        addLog("Sending result to master");
        client.sendResult(to);
        if (to != null) {
            addLog("------------ Task Done Credits recieved ------------");
        } else {
            addLog("------------------- Task Failed --------------------");
        }
        addLog("");
        client.close();
    }

    @Override
    public void OnMessage(String msg) {
        addLog(msg);
    }

    private void setFree() {
        synchronized (this) {
            busy = false;
        }
    }

    /**
     * Try performing action. This function prevents client to perform actions
     * when client already working on some other task.
     *
     * @return
     */
    private boolean tryAsyncAction() {
        synchronized (this) {
            boolean ret = true;
            if (busy == true) {
                ret = false;
            } else {
                busy = true;
                ret = true;
            }
            return ret;
        }
    }

}
