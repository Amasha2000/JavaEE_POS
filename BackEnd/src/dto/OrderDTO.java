package dto;

public class OrderDTO {
    private String orderId;
    private String cusId;
    private String date;
    private double cost;

    public OrderDTO() {
    }

    public OrderDTO(String orderId, String cusId, String date, double cost) {
        this.setOrderId(orderId);
        this.setCusId(cusId);
        this.setDate(date);
        this.setCost(cost);
    }

    public String getOrderId() {
        return orderId;
    }

    public void setOrderId(String orderId) {
        this.orderId = orderId;
    }

    public String getCusId() {
        return cusId;
    }

    public void setCusId(String cusId) {
        this.cusId = cusId;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public double getCost() {
        return cost;
    }

    public void setCost(double cost) {
        this.cost = cost;
    }
}
