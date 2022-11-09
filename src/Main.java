
import java.util.Scanner;
public class Main {
    public static void main(String[] args) {
        System.out.println("Hello world!");
        getPrincipal();
    }

    public static void getPrincipal() {
        System.out.print("Enter the amount you need to borrow:");
        Scanner scannerPrincipal = new Scanner(System.in);
        // create scanner object that lets user enter value in terminal
        int principal = scannerPrincipal.nextInt();
        int max = 10_000_000;
        int min = 25_000;
        int compMax = Integer.compare(max, principal);
        // if y bigger than x, returns -1
        int compMin = Integer.compare(principal, min);
        System.out.println("comp min is " + compMin);
        // if x greater than y, returns 1

        int zero = 0;
        if (compMax < 0) {
            System.out.println("Max principal is $10,000,000. Please re-enter a valid principal.");
            getPrincipal();
        } else if (compMin < 0) {
            System.out.println("Min principal is $25,000. Please re-enter a valid principal.");
            getPrincipal();
        }

        // create principal primitive type that will hold estimated cost in int type
        System.out.println("Your principal is: " + principal);
        getInterestRate(principal);
    }

    public static void getInterestRate(int principal) {
        System.out.print("Your estimated annual interest rate:");
        Scanner scannerInterest = new Scanner(System.in);
        // Scanner class lets us create scanner objects
        // from scanner package in Java, it is used to get userInput

        double annualInterest = scannerInterest.nextDouble();
        double monthlyInterest = (annualInterest / (double)12) / (double)100;
        System.out.println("Your monthly interest rate is: " + monthlyInterest);

        getLoanTerm(principal, monthlyInterest);
    }

    public static void getLoanTerm(int principal, double monthlyInterest) {
        System.out.print("How long is the loan term in years?");
        Scanner loanTerm = new Scanner(System.in);

        int max = 35;
        int min = 5;

        byte years = loanTerm.nextByte();
        int compMax = Integer.compare(years, min); // if x smaller than y, return -1
        int compMin = Integer.compare(years, max); // if x greater than y, return 1

        if (compMax < 0) {
            System.out.println("Minimum loan term is 5 years. Please increase loan term.");
            getLoanTerm(principal, monthlyInterest);
        } else if (compMin > 0) {
            System.out.println("Max loan term is 35 years. Please decrease loan term.");
            getLoanTerm(principal, monthlyInterest);
        }

        int loanPayments = (int)years * 12;
        System.out.println("Your total number of payments is " + loanPayments);

        checkInputsCorrect(principal, monthlyInterest, loanPayments);
    }

    public static void checkInputsCorrect(int principal, double monthlyInterest, int loanPayments) {
        System.out.println("Are all these inputs correct? Enter Y or N");
        Scanner checkFinalNumbers = new Scanner(System.in);
        String yesOrNo = checkFinalNumbers.nextLine();

        if (yesOrNo.equals("Y") || yesOrNo.equals("Yes")) {
            outputMonthlyMortgageEstimate(principal, monthlyInterest, loanPayments);
        } else {
            getPrincipal();
        }
    }

    public static void outputMonthlyMortgageEstimate(int principal, double monthlyInterest, int loanPayments) {

        float brackets = (float)1 + ((float)monthlyInterest);
        float indices = (float)loanPayments;

        float monthlyTimesPayments = (float)Math.pow(brackets, loanPayments); // should be 2.454
        float numerator = (float)monthlyInterest * monthlyTimesPayments;
        float denom = monthlyTimesPayments - (float)1;


        float result = (float)principal * (numerator / denom);
        System.out.println("Your monthly mortgage is " + result);
    }


}