function ItemDTO(itemCode, itemName, quantity, price) {
    var __itemCode = itemCode;
    var __itemName = itemName;
    var __quantity = quantity;
    var __price = price;

    this.setItemCode = function (code) {
        __itemCode = code;
    }

    this.getItemCode = function () {
        return __itemCode;
    }

    this.setItemName = function (name) {
        __itemName = name;
    }

    this.getItemName = function () {
        return __itemName;
    }

    this.setQuantity = function (quantity) {
        __quantity = quantity;
    }

    this.getQuantity = function () {
        return __quantity;
    }

    this.setPrice = function (price) {
        __price = price;
    }

    this.getPrice = function () {
        return __price;
    }
}