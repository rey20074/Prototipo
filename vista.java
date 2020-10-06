/*Clase que implementara la vista del programa
Ultima modificacion 6 de Junio
*/

import javax.swing.JOptionPane;
import java.util.Scanner;

public class vista{
  
  public void bienvenida(){
    JOptionPane.showMessageDialog(null,"\n\n Bienvenido porfavor ingrese su nombre de usuario y contraseña");
  }

  public void mensaje(String Mensaje){
    JOptionPane.showMessageDialog(null, Mensaje);
  }

  public String datos(String a){
    string dato = JOptionPane.showMessageDialog(null, a);
    return dato;
  }  

  //se asegura que las opciones del menu sean enteros y esten dentro del rango dado
  public int opciones(String a,int mayor){
    boolean bandera= false;
    int opcion=0;
    while (bandera == false && opcion <= mayor)
    {
      try
      {
        while(opcion < 1 && opcion > mayor){
          opcion=Integer.parseInt(JOptionPane.showInputDialog(null,a));
          bandera = true;
        }
        
      }
      catch(Exception e){ 
        JOptionPane.showMessageDialog(null, "Porfavor ingrese solo una opcion del 1 al "+mayor);
      }
    }  
    return opcion;
  }  
}
