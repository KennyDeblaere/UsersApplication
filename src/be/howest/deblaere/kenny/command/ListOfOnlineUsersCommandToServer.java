package be.howest.deblaere.kenny.command;

import be.howest.deblaere.kenny.server.connect.ClientConnection;

import java.io.IOException;
import java.io.Serializable;
import java.util.List;

/**
 * Created by Kenny Deblaere.
 */
public class ListOfOnlineUsersCommandToServer implements CommandToServer, Serializable {
    private String onlineUsers;


    private String createUserList(List<String> userList){
        String onlineUsers = "The list of online users:";
        for(String user : userList) {
            onlineUsers += "\n" + user;
        }
        return onlineUsers;
    }

    @Override
    public void performOnServer(ClientConnection connection, List<ClientConnection> allConnections) {
        try {
            onlineUsers = createUserList(connection.getServer().getOnlineUsers());
            CommandToClient cmd = new ListOfOnlineUsersCommandToClient(onlineUsers);
            connection.getServer().broadcastMessage(cmd);
        } catch (IOException e) {
            System.out.println("Error on performOnServer(ClientConnection connection, List<ClientConnection> allConnections): Can't broadcast");
        }
    }
}
