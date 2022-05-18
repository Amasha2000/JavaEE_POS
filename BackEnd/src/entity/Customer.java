package entity;

public class Customer {
    private String customerID;
    private String customerName;
    private String customerAddress;
    private String customerTeleNumber;

    public Customer(String customerID, String customerName, String customerAddress, String customerTeleNumber) {
        this.customerID = customerID;
        this.customerName = customerName;
        this.customerAddress = customerAddress;
        this.customerTeleNumber = customerTeleNumber;
    }

    public Customer() {

    }

    public String getCustomerID() {
        return customerID;
    }

    public void setCustomerID(String customerID) {
        this.customerID = customerID;
    }

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public String getCustomerAddress() {
        return customerAddress;
    }

    public void setCustomerAddress(String customerAddress) {
        this.customerAddress = customerAddress;
    }

    public String getCustomerTeleNumber() {
        return customerTeleNumber;
    }

    public void setCustomerTeleNumber(String customerTeleNumber) {
        this.customerTeleNumber = customerTeleNumber;
    }

}
