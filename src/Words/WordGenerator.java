package Words;
import java.io.BufferedReader;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Random;
import java.util.random.*;
import java.util.regex.Pattern;
import java.text.Normalizer;



public class WordGenerator {
    private String word;
    private Character[] charOfWord;
   
    public String generateWord() {
        Random r=new Random();
        int numLine=r.nextInt(1,101);
        Path path = Paths.get("src","Words","Words.csv");
        String randomLine="";
        boolean flag=true;
        while(flag){
            try(BufferedReader reader = Files.newBufferedReader(path, Charset.defaultCharset())){
                randomLine=readLineFromFile(reader, numLine);
                
                }
                catch(Exception e){
                    System.err.format("Erro de formatacao: %s%n", e);
                }
                if (randomLine.length()==5){
                    return randomLine.toLowerCase();
                }
        }   
        return randomLine;
           
    }
    public String getWord() {
        return word;
    }
    public static String readLineFromFile(BufferedReader reader,int lineNum) throws IOException{
        String line="";
        for (int i=0;i<lineNum;i++){
            line=reader.readLine();
        }
        return line;
    }
    

    
    
}


