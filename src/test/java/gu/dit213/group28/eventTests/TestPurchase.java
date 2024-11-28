package gu.dit213.group28.eventTests;


import static org.junit.jupiter.api.Assertions.*;

import gu.dit213.group28.model.EventFacade;
import gu.dit213.group28.model.Stock;
import gu.dit213.group28.model.enums.CompanyStocks;
import org.junit.jupiter.api.*;
public class TestPurchase {


  @Test
  public void testPurchaseStock(){
    EventFacade eventFacade = new EventFacade();

    Stock newStock = new Stock(CompanyStocks.PFIZER, 0.0, 1000, 50);
    eventFacade.buyStock(newStock, 1);
    //Assertions.assertEquals(14,newStock.getQuantity());
    //Assertions.assertTrue(newStock.getMultiplier() > 0.0);
    Assertions.assertTrue(newStock.getValue() > 1000);
    System.out.println(newStock.getMultiplier());
    System.out.println(newStock.getValue());
  }
}
