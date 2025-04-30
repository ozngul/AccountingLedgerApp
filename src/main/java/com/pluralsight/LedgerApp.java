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

                    while (true) {
                        System.out.println("\n--- Ledger Menu ---");
                        System.out.println("A) All Transactions");
                        System.out.println("D) Deposits Only");
                        System.out.println("P) Payments Only");
                        System.out.println("R) Reports (Search by Vendor)");
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
                                System.out.print("Enter vendor name to search: ");
                                String vendorSearch = scanner.nextLine().trim().toLowerCase();
                                for (Transaction t : all) {
                                    if (t.getVendor().toLowerCase().contains(vendorSearch)) {
                                        System.out.println(t);
                                    }
                                }
                                break;
                            case "H":
                                // go back to main menu
                                break;
                            default:
                                System.out.println("Invalid option.");
                        }

                        if (ledgerChoice.equals("H")) {
                            break; // Exit ledger submenu
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
