function Customer(customerID,customerName,customerAddress,customerTeleNumber){
    var __cusId=customerID;
    var __cusName=customerName;
    var __cusAddress=customerAddress;
    var __cusTeleNumber=customerTeleNumber;

    this.setCustomerID=function (id){
        __cusId=id;
    }

    this.getCustomerID=function (){
        return __cusId;
    }
    this.setCustomerName = function (name) {
        __cusName = name;
    };

    this.getCustomerName = function () {
        return __cusName;
    };

    this.setCustomerAddress = function (address) {
        __cusAddress = address;
    };

    this.getCustomerAddress = function () {
        return __cusAddress;
    };

    this.setCustomerTeleNumber = function (teleNumber) {
        __cusTeleNumber = teleNumber;
    };

    this.getCustomerTeleNumber = function () {
        return __cusTeleNumber;
    };

}