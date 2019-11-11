/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package trabalhoalcides;

import java.io.ByteArrayOutputStream;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.ObjectOutput;
import java.io.ObjectOutputStream;
import java.net.Socket;


public class Trabalhador extends Thread {
	
	private Socket t;
	
	public Trabalhador( Socket t )
	{
		this.t = t;
	}
	private byte[] convertToBytes(Object object) throws IOException {
            try (ByteArrayOutputStream bos = new ByteArrayOutputStream();
                 ObjectOutput out = new ObjectOutputStream(bos)) {
                out.writeObject(object);
                return bos.toByteArray();
            } 
        }
	public void run()
	{
		try
		{
                    
                        ObjectOutputStream oos = new ObjectOutputStream(t.getOutputStream());
                        oos.flush();
                        Thread.sleep(1000);
                        oos.writeObject(Servidor.usuario);
                        
			DataInputStream entrada = new DataInputStream( t.getInputStream());
			DataOutputStream saida = new DataOutputStream( t.getOutputStream());
                        
                        
			String nomerecebe = entrada.readUTF();
                        String mensagem = entrada.readUTF();
                        String nome = entrada.readUTF();
			System.out.println( "Recebidos: " + nomerecebe + "Mensagens:"+ mensagem);
                        
                        
			Thread.sleep( 5000 ); // dorme 5 segundos
                        
                        
                        saida.writeBytes(Servidor.usuario.toString());
                        
			if (Users.usuario.containsKey(nomerecebe)) {
                            
                            String[] envioMen = new String[2];
                            envioMen[0] = nome;
                            envioMen[1] = mensagem;
                            Users.usuario.get(nomerecebe).add(envioMen);
                            
                        } else {
                            System.err.println("Chave não existe");
                        }
			
			
			t.close();
			System.out.println( "Servidor: conexao encerrada");
		}
		catch( Exception e )
		{
			System.out.println( e );
		}
		
	}

}