import java.io.*;
import java.util.Scanner;

public class metrics {

  public static void main(String[] args) throws IOException {
    if (args.length == 0){
      System.out.println("Enter an arguement.");
    } else {
      for (String a : args){
        try (BufferedReader file = new BufferedReader(new FileReader(a))){
          String line;
          while ((line = file.readLine()) !=null){
            System.out.println(line);
          }
        }
      }
    }
  }
}
