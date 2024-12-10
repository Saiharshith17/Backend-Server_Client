package SingleThreaded;
import java.io.IOException;
import java.net.ServerSocket;
import java.io.BufferedReader;
import java.io.*;
import java.util.*;
import java.net.Socket;

public class Server {
    public void run() throws Exception {
        int port = 8010;
        ServerSocket serverSocket = new ServerSocket(port);
        System.out.println("Server is listening on port " + port);

        while (true) {
            try (Socket acceptedConnection = serverSocket.accept();
                 PrintWriter toClient = new PrintWriter(acceptedConnection.getOutputStream(), true);
                 BufferedReader fromClient = new BufferedReader(new InputStreamReader(acceptedConnection.getInputStream()))) {

                System.out.println("Connection accepted from Client " + acceptedConnection.getRemoteSocketAddress());

                // Send a message to the client
                toClient.println("Hello from the server");
                System.out.println("Message sent to client: Hello from the server");

                // Read the client's message
                String clientMessage = fromClient.readLine();
                System.out.println("Client says: " + clientMessage);
            } catch (IOException e) {
                System.err.println("Error handling client connection: " + e.getMessage());
            }
        }
    }

    public static void main(String[] args) {
        Server server = new Server();
        try {
            server.run();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}