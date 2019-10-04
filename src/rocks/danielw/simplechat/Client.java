package rocks.danielw.simplechat;

import java.io.IOException;
import java.net.Socket;

class Client {

  void start() throws IOException {
    Socket socket = new Socket("localhost", 2000);
    ChatFrame chatFrame = new ChatFrame("Simple Chat", socket);
    chatFrame.setVisible(true);
  }

}
