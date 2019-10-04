package rocks.danielw.httpserver;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintWriter;

class HttpResponseUtils {

  static void sendResourceNotFound(OutputStream out) throws IOException {
    PrintWriter writer = new PrintWriter(out);
    writer.println("HTTP/1.1 404 ResourceNotFound");
    writer.close();
    out.close();
  }

  static void sendSuccessResponse(InputStream in, OutputStream out) throws IOException {
    PrintWriter writer = new PrintWriter(out);
    writer.println("HTTP/1.1 200 OK");
    writer.println("content-type: text/html");
    writer.println();
    writer.flush();

    IOUtils.copy(in, out);
    out.close();
  }

  static void sendInternalError(OutputStream out) throws IOException {
    PrintWriter writer = new PrintWriter(out);
    writer.println("HTTP/1.1 500 InternalServerError");
    writer.close();
    out.close();
  }

}
