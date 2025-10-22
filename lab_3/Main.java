package lab3;
import java.util.Scanner;

public class Main {
    public static void main(String[] args){
        Scanner scan = new Scanner(System.in);
        Translator tr;
        while(true) {
            try{
                System.out.print("Dictionary file: ");
                tr = new Translator(scan.nextLine());
                break;
            }catch(Translator.FileReadException err) {
                System.out.println("Error reading file: " + err.getMessage() + "\n");
            }
            catch(Translator.InvalidFileFormatException err) {
                System.out.println(err.getMessage() + "\n");
            }
        }

        System.out.print("Text(eng): ");
        String text = scan.nextLine();
        System.out.print("Text(ru) : " + tr.translate(text));
    }
}