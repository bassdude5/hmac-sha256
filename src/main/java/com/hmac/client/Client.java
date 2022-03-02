package com.hmac.client;

import java.net.*;
import java.io.*;
import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;

import com.hmac.HMAC;

public class Client {

    public static final int STRING_SIZE = 30;
    private static final String secretKey = "top_secret_key";
    // initialize socket and input output streams
    private Socket socket = null;
    private DataInputStream input = null;
    private DataOutputStream out = null;
    static final int PORT_NUMBER = 5000;

    // constructor to put ip address and port
    public Client(String address, int port) {
        // establish a connection
        try {
            socket = new Socket(address, port);
            System.out.println("Connected");

            // sends output to the socket
            out = new DataOutputStream(socket.getOutputStream());
        } catch (UnknownHostException u) {
            System.out.println(u);
        } catch (IOException i) {
            System.out.println(i);
        }

        try {
            String randomString = generateRandomString(STRING_SIZE);
            System.out.println(String.format("Generated String: %s\nKey: %s\n", randomString, secretKey));

            String hash = HMAC.encode(secretKey, randomString);
            out.writeUTF(randomString + "\n" + hash + "\n" + secretKey);
        }

        catch (Exception e) {
            System.out.println(e);
        }
        // close the connection
        try {
            out.close();
            socket.close();
        } catch (IOException i) {
            System.out.println(i);
        }
    }

    public static void main(String[] args) {
        Client client = new Client("127.0.0.1", PORT_NUMBER);
    }

    public static String generateRandomString(int size) {
        String AlphaNumericString = "ABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789abcdefghijklmnopqrstuvxyz";
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < size; i++) {
            // generate random number [0,length)
            int index = (int) Math.floor((AlphaNumericString.length() * Math.random()));
            // get the char at that index
            sb.append(AlphaNumericString
                    .charAt(index));
        }

        return sb.toString();
    }
}
