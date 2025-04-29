package com.pluralsight;

import java.io.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

public class TransactionService {
    private static final String FILE_PATH = "src/main/resources/transactions.csv";

    // Save a transaction to the CSV file
    public void saveTransaction(Transaction transaction) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(FILE_PATH, true))) {
            writer.write(transaction.toCSV());
            writer.write(System.lineSeparator()); // platforma uygun yeni satır
        } catch (IOException e) {
            System.out.println("Error saving transaction: " + e.getMessage());
        }
    }

    // Load all transactions from the CSV file
    public List<Transaction> loadTransactions() {
        List<Transaction> transactions = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(FILE_PATH))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split("\\|");
                if (parts.length == 5) {
                    Transaction transaction = new Transaction(
                            parts[0], parts[1], parts[2], parts[3], Double.parseDouble(parts[4])
                    );
                    transactions.add(transaction);
                }
            }
        } catch (IOException e) {
            System.out.println("Error loading transactions: " + e.getMessage());
        }
        return transactions;
    }

    // Generate a Transaction object using current date/time and user input
    public Transaction createTransaction(String description, String vendor, double amount) {
        LocalDateTime now = LocalDateTime.now();
        String date = now.toLocalDate().toString();
        String time = now.toLocalTime().format(DateTimeFormatter.ofPattern("HH:mm:ss"));
        return new Transaction(date, time, description, vendor, amount);
    }
}
