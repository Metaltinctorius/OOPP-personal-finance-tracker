package gu.dit213.group28.TransactionTest;


import static org.junit.jupiter.api.Assertions.assertEquals;

import gu.dit213.group28.model.TransactionHandler;
import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import gu.dit213.group28.model.Transaction;
import gu.dit213.group28.model.enums.TransactionCategory;
import gu.dit213.group28.model.enums.TransactionType;


public class DateTest {
  TransactionHandler handler;

  @BeforeEach
  public void  setup(){
    TransactionHandler handler = new TransactionHandler();
  }

  @Test
  public void testDateParser(){
    String date = "2024-11-11";
    LocalDate ldate = handler.parseAndValidateDate(date);
    LocalDate expectedDate = LocalDate.of(2024, 11, 11);
    Assertions.assertEquals(ldate, expectedDate);
  }

  @Test
  public void testInvalidDateParser(){
    String date = "20241111"; // invalid

    Assertions.assertThrows(IllegalArgumentException.class, () -> {
      TransactionHandler.parseAndValidateDate(date);
    });
  }

  /**
   * Tests if the Date is invalid (month 13), this is handled by the LocalDate class and it throws a DateTimeParserException
   */
  @Test
  public void testInvalidMonth() {
    String dateStr = "2024-13-11";

    Assertions.assertThrows(DateTimeParseException.class, () -> {
      TransactionHandler.parseAndValidateDate(dateStr);
    });
  }





}
