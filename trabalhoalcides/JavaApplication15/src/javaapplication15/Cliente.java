/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package javaapplication15;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;
import java.util.concurrent.Semaphore;
import java.util.logging.Level;
import java.util.logging.Logger;


public class Cliente {

    /**
     * @param args
     */
    
    
    public static void main(String[] args) throws InterruptedException  {
        // TODO Auto-generated method stub
        // abre conexão com o servido

       
        try {
            
            Socket s = new Socket("10.150.99.127", 4040);
            
            DataInputStream ois = new DataInputStream(s.getInputStream());
            ArrayList<String> users = new ArrayList();
            String a =  "";
            Thread.currentThread().sleep(1000); 
            
            while (ois.available() != 0) {
               a = ois.readUTF();
               users.add(a);
            }
//            for(int i = 0; i  < 10; i ++) {
//                
//                users.add(ois.readUTF());
//            }
            
            System.out.println(users);
            
            System.out.println("Qual seu nome");
            Scanner meu = new Scanner(System.in);
            String user = meu.nextLine();
            Semaphore mutexEnviar = new Semaphore(1);
            Semaphore mutexReceber = new Semaphore(0);
            ThreadEnviar envia = new ThreadEnviar(user, s, users, mutexEnviar, mutexReceber);
            System.out.println("aaaaaaaaaaaaaa");
            ThreadReceber recebe = new ThreadReceber(user, s, mutexEnviar, mutexReceber);
            envia.start();
            recebe.start();
          
            System.out.println("Cliente: conexao encerrada");
            
        } catch (UnknownHostException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
//        } catch (InterruptedException e) {
//            e.printStackTrace();
//        } catch (ClassNotFoundException ex) {
//            Logger.getLogger(Cliente.class.getName()).log(Level.SEVERE, null, ex);
//        }
    }
}
