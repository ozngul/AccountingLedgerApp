package com.pluralsight;

import java.util.List;
import java.util.Scanner;
// This is the main class where the CLI (terminal menu) is displayed and controlled
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
                    // Get deposit info from user
                    System.out.print("Enter description: ");
                    String depDesc = scanner.nextLine();
                    System.out.print("Enter vendor: ");
                    String depVendor = scanner.nextLine();
                    System.out.print("Enter amount: ");
                    double depAmount = Double.parseDouble(scanner.nextLine());

                    // Save deposit transaction
                    Transaction deposit = service.createTransaction(depDesc, depVendor, depAmount);
                    service.saveTransaction(deposit);
                    System.out.println("Deposit added!");
                    break;

                case "P":
                    // Get payment info from user
                    System.out.print("Enter description: ");
                    String payDesc = scanner.nextLine();
                    System.out.print("Enter vendor: ");
                    String payVendor = scanner.nextLine();
                    System.out.print("Enter amount: ");
                    double payAmount = Double.parseDouble(scanner.nextLine()) * -1;

                    // Save payment transaction
                    Transaction payment = service.createTransaction(payDesc, payVendor, payAmount);
                    service.saveTransaction(payment);
                    System.out.println("Payment recorded!");
                    break;

                case "L":
                    List<Transaction> all = service.loadTransactions();

                    while (true) {
                        System.out.println("\n--- Ledger Menu ---");
                        System.out.println("A) All Transactions");
                        System.out.println("D) Deposits Only");
                        System.out.println("P) Payments Only");
                        System.out.println("R) Reports");
                        System.out.println("H) Home");
                        System.out.print("Select an option: ");
                        String ledgerChoice = scanner.nextLine().trim().toUpperCase();

                        switch (ledgerChoice) {
                            case "A":
                                for (Transaction t : all) {
                                    System.out.println(t);
                                }
                                break;
                            case "D":
                                for (Transaction t : all) {
                                    if (t.getAmount() > 0) {
                                        System.out.println(t);
                                    }
                                }
                                break;
                            case "P":
                                for (Transaction t : all) {
                                    if (t.getAmount() < 0) {
                                        System.out.println(t);
                                    }
                                }
                                break;

                            case "R":
                                while (true) {
                                    System.out.println("\n--- Reports Menu ---");
                                    System.out.println("1) Month to Date");
                                    System.out.println("2) Previous Month");
                                    System.out.println("3) Year to Date");
                                    System.out.println("4) Previous Year");
                                    System.out.println("5) Search by Vendor");
                                    System.out.println("0) Back");
                                    System.out.print("Select a report: ");
                                    String reportChoice = scanner.nextLine();

                                    if (reportChoice.equals("0")) break;

                                    switch (reportChoice) {
                                        case "1":
                                            for (Transaction t : all) {
                                                if (service.isInCurrentMonth(t.getDate())) {
                                                    System.out.println(t);
                                                }
                                            }
                                            break;
                                        case "2":
                                            for (Transaction t : all) {
                                                if (service.isInPreviousMonth(t.getDate())) {
                                                    System.out.println(t);
                                                }
                                            }
                                            break;
                                        case "3":
                                            for (Transaction t : all) {
                                                if (service.isInCurrentYear(t.getDate())) {
                                                    System.out.println(t);
                                                }
                                            }
                                            break;
                                        case "4":
                                            for (Transaction t : all) {
                                                if (service.isInPreviousYear(t.getDate())) {
                                                    System.out.println(t);
                                                }
                                            }
                                            break;
                                        case "5":
                                            System.out.print("Enter vendor name: ");
                                            String vendorSearch = scanner.nextLine().trim().toLowerCase();
                                            for (Transaction t : all) {
                                                if (t.getVendor().toLowerCase().contains(vendorSearch)) {
                                                    System.out.println(t);
                                                }
                                            }
                                            break;
                                        default:
                                            System.out.println("Invalid report option.");
                                    }
                                }
                                break;

                            case "H":
                                break;

                            default:
                                System.out.println("Invalid option.");
                        }

                        if (ledgerChoice.equals("H")) {
                            break;
                        }
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
