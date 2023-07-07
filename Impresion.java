/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package parcial2_estructuradedatos.colasimpresion;

/**
 *
 * @author pc
 */
public class Impresion { //Clase que crea el constructor del area y tipo de cada trabajo.

    int area;
    int tipo;

    public Impresion(int ar, int ti) {

        area = ar;
        tipo = ti;
    }

    public int getArea() {
        return area;
    }

    public void setArea(int area) {
        this.area = area;
    }

    public int getTipo() {
        return tipo;
    }

    public void setTipo(int tipo) {
        this.tipo = tipo;
    }

}
