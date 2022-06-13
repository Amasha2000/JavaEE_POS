populateItemDropDown();
populateCustomerDropDown();
//set the today date
document.getElementById('date').valueAsDate = new Date();

//populate customer drop down list
function populateCustomerDropDown() {
    var select = document.getElementById('cusID');
    $('#cusID').empty();
    var customerOptions = [];
    customerOptions[0] = 'Choose...';

    $.ajax({
        url:"http://localhost:8080/BackEnd_Web_exploded/customer?option=GETID",
        method:"GET",
        success:function (resp){
            for (var i=0;i<resp.data.length;i++) {
                customerOptions[i + 1] = resp.data[i];
            }
            for (var i = 0; i < customerOptions.length; i++) {
                var opt = customerOptions[i];
                var el = document.createElement('option');
                el.textContent = opt;
                el.value = opt;
                select.appendChild(el);
            }
        },
        error:function(ob,state){
            console.log(ob,state);
        }
    });
}


//populate item drop down list
function populateItemDropDown() {
    var select = document.getElementById('ItemCode');
    $('#ItemCode').empty();
    var itemOptions = [];
    itemOptions[0] = 'Choose...';

    $.ajax({
        url:"http://localhost:8080/BackEnd_Web_exploded/item?option=GETID",
        method:"GET",
        success:function (resp){
            for (var i=0;i<resp.data.length;i++) {
                itemOptions[i + 1] = resp.data[i];
            }
            for (var i = 0; i < itemOptions.length; i++) {
                var opt = itemOptions[i];
                var el = document.createElement('option');
                el.textContent = opt;
                el.value = opt;
                select.appendChild(el);
            }
        },
        error:function(ob,state){
            console.log(ob,state);
        }
    });
}

//load selected customer details
$('#cusID').on('change', function () {
    getSelectedCustomer();
    //validateCustomerFeild();
});

function getSelectedCustomer() {
    customerSearch($('#cusID').val());
}

function customerSearch(id){
    $.ajax({
        url: "http://localhost:8080/BackEnd_Web_exploded/customer?option=SEARCH&CustomerID="+id,
        method:"GET",
        success:function (resp) {
            if (resp.status == 200) {
                for (var i = 0; i < resp.data.length; i++) {
                    $('#customer').val(resp.data[i].id);
                    $('#name').val(resp.data[i].name);
                    $('#address').val(resp.data[i].address);
                    $('#telNum').val(resp.data[i].teleNumber);
                }
            }
        },
            error:function(ob,state){
                console.log(ob,state);
            }
        });
}

//load selected item details
$('#ItemCode').on('change', function () {
    getSelectedItem();
    checkSufficientQuantity();
   // validateItemField();
});


function getSelectedItem() {
    itemSearch($('#ItemCode').val());
}

function itemSearch(id){
    $.ajax({
        url: "http://localhost:8080/BackEnd_Web_exploded/item?option=SEARCH&ItemCode="+id,
        method:"GET",
        success:function (resp) {
            if (resp.status == 200) {
                for (var i = 0; i < resp.data.length; i++) {
                    $('#item').val(resp.data[i].code);
                    $('#itemName').val(resp.data[i].itemName);
                    $('#price').val(resp.data[i].price);
                    $('#quantityOnHand').val(resp.data[i].quantity);
                }
            }
        },
        error:function(ob,state){
            console.log(ob,state);
        }
    });
}

//set order ID automatically

$("#orderID").val("O00-001");

function createAutoID() {
    // $.ajax({
    //     url:"http://localhost:8080/BackEnd_Web_exploded/order?option=GETID",
    //     method:"GET",
    //     success:function (resp){
    //         $("#orderID").val(resp.data);
    //     },
    //     error:function(ob,state){
    //         console.log(ob,state);
    //     }
    // });
}

