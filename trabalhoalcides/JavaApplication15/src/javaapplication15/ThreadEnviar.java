package javaapplication15;

import java.io.DataOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.net.Socket;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;
import java.util.concurrent.Semaphore;
import java.util.logging.Level;
import java.util.logging.Logger;

 public class ThreadEnviar extends Thread {

    private String user;
    private Socket s;
    private HashMap<String, ArrayList<String>> listaUsuario;
    private Semaphore mutexEnviar;
    private Semaphore mutexReceber;

    public ThreadEnviar(String user, Socket s, HashMap<String, ArrayList<String>> listaUsuario, Semaphore mutexEnviar, Semaphore mutexReceber) {
        this.mutexEnviar = mutexEnviar;
        this.mutexReceber = mutexReceber;
        this.user = user;
        this.s = s;
        this.listaUsuario = listaUsuario;
    }

    @Override
    public void run() {
        
        try {
            
            Scanner meu = new Scanner(System.in);

            DataOutputStream saida = new DataOutputStream(s.getOutputStream());

            if (listaUsuario.containsKey(user)) {
                System.out.println("Usuario Autenticado \n");

                while(true){
                    mutexEnviar.acquire();
                    System.out.println("Enviar diz, RECEBI ESSA" + listaUsuario.keySet());
                    System.out.println("Digite o destinatario da mensagem");
                    String nomeRemetente = meu.nextLine();
                    while (! listaUsuario.containsKey(nomeRemetente)){
                        System.out.println("Destinatario inexistente");
                        nomeRemetente = meu.nextLine();
                    }
                    System.out.println("Digite sua mensagem");
                    String mensagem = meu.nextLine();

                    saida.writeUTF(nomeRemetente);
                    saida.writeUTF(mensagem);
                    
                    mutexReceber.release();
                    
                    
                }
            }
            

        } catch (Exception e) {
            System.out.println("Ai brow mandou mal el" + e);
            e.printStackTrace();
        }
    }
}
