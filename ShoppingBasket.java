import java.math.BigDecimal;
import java.math.RoundingMode;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ShoppingBasket {

    private List<SaleItem> list;
    private Map<SaleItem, Integer> countItems;
    private boolean alreadyCalled;

    public ShoppingBasket() {
        list = new ArrayList<>();
        countItems = new HashMap<>();
        alreadyCalled = false;
    }

    public void addItem(SaleItem item) {

        if (!alreadyCalled) {

            if (countItems.keySet().contains(item)) {
                countItems.put(item, countItems.get(item)+1);
            } else {
                countItems.put(item, 1);
            }
    
            list.add(item);

        } else {
            throw new InvalidPurchase("Already locked");
        }


    }

    public int total() {
        int sum = 0;
        for (SaleItem item : list) {
            sum = sum + item.getPrice();
        }
        return sum;
    }

    
    public boolean checkStock() {
        
        boolean check = true;

        for (SaleItem item : countItems.keySet()) {
            if (item.getStock() < countItems.get(item)) {
                check = false;
            }
        }

        if (check) {
            return check;
        } else {
            throw new OutOfStock();
        }

    }


    public void finalizePurchase() throws OutOfStock {
       
            if (checkStock()) {

                if (!alreadyCalled) {
    
                    for (SaleItem item : countItems.keySet()) {
                        item.setStock(countItems.get(item));
                    }
                    alreadyCalled = true;
                }
            }

    }


    public void printReceipt() {

        finalizePurchase();

        Comparator<SaleItem> comparator = Comparator.comparing(SaleItem::getCategory).thenComparing(SaleItem::getName);
        Collections.sort(list, comparator);
        double finalTotal = (double) total() / 100;
        DecimalFormat df = new DecimalFormat("0.00"); 

        for (SaleItem item:list) {
            double finalPrice = (double) item.getPrice()/100;
            finalPrice = Math.round(finalPrice*100.0)/100.0;

            System.out.println(item.getName() + " $" + df.format(finalPrice));

            }

        System.out.println("---");
       
        System.out.println("Total:" + " $" + df.format(finalTotal));
    
        

    }

    
}
