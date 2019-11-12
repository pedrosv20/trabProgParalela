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
            while (true) {
                System.out.println("AA");
                DataOutputStream saida = new DataOutputStream(t.getOutputStream());

                if (teste) {

                    
//                Thread.sleep(1000);
                    for (String key : Servidor.usuario.keySet()) {
                        //Capturamos o valor a partir da chave
                        saida.flush();
                        saida.writeUTF(key);
                    }
                    teste = false;
                }
//
                System.out.println("BB");
                DataInputStream entrada = new DataInputStream(t.getInputStream());
//                        System.out.println(entrada.readObject());

                System.out.println("CC");

                String destinatario = entrada.readUTF();
                System.out.println("aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa" + destinatario);
                if (destinatario.charAt(0) == '!') {//Caso a thread receber mande algo 
                    String nome = destinatario.substring(1);

                    System.out.println(Servidor.usuario.get(nome).getClass().getName());
                    ArrayList<String> temp = Servidor.usuario.get(nome);

                    System.out.println("Temp: " + temp);
                    for(int i = 0 ; i< temp.size(); i++){
                        saida.flush();
                        saida.writeUTF(temp.get(i));
                    }
                    
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
            //t.close();
            //System.out.println("acabou");
        } catch (Exception e) {

            System.out.println("Servidor: Conexao encerrada");
            e.printStackTrace();
        }
    }
}
