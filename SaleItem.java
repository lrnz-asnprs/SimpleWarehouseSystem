public class SaleItem {

    private String name;
    private String category;
    private int priceInCents;
    private int stock;

    public SaleItem(String name, String category, int priceInCents, int stock) {
        this.name = name;
        this.category = category;
        this.priceInCents = priceInCents;
        this.stock = stock;
    }


    public void setPrice(int price) {
        priceInCents = price;
    }

    public boolean checkStock(int count) {
        if (stock >= count) {
            return true;
        } else {
            return false;
        }
    }

    public int getPrice() {
        return priceInCents;
    }

    public int getStock() {
        return stock;
    }

    public String getName() {
        return name;
    }

    public String getCategory() {
        return category;
    }

    public void addToStock(int extra) {
        stock = stock + extra;
    }

    public void setStock(int minus) {
        stock = stock - minus;
    }
    
}
