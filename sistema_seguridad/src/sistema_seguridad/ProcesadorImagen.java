/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sistema_seguridad;

import java.io.IOException;
import java.util.concurrent.Semaphore;


/**
 *
 * @author federico
 */
public class ProcesadorImagen implements Runnable {

    private final Semaphore semImagen;
    private final Semaphore semAlerta;

    @Override
    public void run() {
        while (true) {
            try {
                semImagen.acquireUninterruptibly();
                Imagen imagen = Buffers.imagenesAProcesar.poll();
                semImagen.release();

                if (imagen != null) {
//                try {
//                    imagen.setMomentoLeida(Reloj.getInstance().getMomentoActual());
//                    //Thread.sleep(imagen.getTiempoProcesamiento()*Reloj.getInstance().getVelocidad());
//                } catch (InterruptedException ex) {
//                }
                    procesar(imagen);
                }
            } catch (IOException | InterruptedException ex) {
            }
        }

    }

    public void procesar(Imagen imagen) throws IOException, InterruptedException {
        
        Logger.getInstancia().log("Procesando | imagen: " + imagen.getCodigo() + 
                " | Tiempo procesamiento: " + imagen.getTiempoProcesamiento());
        
        int momentoActual = Reloj.getInstance().getMomentoActual();        
        while(imagen.getTiempoProcesamiento() > Reloj.getInstance().getMomentoActual() - momentoActual) {
            // NOOP porque un while vacio no anda
            System.out.println("");
        }
        
        Logger.getInstancia().log("Fin procesamiento | imagen: " + imagen.getCodigo());
        
        Delincuente delincuente = BaseDatos.esDelincuente(imagen);
        if (delincuente != null) {
            Alerta alerta = new Alerta(imagen, delincuente, Reloj.getInstance().getMomentoActual());
            semAlerta.acquire();
            Buffers.alertasANotificar.add(alerta);
            Logger.getInstancia().log("Alerta agregada | " + 
                    "Persona: " + alerta.getPersona().getNombre() + " " + alerta.getPersona().getApellido());
            semAlerta.release();

        }
    }

    public ProcesadorImagen(Semaphore semImagen, Semaphore semAlerta) {
        this.semImagen = semImagen;
        this.semAlerta = semAlerta;
    }

}
