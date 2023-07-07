/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package parcial2_estructuradedatos.colasimpresion;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

/**
 *
 * @author pc
 */
public class Main {

    static Queue<Impresion>cola1=new LinkedList<Impresion>(); //Se declara la cola.
    static Queue<Impresion>cola2=new LinkedList<Impresion>(); //Se declara la cola.
    static LinkedList<Impresion>listaEspera = new LinkedList<Impresion>(); //Se declara la lista.
    
    public void leerArchivo() { //Método que lee el contenido del archivo listaTrabajo y llama al método verificarCola para agregarlo el trabajo a la cola de impresión.

        File archivo = new File("C:\\Users\\pc\\Documents\\NetBeansProjects\\Parcial2_EstructuraDeDatos(ColasImpresion)\\src\\Archivo\\listaTrabajo.txt");

        if (archivo.exists()) { //Se verifica que exista el archivo.

            FileReader Fr;

            try {

                Fr = new FileReader(archivo); //Se declara FileReader y se pasa como parametro el archivo.

                BufferedReader br = new BufferedReader(Fr);
                String linea;
                String delimiter = ","; //Se le indica que busque por medio de este caracter.

                while (((linea = br.readLine()) != null)) {

                    String a[] = linea.split(delimiter);
                    String parte1 = a[0]; //Se separa el area.
                    String parte2 = a[1]; //Se separa el tipo.
                    int area = Integer.parseInt(parte1);
                    int tipo = Integer.parseInt(parte2);
                    
                    verificarCola(area, tipo); //Se envia al método verificarCola.

                }

            } catch (FileNotFoundException e) {

                e.printStackTrace();

            } catch (IOException e) {

                e.printStackTrace();

            }

        } else {
            System.out.println("Archivo no encontrado");
        }

    }
    
    public void verificarCola(int area, int tipoTrabajo){ //Método que verifica en que cola se debe agregar el trabajo.
        
        if(area==1||area==2){ //Se verifica el tipo de area, si es Gerencia o Administración.
            
            if(cola1.isEmpty()){ //Si cola1 esta vacia, directamente se agrega.
                
                Impresion nuevoTrabajo = new Impresion(area, tipoTrabajo);
                cola1.offer(nuevoTrabajo);
                System.out.println("Area "+area+" Tipo "+tipoTrabajo+" agregado a cola 1.");
                
            }else{
                
                if(area==2){ //Si el area es Administración, que directamente lo agregue a cola1.
                    
                    Impresion nuevoTrabajo = new Impresion(area, tipoTrabajo);
                    cola1.offer(nuevoTrabajo);
                    System.out.println("Area "+area+" Tipo "+tipoTrabajo+" agregado a cola 1.");
                    
                }else if(area == 1){ //Si el area es Gerencia se verifican los tiempos.
                    
                int totalCola1 = 0;
                int totalCola2 = 0;
                int tamañoCola1 = cola1.size();
                int tamañoCola2 = cola2.size();
                
                for (int i = 0; i < tamañoCola1; i++) {
                    int area1 = cola1.peek().getArea();
                    int tipo1 = cola1.peek().getTipo();
                    Impresion encolar = new Impresion(area1, tipo1);
                    cola1.offer(encolar); //Se agrega nuevamente el primer elemento de la cola.
                    cola1.poll(); //Se elimina el primer elemento de la cola.
                    totalCola1 = totalCola1 + tipo1; //Se cuenta el tiempo total de impresión de cola1.
                }
                
                for (int i = 0; i < tamañoCola2; i++) {
                    int area2 = cola2.peek().getArea();
                    int tipo2 = cola2.peek().getTipo();
                    Impresion encolar = new Impresion(area2, tipo2);
                    cola2.offer(encolar); //Se agrega nuevamente el primer elemento de la cola.
                    cola2.poll(); //Se elimina el primer elemento de la cola.
                    totalCola2 = totalCola2 + tipo2; //Se cuenta el tiempo total de impresión de cola2.
                }
                
                if(totalCola1 > 5){ //Se verifica que si el tiempo de duración es mayor a 5.
                    
                    if(cola2.size() == 2){ //Si la cola2 tiene un tamaño de 2, se agrega directamente a la lista de espera de primeras.
                        
                        Impresion nuevoTrabajo = new Impresion(area, tipoTrabajo);
                        listaEspera.addFirst(nuevoTrabajo);
                        System.out.println("Area "+area+" Tipo "+tipoTrabajo+" agregado a lista de espera de primeras.");
                        
                    }else{ //Si no se agrega a la cola2.
                        
                        Impresion nuevoTrabajo = new Impresion(area, tipoTrabajo);
                        cola2.offer(nuevoTrabajo);
                        System.out.println("Area "+area+" Tipo "+tipoTrabajo+" agregado a cola 2.");
                        
                    }
                    
                }else{ //Si la cola1 es menor a 5, se mete a la cola1.
                    
                    Impresion nuevoTrabajo = new Impresion(area, tipoTrabajo);
                    cola1.offer(nuevoTrabajo);
                    System.out.println("Area "+area+" Tipo "+tipoTrabajo+" agregado a cola 1.");
                    
                }

                }
                
            }
            
        }else if(area==3||area==4){ //Se verifica si es mercadeo o producción.
            
            if(cola2.size() == 2){
                
                Impresion nuevoTrabajo = new Impresion(area, tipoTrabajo);
                listaEspera.add(nuevoTrabajo);
                System.out.println("Area "+area+" Tipo "+tipoTrabajo+" agregado a lista de espera.");
                
            }else{
                
                Impresion nuevoTrabajo = new Impresion(area, tipoTrabajo);
                cola2.offer(nuevoTrabajo);
                System.out.println("Area "+area+" Tipo "+tipoTrabajo+" agregado a cola 2.");
                
            }
            
        }
        
    }
    
    
    public static void mensaje(){ //Método que imprime el mensaje de selección de opción.
        
         System.out.println("MENU\n"
                    + "1. Pre-cargar impresiones\n"
                    + "2. Ingresar impresión a cola\n"
                    + "3. Imprimir\n"
                    + "4. Salir");
        
    }

