function OrderDTO(orderID, customerId, date, totalCost) {
    var __orderID = orderID;
    var __customerId = customerId;
    var __date = date;
    var __totalCost = totalCost;

    this.setOrderID = function (orderId) {
        __orderID = orderId;
    }

    this.getOrderID = function () {
        return __orderID;
    }

    this.setCusId = function (custId) {
        __customerId = custId;
    }

    this.getCusId = function () {
        return __customerId;
    }

    this.setDate = function (orderDate) {
        __date = orderDate;
    }

    this.getDate = function () {
        return __date;
    }

    this.setCost = function (cost) {
        __totalCost = cost;
    }

    this.getCost = function () {
        return __totalCost;
    }
}