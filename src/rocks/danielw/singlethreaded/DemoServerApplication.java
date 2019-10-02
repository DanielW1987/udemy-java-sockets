package rocks.danielw.singlethreaded;

import rocks.danielw.common.ProductService;
import rocks.danielw.common.ProductServiceImpl;

public class DemoServerApplication {

  public static void main(String[] args) throws Exception {
    ProductService productService = new ProductServiceImpl();
    SingleThreadedServer server   = new SingleThreadedServer(productService);

    server.start();
  }

}

