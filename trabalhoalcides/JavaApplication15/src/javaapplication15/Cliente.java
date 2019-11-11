/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package javaapplication15;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class Cliente {

    /**
     * @param args
     */
    
    
    public static void main(String[] args)  {
        // TODO Auto-generated method stub
        // abre conexão com o servido
        
       
        try {
            
            Socket s = new Socket("127.0.0.1", 5003);
            
            ThreadEnviar teste = new ThreadEnviar(s);
            ThreadReceber recebe = new ThreadReceber(s);
            teste.start();
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
            teste.join();
            System.out.println("Cliente: conexao encerrada");
            
        } catch (UnknownHostException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }

}