//check if the quantity is sufficient
function checkSufficientQuantity() {
    $("#quantity").on('keyup', function () {
        if ($("#quantityOnHand").val() >= $("#quantity").val() && regExItemQuantity.test($("#quantity").val())) {
            $('#quantity').css('border', '3px solid green');
            $("#orderQuantity-error").text('');
            $("#btnAddItem").attr('disabled', false);
        } else {
            $('#quantity').css('border', '3px solid red');
            $("#orderQuantity-error").text("Please enter quantity lower than " + $("#quantityOnHand").val() + " or check Qunatity On Hand is sufficient");
            $("#btnAddItem").attr('disabled', true);
        }
    });
}

var cartArray =new Array();

//add items to cart
$("#btnAddItem").on("click", function () {
     var code = $("#item").val();
     var name = $("#itemName").val();
     var unitPrice = $("#price").val();
     var qty = $("#quantity").val();
     var totalPrice = (qty * unitPrice).toFixed(2);
     var qtyOnHand=$("#quantityOnHand").val();

     var item={
        "code":code,
        "name":name,
        "unitPrice":unitPrice,
        "qty" :qty,
        "tot":totalPrice
     }

    // var updatedItem={
    //     itemCode:code,
    //     itemName:name,
    //     qty:+qtyOnHand - +qty,
    //     price:unitPrice
    // }
    //
    // $.ajax({
    //     url: "http://localhost:8080/BackEnd_Web_exploded/item",
    //     method:"PUT",
    //     contentType: "application.json",
    //     data: JSON.stringify(updatedItem),
    //     success:function (res) {
    //         if (res.status == 200) {
    //
    //             //Load Item Details To Table
    //             loadItemDetailsToTable();
    //
    //             getSelectedItem();
    //
    //         } else if (res.status == 400) {
    //             alert(res.message);
    //         } else {
    //             alert(res.data)
    //         }
    //     },
    //     error: function (ob, status, t) {
    //         alert(ob);
    //     }
    // });



    if(cartArray.length!=0) {
         var resp= searchCart(code);
        if(resp) {
                    let totalQty = +(resp.qty) + +qty;
                    let total = (totalQty * unitPrice).toFixed(2);

                    resp.code = code;
                    resp.name = name;
                    resp.unitPrice = unitPrice;
                    resp.qty = totalQty;
                    resp.tot = total;

                    loadCartDetailsToTable();

                    // searchItemResponse.setQuantity(+searchItemResponse.getQuantity() - +qty);
                    // getSelectedItem();

                    $("#quantity").val("");
                    // removeItems();

                    setTotal();
                }else {
            cartArray.push(item);
            loadCartDetailsToTable();
            // searchItemResponse.setQuantity(+searchItemResponse.getQuantity() - +qty);
            // getSelectedItem();

            $("#quantity").val("");

            setTotal();
            

            // let searchItemResponse = searchItem(code);
        }
        }else{
         cartArray.push(item);
         loadCartDetailsToTable();
         // searchItemResponse.setQuantity(+searchItemResponse.getQuantity() - +qty);
         // getSelectedItem();

         $("#quantity").val("");

         setTotal();

         // let searchItemResponse = searchItem(code);
     }

});

//load cart details to the table
function loadCartDetailsToTable() {
    $('#cart tbody').empty();
    for (var i of cartArray) {
        $('#cart tbody').append(
            `<tr><td>${i.code}</td><td>${i.name}</td><td>${i.unitPrice}</td><td>${i.qty}</td><td>${i.tot}</td></tr>`
        );
    }
    removeItems();
}

//search cart
function searchCart(id) {
    for (var i = 0; i < cartArray.length; i++) {

        if (cartArray[i].code == id) {
            return cartArray[i];
        }
    }
}

//calculate total
function setTotal() {
    var sumOfTotal = 0;
    for (var i = 0; i < cartArray.length; i++) {
        sumOfTotal = (+sumOfTotal + +cartArray[i].tot).toFixed(2);
    }
    $("#total").text(sumOfTotal);
    $("#sub-total").text(sumOfTotal);
}

