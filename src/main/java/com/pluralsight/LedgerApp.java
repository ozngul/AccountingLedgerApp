package com.pluralsight;

import java.util.List;
import java.util.Scanner;

public class LedgerApp {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        TransactionService service = new TransactionService();

        while (true) {
            System.out.println("\n=== Accounting Ledger ===");
            System.out.println("D) Add Deposit");
            System.out.println("P) Make Payment");
            System.out.println("L) View Ledger");
            System.out.println("X) Exit");
            System.out.print("Select an option: ");
            String choice = scanner.nextLine().trim().toUpperCase();

            switch (choice) {
                case "D":
                    System.out.print("Enter description: ");
                    String depDesc = scanner.nextLine();
                    System.out.print("Enter vendor: ");
                    String depVendor = scanner.nextLine();
                    System.out.print("Enter amount: ");
                    double depAmount = Double.parseDouble(scanner.nextLine());

                    Transaction deposit = service.createTransaction(depDesc, depVendor, depAmount);
                    service.saveTransaction(deposit);
                    System.out.println("Deposit added!");
                    break;

                case "P":
                    System.out.print("Enter description: ");
                    String payDesc = scanner.nextLine();
                    System.out.print("Enter vendor: ");
                    String payVendor = scanner.nextLine();
                    System.out.print("Enter amount: ");
                    double payAmount = Double.parseDouble(scanner.nextLine()) * -1; // negative

                    Transaction payment = service.createTransaction(payDesc, payVendor, payAmount);
                    service.saveTransaction(payment);
                    System.out.println("Payment recorded!");
                    break;

                case "L":
                    List<Transaction> all = service.loadTransactions();
                    System.out.println("\n--- Ledger ---");
                    for (Transaction t : all) {
                        System.out.println(t);
                    }
                    break;

                case "X":
                    System.out.println("Exiting...");
                    return;

                default:
                    System.out.println("Invalid choice.");
            }
        }
    }
}
