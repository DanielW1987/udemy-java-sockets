package rocks.danielw.simplechat;

import java.io.IOException;

public class ServerApplication {

  public static void main(String[] args) throws IOException {
    Server server = new Server();
    server.start();
  }

}
