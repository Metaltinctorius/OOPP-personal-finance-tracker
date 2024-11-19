package gu.dit213.group28.model;

import gu.dit213.group28.model.enums.TransactionCategory;
import gu.dit213.group28.model.enums.TransactionType;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import jdk.jfr.Category;


/**
 * This class handles the transaction data it recieves from the user input, creates a transaction object and stores it in the database.
 */
public class TransactionHandler {
  /**
   * purpose of the global variable is to standardize date parsing and formatting across the TransactionHandler class
   */
  private static final DateTimeFormatter DATE_FORMAT = DateTimeFormatter.ofPattern("yyyy-MM-dd");

  private void createTransaction(){
    double amount = 100;
    TransactionType expense = TransactionType.EXPENSE;
    LocalDate date = parseAndValidateDate("2024-11-11");
    TransactionCategory category = TransactionCategory.RENT;


    Transaction transaction = new Transaction.TransactionBuilder(amount, expense, date).setCategory(category).build();
    Transaction transactionWithoutCategory = new Transaction.TransactionBuilder(amount, expense, date).build();
  }






  public static LocalDate parseAndValidateDate(String dateString) {
    // Check if the dateString matches the expected format
    if (dateString == null || !dateString.matches("\\d{4}-\\d{2}-\\d{2}")) {
      throw new IllegalArgumentException("Invalid date format. Expected yyyy-MM-dd.");
    }

    try {
      // Try to parse the date using the predefined format
      LocalDate date = LocalDate.parse(dateString, DATE_FORMAT);
      return date; // Return the valid date
    } catch (Error e) {
      // Catch invalid date syntax errors
      throw new IllegalArgumentException("Invalid date format. Expected yyyy-MM-dd.", e);
    }
  }

  public static double validateAmount(double amount){
    if(amount > 0) return amount;
    else throw new IllegalArgumentException("Amount cannot be negative.");
  }

  public static TransactionType validateType(TransactionType type){
    if(type.equals(TransactionType.EXPENSE)) return TransactionType.EXPENSE;
    if(type.equals(TransactionType.INCOME)) return TransactionType.INCOME;
    else throw new IllegalArgumentException(("Wrong transaction type, must be either 'EXPENSE', or 'INCOME'"));
  }

}
