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
import java.util.ArrayList;

/**
 *
 * @author augusto.coelho
 */
public class ThreadReceber extends Thread {

    private String name;

    public ThreadReceber(String name) {
        this.name = name;
    }

    public void run() {
        try {

            Socket s = new Socket("127.0.0.1", 5000);
            System.out.println("Cliente: conexao feita");

            
            DataInputStream entrada = new DataInputStream(s.getInputStream());
            
//            String a = entrada.readUTF();
            
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

            
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}
