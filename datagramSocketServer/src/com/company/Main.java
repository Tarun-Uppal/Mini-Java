package com.company;

import java.io.IOException;
import java.net.*;

public class Main {

    public static void main(String[] args) throws IOException {
        receive();
    }
    private static DatagramSocket getReceiveSocket() throws UnknownHostException, SocketException {
        DatagramSocket receiveSocket = new DatagramSocket(5000, InetAddress.getByName("0.0.0.0")); // 0.0.0.0 for listen to all ips
        receiveSocket.setBroadcast(true);
        return receiveSocket;
    }

    public static void receive() throws IOException {
        // Discovery request command
        byte[] buffer = "__DISCOVERY_REQUEST__".getBytes();
        DatagramPacket packet = new DatagramPacket(buffer, buffer.length);
        getReceiveSocket().receive(packet);
        System.out.println("Discovery package received! -> " + packet.getAddress() + ":" + packet.getPort());
        // Get received data
        String data = new String(packet.getData()).trim();
        if (data.equals("__DISCOVERY_REQUEST__")) { // validate command
            // Send response
            byte[] response = new byte["__DISCOVERY_RESPONSE".length()];
            DatagramPacket responsePacket = new DatagramPacket(response, response.length, packet.getAddress(), packet.getPort());
            getReceiveSocket().send(responsePacket);
            System.out.println("Response sent to: " + packet.getAddress() + ":" + packet.getPort());
        } else {
            System.err.println("Error, not valid package!" + packet.getAddress() + ":" + packet.getPort());
        }
    }
}
