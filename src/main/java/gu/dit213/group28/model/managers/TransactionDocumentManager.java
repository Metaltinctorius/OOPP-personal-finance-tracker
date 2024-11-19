package gu.dit213.group28.model.managers;

import gu.dit213.group28.model.Transaction;
import org.bson.Document;

/**
 * This class handles the document creation and reading from and to Transaction type.
 */
public class TransactionDocumentManager {
  public static Document toDocument(Transaction transaction){
    return new Document("amount", transaction.getAmount())
        .append("type", transaction.getType())
        .append("date", transaction.getDate());
  }

  public static Transaction fromDocument(Document doc){
    return null; // TODO
  }
}

