loadItemDetailsToTable();

//--------------------------------------------Save Item-------------------------------------------------------------
$('#button-save-item').click(function () {
    saveItem();
});

function saveItem() {
    $('#item-table tbody tr').off();

    if (confirm('Do you want to Save Item Details?')) {
        // if (response != undefined) {
        //     swal("The Item already exists", "warning");
        //     //clear Input Fields
        //     clearAllItemDetails();
        // } else {

        var itemForm = $("#itemForm").serialize();

        $.ajax({
            url: "http://localhost:8080/BackEnd_Web_exploded/item",
            method: "POST",
            data: itemForm,
            success: function (res) {
                if (res.status == 200) {

                    alert(res.message);

                    //Load Item Details To Table
                    loadItemDetailsToTable();

                    //clear Input Fields
                    clearAllItemDetails();

                    //Load Table Details To Input Fields
                    loadItemDetailsToInputFields();

                    //Remove Table Details when double click the row
                    removeItemTableRows();

                    //populate item drop down list
                     populateItemDropDown();

                }
            },
            error: function (ob, textStatus, error) {
                alert(textStatus);

            }
        });

        // var response = searchItem(itemCode);

    } else {
        //clear Input Fields
        clearAllItemDetails();
    }
}

//-----------------------------------------Update Item Details------------------------------------------------------
$('#button-update-item').click(function () {
    updateItem();
});

function updateItem() {
    if (confirm('Do you want to Update Item Details?')) {

        // var code = $("#item-code").val();
        // var response = searchItem(code);
        //
        // if (response != undefined) {
            var item={
                code:$("#item-code").val(),
                itemName:$("#item-name").val(),
                quantity:$("#item-quantity").val(),
                price:$("#item-price").val()
            }

        $.ajax({
            url: "http://localhost:8080/BackEnd_Web_exploded/item",
            method: "PUT",
            contentType: "application.json",
            data: JSON.stringify(item),
            success: function (res) {
                if (res.status == 200) {
                    alert(res.message);
                    //Load Item Details To Table
                    loadItemDetailsToTable();

                    //clear Input Fields
                    clearAllItemDetails();

                    //Load Table Details To Input Fields
                    loadItemDetailsToInputFields();

                    //Remove Table Details when double click the row
                    removeItemTableRows();

                } else if (res.status == 400) {
                    alert(res.message);
                } else {
                    alert(res.data)
                }
            },
            error: function (ob, status, t) {
                alert(ob);
            }
        });

        // } else {
        //     swal("Add Item Details To Update!!!", "warning");
        //     clearAllItemDetails();

    } else {
        //Do Nothing
        clearAllItemDetails();
    }
}

//-------------------------------------------------Delete Item------------------------------------------------------
$('#button-delete-item').click(function () {
    deleteItem();
});

function deleteItem() {
    //let response = searchItem($("#item-code").val());
    if (confirm('Do you want to Delete Item Details?')) {
      //  if (response != undefined) {
           var code=$("#item-code").val();

        $.ajax({
            url:"http://localhost:8080/BackEnd_Web_exploded/item?ItemCode="+code,
            method:"DELETE",
            success:function (res){
                if(res.status==200){
                    alert(res.message);

                    //Load Item Details To Table
                    loadItemDetailsToTable();

                    //clear Input Fields
                    clearAllItemDetails();

                    //Load Table Details To Input Fields
                    loadItemDetailsToInputFields();

                    //Remove Table Details when double click the row
                    removeItemTableRows();

                    //populate item drop down list
                    populateItemDropDown();

                }else if(res.status==400){
                    alert(res.data);
                }else{
                    alert(res.data);
                }
            },
            error:function (ob,status,t){
                alert(ob);
            }
        });

        // } else {
        //     swal("Add Item Details To Delete!!!", "warning");
        //     clearAllItemDetails();
        // }
    } else {
        //Do Nothing
        clearAllItemDetails();
    }
}

//---------------------------------------------Search item----------------------------------------------------------
var responseSearchItem=true;
$('#item-search-button').on('click', function () {
    var itemCode = $('#item-search').val();
    searchItem(itemCode);
    if (responseSearchItem) {
        alert("Done");
    } else {
        $('#item-search').val('');
        swal('No such a Item', "info");
    }
});

function searchItem(code) {
    $.ajax({
        url: "http://localhost:8080/BackEnd_Web_exploded/item?option=SEARCH&ItemCode="+code,
        method:"GET",
        success:function (resp){
            if (resp.status == 200) {
                for (var i = 0; i < resp.data.length; i++) {
                    $('#item-code').val(resp.data[i].code);
                    $('#item-name').val(resp.data[i].itemName);
                    $('#item-quantity').val(resp.data[i].quantity);
                    $('#item-price').val(resp.data[i].price);
                }

                $('#item-search').val('');

                //Load Table Details To Input Fields
                loadItemDetailsToInputFields();

                //Remove Table Details when double click the row
                removeItemTableRows();

                responseSearchItem = true;
            }

        },
        error:function(ob,state){
            console.log(ob,state);
            responseSearchItem=false;
        }
    });
}

