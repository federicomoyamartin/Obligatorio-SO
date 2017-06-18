/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sistema_seguridad;

import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author federico
 */
public class Reloj implements Runnable {
    public static Reloj instance = null;
    public static int velocidad = 1000;
    private long momentoActual;
    

    public int getVelocidad() {
        return velocidad;
    }

    public void setVelocidad(int velocidad) {
        this.velocidad = velocidad;
    }
    
    
    
    public Reloj(){
        this.momentoActual = 0;
    }

    public long getMomentoActual() {
        return momentoActual;
    }

    public void setMomentoActual(long momentoActual) {
        this.momentoActual = momentoActual;
    }
    
    
    public static Reloj getInstance(){
        if (instance ==null){
            instance = new Reloj();
        }
        return instance;
    }
    
    
    

    @Override
    public void run() {
        while(true){
            Reloj.getInstance().setMomentoActual(Reloj.getInstance().getMomentoActual()+1);
            try {
                Thread.sleep(velocidad);
                System.out.println(Reloj.getInstance().getMomentoActual());
            } catch (InterruptedException ex) {
                Logger.getLogger(Reloj.class.getName()).log(Level.SEVERE, null, ex);
            }
            
            
            
        }
        
        
        
    }
    
    
 }