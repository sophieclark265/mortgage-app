package calculator;

import input.QueryUser;
import input.NumberConverter;

import java.util.Scanner;



public class MortgageCalculator {

    public static void calculate() {
        boolean inputsCorrect = false;

        int principal = 0;
        double interestRate = 0;
        int loanTerm = 0;

        while(!inputsCorrect) {
            principal = getPrincipal();
            interestRate = getInterestRate();
            loanTerm = getLoanTerm();
            inputsCorrect = checkInputsCorrect();
        }
            outputMonthlyMortgageEstimate(principal, interestRate, loanTerm);
    }

    public static int getPrincipal() {
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

        return principal;

        // create principal primitive type that will hold estimated cost in int typeSystem.out.println("Your principal is: " + principal);
    }

    public static double getInterestRate() {
        int monthsInYear = 12;
        int percent = 100;
        System.out.print("Your estimated annual interest rate:");

        String errorMessage = "Please enter a decimal number.";
        double annualInterest = QueryUser.queryUserForDouble(errorMessage);

        if (annualInterest > 30) {
            System.out.println("Please enter a rate less than 30%");
            return getInterestRate();
        }


        double monthlyInterest = (annualInterest / (double)monthsInYear) / (double)percent;
        System.out.println("monthly interest is: " + monthlyInterest);
        return monthlyInterest;
    }

    public static int getLoanTerm() {
        System.out.print("Your loan term (in years):");

        String errorMessage = "Please enter a number of years.";
        int loanTermInYears = QueryUser.queryUserForInteger(errorMessage);

        int max = 35;
        int min = 5;

        if (loanTermInYears > max) {
            System.out.println("Max loan term is 35 years. Please decrease loan term.");
            getLoanTerm();
        } else if (loanTermInYears < min) {
            System.out.println("Minimum loan term is 5 years. Please increase loan term.");
            getLoanTerm();
        }

        return loanTermInYears * 12;
    }

    public static boolean checkInputsCorrect() {
        System.out.println("Are all these inputs correct? Enter Y or N");
        Scanner checkFinalNumbers = new Scanner(System.in);
        String yesOrNo = checkFinalNumbers.nextLine();

        if (yesOrNo.equalsIgnoreCase("Y") || yesOrNo.equalsIgnoreCase("Yes")) {
            return true;
        } else {
            return false;
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
