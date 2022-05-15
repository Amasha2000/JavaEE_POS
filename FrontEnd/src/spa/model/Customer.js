function CustomerDTO(customerId, customerName, customerAddress, customerTeleNumber) {
    var __customerId = customerId;
    var __customerName = customerName;
    var __customerAddress = customerAddress;
    var __customerTeleNumber = customerTeleNumber;


    this.setCustomerId = function (id) {
        __customerId = id;
    };

    this.getCustomerId = function () {
        return __customerId;
    };

    this.setCustomerName = function (name) {
        __customerName = name;
    };

    this.getCustomerName = function () {
        return __customerName;
    };

    this.setCustomerAddress = function (address) {
        __customerAddress = address;
    };

    this.getCustomerAddress = function () {
        return __customerAddress;
    };

    this.setCustomerTeleNumber = function (teleNumber) {
        __customerTeleNumber = teleNumber;
    };

    this.getCustomerTeleNumber = function () {
        return __customerTeleNumber;
    };
}
