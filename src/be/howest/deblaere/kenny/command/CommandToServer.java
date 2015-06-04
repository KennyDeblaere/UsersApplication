package be.howest.deblaere.kenny.command;

import be.howest.deblaere.kenny.server.connect.ClientConnection;

import java.util.List;

/**
 * Created by Kenny Deblaere.
 */
public interface CommandToServer {
    void performOnServer(ClientConnection connection, List<ClientConnection> allConnections);
}
