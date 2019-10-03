package rocks.danielw.udp;

import java.io.IOException;

public class DemoClientApplication {

  public static void main(String[] args) throws IOException {
    Client client = new Client();
    client.start();
  }

}
