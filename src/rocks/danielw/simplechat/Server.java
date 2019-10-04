package rocks.danielw.simplechat;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

class Server {

  void start() throws IOException {
    ServerSocket serverSocket = new ServerSocket(2000);

    while (true) {
      System.out.println("Waiting for client");
      Socket socket = serverSocket.accept();
      ChatFrame chatFrame = new ChatFrame("Simple Chat (Server)", socket);
      chatFrame.setVisible(true);
    }
  }

}
