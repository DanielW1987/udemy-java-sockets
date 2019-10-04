package rocks.danielw.httpserver;

import java.io.IOException;

public class Application {

  public static void main(String[] args) throws IOException {
    new SimpleHttpServer().start();
  }

}
