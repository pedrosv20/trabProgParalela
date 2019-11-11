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
    private HashMap<String, ArrayList<String>> listaUsuario;

    public ThreadEnviar(Socket s, HashMap<String, ArrayList<String>> listaUsuario) {
        this.s = s;
        this.listaUsuario = listaUsuario;
    }

    @Override
    public void run() {
        try {
            Scanner meu = new Scanner(System.in);

            DataOutputStream saida= new DataOutputStream( s.getOutputStream());

            System.out.println("Qual seu nome");
            String user = meu.nextLine();
            if (listaUsuario.containsKey(user)) {
                System.out.println("Usuario Autenticado \n");
                while (true) {
                    System.out.println("Enviar diz, RECEBI ESSA" + listaUsuario.keySet());
                    System.out.println("Digite o destinatario da mensagem");
                    String nomeRemetente = meu.nextLine();
                    System.out.println("Digite sua mensagem");
                    String mensagem = meu.nextLine();

                    saida.writeUTF(nomeRemetente);
                    saida.writeUTF(mensagem);
                }
            }
        }
        catch (Exception e) {
            System.out.println("Ai brow mandou mal el" + e);
        }
    }
}
