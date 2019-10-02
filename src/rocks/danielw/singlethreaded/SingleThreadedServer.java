package rocks.danielw.singlethreaded;

import rocks.danielw.common.ProductService;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;

class SingleThreadedServer {

  private final ProductService productService;

  SingleThreadedServer(ProductService productService) {
    this.productService = productService;
  }

  void start() throws IOException {
    // server is waiting on this port for a client
    ServerSocket serverSocket = new ServerSocket(9999);
    System.out.println("Stared listening on port 9999...");

    while (true) {
      System.out.println("Waiting for client");
      Socket socket = serverSocket.accept();

      // get InputStream and OutputStream
      InputStream in = socket.getInputStream();
      OutputStream out = socket.getOutputStream();

      // read data from InputStream
      System.out.println("Waiting for product information from client...");
      byte[] request = new byte[1024];
      int resultCode = in.read(request);
      String product = new String(request).trim();

      System.out.println("Received product name: " + product);

      // send price for requested product
      double price = productService.getProductPrice(product);
      out.write(Double.toString(price).getBytes());

      // close socket
      socket.close();
    }
  }
}
