package rocks.danielw.multithreaded;


import rocks.danielw.common.ProductService;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;

public class ServiceThread extends Thread {

  private Socket socket;
  private ProductService productService;

  ServiceThread(Socket socket, ProductService productService) {
    this.socket = socket;
    this.productService = productService;
  }

  @Override
  public void run() {
    try{
      InputStream in = socket.getInputStream();
      OutputStream out = socket.getOutputStream();

      System.out.println("Waiting for product information from client...");
      byte[] request = new byte[1024];
      int resultCode = in.read(request);
      String product = new String(request).trim();

      System.out.println("Received product name: " + product);

      double price = productService.getProductPrice(product);

      out.write(Double.toString(price).getBytes());

      socket.close();
    }
    catch (IOException ioe) {
      ioe.printStackTrace();
    }
  }

}
