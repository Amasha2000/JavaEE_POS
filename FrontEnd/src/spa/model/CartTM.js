function CartTM(itemID, item, unitPrice, qty, totalPrice) {
    var __itemID = itemID;
    var __item = item;
    var __unitPrice = unitPrice;
    var __qty = qty;
    var __totalPrice = totalPrice;

    this.setItemID = function (code) {
        __itemID = code;
    }

    this.getItemID = function () {
        return __itemID;
    }

    this.setItem = function (itemName) {
        __item = itemName;
    }

    this.getItem = function () {
        return __item;
    }

    this.setItemUnitPrice = function (price) {
        __unitPrice = price;
    }

    this.getItemUnitPrice = function () {
        return __unitPrice;
    }

    this.setQty = function (quantity) {
        __qty = quantity;
    }

    this.getQty = function () {
        return __qty;
    }

    this.setTotalPrice = function (price) {
        __totalPrice = price;
    }

    this.getTotalPrice = function () {
        return __totalPrice;
    }
}