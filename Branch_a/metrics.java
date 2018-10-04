import java.io.*;
import java.util.Scanner;

public class metrics {

  public static void main(String[] args) throws IOException {
    File folder = new File( args[1] );
    File[] listOfFiles = folder.listFiles();
    for (int i = 0; i < listOfFiles.length; i++) {
      if (listOfFiles[i].isFile()) {
      } else if (listOfFiles[i].isDirectory()) {
        System.out.println("Directory " + listOfFiles[i].getName());
      }
    }
    fileRead(listOfFiles);
  }

  public static void fileRead (File listOfFiles[]) throws IOException{
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
        fileCount++;
        fileAnaylsis readingStuff = new fileAnaylsis(fileObject, numOfLines);
        System.out.println(readingStuff.toString());
        System.out.println(readingStuff.getLine() + " lines");
        System.out.println(readingStuff.getWordCount(fileObject) + " words");
        System.out.println(readingStuff.splitChar(fileObject) + " chars");
      }
    }
  }
}
