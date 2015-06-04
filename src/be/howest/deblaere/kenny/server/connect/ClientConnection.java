package be.howest.deblaere.kenny.server.connect;

import be.howest.deblaere.kenny.command.CommandToClient;
import be.howest.deblaere.kenny.command.CommandToServer;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

/**
 * Created by Kenny Deblaere.
 */
public class ClientConnection extends Thread {
    private Socket socket;
    private UsersServer server;

    private ObjectInputStream receiving;
    private ObjectOutputStream sending;

    public ClientConnection(Socket clientSocket, UsersServer usersServer){
        socket = clientSocket;
        server = usersServer;
    }

    @Override
    public void run() {
        try {
            sending = new ObjectOutputStream(socket.getOutputStream());
            receiving = new ObjectInputStream(socket.getInputStream());
            receiveFromClient().start();
        } catch (IOException e) {
            System.out.println("Error at function run() : Can't instantiate sending or receiving");
            close();

        }


    }

    private Thread receiveFromClient(){
        return new Thread(){
            @Override
            public void run() {
                while (!interrupted())
                    try {
                        processClientCommand();
                    } catch (IOException | ClassNotFoundException e) {
                        interrupt();
                        System.out.println("Error at method receiveFromClient(): Can't receive a command");
                    }
            }
        };
    }

    private void processClientCommand() throws IOException, ClassNotFoundException {
        CommandToServer cmd = (CommandToServer) receiving.readObject();
        cmd.performOnServer(this, server.getAllClientConnections());
    }

    public void sendToClient(CommandToClient cmd) throws IOException {
        sending.writeObject(cmd);
        sending.flush();
    }

    private void close(){
        try {
            socket.close();
            System.out.println("Server is closed");
        } catch (IOException e) {
            System.out.println("Error at function close() : can't close socket");
        }
    }

    public UsersServer getServer() {
        return server;
    }
}
