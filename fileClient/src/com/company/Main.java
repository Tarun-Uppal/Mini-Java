package com.company;

import java.io.BufferedOutputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.Socket;

public class Main {
    public static void main (String [] args ) throws IOException {
        int filesize=1022386;
        int bytesRead;
        int currentTot = 0;
        Socket socket = new Socket("127.0.0.1",15123);
        byte [] bytearray = new byte [filesize];
        InputStream is = socket.getInputStream();
        FileOutputStream fos = new FileOutputStream("copy.doc");
        BufferedOutputStream bos = new BufferedOutputStream(fos);
        bytesRead = is.read(bytearray, 0, bytearray.length);
        currentTot = bytesRead;
        do {
            bytesRead = is.read(bytearray, currentTot, (bytearray.length-currentTot));
            if(bytesRead >= 0) {
                currentTot += bytesRead;
            }
        }
        while(bytesRead > -1);
            bos.write(bytearray, 0 , currentTot);
            bos.flush();
            bos.close();
            socket.close();
    }
//    Read more: http://mrbool.com/file-transfer-between-2-computers-with-java/24516#ixzz6hRFfct61
}
