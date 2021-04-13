package com.ichen;

import java.io.*;
import java.net.*;

public class ImageClient {
    public static void main(String[] args) {
        String host = "localhost";
        try {
            Socket socket = new Socket(host, 8008);
            BufferedInputStream socketInput = new BufferedInputStream(socket.getInputStream());
            PrintWriter socketOutput = new PrintWriter(new OutputStreamWriter(socket.getOutputStream()));
            FileOutputStream fileWriter = new FileOutputStream("/Users/ichen/Desktop/CSC2610/hw5/Koala2.jpg");
            byte[] buffer = new byte[1024];

            socketOutput.println("Koala.jpg");
            socketOutput.flush();
            System.out.println("Getting file...");
            int count;
            while ((count = socketInput.read(buffer)) > 0)
            {
                fileWriter.write(buffer, 0, count);
            }
            System.out.println("Got file");
            socketInput.close();
            socketOutput.close();
            fileWriter.close();
            socket.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}