//remove items from the cart
function removeItems() {
    $('#cart tbody tr').dblclick(function () {
        let response = searchCart($(this).children(":nth-child(1)").text());
        let index = cartArray.indexOf(response);
        cartArray.splice(index, 1);
        loadCartDetailsToTable();
        setTotal();
    });
}

//set the balance
$("#cash").on('keyup', function () {
    $("#balance").val(($("#cash").val()) - ($("#sub-total").text()));
    $('#cash').css(
        'border',
        'solid 2px #ced4da'
    );
    $("#credit-error").text('');
});

//calculate discount
$("#discount").on('keyup', function () {
    let discount = $("#discount").val();
    if (regExDiscount.test(discount)) {
        let price = +$("#sub-total").text() - +$("#sub-total").text() * (discount / 100);
        $("#sub-total").text(price);
        $("#balance").val(($("#cash").val()) - ($("#sub-total").text()));
        $('#discount').css('border', '3px solid green');
        $('#discount-error').text('');
    } else {
        $('#discount').css('border', '3px solid red');
        $('#discount-error').text('Wrong value entered as discount');
        $("#balance").val('');
        setTotal();
    }
});

//place order
$("#btn-purchase").on("click", function () {
    let sub = $("#sub-total").text();
    let cash = $("#cash").val();
    if (+sub > +cash) {
        $('#cash').css('border', '3px solid red');
        $("#credit-error").text('Insufficient Cerdit');
        swal("Insufficient Credit....Check the cash", "warning");
    } else {
        $('#cash').css(
            'border',
            'solid 2px #ced4da'
        );
        $("#credit-error").text('');


        var oId=$("#orderID").val();
        var cusId = $("#customer").val();
        var date = $("#date").val();
        var discount = $("#discount").val();
        var total = $("#total").text();

        var order={
            "oId":oId,
            "cusId":cusId,
            "date":date,
            "discount":discount,
            "total":total,
            "cart":cartArray
        }

        $.ajax({
            url: "http://localhost:8080/BackEnd_Web_exploded/order",
            method: "POST",
            contentType:"application/json",
            data: JSON.stringify(order),
            success: function (res) {
                if (res.status == 200) {

                    //alert(res.message);
                    alert("Order Placed Successfully", "success");
                    clearOrderDetail();
                    //createAutoID();
                }
            },
            error: function (ob, textStatus, error) {
                alert(textStatus);
            }
        });
    }
});

//clear data
function clearOrderDetail() {
    $('#orderID,#customer,#name,#address,#telNum,#item,#itemName,#quantityOnHand,#price,#cash,#discount,#balance').val('');
    $('#total,#sub-total').text('');
    $('#orderID,#customer,#name,#address,#telNum,#item,#itemName,#quantityOnHand,#price,#discount,#quantity').css(
        'border',
        'solid 2px #ced4da'
    );
    cartArray = [];
    loadCartDetailsToTable();
    $('#customer-error,#customer-name-error,#customer-address-error,#number-error,#item-error,#itemName-error,#qty-error,#price-error').text('');
}

//search order
// $('#order-search').keydown(function (event) {
//     if (event.key == 'Control') {
//         var oId = $('#order-search').val();
//         var responseId = searchOrder(oId);
//         if (responseId) {
//             var responseCustomer = searchCustomer(responseId.getCusId());
//             $('#customer').val(responseId.getCusId());
//             $('#name').val(responseCustomer.getCustomerName());
//             $('#address').val(responseCustomer.getCustomerAddress());
//             $('#telNum').val(responseCustomer.getCustomerTeleNumber());
//             $('#total').text(responseId.getCost());
//         } else {
//             swal("No such an order", "info");
//         }
//     }
// });

