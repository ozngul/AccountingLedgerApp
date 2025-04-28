package com.pluralsight;
import java.util.List;

    // This class will take care of reading and writing transactions from/to our CSV file.
    // Instead of writing file code everywhere, we organize it here — this makes our project clean and modular.
    public class TransactionService {


        // This method is supposed to read all the transactions from the CSV file
        // and return them as a list of Transaction objects.
        // Right now it's empty because we will build it later!
        public List<Transaction> loadTransactions() {
            // This method will load transactions from transactions.csv
            return null; // (To be implemented tomorrow)
        }

        // This method will take a new Transaction and add it to the CSV file.
        // Every time the user adds a deposit or payment, we call this method to save it.
        public void saveTransaction(Transaction transaction) {

        }
    }
