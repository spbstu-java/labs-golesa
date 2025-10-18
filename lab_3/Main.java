package lab3;

import java.io.IOException;
import java.util.*;

public class Main {
    public static void main(String[] args) throws Translator.InvalidFileFormatException, IOException {
        System.out.print("Text(eng): ");
        Scanner scan = new Scanner(System.in);
        Translator tr = new Translator("dict.txt");
        String text = scan.nextLine();
        System.out.print("Text(ru) : " + tr.translate(text));
    }
}