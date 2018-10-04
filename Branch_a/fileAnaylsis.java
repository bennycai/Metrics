public class fileAnaylsis {

    private String file;
    private int line;
    private int wordCount;
    private int splitChar;

    public fileAnaylsis (String file, int line){ //gets number of line and gets the file from main
        this.file = file;
        this.line = line;
    }

    public int getLine(){                       //used to get the number of lines in a file
        return line;
    }

    public int getWordCount(String file) {
        String[] splitWord = file.split(" ");       //splits the words separated by spaces
        int wordCount = splitWord.length;
        return wordCount - 1;                           //its always one extra, so we subtract one
    }
    public int splitChar(String file){
        file = file.replace(" ","");            //takes off the spaces then takes the length of string
        return file.length();
    }
    public String toString (){
        //System.out.println(file);
        return String.valueOf(splitChar(file) + " characters " + getLine() +" lines "+ getWordCount(file)+" words ");
                                        //returns all the elements that this class does
    }
}
