
public class Main {
    public static void main(String[] args) {
        
        ShoppingBasket basket = new ShoppingBasket();

        SaleItem item = new SaleItem("Snickerdoodle", "Snicker", 100, 1);

        basket.addItem(item);

        basket.printReceipt();
}
}