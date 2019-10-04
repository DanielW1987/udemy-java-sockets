package rocks.danielw.httpserver;

import java.io.InputStream;

class ResourceLoader {

  InputStream getResource(String uri) {
    return ResourceLoader.class.getResourceAsStream(uri);
  }

}
