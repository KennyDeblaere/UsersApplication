package be.howest.deblaere.kenny.command;

import be.howest.deblaere.kenny.client.view.UsersUI;

import java.io.Serializable;

/**
 * Created by Kenny Deblaere.
 */
public class ListOfOnlineUsersCommandToClient implements CommandToClient, Serializable {
    private String onlineUserList;

    public ListOfOnlineUsersCommandToClient(String onlineUserList) {
        this.onlineUserList = onlineUserList;
    }

    @Override
    public void performOnClient(UsersUI ui) {
        String message = "Online\n" + onlineUserList;
        ui.addMessage(message);
    }

    @Override
    public void setMessage(String message) {
        this.onlineUserList = message;
    }
}
