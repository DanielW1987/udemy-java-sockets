package rocks.danielw.udp;

import java.io.IOException;

public class DemoServerApplication {

  public static void main(String[] args) throws IOException {
    Server server = new Server();
    server.start();
  }

}