// function searchOrder(id) {
//     for (let i = 0; i < orderArray.length; i++) {
//         if (orderArray[i].getOrderID() == id) {
//             return orderArray[i];
//         }
//     }
// }


//validate customer details
$('#customer,#name,#address,#telNum').on('keyup', function () {
    validateCustomerFeild();
});

function validateCustomerFeild() {
    let id = $('#customer').val();
    if (regExCusID.test(id)) {
        $('#customer').css('border', '3px solid green');
        $('#customer-error').text('');
        let name = $('#name').val();
        if (regExCusName.test(name)) {
            $('#name').css('border', '3px solid green');
            $('#customer-name-error').text('');
            let address = $('#address').val();
            if (regExCusAddress.test(address)) {
                $('#address').css('border', '3px solid green');
                $('#cus-address-error').text('');
                let teleNum = $('#telNum').val();
                if (regExTeleNumber.test(teleNum)) {
                    $('#telNum').css('border', '3px solid green');
                    $('#number-error').text('');
                } else {
                    $('#telNum').css('border', '3px solid red');
                    $('#number-error').text('Wrong... format : 000-0000000');
                }
            } else {
                $('#address').css('border', '3px solid red');
                $('#cus-address-error').text('Wrong... format : Minimum 7');
            }
        } else {
            $('#name').css('border', '3px solid red');
            $('#customer-name-error').text(
                'Wrong... format :Minimum 3,Max 50 Space Allowed'
            );
        }
    } else {
        $('#customer').css('border', '3px solid red');
        $('#customer-error').text('Wrong... format :C00-000');
    }
}

//validate item details
$('#item,#itemName,#price,#quantityOnHand').on('keyup', function () {
    validateItemField();
});

function validateItemField() {
    let code = $('#item').val();
    if (regExItemCode.test(code)) {
        $('#item').css('border', '3px solid green');
        $('#item-error').text('');
        let name = $('#itemName').val();
        if (regExItemName.test(name)) {
            $('#itemName').css('border', '3px solid green');
            $('#itemName-error').text('');
            let quantity = $('#quantityOnHand').val();
            if (regExItemQuantity.test(quantity)) {
                $('#quantityOnHand').css('border', '3px solid green');
                $('#qty-error').text('');
                let price = $('#price').val();
                if (regExItemPrice.test(price)) {
                    $('#price').css('border', '3px solid green');
                    $('#price-error').text('');
                } else {
                    $('#price').css('border', '3px solid red');
                    $('#price-error').text('Wrong... format : 0.00');
                }
            } else {
                $('#quantityOnHand').css('border', '3px solid red');
                $('#qty-error').text('Wrong... format : Only Numbers');
            }
        } else {
            $('#itemName').css('border', '3px solid red');
            $('#itemName-error').text(
                'Wrong... format :Minimum 3,Max 50 Space Allowed'
            );
        }
    } else {
        $('#item').css('border', '3px solid red');
        $('#item-error').text('Wrong... format :I00-000');
    }
}

//validate order id and order search field
const regExDiscount = /^[1-9][.]?[0-9]?[.]?([0-9]{0,2})?$/;
const regExOrderID = /^(O00-)[0-9]{3,4}$/;

$("#orderID").on('keyup', function () {
    validateOrderID();
});


$("#order-search").on('keyup', function () {
    validateOrderSearch();
});

function validateOrderID() {
    if (regExOrderID.test($("#orderID").val())) {
        $('#orderID').css('border', '3px solid green');
        $('#order-error').text('');
    } else {
        $('#orderID').css('border', '3px solid red');
        $('#order-error').text("Wrong... format :O00-000");
    }
}

function validateOrderSearch() {
    if (regExOrderID.test($("#order-search").val())) {
        $('#order-search').css('border', '3px solid green');
        $('#order-search-error').text('');
    } else {
        $('#order-search').css('border', '3px solid red');
        $('#order-search-error').text("Wrong... format :I00-000");
    }
}







