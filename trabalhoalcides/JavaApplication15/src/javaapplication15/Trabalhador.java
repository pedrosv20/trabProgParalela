/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package javaapplication15;

import java.io.ByteArrayOutputStream;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutput;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Trabalhador extends Thread {

    private Socket t;
    boolean teste = true;
    

    public Trabalhador(Socket t) {
        this.t = t;
    }

    @Override
    public void run() {

        try {
            while (t.isConnected() && !t.isClosed()) {
                System.out.println("AA");
                ObjectOutputStream oos = new ObjectOutputStream(t.getOutputStream());
                
                if (teste) {
                    

                    oos.flush();
//                Thread.sleep(1000);
                    oos.writeObject(Servidor.usuario);
                    teste = false;
                }

                System.out.println("BB");
                DataInputStream entrada = new DataInputStream(t.getInputStream());
//                        System.out.println(entrada.readObject());

                DataOutputStream saida = new DataOutputStream(t.getOutputStream());

                System.out.println("CC");
                
                String destinatario = entrada.readUTF();
                System.out.println("aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa" + destinatario);
                if (destinatario.charAt(0) == '!') {
                    String nome = destinatario.substring(1);
                    oos.flush();
                    System.out.println(Servidor.usuario.get(nome).getClass().getName());
                    ArrayList<String> temp = Servidor.usuario.get(nome);
                    
                    System.out.println("Temp: "+ temp);
                    oos.writeUTF(temp.get(0));
                    Servidor.usuario.get(nome).clear();
                    System.out.println(Servidor.usuario);

                } else {
                    String mensagem = entrada.readUTF();

                    System.out.println("destinatario");
                    if (Servidor.usuario.keySet().contains(destinatario)) {
                        System.out.println("Trabalhador diz: Destinatario confirmado");
                        Servidor.usuario.get(destinatario).add(mensagem);
                    }
                    System.out.println(Servidor.usuario);
                }
            }
            t.close();
            System.out.println("acabou");
        } catch (Exception e) {

            System.out.println("Servidor: Conexao encerrada");
            e.printStackTrace();
        }
    }
}
