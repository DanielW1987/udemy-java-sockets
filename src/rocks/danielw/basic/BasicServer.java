package rocks.danielw.basic;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;

public class BasicServer {

  public static void main(String... args) throws IOException {

    // On this socket clients can ask the server for a connection
    // server is waiting on this port for a client
    ServerSocket serverSocket = new ServerSocket(9999);
    System.out.println("Waiting for client...");

    // Listens for a connection to be made to this socket and accepts it.
    // Communication takes place on this socket. The socket provides an InputStream and an OutputStream.
    // The method blocks until a connection is made.
    // This Socket uses another port
    Socket socket = serverSocket.accept();

    System.out.println("Client connected...");

    // get input and output from servers socket
    InputStream in   = socket.getInputStream();
    OutputStream out = socket.getOutputStream();

    // read data from InputStream
    byte[] buffer = new byte[1024];
    int resultCode = in.read(buffer);

    System.out.println("Received from client - " + new String(buffer).trim() + " (return code: " + resultCode + ")");

    // write data to OutputStream
    out.write("Hello from Server".getBytes());

    // close sockets
    socket.close();
    serverSocket.close();

  }
}
