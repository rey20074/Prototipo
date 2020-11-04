import com.opencsv.*;
import java.io.*;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Random;
import java.util.Collection;

public class Hospitales{
  Lectura leer=new Lectura();
  
  ArrayList<String> Hospital = new ArrayList<String>();
  ArrayList<String> Medicos = new ArrayList<String>();
  ArrayList<String> Camas = new ArrayList<String>();
  ArrayList<String> CamasEnUso = new ArrayList<String>();
  ArrayList<String> Fuente = new ArrayList<String>();
  

  //Leera el archivo CSV donde se alamacenan los hospitales y sus datos
  public void leerArchivo(){
    FileReader archCSV = null;
    CSVReader csvReader = null;
    
    try {
      //Leo el archivo con el separador estándar ","
       archCSV = new FileReader("archivos/Hospitales.csv");
       csvReader = new CSVReader(archCSV);
       String[] fila = null;
       while((fila = csvReader.readNext()) != null) {
           //almacena cada columna de csv en lista
           Hospital.add(fila[0]);
           Medicos.add(fila[1]);
           Camas.add(fila[2]);
           CamasEnUso.add(fila[3]);
           Fuente.add(fila[4]);

      }
    }
    catch(IOException e) {
      System.out.println(e);
    }
    catch(Exception e) {
      System.out.println(e);
    }
    finally {
      try { 
        archCSV.close();
        csvReader.close();
      }
      catch(IOException e) {
        System.out.println(e);
      }
    }
  }

  //muestra los datos de los hospitales
 
  public void mostrarTodo(){
    for(int i=1;i<Hospital.size();i++){
    System.out.println("El hospital "+Hospital.get(i)+" tiene un total de "+Medicos.get(i)+" medicos y un total de "+Camas.get(i)+" camas.");
    System.out.println("Tiene un total de "+CamasEnUso.get(i)+" camas en uso.\n");
    }
    System.out.println("Informacion consultada de: ");
    for(int i=1;i<Hospital.size();i++){
      System.out.println(Fuente.get(i));
    }
  }




//Pasar de String a int
  ArrayList<Integer> Camasint = new ArrayList<Integer>();   
  ArrayList<Integer> CamasEnUsoint = new ArrayList<Integer>();
  ArrayList<Integer> Medicosint = new ArrayList<Integer>();

//convierte Arrays String a Int
  public void conversionInt(){
    for(int i=1;i<Camas.size();i++){
      Camasint.add(Integer.parseInt(Camas.get(i)));
      CamasEnUsoint.add(Integer.parseInt(CamasEnUso.get(i)));
      Medicosint.add(Integer.parseInt(Medicos.get(i)));
    } 
  }

  //cola, se le mostrara al usuario la cantidad de camas en uso y la cantidad de camas disponibles, tambien se le mostrara cual es el hospital con mayor numero de camas disponibles y el que esta mas ocupado 
  public void cola(){
    int mayor, menor, mayoruso, menoruso, mayormed, menormed;
    mayor = menor = mayoruso = menoruso = mayormed = menormed = 0;
    //mayor y menor de la lista de camas
    for (int i = 0; i < Camasint.size(); i++) {
      if(Camasint.get(i) > mayor) {
          mayor = Camasint.get(i);
      }
      if(Camasint.get(i)<menor) {
          menor = Camasint.get(i);
      }
    }
    //mayor y menor de la lista de camas en uso
    for (int i = 0; i < CamasEnUsoint.size(); i++) {
      if(CamasEnUsoint.get(i) > mayoruso) {
          mayoruso = CamasEnUsoint.get(i);
      }
      if(CamasEnUsoint.get(i)<menoruso) {
          menoruso = CamasEnUsoint.get(i);
      }
    }
    //mayor y menor de la lista de medicos
    for (int i = 0; i < Medicosint.size(); i++) {
      if(Medicosint.get(i) > mayormed) {
          mayormed = Medicosint.get(i);
      }
      if(Medicosint.get(i)<menormed) {
          menormed = Medicosint.get(i);
      }
    }

    ArrayList<Integer> indice = new ArrayList<Integer>();
    ArrayList<Integer> restcamasyuso = new ArrayList<Integer>();
    //hospital con mayor numero de camas disponibles

    for(int i=0;i<CamasEnUsoint.size();i++){
      restcamasyuso.add((Camasint.get(i))-(CamasEnUsoint.get(i)));
      indice.add(i);
    }

    ArrayList<String> CamasD = new ArrayList<String>();
    CamasD.add("CamasDisponibles");
    for(int i=0;i<restcamasyuso.size();i++){
      CamasD.add(restcamasyuso.get(i)+"");
    }
    int m,men;
    m=men=0;
    //mira el mayor y menor de las camas disponibles
    for (int i = 0; i < restcamasyuso.size(); i++) {
      if(restcamasyuso.get(i) > m) {
        m = restcamasyuso.get(i);
      }
      if(restcamasyuso.get(i)<men) {
        men = restcamasyuso.get(i);
      }
    }
    //ya para terminar se ve el mejor hospital por su numero de camas disponibles
    for(int i=1;i<CamasD.size();i++){
      System.out.println("Para el hospital "+Hospital.get(i)+" hay un total de "+CamasD.get(i)+" camas disponibles");
    }
    //imprime el mejor hospital dependiendo de su numero de camas disponibles
    String mayorfinal=m+"";
    String menorfinal=men+"";
    for(int i=0;i<CamasD.size();i++){
      if(mayorfinal.equals(CamasD.get(i))){
        System.out.println("\nPor lo que le recomendamos ir al hospital "+Hospital.get(i)+" debido a que tiene un total de "+CamasD.get(i)+" camas disponibles.");
      }
      if(menorfinal.equals(CamasD.get(i))){
        System.out.println("\nPor lo que NO le recomendamos ir al hospital "+Hospital.get(i)+" debido a que tiene un total de "+CamasD.get(i)+" camas disponibles.");
      }
    } 
  }


  //Cambiar las camas en uso cada cierto intervalo de tiempo, sumar o restarle cierta cantidad de camas en uso respetando siempre los limites
  //random de camas por dia
  public void camasAleatorias(){
    Random rand = new Random();
    for (int i = 0; i < CamasEnUsoint.size();i++){
      
      int x=CamasEnUsoint.get(i);
      int y=x+10;
      int z=x-10;
      int numero = rand.nextInt(y-z+1)+z ;
      while(numero>Camasint.get(i)){
        x=CamasEnUsoint.get(i);
        y=x+10;
        z=x-10;
        numero = rand.nextInt(y-z+1)+z ;
      }
      CamasEnUsoint.set( i, numero);
    }    

  }
  
  
  

}