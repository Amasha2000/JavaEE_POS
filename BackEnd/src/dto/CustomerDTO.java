package dto;

public class CustomerDTO {
    private String customerID;
    private String customerName;
    private String customerAddress;
    private String customerTeleNumber;

    public CustomerDTO() {
    }

    public CustomerDTO(String customerID, String customerName, String customerAddress, String customerTeleNumber) {
        this.setCustomerID(customerID);
        this.setCustomerName(customerName);
        this.setCustomerAddress(customerAddress);
        this.setCustomerTeleNumber(customerTeleNumber);
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
