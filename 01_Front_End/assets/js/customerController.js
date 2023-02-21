$('#register-form-date').val(today);
$('#customer-home-pickup').val(today);
$('#customer-home-return').val(tomorrow);

var customer;
var customer_nic;

$('#registerNowBtn').click(function (){
    registerCustomer();
})

function registerCustomer() {
    // var data = new FormData();

    // let nicImage = $("#register-form-NIC-image")[0].files[0].name;
    // let nicFileName = nicImage.name;
    //
    //
    // data.append("file",nicImage);
    //
    // let CustomerDTO = {
    //      nic:$("#register-form-nic").val(),
    //     address : $("#register-form-address").val(),
    //     contactNumber: $("#register-form-mobile").val(),
    //     name: $("#register-form-name").val(),
    //     email: $("#register-form-email").val(),
    //     drivingLicenseNo: $("#register-form-drivingNo").val(),
    //     password: $("#register-form-password").val(),
    //     user_name: $("#register-form-user-name").val(),
    //     imageLocation: nicFileName,
    // }
    //
    //
    //
    // $.ajax({
    //     url: baseUrl +"customer",
    //     method: 'post',
    //      async: true,
    //     // contentType: false,
    //      processData: false,
    //     data: JSON.stringify(CustomerDTO),
    //     contentType: "application/json",
    //
    //         success: function (resp){
    //             alert(resp.message);
    //             if (resp.data==null){
    //                 openCustomerHome(resp.data);
    //
    //             }
    //         },
    //         error: function (err) {
    //             console.log(err);
    //         }
    //
    // })
    // cleanRegisterForm();


    let nicFileName = $("#register-form-NIC-image")[0].files[0].name;

    // let nicFileName = nicImage.name;
    // data.append("file",nicImage);

    let nic =  $("#register-form-nic").val();
    let address = $("#register-form-address").val();
    let contactNumber =$("#register-form-mobile").val();
    let name= $("#register-form-name").val();
    let drivingLicenseNo= $("#register-form-drivingNo").val();
    let email= $("#register-form-email").val();
    let password =$("#register-form-password").val();
    let user_name= $("#register-form-user-name").val();


    var newDetails = {
        nic: nic,
        address: address,
        contactNumber: contactNumber,
        name: name,
        drivingLicenseNo: drivingLicenseNo,
        email: email,
        password:password,
        user_name:user_name,
        imageLocation: nicFileName
    }

    $.ajax({
        url: baseUrl + "customer",
        method: 'post',
        // async: true,
        contentType: "application/json",
        // processData: false,
        data: JSON.stringify(newDetails),
        success: function (resp) {
            alert(resp.message);
            loadImage();
        },
        error: function (err) {
            console.log(err);
        }
    });
    cleanRegisterForm();
}

function cleanRegisterForm() {
    $('#register-form-name,#register-form-nic,#register-form-email,#register-form-mobile, #register-form-address,#register-form-user-name ,#register-form-password, #register-form-NIC-image, #register-form-License-image,#register-form-drivingNo').css({
        border: '1px solid gray',
    })
    $('#register-form-name,#register-form-nic,#register-form-email,#register-form-mobile, #register-form-address,#register-form-user-name ,#register-form-password, #register-form-NIC-image, #register-form-License-image,#register-form-drivingNo').val("")

}

function openCustomerHome(data){
    $("#registerForm").css("display", "none")

    $("#customer").css("display", "block")
    $("#customerNavbar").css("display", "block")

    $("#customer-profile-nic").val(data.nic)
    $("#customer-profile-name").val(data.user_name)
    $("#customer-profile-email").val(data.email)
    $("#customer-profile-address").val(data.address)
    $("#customer-profile-mobile").val(data.mobile)
}

function loadImage(){
    var data = new FormData();

    let file = $("#register-form-NIC-image")[0].files[0];
    let fileName = $("#register-form-NIC-image")[0].files[0].name;
    data.append("myFile", file, fileName);

    $.ajax({
        url: baseUrl + "api/v1/upload",
        method: 'post',
        async: true,
        contentType: false,
        processData: false,
        data: data,
        success: function (resp) {
            alert("Successfully Uploaded");
            //loadTheLastUploadedImage();
        },
        error: function (err) {
            console.log(err);
        }
    });
}


function loadAllCustomers(){
    $("#admin-customer-table").empty();

    $.ajax({
        url: baseUrl + "customer",
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

$("#customer-updateBtn").click(function (){
    updateCustomer();
})

function updateCustomer(){

    var newDetails = {
        nic: $("#customer-profile-nic").val(),
        address: $("#customer-profile-address").val(),
        contactNumber: $("#customer-profile-mobile").val(),
        name: $("#customer-profile-name").val(),
        email: $("#customer-profile-email").val(),
        password: customer.password,
        user_name: customer.user_name,
        imageLocation: customer.imageLocation,
    }

    $.ajax({
        url: baseUrl + "customer",
        method: "PUT",
        contentType: "application/json",
        data: JSON.stringify(newDetails),
        success: function (res) {
            if (res.status === 200) {
                alert(res.message)
            } else {
                alert("Cant update your Details in this moment")
            }
        },
        error: function (ob) {
            console.log(ob.responseJSON.message);
        }
    });
}


// let Customer={
//     nic: nic,
//     address: address,
//     contactNumber: contactNumber,
//     drivingLicenseNumber: drivingLicenseNumber,
//     email: email,
//     imageLocation: nicFileName,
//     name: name,
//     password: password,
//     user_name: user_name
// }

// data.append("customer",new Blob([JSON.stringify(CustomerDTO)]))

// $.ajax({
//     url: baseUrl +"customer",
//     method: 'post',
//     async: true,
//     contentType: false,
//     processData: false,
//     data: data,
//
//     success: function (resp){
//         alert(resp.message);
//         if (resp.data==null){
//             openCustomerHome(resp.data);
//
//         }
//     },
//     error: function (err) {
//         console.log(err);
//
//     }
// });
//
// cleanRegisterForm();
