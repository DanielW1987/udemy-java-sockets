package rocks.danielw.httpserver;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

class IOUtils {

  static void copy(InputStream in, OutputStream out) throws IOException {
    int value;
    while ((value = in.read()) != -1) {
      out.write(value);
    }
  }

}
