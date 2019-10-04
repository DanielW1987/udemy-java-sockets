package rocks.danielw.httpserver;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

class SimpleHttpServer {

  void start() throws IOException {
    ServerSocket connectionSocket = new ServerSocket(8085);
    ExecutorService executor = Executors.newFixedThreadPool(5);

    while (true) {
      Socket clientSocket = connectionSocket.accept();
      executor.submit(new ServiceRequestTask(clientSocket));
    }
  }

}