    public static void main(String[] args) { //Método Main, de ejecución.
        
        boolean flag = true;
        Scanner sc = new Scanner(System.in);
        Main objMain = new Main();
        int decisionMenu = 0;
        
        mensaje();
        
        while (flag==true) { //Se crea un bucle.    
            
            int decision = sc.nextInt(); //Se captura el número de opción elegido.
            
            switch(decision){ 
                
                case 1: //De ser 1, llama el método leer archivo y se le asigna 1 a decision Menu para no volver a leer el archivo.
                    
                    if(decisionMenu == 1){
                        System.out.println("Error ! La cola ya a sido pre-cargada.");
                    }else{
                        
                        objMain.leerArchivo();
                        decisionMenu = 1;
                        System.out.println("Datos cargados exitosamente.");
                        mensaje();
                    }
                    
                    
                break;
                
                case 2: //De ser 2 se pregunta el area y el tipo de trabajo, se llama al metodo verificarCola y se agrega a la cola correspondiente.
                    
                    System.out.println("Seleccione el área\n"
                            + "1. Gerencia\n"
                            + "2. Administración\n"
                            + "3. Mercadeo\n"
                            + "4. Producción");
                    
                    int area = sc.nextInt();
                    
                    System.out.println("Seleccion tipo de trabajo\n"
                            + "1. Sencillo\n"
                            + "2. Informe\n"
                            + "3. Catálogo");
                    
                    int tipoTrabajo = sc.nextInt();
                    
                    objMain.verificarCola(area, tipoTrabajo);
                    System.out.println("Impresión agregada exitosamente.");
                    mensaje();
                break;
                
                case 3: //De ser 3, se crean los hilos correspondientes a cada impresora, de imprime el mensaje y se ejecutan los hilos de Impresora1 e Impresora2.
                   
                    Thread hilo = new Impresora1("Proceso 1");
                    Thread hilo2 = new Impresora2("Proceso 2");

                    try {
                        System.out.println("Tipo sencillo = 3 segundos\n"
                                + "Tipo informe = 6 segundos\n"
                                + "Tipo catálogo = 10 segundos");
                        
                        hilo.sleep(3000);
                        hilo2.sleep(3000);
                        hilo.start();
                        hilo2.start();
                        
                    } catch (Exception e) {
                    }
                break;
                
                case 4: //De ser 4 termina la ejecución del programa.
                    
                    System.exit(0);
                    
                break;
                
                
                
            }
            
        }
        
    }
    
}
