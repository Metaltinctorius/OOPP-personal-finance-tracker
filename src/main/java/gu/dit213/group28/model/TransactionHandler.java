package gu.dit213.group28.model;

import gu.dit213.group28.model.enums.TransactionCategory;
import gu.dit213.group28.model.enums.TransactionType;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;



/**
 * This class handles the transaction data it receives from the user input, creates a transaction
 * object and stores it in the database.
 */
public class TransactionHandler
{
  /**
   * purpose of the global variable is to standardize date parsing
   * and formatting across the TransactionHandler class
   */
  private static final DateTimeFormatter DATE_FORMAT = DateTimeFormatter.ofPattern("yyyy-MM-dd");

  // Kan göra att den tar in en lista av arguments istället (list of strings)
  // och parsar varje data för sig.
  public Transaction createTransaction(List<String> args)
  {
    // First 3 arguments of any transaction must always contain an amount, a type and a date.
    double amount = validateAmount(Double.parseDouble(
        args.get(0))); //parses the string to a double, validates the double, then stores it.
    TransactionType type =
        parseAndValidateType(args.get(1));  // TODO: Implement a string parser for the type.
    LocalDate date = parseAndValidateDate(
        (args.get(2))); // Parses the string and validates it, creating an object of type LocalDate.

    // Creates a basic transaction that has the 3 required fields
    Transaction.TransactionBuilder builder = new Transaction.TransactionBuilder(amount, type, date);

    //  TODO: Implement traversing for other fields in the argument list
    for (int i = 3; i < args.size(); i++)
    {
      String field = args.get(i).toLowerCase(); // Ensure case-insensitivity

      switch (field)
      {
        case "category":
          TransactionCategory category = TransactionCategory.valueOf(args.get(++i).toUpperCase());
          builder.setCategory(category);
          break;

        case "rate":
          break;

        case "goal":
          break;

        case "timeinterval":
          break;

        default:
          throw new IllegalArgumentException("Unknown field: " + field);
      }
    }
    return builder.build();
  }


  /**
   * Takes in a string with the legal format "yyyy-mm-dd" and parses it to a date of type LocalDate
   *
   * @param dateString The input string received from the user
   * @return returns the date if the date is of correct format, otherwise throws an error.
   */
  public static LocalDate parseAndValidateDate(String dateString)
  {
    if (dateString == null)
    {
      dateString = LocalDate.now().toString(); // Default to today if date is not set
    }
    if (!dateString.matches("\\d{4}-\\d{2}-\\d{2}"))
    {
      throw new IllegalArgumentException("Invalid date format. Expected yyyy-MM-dd.");
    }
    try
    {
      return LocalDate.parse(dateString, DATE_FORMAT); // Return the valid date
    } catch (Error e)
    {
      throw new IllegalArgumentException("Invalid date format. Expected yyyy-MM-dd.", e);
    }
  }

  public static double validateAmount(double amount)
  {
    if (amount > 0)
    {
      return amount;
    } else
    {
      throw new IllegalArgumentException("Amount cannot be negative.");
    }
  }

  // KOMMER ANTAGLIGEN ATT ÄNDRAS TILL ATT BLI EN PARSER FÖR STRING INPUTS ISTÄLLET //
  public static TransactionType parseAndValidateType(String type)
  {
    String field = type.toLowerCase();
    if (field.equals("expense"))
    {
      return TransactionType.EXPENSE;
    }
    if (field.equals("income"))
    {
      return TransactionType.INCOME;
    } else
    {
      throw new IllegalArgumentException(
          ("Wrong transaction type, must be either 'EXPENSE', or 'INCOME'"));
    }
  }

}
