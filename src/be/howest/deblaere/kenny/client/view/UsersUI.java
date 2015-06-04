package be.howest.deblaere.kenny.client.view;

import be.howest.deblaere.kenny.client.connection.UsersClient;

/**
 * Created by Kenny Deblaere.
 */
public interface UsersUI {
    void addMessage(String message);
    void setClient(UsersClient client);
}
