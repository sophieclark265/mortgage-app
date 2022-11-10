package input;

import java.util.Scanner;

public class QueryUser {
    public static int queryUserForInteger(String errorMessage) {
        Scanner systemInputScanner = new Scanner(System.in);

        while (!systemInputScanner.hasNextInt()) {
            System.out.println(errorMessage);
            systemInputScanner.next();
        }

        return systemInputScanner.nextInt();
    }

    public static double queryUserForDouble(String errorMessage) {
        Scanner systemInputScanner = new Scanner(System.in);

        while (!systemInputScanner.hasNextDouble()) {
            System.out.println(errorMessage);
            systemInputScanner.next();
        }

        return systemInputScanner.nextDouble();
    }
}