//-----------------------------------------load item details to table-----------------------------------------------
function loadItemDetailsToTable() {
    $('#item-table tbody').empty();

    $.ajax({
        url:"http://localhost:8080/BackEnd_Web_exploded/item?option=GETALL",
        method:"GET",
        success:function (resp){
            for (var i=0;i<resp.data.length;i++) {
                $('#item-table tbody').append(
                    `<tr><td>${resp.data[i].code}</td><td>${resp.data[i].itemName}</td><td>${resp.data[i].quantity}</td><td>${resp.data[i].price}</td></tr>`
                );
            }
            loadItemDetailsToInputFields();
        },
        error:function(ob,state){
            console.log(ob,state);
        }
    });
}


//------------------------------------------Clear Input Fields----------------------------------------------------------
$('#button-clear-item').click(function () {
    clearAllItemDetails();
});

function clearAllItemDetails() {
    $('#item-code,#item-name,#item-quantity,#item-price').val('');
    $('#item-code,#item-name,#item-quantity,#item-price').css(
        'border',
        'solid 2px #ced4da'
    );
    $('#item-code').focus();
    $('#button-save-item').attr('disabled', true);
    $('#code-error,#item-name-error,#item-quantity-error,#item-price-error').text('');

    //Load Table Details To Input Fields
    loadItemDetailsToInputFields();

    //Remove Table Details when double click the row
    removeItemTableRows();
}

//------------------------------Load Table Details To Input Fields------------------------------------------------------
function loadItemDetailsToInputFields() {
    $('#item-table tbody tr').click(function () {
        $('#item-code').val($(this).children(':nth-child(1)').text());
        $('#item-name').val($(this).children(':nth-child(2)').text());
        $('#item-quantity').val($(this).children(':nth-child(3)').text());
        $('#item-price').val($(this).children(':nth-child(4)').text());
    });
}

//--------------------------Remove Table Details when double click the row----------------------------------------------
function removeItemTableRows() {
    $('#item-table tbody tr').dblclick(function () {
        $(this).remove();
    });
}

//------------------------------------Validate Item Input Fields----------------------------------------------------
const regExItemCode = /^(I00-)[0-9]{3,4}$/;
const regExItemName = /^([A-z]+([\ A-z]+)*){3,50}$/;
const regExItemQuantity = /^[1-9]([0-9]{0,4})$/;
const regExItemPrice = /^(0(?!\.00)|[1-9]\d{0,6})\.\d{2}$/;


$('#item-code,#item-name,#item-quantity,#item-price').on('keyup', function () {
    validateItem();
});

function validateItem() {
    let code = $('#item-code').val();
    if (regExItemCode.test(code)) {
        $('#item-code').css('border', '3px solid green');
        $('#code-error').text('');
        let name = $('#item-name').val();
        if (regExItemName.test(name)) {
            $('#item-name').css('border', '3px solid green');
            $('#item-name-error').text('');
            let quantity = $('#item-quantity').val();
            if (regExItemQuantity.test(quantity)) {
                $('#item-quantity').css('border', '3px solid green');
                $('#item-quantity-error').text('');
                let price = $('#item-price').val();
                if (regExItemPrice.test(price)) {
                    $('#item-price').css('border', '3px solid green');
                    $('#item-price-error').text('');
                    itemValidation(true);
                } else {
                    $('#item-price').css('border', '3px solid red');
                    $('#item-price-error').text('Wrong... format : 0.00');
                    itemValidation(false);
                }
            } else {
                $('#item-quantity').css('border', '3px solid red');
                $('#item-quantity-error').text('Wrong... format : Only Numbers');
                itemValidation(false);
            }
        } else {
            $('#item-name').css('border', '3px solid red');
            $('#item-name-error').text(
                'Wrong... format :Minimum 3,Max 50 Space Allowed'
            );
            itemValidation(false);
        }
    } else {
        $('#item-code').css('border', '3px solid red');
        $('#code-error').text('Wrong... format :I00-000');
        itemValidation(false);
    }
}


//-----------------------------------Enable or Disable save button------------------------------------------------------
$('#button-save-item').attr('disabled', true);

function itemValidation(value) {
    if (value) {
        $('#button-save-item').attr('disabled', false);
    } else {
        $('#button-save-item').attr('disabled', true);
    }
}


//--------------------------------------- Prevent focusing by Tab-------------------------------------------------------
$('#item-code,#item-name,#item-quantity,#item-price').on(
    'keydown',
    function (event) {
        if (event.key == 'Tab') {
            event.preventDefault();
        }
    }
);

//-----------------------------------------Focus next input fields------------------------------------------------------
$('#item-code').keydown(function (event) {
    if (event.key == 'Enter') {
        $('#item-name').focus();
    }

    if (event.key == 'Control') {
        var code = $('#item-code').val();
        searchItem(code);
    }
});

$('#item-name').keydown(function (event) {
    if (event.key == 'Enter') {
        $('#item-quantity').focus();
    }
});

$('#item-quantity').keydown(function (event) {
    if (event.key == 'Enter') {
        $('#item-price').focus();
    }
});

//----------------------Load Item Details To Table when press the Enter Key-----------------------------------------
$('#item-price').keydown(function (event) {
    $('#item-table tbody tr').off();
    if (event.key == 'Enter') {
        saveItem();
    }
});
