package be.howest.deblaere.kenny.command;

import be.howest.deblaere.kenny.client.view.UsersUI;

import java.io.Serializable;

/**
 * Created by Kenny Deblaere.
 */
public class PutUserOnlineCommandToClient implements CommandToClient, Serializable {
    private String nickname;

    public PutUserOnlineCommandToClient(String nickname){
        this.nickname = nickname;
    }

    @Override
    public void performOnClient(UsersUI ui) {
        ui.addMessage(nickname);
    }

    @Override
    public void setMessage(String message) {
        this.nickname = message;
    }
}
