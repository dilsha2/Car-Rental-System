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

//user login
$("#loginUserBtn").click(function (){

    let userDTO = {
        user_name: $("#login-page-user-name").val(),
        password: $("#login-page-password").val()
    }

    $.ajax({

    })
})

// /---------------admin profile navigations
//--Dashboard
$("#adminDashboardBtn").click(function () {

    $("#adminDailySummary").css("display", "inline-flex")

    $("#adminReservation").css("display", "none")
    $("#adminCars").css("display", "none")
    $("#adminDrivers").css("display", "none")
    $("#adminCustomer").css("display", "none")
    $("#adminPayments").css("display", "none")

   // loadDailySummary();
})

//--Payment
$("#adminPaymentBtn").click(function () {
    $("#adminPayments").css("display", "inline-flex")

    $("#adminDrivers").css("display", "none")
    $("#adminCustomer").css("display", "none")
    $("#adminCars").css("display", "none")
    $("#adminReservation").css("display", "none")
    $("#adminDailySummary").css("display", "none")

    $("#admin-all-drivers-title").css("display", "block")
    $("#admin-all-driverSchedule-title").css("display", "none")


    $("#enableSaveDriverBtn").css("display", "block");
    $("#enableSearchDriverBtn").css("visibility", "hidden");

   // loadAllDrivers()
})


