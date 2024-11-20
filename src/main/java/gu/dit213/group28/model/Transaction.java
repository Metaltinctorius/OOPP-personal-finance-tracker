package gu.dit213.group28.model;

import gu.dit213.group28.model.enums.TransactionCategory;
import gu.dit213.group28.model.enums.TransactionType;
import java.time.LocalDate;

public class Transaction {

  /** Required parameter */
  private double amount;

  /** Required paramter */
  private TransactionType type;

  /** Required paramter */
  private LocalDate date;

  /** Optional Paramter */
  private TransactionCategory category;

  private Transaction(TransactionBuilder builder) {
    this.amount = builder.amount;
    this.type = builder.type;
    this.date = builder.date;
    this.category = builder.category;
  }

  public double getAmount() {
    return amount;
  }

  public TransactionType getType() {
    return type;
  }

  public LocalDate getDate() {
    return date;
  }

  public TransactionCategory getCategory() {
    return category;
  }

  // Builder class, based on:
  // https://www.digitalocean.com/community/tutorials/builder-design-pattern-in-java
  public static class TransactionBuilder {

    // Required
    private double amount;
    private TransactionType type;
    private LocalDate date;

    // Optional
    private TransactionCategory category;

    public TransactionBuilder(double amount, TransactionType type, LocalDate date) {
      this.amount = amount;
      this.type = type;
      this.date = date;
    }

    public TransactionBuilder setCategory(TransactionCategory category) {
      this.category = category;
      return this;
    }

    public Transaction build() {
      return new Transaction(this);
    }
  }
}
