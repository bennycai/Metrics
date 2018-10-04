import java.io.*;
import java.util.Scanner;

public class metrics {

  public static void main(String[] args) {

    if (args.length == 0) {
      howToUse();
      System.exit(0);
    }

    String arguements = "";
    if (args[0].contains("-")) {
      if (args[0].contains("l")) arguements += "l";
      if (args[0].contains("c")) arguements += "c";
      if (args[0].contains("w")) arguements += "w";
    } else {
      arguements = "lcw";
    }

    File folder = new File( args[args.length-1] );
    File[] listOfFiles = folder.listFiles();
    for (int i = 0; i < listOfFiles.length; i++) {
      if (listOfFiles[i].isFile()) {
      } else if (listOfFiles[i].isDirectory()) {
        System.out.println("Directory " + listOfFiles[i].getName());
      }
    }
    fileRead(listOfFiles, arguements);
  }

  public static void fileRead (File listOfFiles[], String arguements) {
    int fileCount = 0;
    while ( fileCount < ((listOfFiles.length))) {
      try (BufferedReader file = new BufferedReader ( new FileReader(listOfFiles[fileCount]))){
        String line = file.readLine();
        String fileObject = "";
        int numOfLines = 0;
        while (line != null ){
          fileObject += line;
          line = file.readLine();
          numOfLines++;
        }
        System.out.println(listOfFiles[fileCount]);
        fileCount++;
        fileAnaylsis readingStuff = new fileAnaylsis(fileObject, numOfLines);

        if (arguements.equals("l")) {
          System.out.println(readingStuff.getLine() + " lines");
        } else if (arguements.equals("w")){
          System.out.println(readingStuff.getWordCount(fileObject) + " words");
        } else if (arguements.equals("c")){
          System.out.println(readingStuff.splitChar(fileObject) + " chars");
        } else {
          System.out.println(readingStuff.toString());
        }
      }
      catch(Exception e){
                System.out.println("Please enter a valid directory or file");   //tells user to enter correct directory or file
      }
    }
  }

  public static void howToUse(){
    System.out.println("-l follow by <filename> will print the line count of a file");
    System.out.println("-c follow by <filename> will print the character count of a file");
    System.out.println("-w follow by <filename> will print the word count of a file");
    System.out.println("<filename> will print all of the above");
    System.out.println("Please rerun program with the following format");
  }

}
