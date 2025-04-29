package com.pluralsight;
// This class represents one financial transaction (a payment or deposit)
// Every time someone adds a deposit or makes a payment, we create a Transaction object.
public class Transaction {

    // These are the details we need to track for every transaction.
    private String date;
        private String time;
        private String description;
        private String vendor;
        private double amount;

        // This is the constructor — it helps us create a new Transaction easily
        public Transaction(String date, String time, String description, String vendor, double amount) {
            this.date = date;
            this.time = time;
            this.description = description;
            this.vendor = vendor;
            this.amount = amount;
        }

        // Getters
        // These are called "getter" methods — they let other parts of the code
        // safely access the information inside a Transaction object.

        public String getDate() {
            return date;
        }

        public String getTime() {
            return time;
        }

        public String getDescription() {
            return description;
        }

        public String getVendor() {
            return vendor;
        }

        public double getAmount() {
            return amount;
        }
    // This method turns a transaction into a CSV string (used when saving to file)
    public String toCSV() {
        return date + "|" + time + "|" + description + "|" + vendor + "|" + amount;
    }
        // For easy printing
        // When we print a Transaction (example: System.out.println(transaction)),
        // we want it to look nice and readable. This method tells Java exactly how to format the output.
        @Override
        public String toString() {
            return date + " | " + time + " | " + description + " | " + vendor + " | " + amount;
        }
    }

