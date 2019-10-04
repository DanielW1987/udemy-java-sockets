package rocks.danielw.simplechat;

import java.io.IOException;

public class ClientApplication {

  public static void main(String[] args) throws IOException {
    Client client = new Client();
    client.start();
  }

}
