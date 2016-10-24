
import java.awt.Button;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.Label;
import java.awt.Panel;
import java.awt.TextField;
import java.awt.event.ActionEvent;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JFrame;

import java.awt.GridLayout;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.net.UnknownHostException;
import java.awt.*;
import java.awt.event.*;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.swing.JFrame;


/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author zquangu112z
 */
//class ClientThread extends Thread{
//    Socket mSocket;
//
//    public ClientThread(Socket mSocket) {
//        this.mSocket = mSocket;
//    }
//
//    @Override
//    public void run() {
//        super.run(); 
//        
//        
//    }
//    
//    
//    
//}
public class MyClient implements ActionListener {

    public static Panel pn1, pn2, pn3, pn;
    public static TextField tfnhap = new TextField(5);
    public static TextField tfkq = new TextField();
    public static Label lbnhap = new Label();
    public static Label lbkq = new Label();
    public static Button bt1, bt2;
    public static String s = "";

    public MyClient() throws UnknownHostException, IOException {
        GUI();
    }

    void GUI() throws UnknownHostException, IOException {

        JFrame fr = new JFrame("Client");
        fr.setSize(400, 200);
        fr.setLayout(new GridLayout());
        lbnhap.setText("Nhap vao chuoi can tinh toan");
        lbkq.setText("Ket qua");
        pn = new Panel(new GridLayout(5, 1));
        pn1 = new Panel(new GridBagLayout());
        bt1 = new Button("send");
        bt2 = new Button("Exit");
        pn.add(lbnhap);
        pn.add(tfnhap);
        pn.add(lbkq);
        pn.add(tfkq);
        pn1.add(bt1);
        pn1.add(bt2);
        pn.add(pn1);
        fr.add(pn);

        bt1.addActionListener(this);
        bt2.addActionListener(this);
        fr.show();

    }

    public static void main(String[] args) throws Exception {
        MyClient t = new MyClient();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        // TODO Auto-generated method stub
        if (e.getSource() == bt1) {
            try {

                Socket socket;
                DataOutputStream outputStream;
                DataInputStream inputStream;

                socket = new Socket("127.0.0.1", 7777);
                outputStream = new DataOutputStream(socket.getOutputStream());
                inputStream = new DataInputStream(socket.getInputStream());

                String s = tfnhap.getText();

                outputStream.writeUTF(s);
                System.out.println("------1");
                Thread t = new Thread(new Runnable() {
                    @Override
                    public void run() {
                        String nhan = "unknown";
                        bt1.enable(false);
                        try {
                            nhan = inputStream.readUTF();
                            outputStream.close();
                            inputStream.close();
                            socket.close();
                        } catch (IOException ex) {
                            Logger.getLogger(MyClient.class.getName()).log(Level.SEVERE, null, ex);
                        }
                        bt1.enable(true);
                        tfkq.setText(nhan);

                    }
                });
                t.start();

                System.out.println("------2");

            } catch (UnknownHostException e1) {
                // TODO Auto-generated catch block
                e1.printStackTrace();
            } catch (IOException e1) {
                // TODO Auto-generated catch block
                e1.printStackTrace();
            }

        }
        if (e.getSource() == bt2) {
            System.exit(0);
        }
    }

}
