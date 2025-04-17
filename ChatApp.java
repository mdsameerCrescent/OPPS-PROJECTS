import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.*;
import java.net.*;

class ChatServer {
    private ServerSocket serverSocket;
    private Socket clientSocket;
    private PrintWriter out;
    private BufferedReader in;

    public ChatServer(int port) throws IOException {
        serverSocket = new ServerSocket(port);
        clientSocket = serverSocket.accept();
        out = new PrintWriter(clientSocket.getOutputStream(), true);
        in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
    }

    public String receiveMessage() throws IOException {
        return in.readLine();
    }

    public void sendMessage(String message) {
        out.println(message);
    }

    public void close() throws IOException {
        in.close();
        out.close();
        clientSocket.close();
        serverSocket.close();
    }
}

class ChatClient {
    private Socket socket;
    private PrintWriter out;
    private BufferedReader in;

    public ChatClient(String serverAddress, int port) throws IOException {
        socket = new Socket(serverAddress, port);
        out = new PrintWriter(socket.getOutputStream(), true);
        in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
    }

    public String receiveMessage() throws IOException {
        return in.readLine();
    }

    public void sendMessage(String message) {
        out.println(message);
    }

    public void close() throws IOException {
        in.close();
        out.close();
        socket.close();
    }
}

class ChatUI extends JFrame {
    private JTextArea textArea;
    private JTextField messageField;
    private JButton sendButton;
    private ChatClient client;

    public ChatUI() {
        textArea = new JTextArea(20, 40);
        messageField = new JTextField(40);
        sendButton = new JButton("Send");

        setLayout(new BorderLayout());
        add(new JScrollPane(textArea), BorderLayout.CENTER);
        add(messageField, BorderLayout.SOUTH);
        add(sendButton, BorderLayout.EAST);

        sendButton.addActionListener(e -> {
            String message = messageField.getText();
            client.sendMessage(message);
            textArea.append("You: " + message + "\n");
            messageField.setText("");
        });

        setTitle("Chat Application");
        setSize(500, 500);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);
    }

    public void setClient(ChatClient client) {
        this.client = client;
    }

    public static void main(String[] args) {
        try {
            ChatUI ui = new ChatUI();
            ChatClient client = new ChatClient("localhost", 12345);
            ui.setClient(client);

            while (true) {
                String message = client.receiveMessage();
                ui.textArea.append("Server: " + message + "\n");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
