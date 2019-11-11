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
import java.util.logging.Level;
import java.util.logging.Logger;

public class Cliente {

    /**
     * @param args
     */
    
    
    public static void main(String[] args)  {
        // TODO Auto-generated method stub
        // abre conexão com o servido

       
        try {
            
            Socket s = new Socket("127.0.0.1", 5003);
            
            ObjectInputStream ois = new ObjectInputStream(s.getInputStream());
            HashMap<String, ArrayList<String>> listaUsuario = (HashMap<String, ArrayList<String>>) ois.readObject();
            
            ThreadEnviar envia = new ThreadEnviar(s, listaUsuario);
            ThreadReceber recebe = new ThreadReceber(s, listaUsuario);
            envia.start();
//            recebe.start();
          /*
            DataInputStream entrada = new DataInputStream(s.getInputStream());

            double resultado = entrada.readDouble();
            //float resultado = entrada.readFloat(); // pode?
            System.out.println("resultado = " + resultado);

            //float resto = entrada.readFloat();
            //System.out.println( "resto = " + resto );
            // s.close(); // encerra a conexao com o servidor
            */
            envia.join();
            System.out.println("Cliente: conexao encerrada");
            
        } catch (UnknownHostException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(Cliente.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
