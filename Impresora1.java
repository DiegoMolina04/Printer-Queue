/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package parcial2_estructuradedatos.colasimpresion;

import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author pc
 */
public class Impresora1 extends Thread{
    
    public Impresora1(String msg){ //Constructor que identifica el hilo.
        
        super(msg);
        
    }
    
    public void run(){ //Ejecución de las instrucciones por parte del hilo.
        
        int tamañoCola1 = Main.cola1.size(); //Toma el tamaño de la cola1.
               
        for (int i = 0; i < tamañoCola1; i++) { //For que recorre toda la cola1.
            
            int area = Main.cola1.peek().getArea(); //Se toma el area.
            int tipo2 = Main.cola1.peek().getTipo(); //Se toma el tipo.
            int tipo = 0;
            
            switch(tipo2){ //Se compara los tipo de trabajo. Y se le asigna un tiempo mayor.
                    
                    case 1:
                        tipo = 3;
                    break;
                    
                    case 2:
                        tipo = 6;
                    break;
                    
                    case 3:
                        tipo=10;
                    break;
                    
            }
            
            System.out.println("Impresora 1 :" + "Area "+area+" Tipo "+tipo2); //Se imprime le area y el tipo.

            try {
                Thread.sleep(tipo*1000); //Se hace esperar el hilo la cantidad de segundos determinado.
            } catch (InterruptedException ex) {
                Logger.getLogger(Impresora1.class.getName()).log(Level.SEVERE, null, ex);
            }
            
            Main.cola1.poll(); //Se desencola el primer elemento.
            
        }
        System.out.println("Impresora 1 finalizo."); //Mensaje de finalización.
        
    }
    
}
