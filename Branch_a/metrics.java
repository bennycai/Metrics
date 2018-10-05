import java.io.*;
import java.util.Scanner;

public class metrics {

  public static void main(String[] args) {

    if (args.length == 0) {
      howToUse();
      System.exit(0);
    }

    String arguements = "";
    /* supposed to used picocli here but I couldnt figure it out on how to use
    so i used these types of code instead */
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
    /* This set of code is to get files from certain directories and store them
    into a listOfFiles array so we can use it later */

    File[] codingLanguages = new File[listOfFiles.length];
    /* created an new array to store all the files with programming languages */

    languageSort(listOfFiles, codingLanguages);
    if (arguements.contains("l") || arguements.contains("c") || arguements.contains("w")) {
      fileRead(listOfFiles, arguements);
    } else if (arguements.contains("s") || arguements.contains("m")){
      programFileRead(codingLanguages, arguements);
    }
    /* if arguemnt is l c or w then it goes to fileRead method, while if user wants
    lines of code or comments, it get sent to programFileRead method */
  }
  public static void programFileRead (File codingLanguages[], String arguements){
    /* wanted this program to only read programming language files, so it uses the
    codingLanguages array where all the languages are stored. Wanted this to print
    the number of LOC and the number of comments, but it keeps returning an error... */

    int fileCount = 0;
    int i =0;
    while (codingLanguages[i] != null){
      i++;
    }
    System.out.println(i);
    while (fileCount <= (i-1)){
      try (BufferedReader files = new BufferedReader (new FileReader(codingLanguages[fileCount]))){
        int numOfComments = 0;
        int numLOC = 0;
        int numOfLines = 0;
        String line = files.readLine();
        System.out.println(line);
        while (line !=null ){
          line = files.readLine();
          numOfLines++;
          if (line.contains("/")){
            numOfComments ++;
          } else numLOC ++;
        }
        /*records the number of comments if the line string contains / and
        if it doesnt not contain it, it will be counted towards a LOC */

        System.out.println(codingLanguages[fileCount]);
        if (arguements.equals("s")) {
          System.out.println(numLOC);
        } else if (arguements.equals("m")){
          System.out.println(numOfComments);
        }

        /* Prints the file name and depending on arguments, it will print the
        LOC or the number of comments. */

        fileCount ++;
      }
      catch(Exception e){
              System.out.println("Please enter a valid directory or file");
              howToUse();
              System.exit(0);
      }
    }
  }
  public static void fileRead (File listOfFiles[], String arguements) {
    /*methods is basically the first Sprint. It will deal with all the word count,
    character count and line count.*/

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

        /* I actually turned each file into a String and then send it over to
        another class to count the number of characters and words. The number of
        lines is counted in this method. */
      }
      catch(Exception e){
                System.out.println("Please enter a valid directory or file");
                howToUse();
                System.exit(0);
      }
    }
  }

  public static void howToUse(){
    /*tells the user how to use the program in command prompt. */
    System.out.println("-l follow by <filename> will print the line count of a file");
    System.out.println("-c follow by <filename> will print the character count of a file");
    System.out.println("-w follow by <filename> will print the word count of a file");
    System.out.println("-s follow by <filename> will print the LOC count of a file");
    System.out.println("-m follow by <filename> will print the comment line count of a file");
    System.out.println("<filename> will print all of the above");
    System.out.println("Please rerun program with the following format");
  }

  public static void languageSort (File listOfFiles[], File codingLanguages[]){
    /* sorts the files that end with java, c, h, cpp, and hpp into codingLanguage
    array from the listOfFile array so we can see which files are in coding
    languages formats */
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
