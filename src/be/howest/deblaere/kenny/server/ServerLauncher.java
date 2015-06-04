package be.howest.deblaere.kenny.server;

import be.howest.deblaere.kenny.server.connect.UsersServer;

/**
 * Created by Kenny Deblaere.
 */
public class ServerLauncher {
    public static void main(String[] args) {
        UsersServer server = new UsersServer();
        server.startServer();
        System.out.println("Server launched");
    }
}
