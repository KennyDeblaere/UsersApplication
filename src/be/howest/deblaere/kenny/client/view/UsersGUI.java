package be.howest.deblaere.kenny.client.view;

import be.howest.deblaere.kenny.client.connection.UsersClient;
import be.howest.deblaere.kenny.command.CommandToServer;
import be.howest.deblaere.kenny.command.PutUserOnlineCommandToServer;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

/**
 * Created by Kenny Deblaere.
 */
public class UsersGUI extends JFrame implements UsersUI {
    private UsersClient client;
    private JTextArea area;
    private JPanel inputPanel;
    private JTextField input;
    private JButton submit;

    public UsersGUI(){
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setComponents();
        addListeners();
        addComponents();
        setVisible(true);
        setExtendedState(MAXIMIZED_BOTH);
    }


    private void setComponents(){
        area = new JTextArea();
        area.append("List of Commands\nPutOnline - <username>\n   Puts online an user\nOnlineList\n   List of online users");
        input = new JTextField(50);
        submit = new JButton("Send");
        inputPanel = new JPanel();
        inputPanel.add(input);
        inputPanel.add(submit);
    }

    private void addListeners(){
        submit.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    addMessage(input.getText());
                    if(input.getText().toLowerCase().contains("PutOnline -".toLowerCase()))
                        client.putOnline(input.getText());
                    if(input.getText().toLowerCase().contains("OnlineList".toLowerCase()))
                        client.getOnlineUsers();
                    input.setText("");
                } catch (IOException e1) {
                    e1.printStackTrace();
                }
            }
        });
    }

    private void addComponents(){
        add(area, BorderLayout.CENTER);
        add(inputPanel, BorderLayout.SOUTH);
    }

    @Override
    public void addMessage(String message) {
        String content = "\n" + message;
        area.append(content);
    }

    @Override
    public void setClient(UsersClient client) {
        this.client = client;
    }
}
