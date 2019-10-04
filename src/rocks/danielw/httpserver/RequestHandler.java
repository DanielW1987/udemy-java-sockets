package rocks.danielw.httpserver;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;

class RequestHandler {

  private final ResourceLoader resourceLoader;

  RequestHandler(ResourceLoader resourceLoader) {
    this.resourceLoader = resourceLoader;
  }

  void handleRequest(Socket socket) {
    OutputStream out = null;
    try {
      out = socket.getOutputStream();
      String request = HttpUtils.getRequest(socket);
      String uri = HttpUtils.getRequestUri(request);
      System.out.println("Received request for URI '" + uri + "'");
      System.out.println(request);

      InputStream in = resourceLoader.getResource(uri);

      if (in == null) {
        System.out.println("Sending resource not found");
        HttpResponseUtils.sendResourceNotFound(out);
        return;
      }

      System.out.println("Sending response");
      HttpResponseUtils.sendSuccessResponse(in, out);
    }
    catch (IOException ioe) {
      ioe.printStackTrace();
      try {
        System.out.println("Sending internal error");
        HttpResponseUtils.sendInternalError(out);
      } catch (IOException e) {
        e.printStackTrace();
      }
    }
    finally {
      try {
        socket.close();
      }
      catch (IOException ioe) {
        System.err.println(ioe.getMessage());
      }
    }
  }

}
