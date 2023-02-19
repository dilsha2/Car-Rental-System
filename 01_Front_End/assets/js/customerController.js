$('#register-form-date').val(today);
$('#customer-home-pickup').val(today);
$('#customer-home-return').val(tomorrow);

var customer;
var customer_nic;

$('#registerNowBtn').click(function (){
    // registerFormValidation();
})

function registerCustomer() {
    var data = new FormData();

    let nic = $("#register-form-NIC-image")[0].file[0];
    let nicFileName = nic.name;


    data.append("file",nic);

    let CustomerDTO = {
        nic: $("#register-form-nic").val(),
        user_name: $("#register-form-user-name"),
        password: $("#register-form-password").val(),
        customer_name: $("#register-form-name").val(),
        address: $("#register-form-address").val(),
        mobile: $("#register-form-mobile").valueOf(),
        email: $("#register-form-email").val(),
        register_date: $("#register-form-date").val(),
        nic_img: nicFileName,
    }

    data.append("customer",new Blob([JSON.stringify(CustomerDTO)]))

    $.ajax({
        url: baseUrl +"customer",
        method: 'post',
        async: true,
        contentType: false,
        processData: false,
        data: data,

        success: function (resp){
            alert(resp.message);
            if (resp.data==null){
                openCustomerHome(resp.data);
                getAvailableCar();
            }
        },
        error: function (err) {
            console.log(err);

        }
    });

    cleanRegisterForm();
}

function cleanRegisterForm() {
    $('#register-form-name,#register-form-nic,#register-form-email,#register-form-mobile, #register-form-address,#register-form-user-name ,#register-form-password, #register-form-NIC-image, #register-form-License-image').css({
        border: '1px solid gray',
    })
    $('#register-form-name,#register-form-nic,#register-form-email,#register-form-mobile, #register-form-address,#register-form-user-name ,#register-form-password, #register-form-NIC-image, #register-form-License-image').val("")

}

function openCustomerHome(){
    $("#registerForm").css("display", "none")

    $("#customer").css("display", "block")
    $("#customerNavbar").css("display", "block")

    $("#customer-profile-nic").val(data.nic)
    $("#customer-profile-name").val(data.user_name)
    $("#customer-profile-email").val(data.email)
    $("#customer-profile-address").val(data.address)
    $("#customer-profile-mobile").val(data.mobile)
}

function loadAllCustomers(){
    $("#admin-customer-table").empty();

    $.ajax({
        url: baseUrl + "controller/customer",
        method: "GET",
        success: function (resp){
            for (const customer of resp.data){
                let row = `<tr><td>${customer.name}</td><td>${customer.nic}</td><td>${customer.address}</td><td>${customer.mobile}</td><td>${customer.email}</td><td>${customer.image}</td></tr>`;
                $("#admin-customer-table").append(row);

                $("#admin-customer-table>tr").off("click");
                $("#admin-customer-table>tr").click(function (){
                    customer_nic = $(this).children(":eq(0)").text();
                    $("#admin-customer-viewBtn").prop('disabled',false);
                });
            }
        }
    })
}


