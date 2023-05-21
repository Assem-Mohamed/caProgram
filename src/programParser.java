import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

public class programParser {
        public static void main(String[] args) {
            try {
                File file = new File("D:\\GUC\\Semester 6\\(CSEN601) Computer System Architecture\\Project\\caProgram\\src\\Instructions.txt");
                FileReader fileReader = new FileReader(file);
                BufferedReader bufferedReader = new BufferedReader(fileReader);
    
                String line;
                while ((line = bufferedReader.readLine()) != null) {
                    System.out.println(line);
                }
    
                bufferedReader.close();
                fileReader.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }