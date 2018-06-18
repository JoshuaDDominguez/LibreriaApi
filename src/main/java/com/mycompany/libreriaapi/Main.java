package com.mycompany.libreriaapi;

import java.io.IOException;
import java.net.URISyntaxException;
import javax.swing.JOptionPane;


public class Main {
/**
 * Clase principal llama a los metodos de la clase metodos.
 * 
 * @param args
 * @throws IOException
 * @throws URISyntaxException 
 */

    public static void main(String[] args) throws IOException, URISyntaxException {
        /**
         * Tenemos la inicializacion de la clase Metodos y luego se procede a la creacion del menu.
         */
        Metodos met=new Metodos();
int opciones=Integer.parseInt(JOptionPane.showInputDialog("1.Clonar\n2.Commit\n3.Inicializar\n4.Push\n5.Salir"));

switch(opciones){
            case 1:
                met.clonar();
                break;
            case 2:
                met.commitRepository();
                break;
            case 3:
                met.inicializarRepo();
                break;
            case 4:
                met.pushRepository();
                break;
            case 5:
               System.exit(0);
        }
    
    }
    }
    

