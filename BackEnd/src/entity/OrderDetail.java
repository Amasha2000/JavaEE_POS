package entity;

public class OrderDetail {
    private String itemCode;
    private String orderId;
    private double discount;
    private double cost;

    public OrderDetail() {
    }

    public OrderDetail(String itemCode, String orderId, double discount, double cost) {
        this.setItemCode(itemCode);
        this.setOrderId(orderId);
        this.setDiscount(discount);
        this.setCost(cost);
    }

    public String getItemCode() {
        return itemCode;
    }

    public void setItemCode(String itemCode) {
        this.itemCode = itemCode;
    }

    public String getOrderId() {
        return orderId;
    }

    public void setOrderId(String orderId) {
        this.orderId = orderId;
    }

    public double getDiscount() {
        return discount;
    }

    public void setDiscount(double discount) {
        this.discount = discount;
    }

    public double getCost() {
        return cost;
    }

    public void setCost(double cost) {
        this.cost = cost;
    }
}
