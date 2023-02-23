//Set Current Date

var now = new Date();
var day = ("0" + now.getDate()).slice(-2);
var month = ("0" + (now.getMonth() + 1)).slice(-2);
var today = now.getFullYear() + "-" + (month) + "-" + (day);
var tomorrow = now.getFullYear() + "-" + (month) + "-" + ("0"+((+day) + (+1))).slice(-2);

// Navigation
// loadTodayAvailableCars();

//Login Page
$("#loginFormBtn").click(function (){
    listNo=0;

    $("#landingPage").css('display','none')
    $("#landingNavbar").css('display','none')

    $("#loginPage").css('display','block')
})


//----------------------user Login
$("#loginUserBtn").click(function () {

    let userDTO = {
        user_name: $("#login-page-user-name").val(),
        password: $("#login-page-password").val()
    }

    $.ajax({
        url: "http://localhost:8080/02_Back_End_war/login",
        method: "POST",
        contentType: "application/json",
        data: JSON.stringify(userDTO),
        success: function (res) {
            if (res.code === 200) {
                if (res.message === ("Customer")) {
                    customerLogin(res.data)
                } else if (res.message === ("Driver")) {
                    driverLogin(res.data)
                } else if (res.message === ("Admin")) {
                    adminLogin(res.data)
                } else {
                    alert(res.message)
                }
            }
            $("#login-page-user-name").val("")
            $("#login-page-password").val("")
        },
        error: function (ob) {
            console.log(ob.responseJSON.message);
        }
    });
})

//---------Customer Login
function customerLogin(data) {
    customer = data
    $("#loginPage").css("display", "none")
    $("#customer").css("display", "block")
    $("#customerNavbar").css("display", "block")

    $("#customer-profile-nic").val(data.nic)
    $("#customer-profile-name").val(data.name)
    $("#customer-profile-email").val(data.email)
    $("#customer-profile-address").val(data.address)
    $("#customer-profile-mobile").val(data.mobile)

    //getAvailableCar();
    //clearAllReservationDetails()

}

//---------Driver Login
function driverLogin(data) {
    $("#loginPage").css("display", "none")
    $("#driverNavBar").css("display", "block")
    $("#driver").css("display", "block")

    //loadDriverSchedule(data);

}

//---------admin Login
function adminLogin(data) {
    $("#loginPage").css("display", "none")
    $("#admin").css("display", "block")

    $("#adminDailySummary").css("display", "block")
    $("#adminCars").css("display", "none")
    $("#adminReservation").css("display", "none")
    $("#adminDrivers").css("display", "none")
    $("#adminCustomer").css("display", "none")
    $("#adminPayments").css("display", "none")

    //loadDailySummary();

}



//Register Page
$(".getStartBtn").click(function (){
    $("#landingPage").css('display','none')
    $("#landingNavbar").css('display','none')
    $("#loginPage").css('display','none')

    $("#registerForm").css('display','block')
})

// Back to home in register page and login form
$(".backToHomeBtn").click(function (){
    $("#landingPage").css('display','block')
    $("#landingNavbar").css('display','block')

    $("#loginPage").css('display','none')
    $("#registerForm").css('display','none')
})

//--------------------customer page navigation

//-home
$("#home-btn").click(function (){
    $("#customerReservation").css("display","none");
    $("#customerProfile").css("display","none");

    $("#customerHome").css("display","block");
    $("#customer-nav-bar").css("display","block")
})

//-mystore
$("#myStore-btn").click(function () {
    $("#customerProfile").css("display", "none")
    $("#customerHome").css("display", "none")
    $("#customerReservation").css("display", "block")
    $("#customer-nav-bar").css("display","block")

    //loadUpcomingReservation();
})

//---------------customer Profile navigations------------------

$("#customerInformationBtn").click(function () {
    $("#customerProfileChangePassword").css("display", "none")

    $("#customerProfileUpdateDetail").css("display", "block")
})

$("#customerChangePasswordBtn").click(function () {
    $("#customerProfileChangePassword").css("display", "block")

    $("#customerProfileUpdateDetail").css("display", "none")
})


// /---------------admin profile navigations------------------------------------

//---Account
$("#myAccount-btn").click(function () {
    $("#customerHome").css("display", "none")
    $("#customerReservation").css("display", "none")

    $("#customerProfile").css("display", "block")
})

//--Dashboard
$("#adminDashboardBtn").click(function () {

    $("#adminDailySummary").css("display", "inline-flex")

    $("#adminReservation").css("display", "none")
    $("#adminCars").css("display", "none")
    $("#adminDrivers").css("display", "none")
    $("#adminCustomer").css("display", "none")
    $("#adminPayments").css("display", "none")

    //loadDailySummary();
})

