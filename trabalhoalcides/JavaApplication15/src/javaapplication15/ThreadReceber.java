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
                 ObjectInputStream ois = new ObjectInputStream(s.getInputStream());
                HashMap<String, ArrayList<String>> listaUsuario = (HashMap<String, ArrayList<String>>) ois.readObject();
                mutex.acquire();
                if (listaUsuario.containsKey(user)) {
                    

                    
                    
                    if (!listaUsuario.get(user).isEmpty()) {
                        System.out.println(listaUsuario.get(user).isEmpty());
                        System.out.println(listaUsuario.get(user));
                        listaUsuario.get(user).clear();
                        System.out.println("Vazia em ?"+listaUsuario.get(user).isEmpty());
                    }
                    
                    
                }
                mutex.release();

            } //            String a = entrada.readUTF();
            //            while (true) {
            //                if (Users.usuario.containsKey(name)) {
            //                    ArrayList<String[]> mensagens = Users.usuario.get(name);
            //                    for (String[] mano : mensagens) {
            //                        System.out.println(mano[0] + " enviou: ");
            //                        System.out.println(mano[1]);
            //                    }
            //
            //                } else {
            //                    System.err.println("Chave não existe");
            //                }
            //
            //                System.out.println(name);
            //            }
            //            Scanner scanner = new Scanner(System.in);
            //            System.out.println("Insira seu nome: ");
            //            saida.writeUTF(scanner.nextLine());
            //            System.out.println("Insira para quem voce quer enviar: ");
            //            saida.writeUTF(scanner.nextLine());
            //            System.out.println("Insira sua mensagem: ");
            //            saida.writeUTF(scanner.nextLine());
            catch (Exception e) {
                e.printStackTrace();
            }
            
        }

    }
}
