package gu.dit213.group28.TransactionTest;

import static org.junit.jupiter.api.Assertions.assertEquals;

import gu.dit213.group28.model.Transaction;
import gu.dit213.group28.model.enums.TransactionCategory;
import gu.dit213.group28.model.enums.TransactionType;
import java.time.LocalDate;
import org.junit.jupiter.api.Test;

public class TransactionsTest {



  @Test
  public void testTransactionCreationWithCategory() {
    // Given values
    double amount = 100.0;
    TransactionType type = TransactionType.EXPENSE;
    LocalDate date = LocalDate.of(2024, 11, 11);
    TransactionCategory category = TransactionCategory.RENT;

    // When: Creating a Transaction using the builder pattern
    Transaction transaction = new Transaction.TransactionBuilder(amount, type, date)
        .setCategory(category) // Set the optional category
        .build();

    // Then: Verify the transaction fields
    assertEquals(amount, transaction.getAmount());
    assertEquals(type, transaction.getType());
    assertEquals(date, transaction.getDate());
    assertEquals(category, transaction.getCategory()); // Ensure category is set
  }
}
