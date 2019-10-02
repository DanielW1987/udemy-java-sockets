package rocks.danielw.multithreaded;

import rocks.danielw.common.ProductService;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

class MultiThreadedServer {

  private final ProductService productService;

  MultiThreadedServer(ProductService productService) {
    this.productService = productService;
  }

  void start() throws IOException {

    ServerSocket serverSocket = new ServerSocket(9999);

    System.out.println("Stared listening on port 9999...");

    while (true) {
      System.out.println("Waiting for client");
      Socket socket = serverSocket.accept();

      // create a new thread to service client
      System.out.println("Starting a thread which will service the client...");
      new ServiceThread(socket, productService).start();
    }
  }
}
