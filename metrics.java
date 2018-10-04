import java.io.*;
import java.util.Scanner;

public class metrics {
    public static void main(String[] args) throws Exception {
        Scanner sc = new Scanner(System.in);
        howToUse();
        int fileCount = 0;
        System.out.println("Please enter wc, wc-l, wc-c, wc-w"); //prompts user to choose what type of word count they want
        String answer = sc.nextLine();
        while (!answer.equals("wc") && !answer.equals("wc-l") && !answer.equals("wc-c") && !answer.equals("wc-w")) {
            //loops until user puts in the correct format and option
            System.out.println("Please enter a valid response: wc, wc-1, wc-c or wc-w");
            answer = sc.nextLine();
        }
        System.out.println("Please enter a directory/file");       //prompts users to enter where the file is, or where the directory is
        String nameInput = sc.nextLine();
        String directory = nameInput;
        File dir = new File(directory);                             //looks at the user input
        File[] fileArray = dir.listFiles();                         //if multiple files exists, this tries to read it
        try {                                                       //if file doesnt exists, this trys to catch it
            for (File read : fileArray) {
                FileReader files = new FileReader(read);            //makes file so buffered reader can be used
                BufferedReader reader = new BufferedReader(files);
                System.out.println(fileArray[fileCount].getName()); //types out the name of the file
                fileCount++;                                        //add count so we can use next array cell

                int lineNum = 0;
                String file = "";
                String line = reader.readLine();
                while (line != null) {
                    file += " " + line;                             //turns file into a long string
                    line = reader.readLine();
                    lineNum++;                                      //records number of lines
                }

                fileReader test = new fileReader(file, lineNum);    // calls filereader class to determine num of words and charactor
                if (answer.equals("wc")) {                          //uses answers to see what the user wants to see...
                    System.out.println(test.toString());
                } else if (answer.equals("wc-l")) {
                    System.out.println(test.getLine() + " lines");
                } else if (answer.equals("wc-w")) {
                    System.out.println(test.getWordCount(file) + " words");
                } else if (answer.equals("wc-c")) {
                    System.out.println(test.splitChar(file) + " chars");
                }
            }
            } catch(FileNotFoundException e){
                System.out.println("Please enter a valid directory or file");   //tells user to enter correct directory or file
                System.err.println(e);
            }
    }
    public static void howToUse(){                      //short intro on how to opperate this program
        System.out.println("wc-l follow by <filename> will print the line count of a file");
        System.out.println("wc-c follow by <filename> will print the character count of a file");
        System.out.println("wc-w follow by <filename> will print the word count of a file");
        System.out.println("wc follow by <filename> will all of the above");
    }
}
