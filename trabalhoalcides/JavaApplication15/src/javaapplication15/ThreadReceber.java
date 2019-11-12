/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package javaapplication15;

import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.io.DataInputStream;
import java.io.ObjectInputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.concurrent.Semaphore;

/**
 *
 * @author augusto.coelho
 */
public class ThreadReceber extends Thread {

    private String user;
    private Socket s;
    //private HashMap<String, ArrayList<String>> listaUsuario;
    private Semaphore mutex;

    public ThreadReceber(String user, Socket s, Semaphore mutex) {
        this.mutex = mutex;
        this.user = user;
        this.s = s;
        //this.listaUsuario = listaUsuario;
    }

    @Override
    public void run() {
        while (true) {
            try {
                Thread.sleep(2);
                mutex.acquire();
                System.out.println("bbbbbbbbbbbbbbbbb");
                DataOutputStream saida = new DataOutputStream(s.getOutputStream());
                ObjectInputStream ois = new ObjectInputStream(s.getInputStream());
                saida.writeUTF("!"+user);
                
                
                
                System.out.println("asfasdfsadf");
                System.out.println(ois.readObject());
                ArrayList<String> cu = (ArrayList<String>) ois.readObject();
                
                System.out.println("sdgsadbssafbh");
                System.out.println(cu);
                saida.close();
                if (!cu.isEmpty()) {

                        System.out.println(cu);
                        cu.clear();
                        System.out.println("Vazia em ?" + cu.isEmpty());
                    

                }
                mutex.release();

            } catch (Exception e) {
                System.out.println("Infelizmente a sessao fechou, rode denovo");
            }

        }

    }
}
