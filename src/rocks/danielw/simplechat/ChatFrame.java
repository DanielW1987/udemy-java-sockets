package rocks.danielw.simplechat;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.DataInput;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;

class ChatFrame extends JFrame {

  private static final long serialVersionUID = 1L;

  private JTextArea textArea = new JTextArea();
  private JTextField textField = new JTextField();
  private JButton btnSend = new JButton("Send");

  private final Socket socket;
  private DataInput in;
  private DataOutputStream out;
  private ReceiverThread receiverThread;

  ChatFrame(String title, Socket socket) {
    this.socket = socket;
    setupSocket();
    setTitle(title);
    prepareFrame();
  }

  private void setupSocket() {
    try {
      in = new DataInputStream(socket.getInputStream());
      out = new DataOutputStream(socket.getOutputStream());
    } catch (IOException e) {
      e.printStackTrace();
    }

    receiverThread = new ReceiverThread();
    receiverThread.start();
  }

  private void onMessage(String message) {
    if (message.equalsIgnoreCase("quit")) {
      try {
        out.writeUTF("quit");
        out.close();
        socket.close();
      } catch (IOException e) {
        e.printStackTrace();
      }
      dispose();
    }
    else {
      String appendedText = textArea.getText() + "\n" + "Other:" + message;
      textArea.setText(appendedText);
    }
  }

  private void prepareFrame() {
    Container content = getContentPane();

    Box south = Box.createHorizontalBox();
    south.add(textField);
    south.add(btnSend);

    textArea.setEditable(false);
    JScrollPane scrollPane = new JScrollPane(textArea);
    content.add(scrollPane, "Center");
    content.add(south, "South");

    setBounds(100, 100, 300, 300);
    setResizable(false);
    addWindowListener(new WindowHandler());
    btnSend.addActionListener(new SendHandler());
  }

  class WindowHandler extends WindowAdapter {
    public void windowClosing(WindowEvent e) {
      try {
        out.writeUTF("Quit");
      } catch (IOException ex) {
        ex.printStackTrace();
      }
    }
  }

  class SendHandler implements ActionListener {
    public void actionPerformed(ActionEvent e) {
      String message = textField.getText();
      String appendedText = textArea.getText() + "\n" + "You:" + message;
      textArea.setText(appendedText);

      try {
        out.writeUTF(message);
      } catch (IOException ex) {
        ex.printStackTrace();
      }
    }
  }

  // read message from InputStream in separate thread to not block the UI thread
  class ReceiverThread extends Thread {

    public void run() {
      while (! interrupted() && ! socket.isClosed()) {
        try {
          String message = in.readUTF();
          onMessage(message);
        } catch (IOException e) {
          e.printStackTrace();
        }
      }
    }

  }

}
