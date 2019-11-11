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
import java.util.logging.Level;
import java.util.logging.Logger;

public class Trabalhador extends Thread {

    private Socket t;

    public Trabalhador(Socket t) {
        this.t = t;
    }

    @Override
    public void run() {
        while (true) {
            try {
                System.out.println("AA");
                ObjectOutputStream oos = new ObjectOutputStream(t.getOutputStream());

                oos.flush();
//                Thread.sleep(1000);
                oos.writeObject(Servidor.usuario);
                System.out.println("BB");
                DataInputStream entrada = new DataInputStream(t.getInputStream());
//                        System.out.println(entrada.readObject());

                DataOutputStream saida = new DataOutputStream(t.getOutputStream());

                System.out.println("CC");

                String destinatario = entrada.readUTF();
                System.out.println("aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa"+destinatario);
                if (destinatario.charAt(0) == '!') {
                    String nome = destinatario.substring(1);
                    oos.flush();
                    System.out.println(Servidor.usuario.get(nome).getClass().getName());
                    oos.writeObject(Servidor.usuario.get(nome));

                } else {
                    String mensagem = entrada.readUTF();

                    System.out.println("destinatario");
                    if (Servidor.usuario.keySet().contains(destinatario)) {
                        System.out.println("Trabalhador diz: Destinatario confirmado");
                        Servidor.usuario.get(destinatario).add(mensagem);
                    }
                    System.out.println(Servidor.usuario);
                }
            } catch (Exception e) {
                try {
                    t.close();
                    break;
                } catch (IOException ex) {
                    Logger.getLogger(Trabalhador.class.getName()).log(Level.SEVERE, null, ex);
                }
                System.out.println("Servidor: Conexao encerrada");
                System.out.println(e);
            }
        }
    }
}
