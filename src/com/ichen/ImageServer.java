package com.ichen;

import javax.imageio.ImageIO;
import javax.imageio.stream.ImageOutputStream;
import java.awt.image.BufferedImage;
import java.io.*;
import java.net.*;
public class ImageServer {
    public static void main(String[] args) {

        try {
            ServerSocket server = new ServerSocket(8008);
            while (true) {
                System.out.println("server is waiting for connection request from clients");
                Socket socket = server.accept();
                BufferedReader socketInput = new BufferedReader(new InputStreamReader(socket.getInputStream()));
                DataOutputStream socketOutput = new DataOutputStream (socket.getOutputStream());
                FileInputStream fileReader = new FileInputStream("/Users/ichen/Desktop/CSC2610/hw5/Koala.jpg");
                byte[] buffer = new byte[1024];
                if(socketInput.readLine().equals("Koala.jpg")) {
                    int nRead = 0;
                    while((nRead = fileReader.read(buffer)) != -1) {
                        socketOutput.write(buffer);
                    }
                }
                else {
                    socketOutput.writeUTF("Sorry, no such picture");
                }
                fileReader.close();
                socketOutput.flush();
                socketOutput.close();
                socketInput.close();
                socket.close();
            }
        } catch (Exception e) { e.printStackTrace(); }
    }
}