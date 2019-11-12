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
    private ArrayList<String> listaUsuario;
    private Semaphore mutexEnviar;
    private Semaphore mutexReceber;

    public ThreadEnviar(String user, Socket s, ArrayList<String> listaUsuario, Semaphore mutexEnviar, Semaphore mutexReceber) {
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

            if (listaUsuario.contains(user)) {
                System.out.println("Usuario Autenticado \n");

                while(true){
                    
                    System.out.println("Enviar diz, RECEBI ESSA" + listaUsuario);
                    System.out.println("Digite o destinatario da mensagem");
                    String nomeRemetente = meu.nextLine();
                    while (!listaUsuario.contains(nomeRemetente)){
                        System.out.println("Destinatario inexistente");
                        nomeRemetente = meu.nextLine();
                    }
                    System.out.println("Digite sua mensagem");
                    String mensagem = meu.nextLine();
                    mutexEnviar.acquire();
                    saida.writeUTF(nomeRemetente);
                    saida.writeUTF(mensagem);
                    
                    mutexReceber.release();

                }         
    
    }
}       catch (InterruptedException ex) {
            Logger.getLogger(ThreadEnviar.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(ThreadEnviar.class.getName()).log(Level.SEVERE, null, ex);
        }
    
    }
 }
