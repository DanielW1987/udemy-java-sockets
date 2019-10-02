package rocks.danielw.common;

import java.util.HashMap;
import java.util.Map;

public class ProductServiceImpl implements ProductService {

  private Map<String, Double> productInfo = new HashMap<>();

  public ProductServiceImpl() {
    productInfo.put("a", 10.99);
    productInfo.put("b", 20.95);
  }

  @Override
  public double getProductPrice(String productName) {
    return productInfo.getOrDefault(productName, 0.0);
  }

}
