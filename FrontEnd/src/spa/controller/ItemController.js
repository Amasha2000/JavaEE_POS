//--------------------------------------------Save Item-------------------------------------------------------------
$('#button-save-item').click(function () {
    saveItem();
});

function saveItem() {
    $('#item-table tbody tr').off();

    if (confirm('Do you want to Save Item Details?')) {
        let itemCode = $('#item-code').val();
        let itemName = $('#item-name').val();
        let quantity = $('#item-quantity').val();
        let price = $('#item-price').val();

        var response = searchItem(itemCode);

        if (response != undefined) {
            swal("The Item already exists", "warning");
            //clear Input Fields
            clearAllItemDetails();
        } else {

            var itemObject = new ItemDTO(itemCode, itemName, quantity, price);

            itemArray.push(itemObject);

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

        var code = $("#item-code").val();
        var response = searchItem(code);

        if (response != undefined) {
            response.setItemCode($("#item-code").val());
            response.setItemName($("#item-name").val());
            response.setQuantity($("#item-quantity").val());
            response.setPrice($("#item-price").val());

            //Load Item Details To Table
            loadItemDetailsToTable();

            //clear Input Fields
            clearAllItemDetails();

            //Load Table Details To Input Fields
            loadItemDetailsToInputFields();

            //Remove Table Details when double click the row
            removeItemTableRows();
        } else {
            swal("Add Item Details To Update!!!", "warning");
            clearAllItemDetails();
        }
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
    let response = searchItem($("#item-code").val());
    if (confirm('Do you want to Delete Item Details?')) {
        if (response != undefined) {
            var index = itemArray.indexOf(response);
            itemArray.splice(index, 1);
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
        } else {
            swal("Add Item Details To Delete!!!", "warning");
            clearAllItemDetails();
        }
    } else {
        //Do Nothing
        clearAllItemDetails();
    }
}

//---------------------------------------------Search item----------------------------------------------------------
$('#item-search-button').on('click', function () {
    var itemCode = $('#item-search').val();
    var responseSearchItem = searchItem(itemCode);
    if (responseSearchItem) {
        $('#item-code').val(responseSearchItem.getItemCode());
        $('#item-name').val(responseSearchItem.getItemName());
        $('#item-quantity').val(responseSearchItem.getQuantity());
        $('#item-price').val(responseSearchItem.getPrice());
        $('#item-search').val('');

        //Load Table Details To Input Fields
        loadItemDetailsToInputFields();

        //Remove Table Details when double click the row
        removeItemTableRows();
    } else {
        $('#item-search').val('');
        swal('No such a Item', "info");
    }
});

function searchItem(code) {
    for (let i = 0; i < itemArray.length; i++) {
        if (itemArray[i].getItemCode() == code) {
            return itemArray[i];
        }
    }
}

//-----------------------------------------load item details to table-----------------------------------------------
function loadItemDetailsToTable() {
    $('#item-table tbody').empty();
    for (var i of itemArray) {
        $('#item-table tbody').append(
            `<tr><td>${i.getItemCode()}</td><td>${i.getItemName()}</td><td>${i.getQuantity()}</td><td>${i.getPrice()}</td></tr>`
        );
    }
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
    loadItemDetailsToTable();
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
        var responseId = searchItem(code);
        $('#item-code').val(responseId.getItemCode());
        $('#item-name').val(responseId.getItemName());
        $('#item-quantity').val(responseId.getQuantity());
        $('#item-price').val(responseId.getPrice());
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
