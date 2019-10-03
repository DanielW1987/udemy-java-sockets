package rocks.danielw.udp;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;

class Client {

  void start() throws IOException {
    DatagramSocket socket = new DatagramSocket();

    String message = "Hello from client";
    byte[] data = message.getBytes();
    DatagramPacket packet = new DatagramPacket(data, data.length, InetAddress.getLocalHost(), 8989);

    socket.send(packet);

    socket.close();
  }

}
