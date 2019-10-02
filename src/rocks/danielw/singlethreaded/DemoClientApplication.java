package rocks.danielw.singlethreaded;

public class DemoClientApplication {

  public static void main(String[] args) throws Exception {
    Client client = new Client();
    client.start();
  }

}

