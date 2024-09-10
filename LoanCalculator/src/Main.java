import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scnr = new Scanner(System.in);

        do {
            // Get user input for principal, annual interest rate, and number of months
            double principal = getUserPrincipalCost(scnr);
            double annualInterest = getAnnualInterest(scnr);
            int numberOfMonths = getNumOfMonths(scnr);

            // Calculate and print the simple interest repayment schedule
            System.out.println("Repayment Schedule for Simple Interest Loan:");
            printSimpleInterestRepayment(principal, annualInterest, numberOfMonths);

            // Calculate and print the amortized repayment schedule
            System.out.println("\nRepayment Schedule for Amortized Loan:");
            printAmortizedRepaymentSchedule(principal, annualInterest, numberOfMonths);

            // Calculate and display the difference in total interest paid
            double simpleInterestTotal = calculateSimpleInterestTotalInterest(principal, annualInterest, numberOfMonths);
            double amortizedTotal = calculateAmortizedTotalInterest(principal, annualInterest, numberOfMonths);
            System.out.printf("\nDifference in Total Interest Paid: $%.2f%n", (simpleInterestTotal - amortizedTotal));

            // Ask if the user wants to calculate another loan schedule
            System.out.print("\nCalculate another loan schedule? (y/n): ");
        } while (scnr.next().equalsIgnoreCase("y"));

        scnr.close();
    }

    // Method to print simple interest repayment schedule
    private static void printSimpleInterestRepayment(double principal, double annualInterest, int numberOfMonths) {
        double monthlyPayment = calculateSimpleInterestMonthlyPayment(principal, annualInterest, numberOfMonths);

        System.out.println("Payment #\tYear #\tPayment Amount");
        for (int i = 1; i <= numberOfMonths; i++) {
            int year = (i - 1) / 12 + 1;
            System.out.printf("%8d\t%5d\t$%.2f%n", i, year, monthlyPayment);
        }
    }

    // Method to calculate total interest paid for simple interest loan
    private static double calculateSimpleInterestTotalInterest(double principal, double annualInterestRate, int numberOfMonths) {
        double monthlyPayment = calculateSimpleInterestMonthlyPayment(principal, annualInterestRate, numberOfMonths);
        return (monthlyPayment * numberOfMonths) - principal;
    }

    // Method to calculate monthly payment for simple interest loan
    private static double calculateSimpleInterestMonthlyPayment(double principal, double annualInterestRate, int numberOfMonths) {
        double monthlyInterestRate = annualInterestRate / 12;
        return (principal * monthlyInterestRate * numberOfMonths + principal) / numberOfMonths;
    }

    // Method to print amortized loan repayment schedule
    private static void printAmortizedRepaymentSchedule(double principal, double annualInterestRate, int numberOfMonths) {
        double monthlyPayment = calculateAmortizedMonthlyPayment(principal, annualInterestRate, numberOfMonths);

        System.out.println("Payment #\tYear #\tPayment Amount");
        for (int i = 1; i <= numberOfMonths; i++) {
            int year = (i - 1) / 12 + 1;
            System.out.printf("%8d\t%5d\t$%.2f%n", i, year, monthlyPayment);
        }
    }

    // Method to calculate total interest paid for amortized loan
    private static double calculateAmortizedTotalInterest(double principal, double annualInterestRate, int numberOfMonths) {
        double monthlyPayment = calculateAmortizedMonthlyPayment(principal, annualInterestRate, numberOfMonths);
        double totalPayment = monthlyPayment * numberOfMonths;
        return totalPayment - principal;
    }

    // Method to calculate monthly payment for amortized loan
    private static double calculateAmortizedMonthlyPayment(double principal, double annualInterestRate, int numberOfMonths) {
        double monthlyInterestRate = annualInterestRate / 12;
        double monthlyPayment = principal * monthlyInterestRate / (1 - Math.pow(1 + monthlyInterestRate, -numberOfMonths));
        return monthlyPayment;
    }

    private static double getUserPrincipalCost(Scanner scnr) {
        System.out.print("Enter the principal cost amount: $");
        return scnr.nextDouble();
    }

    private static double getAnnualInterest(Scanner scnr) {
        System.out.print("Enter the annual interest rate in percentage (%): ");
        return scnr.nextDouble() / 100.0;
    }

    private static int getNumOfMonths(Scanner scnr) {
        System.out.print("Enter the number of months for the given loan: ");
        return scnr.nextInt();
    }
}

