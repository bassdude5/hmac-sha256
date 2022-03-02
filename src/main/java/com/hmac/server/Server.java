package com.hmac.server;

// A Java program for a Server 
import java.net.*;
import java.io.*;

import com.hmac.HMAC;

public class Server {

    // initialize socket and input stream
    private Socket socket = null;
    private ServerSocket server = null;
    private DataInputStream in = null;
    static final int PORT_NUMBER = 5000;

    // constructor with port
    public Server(int port) {
        // starts server and waits for a connection
        try {
            server = new ServerSocket(port);
            System.out.println("Server started");

            System.out.println("Waiting for a client ...");

            socket = server.accept();
            System.out.println("Client accepted");

            // takes input from the client socket
            in = new DataInputStream(
                    new BufferedInputStream(socket.getInputStream()));

            String line = "";
            try {
                line = in.readUTF();

                System.out.println(line);

                String[] output = line.split("\n");

                if (output.length != 3) {
                    System.out.println("Error: Data Sent incorrectly");
                } else {
                    String randomString = output[0];
                    String hash = output[1];
                    String secretKey = output[2];

                    String serverHash = HMAC.encode(secretKey, randomString);
                    System.out.printf("Received-\nString: %s\nHash: %s\nSecret Hash: %s\n", randomString, hash,
                            secretKey);
                    System.out.printf("%s\n", serverHash.equals(hash) ? "MAC OK" : "MAC failed");
                }
            } catch (IOException i) {
                System.out.println(i);
            }

            System.out.println("Closing connection");

            // close connection
            socket.close();
            in.close();

        }

        catch (Exception i) {
            System.out.println(i);
        }
    }

    public static void main(String args[]) {
        Server server = new Server(PORT_NUMBER);
    }
}