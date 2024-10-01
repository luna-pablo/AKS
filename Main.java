import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.math.BigInteger;

public class Main {

  /**
   * @param args
   */
  public static void main(String[] args) {
    // TODO Auto-generated method stub    
    boolean cert = false;
    BufferedWriter writer = null;
    
    for(int i = 0; i < args.length; i++)
    {
      if(args[i].trim().equals("-c"))
        cert=true;
      if(args[i].trim().equals("-v"))
        Interface.verbose = true;
      if(args[i].trim().equals("-vs"))
      {
        Interface.verbose = true;
        MillerRabin.verbose = true;
        AKS.verbose = true;
        MillerRabinThread.verbose = true;
      }
    }
    
    try {
      writer = new BufferedWriter(new FileWriter("prime_check_results.txt"));
      for (int a = 0; a < args.length; a++) {
        if(! args[a].trim().equals("-c")  && ! args[a].trim().equals("-v") && ! args[a].trim().equals("-vs"))
        {
          try {
              long startTime = System.currentTimeMillis();; // Start timer
              BigInteger number = new BigInteger(args[a].trim());
              Interface i = new Interface(number, cert);

              boolean is_prime = i.isPrime();
              // Print whether the number is prime or not
              System.out.println(number + " - " + (is_prime ? "PRIME" : "NOT PRIME"));
              long endTime = System.currentTimeMillis();; // End timer
              if((i.PASO1_TIME + i.PASO2_TIME + i.PASO3_TIME + i.PASO5_TIME) > 0){
                System.out.println("Tiempo Paso 1: " + (i.PASO1_TIME) + " ms");
                System.out.println("Tiempo Paso 2: " + (i.PASO2_TIME) + " ms");
                System.out.println("Tiempo Paso 3: " + (i.PASO3_TIME) + " ms");
                System.out.println("Tiempo Paso 5: " + (i.PASO5_TIME) + " ms");
                System.out.println("Tiempo total Pasos: " + (i.PASO1_TIME + i.PASO2_TIME + i.PASO3_TIME + i.PASO5_TIME) + " ms");
                System.out.println("Tiempo total: " + (endTime - startTime) + " ms");
                writer.write(number + " - " + is_prime + " - Paso1 time: " + i.PASO1_TIME + " - Paso2 time: " + i.PASO2_TIME + " - Paso3 time: " + i.PASO3_TIME + " - Paso5 time: " + i.PASO5_TIME + " - Tiempo total pasos: " + (i.PASO1_TIME + i.PASO2_TIME + i.PASO3_TIME + i.PASO5_TIME) + " - Tiempo total: " + (endTime - startTime));
                writer.newLine();
              }
              else{
                System.out.println("Tiempo total: " + (endTime - startTime) + " ms");
                System.out.println("Tiempo Paso 1: " + (i.PASO1_TIME) + " ms");
                System.out.println("Tiempo Paso 2: " + (i.PASO2_TIME) + " ms");
                System.out.println("Tiempo Paso 3: " + (i.PASO3_TIME) + " ms");
                System.out.println("Tiempo Paso 5: " + (i.PASO5_TIME) + " ms");
                System.out.println("Tiempo total Pasos: " + (i.PASO1_TIME + i.PASO2_TIME + i.PASO3_TIME + i.PASO5_TIME) + " ms");
                writer.write(number + " - " + is_prime + " - Tiempo total: " + (endTime - startTime));
                writer.newLine();
              }              

          } catch (NumberFormatException e) {
              System.out.println("Invalid number: " + args[a].trim());
          }
        }
      }
    }
    catch(Exception e)
    {
      System.out.println(e.getMessage());
    } finally {
      try {
          if (writer != null)
              writer.close();
      } catch (IOException e) {
          e.printStackTrace();
      }
    }   

  }

}