//--Reservation
$("#adminReservationBtn").click(function () {
    $("#adminReservation").css("display", "inline-flex")

    $("#adminDailySummary").css("display", "none")
    $("#adminCars").css("display", "none")
    $("#adminDrivers").css("display", "none")
    $("#adminCustomer").css("display", "none")
    $("#adminPayments").css("display", "none")

    $("#admin-reservation-title").css("display", "block")
    $("#admin-todayPickups-title").css("display", "none")

    $("#admin-update-reservation").css("display", "block")
    $("#admin-view-reservation").css("display", "none")

    //loadPendingReservations();
})

//--Cars
$("#adminCarsBtn").click(function () {
    $("#adminCars").css("display", "inline-flex")

    $("#adminReservation").css("display", "none")
    $("#adminDailySummary").css("display", "none")
    $("#adminDrivers").css("display", "none")
    $("#adminCustomer").css("display", "none")
    $("#adminPayments").css("display", "none")


    $("#availableBtn").css("display", "none");
    $("#unavailableBtn").css("display", "none");
    $("#maintainBtn").css("display", "none");
    $("#underMaintainBtn").css("display", "none");
    $("#viewButton").css("display", "block");


    $("#admin-all-cars-title").css("display", "block")
    $("#admin-all-unavailableCars-title").css("display", "none");
    $("#admin-all-needMaintains-title").css("display", "none");
    $("#admin-all-underMaintains-title").css("display", "none");
    $("#admin-all-availableCars-title").css("display", "none");

})

//--Customer
$("#adminCustomerBtn").click(function () {
    $("#adminCustomer").css("display", "inline-flex")

    $("#adminCars").css("display", "none")
    $("#adminReservation").css("display", "none")
    $("#adminDailySummary").css("display", "none")
    $("#adminDrivers").css("display", "none")
    $("#adminPayments").css("display", "none")

    loadAllCustomers();
})

//--Drivers
$("#adminDriversBtn").click(function () {
    $("#adminDrivers").css("display", "inline-flex")

    $("#adminCustomer").css("display", "none")
    $("#adminCars").css("display", "none")
    $("#adminReservation").css("display", "none")
    $("#adminDailySummary").css("display", "none")
    $("#adminPayments").css("display", "none")

    $("#admin-driver-schedule-table").css("display", "none")
    $("#admin-driver-table").css("display", "block")


    $("#admin-all-drivers-title").css("display", "block")
    $("#admin-all-driverSchedule-title").css("display", "none")

    $("#enableSaveDriverBtn").css("display", "block");
    $("#enableSearchDriverBtn").css("visibility", "hidden");

    loadAllDrivers();
})

//--Payment
$("#adminPaymentBtn").click(function () {
    $("#adminPayments").css("display", "inline-flex")

    $("#adminDrivers").css("display", "none")
    $("#adminCustomer").css("display", "none")
    $("#adminCars").css("display", "none")
    $("#adminReservation").css("display", "none")
    $("#adminDailySummary").css("display", "none")


})

$("#home-nextBtn").click(function () {
    if (carList.length === listNo) {
        return
    }
    $('#div-one, #div-two,#div-three').css({
        display: 'none'
    })

    loadDataToDiv()

})

$("#home-PreviousBtn").click(function () {
    if (3 >= listNo) {
        return
    }
    $('#div-one, #div-two,#div-three').css({
        display: 'none'
    })
    listNo = listNo - (displayDiv + 3)
    loadDataToDiv()
})

let divArray = ["#div-one", "#div-two", "#div-three"];

function loadDataToDiv(){
        displayDiv = 0
        for (var i = 0; listNo <= carList.length - 1; i++, listNo++, displayDiv++) {

            $("#tag").css("display", "none")
            $(divArray[i]).css("display", "block")

            if (i > 2) {
                break
            }
            let img = "#" + $(divArray[i]).children()[0].id
            let type = "#" + $(divArray[i]).children().children()[0].id;
            let brand = "#" + $(divArray[i]).children().children()[1].id;
            let daily = "#" + $(divArray[i]).children().children()[4].id
            let monthly = "#" + $(divArray[i]).children().children()[7].id

            let fuel = "#" + $("#" + $(divArray[i]).children().children()[9].id).children()[1].id;
            let transmission = "#" + $("#" + $(divArray[i]).children().children()[10].id).children()[1].id;

            $(img).attr("src", baseUrl + carList[listNo].image1.image1)
            $(type).text(carList[listNo].type)
            $(brand).text(carList[listNo].Brand)
            $(daily).text(carList[listNo].dailyRate)
            $(monthly).text(carList[listNo].monthlyRate)
            $(fuel).text(carList[listNo].fuelType)
            $(transmission).text(carList[listNo].transmissionType)
        }
}


let baseUrl = "http://localhost:8080/02_Back_End_war/"