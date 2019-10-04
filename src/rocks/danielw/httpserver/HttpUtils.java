package rocks.danielw.httpserver;

import java.io.IOException;
import java.io.InputStream;
import java.net.Socket;
import java.util.StringTokenizer;

class HttpUtils {

  static String getRequest(Socket socket) throws IOException {
    StringBuilder requestString = new StringBuilder();
    byte [] requestBytes = new byte[40000];

    InputStream in = socket.getInputStream();
    int n = in.read(requestBytes);

    String requestPart = null;

    if (n != -1) {
      requestPart = new String(requestBytes, 0, n);
      requestString.append(requestPart);
    }

    if (requestPart != null && requestPart.contains("multipart/form-data")) {
      n = in.read(requestBytes);
      if (n != -1) {
        requestString.append( new String(requestBytes, 0, n));
      }
    }

    return requestString.toString();
  }

  static String getRequestUri(String request) {
    StringTokenizer stk = new StringTokenizer(request);
    String method = stk.hasMoreTokens() ? stk.nextToken() : null;
    return stk.hasMoreTokens() ? stk.nextToken() : null; // the uri
  }

}
