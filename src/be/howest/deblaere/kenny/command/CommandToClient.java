package be.howest.deblaere.kenny.command;

import be.howest.deblaere.kenny.client.view.UsersUI;

/**
 * Created by Kenny Deblaere.
 */
public interface CommandToClient {
    void performOnClient(UsersUI ui);
    void setMessage(String message);
}
