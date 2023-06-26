package utils;
import java.util.Scanner;

public class Scan {
    private static Scanner scan;
    static {
        scan = new Scanner(System.in);
    }
    public static String getString(String print) {
        System.out.println(print);
        return scan.nextLine();
    }
}
