loadCustomerDetailsToTable();

//--------------------------------------------Save Customer-------------------------------------------------------------
$('#button-save-customer').click(function () {
    saveCustomer();
});

function saveCustomer() {
    $('#customer-table tbody tr').off();

    if (confirm('Do you want to Save Customer Details?')) {
        //  searchCustomer($("#cus-id"));
        //
        // if (responseSearchCustomer) {
        //     alert("The Customer already exists", "warning");
        //     //clear Input Fields
        //     clearAll();
        //
        //  } else {

            var customerForm = $("#customerForm").serialize();

            $.ajax({
                url: "http://localhost:8080/BackEnd_Web_exploded/customer",
                method: "POST",
                data: customerForm,
                success: function (res) {
                    if (res.status == 200) {

                        alert(res.message);

                        // Load Customer Details To Table
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
                },
                error: function (ob, textStatus, error) {
                    alert(textStatus);
                }
            });

        //}
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

       // var id = $("#cus-id").val();
       // var response = searchCustomer(id);

        // if (response != undefined) {
        var customer={
            id:$("#cus-id").val(),
            name:$("#cus-name").val(),
            address:$("#cus-address").val(),
            teleNumber:$("#tel-num").val()
        }
            $.ajax({
               url:"http://localhost:8080/BackEnd_Web_exploded/customer",
               method:"PUT",
               contentType:"application.json",
               data:JSON.stringify(customer),
               success:function (res) {
                   if(res.status==200){
                       alert(res.message);
                       //Load Customer Details To Table
                       loadCustomerDetailsToTable();

                       //clear Input Fields
                       clearAll();

                       //Load Table Details To Input Fields
                       loadCustomerDetailsToInputFields();

                       //Remove Table Details when double click the row
                       removeTableRows();
                   }else if(res.status==400){
                       alert(res.message);
                   }else{
                       alert(res.data)
                   }
               },
               error:function (ob,status,t){
                   alert(ob);
               }

            });

        // } else {
        //     swal("Add Customer Details To Update!!!", "warning");
        //     clearAll();
        // }
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
    // let response = searchCustomer($("#cus-id").val());
    if (confirm('Do you want to Delete Customer Details?')) {
        // if (response != undefined) {
           var cusId=$("#cus-id").val();
           $.ajax({
               url:"http://localhost:8080/BackEnd_Web_exploded/customer?CustomerID="+cusId,
               method:"DELETE",
               success:function (res){
                   if(res.status==200){
                       alert(res.message);
                       //Load Customer Details To Table
                       loadCustomerDetailsToTable();

                       //clear Input Fields
                       clearAll();

                       //Load Table Details To Input Fields
                       loadCustomerDetailsToInputFields();

                       //Remove Table Details when double click the row
                       removeTableRows();

                      // populate customer drop down list
                       populateCustomerDropDown();
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
        //     swal("Add Customer Details To Delete!!!", "warning");
        //     clearAll();
        // }
    } else {
        //Do Nothing
        clearAll();
    }
}

//---------------------------------------------Search customer----------------------------------------------------------
var responseSearchCustomer=true;
$('#customer-search-button').on('click', function () {
    var cusId = $('#cus-search').val();
    searchCustomer(cusId);
    console.log(responseSearchCustomer);

    if (responseSearchCustomer) {
        alert("Done");
    }else{
        $('#cus-search').val('');
        alert('No such a Customer', "info");
    }
});

function searchCustomer(id) {
    $.ajax({
        url: "http://localhost:8080/BackEnd_Web_exploded/customer?option=SEARCH&CustomerID="+id,
        method:"GET",
        success:function (resp){
            if (resp.status == 200) {
                for (var i = 0; i < resp.data.length; i++) {
                    $('#cus-id').val(resp.data[i].id);
                    $('#cus-name').val(resp.data[i].name);
                    $('#cus-address').val(resp.data[i].address);
                    $('#tel-num').val(resp.data[i].teleNumber);
                }

                $('#cus-search').val('');

                //Load Table Details To Input Fields
                loadCustomerDetailsToInputFields();

                //Remove Table Details when double click the row
                removeTableRows();

                responseSearchCustomer = true;
            }

        },
        error:function(ob,state){
            console.log(ob,state);
            responseSearchCustomer=false;
        }
    });
}

//-----------------------------------------load customer details to table-----------------------------------------------
function loadCustomerDetailsToTable() {
    $('#customer-table tbody').empty();
    $.ajax({
        url:"http://localhost:8080/BackEnd_Web_exploded/customer?option=GETALL",
        method:"GET",
        success:function (resp){
            for (var i=0;i<resp.data.length;i++) {
                $('#customer-table tbody').append(
                    `<tr><td>${resp.data[i].id}</td><td>${resp.data[i].name}</td><td>${resp.data[i].address}</td><td>${resp.data[i].teleNumber}</td></tr>`
                );
            }
            loadCustomerDetailsToInputFields();
        },
        error:function(ob,state){
            console.log(ob,state);
        }
    });
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


// //-----------------------------------Enable or Disable save button------------------------------------------------------
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
        searchCustomer(cusId);
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
  