package be.howest.deblaere.kenny.client;

import be.howest.deblaere.kenny.client.connection.UsersClient;
import be.howest.deblaere.kenny.client.view.UsersGUI;
import be.howest.deblaere.kenny.client.view.UsersUI;

/**
 * Created by Kenny Deblaere.
 */
public class ClientLauncher {
    public static void main(String[] args) {
        UsersUI ui = new UsersGUI();
        UsersClient client = new UsersClient(ui);
        ui.setClient(client);
        client.logon();
        System.out.println("Client launched");
    }
}
