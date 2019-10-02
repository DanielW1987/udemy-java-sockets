package rocks.danielw.basic;

import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;

public class BasicClient {

  public static void main(String... args) throws Exception{

    // Connect to the server socket
    Socket socket = new Socket("127.0.0.1", 9999);

    System.out.println("Connected to server...");

    // get input and output from clients socket
    InputStream in = socket.getInputStream();
    OutputStream out = socket.getOutputStream();

    // write data to OutputStream
    out.write("Hello from Client".getBytes());

    // read data from InputStream
    byte[] response = new byte[1024];
    int resultCode = in.read(response);

    System.out.println("Received from server: " + new String(response).trim() + " (result code: " + resultCode + ")");

    // close socket
    socket.close();

  }
}
