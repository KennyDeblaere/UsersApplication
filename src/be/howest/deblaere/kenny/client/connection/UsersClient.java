package be.howest.deblaere.kenny.client.connection;

import be.howest.deblaere.kenny.client.view.UsersUI;
import be.howest.deblaere.kenny.command.CommandToClient;
import be.howest.deblaere.kenny.command.CommandToServer;
import be.howest.deblaere.kenny.command.ListOfOnlineUsersCommandToServer;
import be.howest.deblaere.kenny.command.PutUserOnlineCommandToServer;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.InetSocketAddress;
import java.net.Socket;

/**
 * Created by Kenny Deblaere.
 */
public class UsersClient {
    private int port;
    private Socket clientSocket;

    private UsersUI ui;

    private ObjectInputStream receiving;
    private ObjectOutputStream sending;


    public UsersClient(UsersUI ui){
        port = 9000;
        clientSocket = new Socket();
        this.ui = ui;
    }

    public void logon(){
        try {
            clientSocket.connect(new InetSocketAddress("127.0.0.1",port));
            System.out.println("The client side has succesfully connected");
            sending = new ObjectOutputStream(clientSocket.getOutputStream());
            receiving = new ObjectInputStream(clientSocket.getInputStream());
            reading().start();
        } catch (IOException e) {
            System.out.println("Error at function logon(): socket could not connect");
        }
    }

    private Thread reading(){
        return new Thread(){
            @Override
            public void run() {
                while (!interrupted())
                    try {
                        read();
                    } catch (IOException | ClassNotFoundException e) {
                        interrupt();
                        System.out.println("Error at method reading(): cant read messages");
                    }
            }
        };
    }

    public void putOnline(String name) throws IOException {
        CommandToServer cmd = new PutUserOnlineCommandToServer(name);
        sending.writeObject(cmd);
        sending.flush();
    }

    public void getOnlineUsers() throws IOException {
        CommandToServer cmd = new ListOfOnlineUsersCommandToServer();
        sending.writeObject(cmd);
        sending.flush();
    }

    public void read() throws IOException, ClassNotFoundException {
        CommandToClient cmd = (CommandToClient) receiving.readObject();
        cmd.performOnClient(ui);
    }
}
