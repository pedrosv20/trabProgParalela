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

    public Trabalhador( Socket t ){
            this.t = t;
    }
    @Override
    public void run(){
        try{
            System.out.println("AA");
            ObjectOutputStream oos = new ObjectOutputStream(t.getOutputStream());

            oos.flush();
//                Thread.sleep(1000);
            oos.writeObject(Servidor.usuario);
            System.out.println("BB");
            DataInputStream entrada = new DataInputStream( t.getInputStream());
//                        System.out.println(entrada.readObject());

            DataOutputStream saida = new DataOutputStream( t.getOutputStream());


            System.out.println("CC");

            String destinatario = entrada.readUTF();
            String mensagem = entrada.readUTF();

            System.out.println("destinatario");
            if (Servidor.usuario.keySet().contains(destinatario)) {
                System.out.println("Trabalhador diz: Destinatario confirmado");
                Servidor.usuario.get(destinatario).add(mensagem);
            }
            System.out.println(Servidor.usuario);
        }
//            Thread.sleep( 5000 ); // dorme 5 segundos

        
//                        saida.writeBytes(Servidor.usuario.toString());
//                        
//			if (Users.usuario.containsKey(nomerecebe)) {
//                            
//                            String[] envioMen = new String[2];
//                            envioMen[0] = nome;
//                            envioMen[1] = mensagem;
//                            Users.usuario.get(nomerecebe).add(envioMen);
//                            
//                        } else {
//                            System.err.println("Chave não existe");
//                        }
//			

//            t.close();
//            System.out.println( "Servidor: conexao encerrada");
        
        catch( Exception e ){
            try {
                t.close();
            } catch (IOException ex) {
                Logger.getLogger(Trabalhador.class.getName()).log(Level.SEVERE, null, ex);
            }
            System.out.println( "Servidor: Conexao encerrada");
            System.out.println( e );
        }	
    }
}