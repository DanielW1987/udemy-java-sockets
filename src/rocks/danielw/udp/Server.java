package rocks.danielw.udp;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;

class Server {

  void start() throws IOException {
    DatagramSocket socket = new DatagramSocket(8989);

    // packet for receiving data
    DatagramPacket packet = new DatagramPacket(new byte[1000], 1000);

    socket.receive(packet);

    System.out.println(new String(packet.getData()));
    System.out.println("Obtained from IP '" + packet.getAddress() + "'");
    System.out.println("Obtained from port " + packet.getPort());

    socket.close();
  }

}
