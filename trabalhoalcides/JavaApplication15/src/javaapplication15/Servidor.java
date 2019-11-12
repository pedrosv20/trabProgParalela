/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package javaapplication15;

import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class Servidor {

    /**
     * @param args
     */
    public static Map<String, ArrayList<String>> usuario = new HashMap<String, ArrayList<String>>();
    
    
    public static void main(String[] args) throws FileNotFoundException, IOException {
        // TODO Auto-generated method stub
        FileReader arq = new FileReader("Usuarios.txt");
        BufferedReader lerArq = new BufferedReader(arq);

        String linha = lerArq.readLine();
        
        while (linha != null) {
            
            Servidor.usuario.put(linha, new ArrayList<>());
            
            linha = lerArq.readLine();
        }
        
        
        lerArq.close();
        arq.close();
        
        try {
            ServerSocket ss = new ServerSocket(4040);

            while (true) {
                System.out.println("Servidor aguardando um cliente ...");
                Socket t = ss.accept(); // bloqueia até receber pedido de conexão do cliente
                System.out.println("Servidor: conexao feita");
                
                Trabalhador trab = new Trabalhador(t);
                trab.start();

            }

            //ss.close();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

    }

}
