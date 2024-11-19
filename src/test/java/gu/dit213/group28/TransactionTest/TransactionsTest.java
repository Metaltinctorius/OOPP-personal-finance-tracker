package gu.dit213.group28.TransactionTest;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

import gu.dit213.group28.model.Transaction;
import gu.dit213.group28.model.TransactionHandler;
import gu.dit213.group28.model.enums.TransactionCategory;
import gu.dit213.group28.model.enums.TransactionType;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class TransactionsTest
{

  private TransactionHandler handler;

  @BeforeEach
  public void setup()
  {
    TransactionHandler handler = new TransactionHandler();
  }

  /**
   *
   */
  @Test
  public void testTransactionCreationWithCategory()
  {
    double amount = 100.0;
    TransactionType type = TransactionType.EXPENSE;
    LocalDate date = LocalDate.of(2024, 11, 11);
    TransactionCategory category = TransactionCategory.RENT;

    Transaction transaction = new Transaction.TransactionBuilder(amount, type, date)
        .setCategory(category)
        .build();

    assertEquals(amount, transaction.getAmount());
    assertEquals(type, transaction.getType());
    assertEquals(date, transaction.getDate());
    assertEquals(category, transaction.getCategory());
  }

  @Test
  public void testTransactionCreationWithOutCategory()
  {
    double amount = 100.0;
    TransactionType type = TransactionType.EXPENSE;
    LocalDate date = LocalDate.of(2024, 11, 11);

    Transaction transaction = new Transaction.TransactionBuilder(amount, type, date).build();

    assertEquals(amount, transaction.getAmount());
    assertEquals(type, transaction.getType());
    assertEquals(date, transaction.getDate());
  }

  /**
   * Test should be caught by the handler and throw an exception, and not create an object with bad date input.
   */
  @Test
  public void testTransactionWithInvalidDate()
  {
    double amount = 100;
    TransactionType type = TransactionType.EXPENSE;
    String date = "20241111"; // Invalid date

    final boolean[] createdTransaction = {false};

    Assertions.assertThrows(IllegalArgumentException.class, () -> {
      LocalDate ldate = handler.parseAndValidateDate(date); // Should throw an error

      new Transaction.TransactionBuilder(amount, type, ldate).build(); // Should not reach here
      createdTransaction[0] = true;
    });
    // from this we can assert that the transaction object was never created due to invalid date
    Assertions.assertFalse(createdTransaction[0]);
  }

  /**
   * Test to create a Transaction object with a valid date
   */
  @Test
  public void testTransactionWithValidDate()
  {
    double amount = 100;
    TransactionType type = TransactionType.EXPENSE;
    String date = "2024-11-11"; // VALID

    final boolean[] createdTransaction = {false};

    LocalDate ldate = handler.parseAndValidateDate(date); // Should throw an error

    Transaction transaction =
        new Transaction.TransactionBuilder(amount, type, ldate).build(); // Should not reach here
    createdTransaction[0] = true;

    assertEquals(ldate, transaction.getDate());
    Assertions.assertTrue(createdTransaction[0]);
  }


  /**
   * Test to create a transaction object with an invalid amount
   */
  @Test
  public void testCreateTestWithInvalidAmount()
  {
    double amount = -100; // Invalid amount
    TransactionType type = TransactionType.EXPENSE;
    LocalDate date = LocalDate.of(2024, 11, 11);

    final boolean[] createdTransaction = {false};

    Assertions.assertThrows(IllegalArgumentException.class, () -> {
      double result = handler.validateAmount(amount);  // Should throw and error
      // This should throw an exception due to the negative amount
      new Transaction.TransactionBuilder(result, type, date).build(); // Should never reach here
      createdTransaction[0] = true;
    });
    Assertions.assertFalse(createdTransaction[0]);
  }

  /**
   * Test to create a transaction object with a valid amount
   */
  @Test
  public void testTransactionWithValidAmount()
  {
    double amount = 100; // VALID
    TransactionType type = TransactionType.EXPENSE;
    String date = "2024-11-11";

    final boolean[] createdTransaction = {false};

    LocalDate ldate = handler.parseAndValidateDate(date);

    Transaction transaction = new Transaction.TransactionBuilder(amount, type, ldate).build();
    createdTransaction[0] = true;

    assertEquals(100, transaction.getAmount());
    Assertions.assertTrue(createdTransaction[0]);
  }


  /**
   * Test that just checks that the input type is still of type TransactionType and the same
   */
  @Test
  public void testTransactionType()
  {
    double amount = 100; // VALID
    TransactionType type = TransactionType.EXPENSE;
    String date = "2024-11-11";

    final boolean[] createdTransaction = {false};

    LocalDate ldate = handler.parseAndValidateDate(date);

    Transaction transaction = new Transaction.TransactionBuilder(amount, type, ldate).build();
    createdTransaction[0] = true;

    assertEquals(TransactionType.EXPENSE, transaction.getType());
    Assertions.assertTrue(createdTransaction[0]);
  }

  @Test
  public void testTransactionTypeWithFlippedType()
  {
    double amount = 100; // VALID
    TransactionType type = TransactionType.INCOME;
    String date = "2024-11-11";

    final boolean[] createdTransaction = {false};

    LocalDate ldate = handler.parseAndValidateDate(date);

    Transaction transaction = new Transaction.TransactionBuilder(amount, type, ldate).build();
    createdTransaction[0] = true;

    assertEquals(TransactionType.INCOME, transaction.getType()); // Should not pass
    Assertions.assertTrue(createdTransaction[0]);
  }


  @Test
  public void testCreateTransactionViaHandler(){
    String amount = "100";
    String type = "expense";
    String date = "2024-01-12";

    List<String> args = new ArrayList<>();
    args.add(amount);
    args.add(type);
    args.add(date);

    TransactionHandler handler = new TransactionHandler();

    LocalDate expectedDate = LocalDate.of(2024, 01, 12);
    Transaction transaction = handler.createTransaction(args);

    Assertions.assertEquals(transaction.getType(), TransactionType.EXPENSE);
    Assertions.assertEquals(transaction.getAmount(), 100);
    Assertions.assertEquals(transaction.getDate(),expectedDate );
  }


}
