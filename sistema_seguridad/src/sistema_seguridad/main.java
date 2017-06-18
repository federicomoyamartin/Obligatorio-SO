/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sistema_seguridad;

import java.util.LinkedList;
import java.util.Queue;

/**
 *
 * @author federico
 */
public class main {

    /**
     * @param args the command line arguments
     */
    
    public static void main(String[] args) {
       Buffers.alertasANotificar = null;
       Buffers.imagenesAProcesar = null;

       ReceptorImagen receptorImagen = new ReceptorImagen();
       ProcesadorImagen procesadorImagen = new ProcesadorImagen();
       Notificador notificador = new Notificador();

       Thread receptorImagenThread = new Thread(receptorImagen);
       Thread procesadorImagenTrhead = new Thread(procesadorImagen);
       Thread notificadorThread = new Thread(notificador);
       Thread reloj = new Thread(Reloj.getInstance());
       

        
        // Hilo del reloj
        reloj.start();
        // Hilo del receptor de imagenes
        receptorImagenThread.start();
        // Hilo del procesador de imagenes
        procesadorImagenTrhead.start();
        // Hilo del notificador
        notificadorThread.start();
        
    }
    
       
}