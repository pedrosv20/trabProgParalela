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
    private Semaphore mutexEnviar;
    private Semaphore mutexReceber;

    public ThreadReceber(String user, Socket s, Semaphore mutexEnviar, Semaphore mutexReceber) {
        this.mutexEnviar = mutexEnviar;
        this.mutexReceber = mutexReceber;
        this.user = user;
        this.s = s;
        //this.listaUsuario = listaUsuario;
    }

    @Override
    public void run() {
        try {
            DataOutputStream saida = new DataOutputStream(s.getOutputStream());
            ObjectInputStream ois = new ObjectInputStream(s.getInputStream());
            while (true) {
                
                mutexReceber.acquire();
                
                System.out.println("bbbbbbbbbbbbbbbbb");

                saida.writeUTF("!" + user);

                System.out.println("asfasdfsadf");
                Object x = ois.readObject();
                
                System.out.println("Obejto: " + x.toString());
                ArrayList<String> cu = (ArrayList<String>) x;
                

                System.out.println("sdgsadbssafbh");
                System.out.println(cu);

                if (!cu.isEmpty()) {

                    System.out.println(cu);
                    cu.clear();
                    System.out.println("Vazia em ?" + cu.isEmpty());

                }

                mutexEnviar.release();
            }
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("Infelizmente a sessao fechou, rode denovo");
        }

    }
}
