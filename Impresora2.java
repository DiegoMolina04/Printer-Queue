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
public class Impresora2 extends Thread{
    
    public Impresora2(String msg){ //Constructor que identifica el hilo2.
        
        super(msg);
        
    }
    
    public void run(){ //Ejecución de las instrucciones por parte del hilo2.
        
        int tamañoCola2 = Main.cola2.size(); //Se toma el tamaño de la cola2.
        int tamañoLista = Main.listaEspera.size(); //Se toma el tamaño de la lista de espera.
        
        int tamañoTotal = tamañoCola2 + tamañoLista; //Se suma el tamaño total.
        
        for (int i = 0; i < tamañoTotal; i++) { //Ciclo que intera sobre la cola2.

            if (Main.cola2.size() > 0) { //Se verifica que la cola2 tenga un tamaño mayor a 0.
                
                int area = Main.cola2.peek().getArea(); //Se toma el primer elemento area de cola2.
                int tipo2 = Main.cola2.peek().getTipo(); //Se toma el ptimer elemento tipo de cola2.
                int tipo=0;
               
                switch(tipo2){ //Se verifica el tipo para asignarle un tiempo distinto.
                    
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

                System.out.println("Impresora 2: "+ "Area " + area + " Tipo " + tipo2); //Se imprime el area y el tipo de trabajo.

                try {
                    Thread.sleep(tipo * 1000); //Se duerme el hilo el tiempo determinado.
                } catch (InterruptedException ex) {
                    Logger.getLogger(Impresora2.class.getName()).log(Level.SEVERE, null, ex);
                }

                Main.cola2.poll(); //Se saca el primer elemento de la cola2.

                if (Main.listaEspera.isEmpty()) { //Se verifica que la listaEspera no este vacia.

                } else { //De no estar vacia.

                    int areaLista = Main.listaEspera.getFirst().getArea(); //Se toma el primer elemento area de listaEspera.
                    int tipoLista = Main.listaEspera.getFirst().getTipo(); //Se toma el primer elemento tipo de listaEspera.

                    Impresion ingresarCola = new Impresion(areaLista, tipoLista); //Se crea el constructor.
                    Main.cola2.offer(ingresarCola); //Se agrega a al cola2.
                    
                    Main.listaEspera.removeFirst(); //Se elimina el primer elemento de listaEspera.

                }
            }else{
                
            }
            
            
        }
        
        System.out.println("Impresora 2 finalizo."); //Se imprime mensaje de finalización.
        Main.mensaje(); //Se muestra el mensaje de opciones.

    }
    
}
