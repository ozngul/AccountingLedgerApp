package com.pluralsight;

import java.io.*;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

// This class handles reading/writing transactions from/to the CSV file
public class TransactionService {
    private static final String FILE_PATH = "src/main/resources/transactions.csv";

    // Save a new transaction by appending it to the CSV file
    public void saveTransaction(Transaction transaction) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(FILE_PATH, true))) {
            writer.write(transaction.toCSV());
            writer.write(System.lineSeparator()); // Ensures each transaction is on a new line
        } catch (IOException e) {
            System.out.println("Error saving transaction: " + e.getMessage());
        }
    }

    // Read all transactions from the CSV file
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

    // Create a transaction using the current date and time
    public Transaction createTransaction(String description, String vendor, double amount) {
        LocalDateTime now = LocalDateTime.now();
        String date = now.toLocalDate().toString();
        String time = now.toLocalTime().format(DateTimeFormatter.ofPattern("HH:mm:ss"));
        return new Transaction(date, time, description, vendor, amount);
    }

    // Create a transaction using current date and time
    // Check if the transaction is from the current month
    public boolean isInCurrentMonth(String dateStr) {
        LocalDate date = LocalDate.parse(dateStr);
        LocalDate now = LocalDate.now();
        return date.getMonth() == now.getMonth() && date.getYear() == now.getYear();
    }

    // Check if the transaction is from the previous month
    public boolean isInPreviousMonth(String dateStr) {
        LocalDate date = LocalDate.parse(dateStr);
        LocalDate lastMonth = LocalDate.now().minusMonths(1);
        return date.getMonth() == lastMonth.getMonth() && date.getYear() == lastMonth.getYear();
    }

    // Check if the transaction is from the current year
    public boolean isInCurrentYear(String dateStr) {
        LocalDate date = LocalDate.parse(dateStr);
        return date.getYear() == LocalDate.now().getYear();
    }

    // Check if the transaction is from the previous year
    public boolean isInPreviousYear(String dateStr) {
        LocalDate date = LocalDate.parse(dateStr);
        return date.getYear() == LocalDate.now().getYear() - 1;
    }
}
