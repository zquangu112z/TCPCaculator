
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.sql.SQLException;

class NewThread extends Thread {

    Socket mSocket;
    int mCount;

    DataInputStream inputStream = null;
    DataOutputStream outputStream = null;

    public NewThread(Socket mSocket, int mCount) {
        this.mSocket = mSocket;
        this.mCount = mCount;
        System.out.println("mCount" + mCount);
    }

    public void run() {
        try {
            inputStream = new DataInputStream(mSocket.getInputStream());
            outputStream = new DataOutputStream(mSocket.getOutputStream());
            String snhan = inputStream.readUTF();
            System.out.println("server rcved");
            String stra = xulychuoi(snhan);
            this.sleep(4000);
            outputStream.writeUTF(stra);

            inputStream.close();
            outputStream.close();

            mSocket.close();
        } catch (Exception e) {
            System.out.println("" + e);
        }
    }

    public String xulychuoi(String s) {
        float ketqua = 0;
        int i = 0;
        float giatritam = 0;
        while (i < s.length()) {
            while ((s.charAt(i) != '+') && ((s.charAt(i) != '-')) && (s.charAt(i) != '*') && (s.charAt(i) != '/')) {
                ketqua = ketqua * 10 + Integer.parseInt(String.valueOf(s.charAt(i)));
                i++;
            }
            if (s.charAt(i) == '+') {
                i++;
                while ((i < s.length() && (s.charAt(i) != '+') && ((s.charAt(i) != '-')) && (s.charAt(i) != '*') && (s.charAt(i) != '/'))) {
                    giatritam = giatritam * 10 + Integer.parseInt(String.valueOf(s.charAt(i)));
                    i++;
                }
                ketqua += giatritam;
                giatritam = 0;
            }
            if ((i < s.length()) && (s.charAt(i) == '-')) {
                i++;
                while ((i < s.length() && (s.charAt(i) != '+') && ((s.charAt(i) != '-')) && (s.charAt(i) != '*') && (s.charAt(i) != '/'))) {
                    giatritam = giatritam * 10 + Integer.parseInt(String.valueOf(s.charAt(i)));
                    i++;
                }
                ketqua -= giatritam;
                giatritam = 0;
            }
            if ((i < s.length()) && (s.charAt(i) == '*')) {
                i++;
                while ((i < s.length() && (s.charAt(i) != '+') && ((s.charAt(i) != '-')) && (s.charAt(i) != '*') && (s.charAt(i) != '/'))) {
                    giatritam = giatritam * 10 + Integer.parseInt(String.valueOf(s.charAt(i)));
                    i++;
                }
                ketqua *= giatritam;
                giatritam = 0;
            }
            if ((i < s.length()) && (s.charAt(i) == '/')) {
                i++;
                while ((i < s.length() && (s.charAt(i) != '+') && ((s.charAt(i) != '-')) && (s.charAt(i) != '*') && (s.charAt(i) != '/'))) {
                    giatritam = giatritam * 10 + Integer.parseInt(String.valueOf(s.charAt(i)));
                    i++;
                }
                ketqua /= giatritam;
                giatritam = 0;
            }

        }
        System.out.print(ketqua);
        return String.valueOf(ketqua);
    }

}

public class Server {

    public static void main(String[] args) throws Exception {
        ServerSocket mServerSocket;
        Socket mSocket;
        int portId = 7777;
        int count = 1;
        try {
            mServerSocket = new ServerSocket(portId);
            System.out.print("Server is start............");
            while (true) {
                mSocket = mServerSocket.accept();
                new NewThread(mSocket, count++).start();
            }
        } catch (Exception e) {
            System.out.println("" + e);
        }

    }

}


/*import java.awt.Button;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.Label;
import java.awt.Panel;
import java.awt.TextField;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.UnknownHostException;
import java.sql.SQLException;
import javax.swing.JFrame;
class ServerInit extend Thread {

 public ServerInit() throws Exception {
        System.out.println("sdf");

        serverSocket = new ServerSocket(2000);
        System.out.print("Server is start............");
        Socket socket = serverSocket.accept();
        inputStream = new DataInputStream(socket.getInputStream());
        outputStream = new DataOutputStream(socket.getOutputStream());
    }
}
class MyView extends Thread {

    public static Panel pn1, pn2, pn3, pn;
    public static TextField tfnhap = new TextField(5);
    public static TextField tfkq = new TextField();
    public static Label lbnhap = new Label();
    public static Label lbkq = new Label();
    public static Button bt1, bt2;
    public static String s = "";
    
    
    ServerSocket serverSocket;
    DataInputStream inputStream;
    DataOutputStream outputStream;

    public MyView() throws Exception {
        System.out.println("sdf");
        GUI();

        serverSocket = new ServerSocket(2000);
        System.out.print("Server is start............");
        Socket socket = serverSocket.accept();
        inputStream = new DataInputStream(socket.getInputStream());
        outputStream = new DataOutputStream(socket.getOutputStream());
    }
    
    void GUI() throws UnknownHostException, IOException {

        JFrame fr = new JFrame("Client");
        fr.setSize(400, 200);
        fr.setLayout(new GridLayout());
        pn = new Panel(new GridLayout(5, 1));
        pn1 = new Panel(new GridBagLayout());
        pn.add(lbnhap);
        pn.add(tfnhap);
        pn.add(lbkq);
        pn.add(tfkq);
        pn.add(pn1);
        fr.add(pn);
        fr.show();
    }

    public synchronized void run() {
        while (true) {
            try {
                String msg_rcv = inputStream.readUTF();
                System.out.println("server rcved");
                //jLabel2.setText(jLabel2.getText() + msg_rcv);
            } catch (Exception e) {
                System.out.println("excp:  " + e);
            }
        }
    }

}

public class Server {

    public static void main(String[] args) throws Exception {
        MyView s = new MyView();
        s.start();
    }
}
 */
