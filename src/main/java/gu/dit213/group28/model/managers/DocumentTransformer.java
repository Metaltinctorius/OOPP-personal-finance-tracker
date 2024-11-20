package gu.dit213.group28.model.managers;

import gu.dit213.group28.model.Transaction;
import org.bson.Document;

/** This class handles the document creation and reading from and to Transaction type. */
public class DocumentTransformer {

  /**
   * This function takes in an object of type Transaction and returns an object of type Document.
   * This is used to store documents in mongodb.
   *
   * @param transaction The transaction to create a document for.
   * @return Returns the document, ready to be placed in the mongodb.
   */
  public static Document toDocument(Transaction transaction) {
    return new Document("amount", transaction.getAmount())
        .append("type", transaction.getType())
        .append("date", transaction.getDate());
  }

  public static Transaction fromDocument(Document doc) {
    return null; // TODO
  }
}
