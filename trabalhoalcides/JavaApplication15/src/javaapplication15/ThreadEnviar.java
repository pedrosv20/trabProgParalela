/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package javaapplication15;

import java.io.DataOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.net.Socket;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author augusto.coelho
 */
public class ThreadEnviar extends Thread {

    private String nome;
    private Socket s;

    public ThreadEnviar(Socket s) {
        this.s = s;
    }


    public void run() {
        
            try {
                
                
                Scanner meu = new Scanner(System.in);
                
                ObjectInputStream ois = new ObjectInputStream(s.getInputStream());
                HashMap<String, ArrayList<String>> listaUsuario = (HashMap<String, ArrayList<String>>) ois.readObject();
                
                System.out.println("Qual seu nome");
                String user = meu.nextLine();
                while (true) {
                    if (listaUsuario.containsKey(user)) {
                        System.out.println("Usuario Autenticado \n");
                        System.out.println("RECEBI ESSA" + listaUsuario.keySet());
                        System.out.println("Digite o remetente da mensagem");
                        String nomeRemetente = meu.nextLine();
                        System.out.println("Digite sua mensagem");
                        String mensagem = meu.nextLine();

                        DataOutputStream saida= new DataOutputStream( s.getOutputStream());


                        saida.writeUTF(nomeRemetente);
                        saida.writeUTF(mensagem);
                    }
                }
                
                
                
            } catch (Exception e) {
                System.out.println("Ai brow mandou mal el" + e);
            }
            
            
            
            
            
       

    }

}
