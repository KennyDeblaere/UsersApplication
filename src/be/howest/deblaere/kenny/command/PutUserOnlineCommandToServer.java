package be.howest.deblaere.kenny.command;

import be.howest.deblaere.kenny.server.connect.ClientConnection;

import java.io.IOException;
import java.io.Serializable;
import java.util.List;

/**
 * Created by Kenny Deblaere.
 */
public class PutUserOnlineCommandToServer implements CommandToServer, Serializable {
    private String nickname;

    public PutUserOnlineCommandToServer(String nickname){
        this.nickname = nickname.toLowerCase();
    }

    private String splitNickname(String nickname){
        return nickname.substring(nickname.indexOf("-")+1);

    }

    @Override
    public void performOnServer(ClientConnection connection, List<ClientConnection> allConnections) {
        try {
            CommandToClient cmd = new PutUserOnlineCommandToClient(nickname);
            if(connection.getServer().userIsUniqe(splitNickname(nickname)))
                connection.getServer().getOnlineUsers().add(splitNickname(nickname));
            else
                cmd.setMessage("The username is already chosen, pick another");
            connection.getServer().broadcastMessage(cmd);
        } catch (IOException e) {
            System.out.println("Error at method performOnServer(ClientConnection connection, List<ClientConnection> allConnections): Can't broadcast");
        }
    }
}
