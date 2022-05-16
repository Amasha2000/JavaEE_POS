//--------------------------------------------Save Customer-------------------------------------------------------------
$('#button-save-customer').click(function () {
    saveCustomer();
});

function saveCustomer() {
    $('#customer-table tbody tr').off();

    if (confirm('Do you want to Save Customer Details?')) {
        let customerId = $('#cus-id').val();
        let customerName = $('#cus-name').val();
        let customerAddress = $('#cus-address').val();
        let customerTeleNum = $('#tel-num').val();

        var response = searchCustomer(customerId);

        if (response != undefined) {
            swal("The Customer already exists", "warning");
            //clear Input Fields
            clearAll();

        } else {

            var customerObject = new CustomerDTO(customerId, customerName, customerAddress, customerTeleNum);

            customerArray.push(customerObject);

            //Load Customer Details To Table
            loadCustomerDetailsToTable();

            //clear Input Fields
            clearAll();

            //Load Table Details To Input Fields
            loadCustomerDetailsToInputFields();

            //Remove Table Details when double click the row
            removeTableRows();

            //populate customer drop down list
            populateCustomerDropDown();

        }
    } else {
        //clear Input Fields
        clearAll();
    }
}

//-----------------------------------------Update Customer Details------------------------------------------------------
$('#button-update-customer').click(function () {
    updateCustomer();
});

function updateCustomer() {
    if (confirm('Do you want to Update Customer Details?')) {

        var id = $("#cus-id").val();
        var response = searchCustomer(id);

        if (response != undefined) {
            response.setCustomerId($("#cus-id").val());
            response.setCustomerName($("#cus-name").val());
            response.setCustomerAddress($("#cus-address").val());
            response.setCustomerTeleNumber($("#tel-num").val());

            //Load Customer Details To Table
            loadCustomerDetailsToTable();

            //clear Input Fields
            clearAll();

            //Load Table Details To Input Fields
            loadCustomerDetailsToInputFields();

            //Remove Table Details when double click the row
            removeTableRows();
        } else {
            swal("Add Customer Details To Update!!!", "warning");
            clearAll();
        }
    } else {
        //Do Nothing
        clearAll();
    }
}

//-------------------------------------------------Delete Customer------------------------------------------------------
$('#button-delete-customer').click(function () {
    deleteCustomer();
});

function deleteCustomer() {
    let response = searchCustomer($("#cus-id").val());
    if (confirm('Do you want to Delete Customer Details?')) {
        if (response != undefined) {
            var index = customerArray.indexOf(response);
            customerArray.splice(index, 1);
            //Load Customer Details To Table
            loadCustomerDetailsToTable();

            //clear Input Fields
            clearAll();

            //Load Table Details To Input Fields
            loadCustomerDetailsToInputFields();

            //Remove Table Details when double click the row
            removeTableRows();

            //populate customer drop down list
            populateCustomerDropDown();
        } else {
            swal("Add Customer Details To Delete!!!", "warning");
            clearAll();
        }
    } else {
        //Do Nothing
        clearAll();
    }
}

//---------------------------------------------Search customer----------------------------------------------------------
$('#customer-search-button').on('click', function () {
    var cusId = $('#cus-search').val();
    var responseSearchCustomer = searchCustomer(cusId);
    if (responseSearchCustomer) {
        $('#cus-id').val(responseSearchCustomer.getCustomerId());
        $('#cus-name').val(responseSearchCustomer.getCustomerName());
        $('#cus-address').val(responseSearchCustomer.getCustomerAddress());
        $('#tel-num').val(responseSearchCustomer.getCustomerTeleNumber());
        $('#cus-search').val('');

        //Load Table Details To Input Fields
        loadCustomerDetailsToInputFields();

        //Remove Table Details when double click the row
        removeTableRows();
    } else {
        $('#cus-search').val('');
        swal('No such a Customer', "info");
    }
});

function searchCustomer(id) {
    for (let i = 0; i < customerArray.length; i++) {
        if (customerArray[i].getCustomerId() == id) {
            return customerArray[i];
        }
    }
}

//-----------------------------------------load customer details to table-----------------------------------------------
function loadCustomerDetailsToTable() {
    $('#customer-table tbody').empty();
    for (var i of customerArray) {
        $('#customer-table tbody').append(
            `<tr><td>${i.getCustomerId()}</td><td>${i.getCustomerName()}</td><td>${i.getCustomerAddress()}</td><td>${i.getCustomerTeleNumber()}</td></tr>`
        );
    }
}


//------------------------------------------Clear Input Fields----------------------------------------------------------
$('#button-clear-customer').click(function () {
    clearAll();
});

