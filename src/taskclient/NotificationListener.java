/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package taskclient;

import Contract.TaskList;
import Contract.TaskObject;

/**
 * An interface for registering listeners for async notifications
 * 
 */
public interface NotificationListener {
	public void OnTaskList(TaskList tl);
	public void OnTask(TaskObject t);
	public void OnError(Exception ex);
	public void OnMessage(String msg);
}
