package com.company;

import java.io.IOException;
import java.net.*;

public class Main {

    public static void main(String[] args) throws IOException {
        send();
    }
    private static DatagramSocket getSendSocket() throws UnknownHostException, SocketException {
        DatagramSocket sendSocket = new DatagramSocket(5001, InetAddress.getLocalHost());
        sendSocket.setBroadcast(true);
        return sendSocket;
    }

    public static void send() throws IOException {
        // Discovery request command
        byte[] data = "__DISCOVERY_REQUEST__".getBytes();
        DatagramPacket packet = new DatagramPacket(data, data.length, InetAddress.getByName("255.255.255.255"), 8002);
        getSendSocket().send(packet);
        System.out.println("Discovery package sent!" + packet.getAddress() + ":" + packet.getPort());

        // Discovery response command
        byte[] response = new byte["__DISCOVERY_RESPONSE__".length()];
        DatagramPacket responsePacket = new DatagramPacket(response, response.length);
        getSendSocket().receive(responsePacket);
        System.out.println("Discovery response received!" + responsePacket.getAddress() + ":" + responsePacket.getPort());
        String responseData = new String(responsePacket.getData()).trim();
        if (responseData.equals("__DISCOVERY_RESPONSE__")) { // Check valid command
            System.out.println("Found buddy!" + responsePacket.getAddress() + ":" + responsePacket.getPort());
        }
    }
}
