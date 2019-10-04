package rocks.danielw.httpserver;

import java.net.Socket;

class ServiceRequestTask implements Runnable {

  private final Socket socket;
  private final RequestHandler requestHandler;

  ServiceRequestTask(Socket socket) {
    this.socket = socket;
    this.requestHandler = new RequestHandler(new ResourceLoader());
  }

  @Override
  public void run() {
    requestHandler.handleRequest(socket);
  }
}
