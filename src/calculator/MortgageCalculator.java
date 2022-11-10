package calculator;

import input.QueryUser;
import input.NumberConverter;

import java.util.Scanner;



public class MortgageCalculator {

    public static void getPrincipal() {
        System.out.print("Enter the amount you need to borrow:");
        String errorMessage = "Please enter a whole number.";

        int principal = QueryUser.queryUserForInteger(errorMessage);
        int max = 10_000_000;
        int min = 25_000;

        if (principal > max) {
            System.out.println("Max principal is $10,000,000. Please re-enter a valid principal.");
            getPrincipal();
        } else if (principal < min) {
            System.out.println("Min principal is $25,000. Please re-enter a valid principal.");
            getPrincipal();
        }

        // create principal primitive type that will hold estimated cost in int typeSystem.out.println("Your principal is: " + principal);
        getInterestRate(principal);
    }

    public static void getInterestRate(int principal) {

        System.out.print("Your estimated annual interest rate:");

        String errorMessage = "Please enter a decimal number.";
        double annualInterest = QueryUser.queryUserForDouble(errorMessage);
        double monthlyInterest = (annualInterest / (double)12) / (double)100;


        getLoanTerm(principal, monthlyInterest);
    }

    public static void getLoanTerm(int principal, double monthlyInterest) {
        System.out.print("Your loan term (in years):");

        String errorMessage = "Please enter a number of years.";
        int loanTermInYears = QueryUser.queryUserForInteger(errorMessage);

        int max = 35;
        int min = 5;

        if (loanTermInYears > max) {
            System.out.println("Max loan term is 35 years. Please decrease loan term.");
            getLoanTerm(principal, monthlyInterest);
        } else if (loanTermInYears < min) {
            System.out.println("Minimum loan term is 5 years. Please increase loan term.");
            getLoanTerm(principal, monthlyInterest);
        }

        int numberOfMonthlyPayments = loanTermInYears * 12;

        checkInputsCorrect(principal, monthlyInterest, numberOfMonthlyPayments);
    }

    public static void checkInputsCorrect(int principal, double monthlyInterest, int loanPayments) {
        System.out.println("Are all these inputs correct? Enter Y or N");
        Scanner checkFinalNumbers = new Scanner(System.in);
        String yesOrNo = checkFinalNumbers.nextLine();

        if (yesOrNo.equalsIgnoreCase("Y") || yesOrNo.equalsIgnoreCase("Yes")) {
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


        float numberResultNoCurrency = (float)principal * (numerator / denom);
        String currencyResult = NumberConverter.convertToDollars(numberResultNoCurrency);
        System.out.println("Your monthly payment is: " + currencyResult);
    }

}