function clearAll() {
    $('#cus-id,#cus-name,#cus-address,#tel-num').val('');
    $('#cus-id,#cus-name,#cus-address,#tel-num').css(
        'border',
        'solid 2px #ced4da'
    );

    $('#cus-id').focus();
    $('#button-save-customer').attr('disabled', true);
    loadCustomerDetailsToTable();
    $('#id-error,#name-error,#address-error,#tele-error').text('');

    //Load Table Details To Input Fields
    loadCustomerDetailsToInputFields();

    //Remove Table Details when double click the row
    removeTableRows();
}

//------------------------------Load Table Details To Input Fields------------------------------------------------------
function loadCustomerDetailsToInputFields() {
    $('#customer-table tbody tr').click(function () {
        $('#cus-id').val($(this).children(':nth-child(1)').text());
        $('#cus-name').val($(this).children(':nth-child(2)').text());
        $('#cus-address').val($(this).children(':nth-child(3)').text());
        $('#tel-num').val($(this).children(':nth-child(4)').text());
    });
}

//--------------------------Remove Table Details when double click the row----------------------------------------------
function removeTableRows() {
    $('#customer-table tbody tr').dblclick(function () {
        $(this).remove();
    });
}

//------------------------------------Validate Customer Input Fields----------------------------------------------------
const regExCusID = /^(C00-)[0-9]{3,4}$/;
const regExCusName = /^([A-z]+([\ A-z]+)*){3,50}$/;
const regExCusAddress = /^[A-z0-9'\/\.\-\s\,]{7,75}$/;
const regExTeleNumber = /^[0-9]{3}[-][0-9]{7}$/;


$('#cus-id,#cus-name,#cus-address,#tel-num').on('keyup', function () {
    validateCustomer();
});

function validateCustomer() {
    let id = $('#cus-id').val();
    if (regExCusID.test(id)) {
        $('#cus-id').css('border', '3px solid green');
        $('#id-error').text('');
        let name = $('#cus-name').val();
        if (regExCusName.test(name)) {
            $('#cus-name').css('border', '3px solid green');
            $('#name-error').text('');
            let address = $('#cus-address').val();
            if (regExCusAddress.test(address)) {
                $('#cus-address').css('border', '3px solid green');
                $('#address-error').text('');
                let teleNum = $('#tel-num').val();
                if (regExTeleNumber.test(teleNum)) {
                    $('#tel-num').css('border', '3px solid green');
                    $('#tele-error').text('');
                    customerValidation(true);
                } else {
                    $('#tel-num').css('border', '3px solid red');
                    $('#tele-error').text('Wrong... format : 000-0000000');
                    customerValidation(false);
                }
            } else {
                $('#cus-address').css('border', '3px solid red');
                $('#address-error').text('Wrong... format : Minimum 7');
                customerValidation(false);
            }
        } else {
            $('#cus-name').css('border', '3px solid red');
            $('#name-error').text(
                'Wrong... format :Minimum 3,Max 50 Space Allowed'
            );
            customerValidation(false);
        }
    } else {
        $('#cus-id').css('border', '3px solid red');
        $('#id-error').text('Wrong... format :C00-000');
        customerValidation(false);
    }
}


//-----------------------------------Enable or Disable save button------------------------------------------------------
$('#button-save-customer').attr('disabled', true);

function customerValidation(value) {
    if (value) {
        $('#button-save-customer').attr('disabled', false);
    } else {
        $('#button-save-customer').attr('disabled', true);
    }
}


//--------------------------------------- Prevent focusing by Tab-------------------------------------------------------
$('#cus-id,#cus-name,#cus-address,#tel-num').on(
    'keydown',
    function (event) {
        if (event.key == 'Tab') {
            event.preventDefault();
        }
    }
);

//-----------------------------------------Focus next input fields------------------------------------------------------
$('#cus-id').keydown(function (event) {
    if (event.key == 'Enter') {
        $('#cus-name').focus();
    }

    if (event.key == 'Control') {
        var cusId = $('#cus-id').val();
        var responseId = searchCustomer(cusId);
        $('#cus-id').val(responseId.getCustomerId());
        $('#cus-name').val(responseId.getCustomerName());
        $('#cus-address').val(responseId.getCustomerAddress());
        $('#tel-num').val(responseId.getCustomerTeleNumber());
    }
});

$('#cus-name').keydown(function (event) {
    if (event.key == 'Enter') {
        $('#cus-address').focus();
    }
});

$('#cus-address').keydown(function (event) {
    if (event.key == 'Enter') {
        $('#tel-num').focus();
    }
});

//----------------------Load Customer Details To Table when press the Enter Key-----------------------------------------
$('#tel-num').keydown(function (event) {
    $('#customer-table tbody tr').off();
    if (event.key == 'Enter') {
        saveCustomer();
    }
});
  