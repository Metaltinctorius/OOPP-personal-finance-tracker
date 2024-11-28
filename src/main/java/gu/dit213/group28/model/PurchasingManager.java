package gu.dit213.group28.model;

import gu.dit213.group28.model.Event;
import gu.dit213.group28.model.MarketCalculator;
import gu.dit213.group28.model.Stock;
import gu.dit213.group28.model.enums.CompanyStocks;
import gu.dit213.group28.model.enums.EventType;
import gu.dit213.group28.model.enums.PlayerAction;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class PurchasingManager {



  /** List of every stock on the market, and their multipliers */
  private final Set <Stock> stocks = new HashSet<>();

  private final MarketCalculator calculator = new MarketCalculator();

  public void buyStock(Stock stock, int amount) {
    if (amount > stock.getQuantity()) {
      throw new IllegalArgumentException("Required amount supersedes the current stock quantity, try a lower amount.");
    }
    if(amount > 0 ){
      for (int i = 0; i < amount; i++) {
        makeUserPurchase(stock, 1);
      }
    } else {
      throw new IllegalArgumentException("Stock amount cannot be 0 or negative");
    }
  }

  private void makeUserPurchase(Stock stock, int amount){
    int quantity = stock.getQuantity(); // The amount of stocks available
    int newQuantity = quantity - amount; // How many are left after user purschase

    double newPrice = calculator.calculateNewPrice(stock.getValue(), amount, quantity);
    double newMultiplier = calculator.calculateMultiplier(stock.getMultiplier(), amount, quantity);

    adjustStock(stock, newQuantity, newMultiplier, newPrice);
  }



  private void adjustStock(Stock stock, int newQuantity, double newMultiplier, double newPrice){
    stock.setQuantity(newQuantity);
    stock.setValue(newPrice);
    stock.setMultiplier(newMultiplier);
  }



  /*
  public void adjustSectorValues(CompanyStocks purchasedStock, double impactFactor) {
    for (CompanyStocks stock : CompanyStocks.values()) {
      if (stock.getCategories().containsAll(purchasedStock.getCategories())) {
        stock.setValue(stock.getValue() + (purchasedStock.getValue() * impactFactor));
        stock.setMultiplier(stock.getQuantity() + (purchasedStock.getMultiplier()));
      }
    }
  }
*/


}
