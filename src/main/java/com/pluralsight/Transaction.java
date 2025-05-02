package com.pluralsight;
// This class represents one financial transaction (a payment or deposit)
// Every time someone adds a deposit or makes a payment, we create a Transaction object.
public class Transaction {

    // This class represents a single transaction: deposit or payment
    private String date;     // The date of the transaction (yyyy-MM-dd)
        private String time; // The time of the transaction (HH:mm:ss)
    private String description; // Short explanation of the transaction
        private String vendor;   // Who is the transaction with
        private double amount;   // Positive = deposit, Negative = payment


    // This is the constructor — it helps us create a new Transaction easily
        public Transaction(String date, String time, String description, String vendor, double amount) {
            this.date = date;
            this.time = time;
            this.description = description;
            this.vendor = vendor;
            this.amount = amount;
        }


    // Getter methods for accessing transaction details


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
    // Format transaction data as a line for CSV file
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

