package rocks.danielw.multithreaded;

import rocks.danielw.common.ProductService;
import rocks.danielw.common.ProductServiceImpl;

public class DemoServerApplication {

  public static void main(String[] args) throws Exception {
    ProductService productService = new ProductServiceImpl();
    MultiThreadedServer server    = new MultiThreadedServer(productService);

    server.start();
  }

}

