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
      if (args[0].contains("s")) arguements += "s";
      if (args[0].contains("m")) arguements += "m";
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
    File[] codingLanguages = new File[listOfFiles.length];

    System.out.println(codingLanguages[1]);
    languageSort(listOfFiles, codingLanguages);
    if (arguements.contains("l") || arguements.contains("c") || arguements.contains("w")) {
      fileRead(listOfFiles, arguements);
    } else if (arguements.contains("s") || arguements.contains("m")){
      programFileRead(codingLanguages, arguements);
    }

  }
  public static void programFileRead (File codingLanguages[], String arguements){
    int fileCount = 0;
    int i =0;
    while (codingLanguages[i] != null){
      i++;
    }
    System.out.println(i);
    while (fileCount <= (i-1)){
      System.out.println("inloop?");
      try (BufferedReader files = new BufferedReader (new FileReader(codingLanguages[fileCount]))){
        int numOfComments = 0;
        int numLOC = 0;
        int numOfLines = 0;
        String line = files.readLine();
        System.out.println(line);
        while (line !=null ){
          line = files.readLine();
          numOfLines++;
          if (line.contains("//")){
            numOfComments ++;
          } else numLOC ++;
        }
        System.out.println(codingLanguages[fileCount]);
        if (arguements.equals("s")) {
          System.out.println(numLOC);
        } else if (arguements.equals("m")){
          System.out.println(numOfComments);
        }
        fileCount ++;
      }
      catch(Exception e){
              System.out.println("Please enter a valid directory or file");
              System.exit(0);
      }
    }
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
                System.out.println("Please enter a valid directory or file");
                System.exit(0);
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

  public static void languageSort (File listOfFiles[], File codingLanguages[]){
    int numOfProgramingFiles = 0;
    for (int i = 0; i < listOfFiles.length; i++){
      String temp = listOfFiles[i].getName();
      if (temp.endsWith("java") ||
          temp.endsWith("c") ||
          temp.endsWith("h") ||
          temp.endsWith("cpp")||
          temp.endsWith("hpp")) {
        codingLanguages[numOfProgramingFiles] = listOfFiles[i];
        numOfProgramingFiles ++;
      }
    }
  }
}
