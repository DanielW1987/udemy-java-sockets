package rocks.danielw.singlethreaded;

import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;
import java.util.Scanner;

class Client {

  Client() {}

  void start() throws Exception{
    // connecting to server
    System.out.println("Connecting to Server...");
    Socket socket = new Socket("127.0.0.1", 9999);
    System.out.println("Connected to Server");

    // get InputStream and OutputStream
    InputStream in = socket.getInputStream();
    OutputStream out = socket.getOutputStream();

    // await user input
    Scanner scanner = new Scanner(System.in);
    System.out.print("Product: ");
    String product  = scanner.nextLine();

    // write user input to server
    out.write(product.getBytes());

    // receive response from server
    byte[] response = new byte[1024];
    int resultCode = in.read(response);
    System.out.println("Received response is: " + new String(response).trim());

    // close connection
    socket.close();
  }
}
