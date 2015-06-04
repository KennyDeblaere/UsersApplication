package be.howest.deblaere.kenny.server.connect;

import be.howest.deblaere.kenny.command.CommandToClient;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Created by Kenny Deblaere.
 */
public class UsersServer {
    private ServerSocket serverSocket;
    private Socket clientSocket;

    private ClientConnection clientConnection;
    private List<ClientConnection> clientConnections;
    private List<String> onlineUsers;

    public UsersServer(){
        try {
            serverSocket = new ServerSocket(9000);
        } catch (IOException e) {
            System.out.println("Error in constructor UsersServer() : could not instantiate serverSocket");
        }

        clientConnections = new ArrayList<>();
        onlineUsers = new ArrayList<>();
    }

    public void startServer(){
        try {
            clientSocket = serverSocket.accept();
            clientConnection = new ClientConnection(clientSocket,this);
            clientConnections.add(clientConnection);
            clientConnection.start();
            System.out.println("Server is connected");
        } catch (IOException e) {
            System.out.println("Error at function startServer() : could not accept connects");
        }
    }

    public List<String> getOnlineUsers() {
        return onlineUsers;
    }

    public boolean userIsUniqe(String username){
        boolean isUniqe = true;
        for(String user : getOnlineUsers())
            if(user.toLowerCase().equals(username.toLowerCase()))
                isUniqe = false;
        return  isUniqe;
    }

    public List<ClientConnection> getAllClientConnections(){
        return Collections.unmodifiableList(clientConnections);
    }

    public void broadcastMessage(CommandToClient cmd) throws IOException {
        for(ClientConnection cc : getAllClientConnections()){
            cc.sendToClient(cmd);
        }
    }
}
