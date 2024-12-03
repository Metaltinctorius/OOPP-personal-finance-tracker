package gu.dit213.group28.eventTests;


import gu.dit213.group28.model.Event;
import gu.dit213.group28.model.EventFacade;
import gu.dit213.group28.model.enums.CompanyStocks;
import java.util.List;
import org.junit.jupiter.api.*;
public class TestPurchase {


  @Test
  public void testPurchaseStock(){
    EventFacade eventFacade = new EventFacade();

    List<Event> log = eventFacade.getEventLog();

    Stock newStock = new Stock(CompanyStocks.PFIZER, 0.0, 1000, 50);
    eventFacade.buyStock(newStock, 1);
    //Assertions.assertEquals(14,newStock.getQuantity());
    //Assertions.assertTrue(newStock.getMultiplier() > 0.0);
    Assertions.assertTrue(newStock.getValue() > 1000);
    Assertions.assertTrue(!log.isEmpty());
    System.out.println(newStock.getMultiplier());
    System.out.println(newStock.getValue());
    System.out.println(log.get(0).getAction() + " " + log.get(0).getDescription());

    System.out.println(log.get(0).getId());
    System.out.println(log.getLast());
    System.out.println(log.getFirst());
  }
}
