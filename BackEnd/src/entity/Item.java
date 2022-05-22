package entity;

public class Item {
    private String code;
    private String itemName;
    private int quantity;
    private double price;

    public Item() {
    }

    public Item(String code, String itemName, int quantity, double price) {
        this.setCode(code);
        this.setItemName(itemName);
        this.setQuantity(quantity);
        this.setPrice(price);
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getItemName() {
        return itemName;
    }

    public void setItemName(String itemName) {
        this.itemName = itemName;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }
}
