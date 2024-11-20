package gu.dit213.group28.model.enums;

import javax.xml.crypto.dsig.TransformService;

public enum TransactionType {
  EXPENSE("expense"),
  INCOME("income");

  private final String value;

  TransactionType(String value) {
    this.value = value;
  }

  public String getValue() {
    return value;
  }

  @Override
  public String toString() {
    return value;
  }

  public static TransactionType fromValue(String value) {
    for (TransactionType type : TransactionType.values()) {
      if (type.value.equalsIgnoreCase(value)) {
        return type;
      }
    }
    throw new IllegalArgumentException(
        "Wrong transaction type, must be either 'EXPENSE', or 'INCOME'");
  }
}
