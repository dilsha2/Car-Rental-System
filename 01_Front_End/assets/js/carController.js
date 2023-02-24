var vehicle_no;
var carList;
var reservationId;
var reservationList;

$("#admin-car-available").click(function () {
    $("#unavailableBtn").css("display", "block");

    $("#maintainBtn").css("display", "none");
    $("#underMaintainBtn").css("display", "none");
    $("#availableBtn").css("display", "none");
    $("#viewButton").css("display", "none");

    $("#admin-all-cars-title").css("display", "none");
    $("#admin-all-unavailableCars-title").css("display", "none");
    $("#admin-all-needMaintains-title").css("display", "none");
    $("#admin-all-underMaintains-title").css("display", "none");
    $("#admin-all-availableCars-title").css("display", "block");

    loadAllCars("unavailableOrAvailableCarsByStatus/Available")


})
$("#admin-car-unavailable").click(function () {
    $("#availableBtn").css("display", "block");

    $("#unavailableBtn").css("display", "none");
    $("#maintainBtn").css("display", "none");
    $("#underMaintainBtn").css("display", "none");
    $("#viewButton").css("display", "none");

    $("#admin-all-cars-title").css("display", "none");
    $("#admin-all-unavailableCars-title").css("display", "block");
    $("#admin-all-needMaintains-title").css("display", "none");
    $("#admin-all-underMaintains-title").css("display", "none");
    $("#admin-all-availableCars-title").css("display", "none");

    loadAllCars("unavailableOrAvailableCarsByStatus/Unavailable")

})
$("#admin-car-underMaintain").click(function () {
    $("#availableBtn").css("display", "block");

    $("#unavailableBtn").css("display", "none");
    $("#maintainBtn").css("display", "none");
    $("#underMaintainBtn").css("display", "none");
    $("#viewButton").css("display", "none");

    $("#admin-all-cars-title").css("display", "none");
    $("#admin-all-unavailableCars-title").css("display", "none");
    $("#admin-all-needMaintains-title").css("display", "none");
    $("#admin-all-underMaintains-title").css("display", "block");
    $("#admin-all-availableCars-title").css("display", "none");

    loadAllCars("carsUnderMaintain")

})

$("#admin-car-needMaintain").click(function () {
    $("#maintainBtn").css("display", "block");

    $("#availableBtn").css("display", "none");
    $("#unavailableBtn").css("display", "none");
    $("#underMaintainBtn").css("display", "none");
    $("#viewButton").css("display", "none");

    $("#admin-all-cars-title").css("display", "none");
    $("#admin-all-unavailableCars-title").css("display", "none");
    $("#admin-all-needMaintains-title").css("display", "block");
    $("#admin-all-underMaintains-title").css("display", "none");
    $("#admin-all-availableCars-title").css("display", "none");


    loadAllCars("carsNeedMaintain")
})

//-----------------save cars

$("#btnCarSave").click(function () {
    saveCar();
})

// function saveCar() {
//
//     let fileName = $("#save-car-frontView")[0].files[0].name;
//     let backFileName = $("#save-car-backView")[0].files[0].name;
//     let sideFileName = $("#save-car-sideView")[0].files[0].name;
//     let interiorFileName = $("#save-car-interior")[0].files[0].name;
//
//
//     let registrationId = $("#save-car-registration-no").val();
//     let Brand = $("#save-car-brand").val();
//     let type = $("#save-car-type").val();
//     let model = $("#save-car-model").val();
//     let transmissionType = $("#save-car-transmission").val();
//     let color = $("#save-car-color").val();
//     let noOfPassenger = $("#save-car-passengers").val();
//     let lastServiceMileage = $("#save-car-mileage").val();
//     let freeServiceMileage = $("#save-car-freeKm-month").val();
//     let fuelType = $("#save-car-fuelType").val();
//     let dailyRate = $("#save-car-daily").val();
//     let monthlyRate = $("#save-car-monthly").val();
//     let priceForExtraKm = $("#save-car-extraKm-price").val();
//     let availability = $("#save-car-status").val();
//     let image1 = fileName;
//     let image2 = backFileName;
//     let image3 = sideFileName;
//     let image4 = interiorFileName;
//
//     var CarDTO = {
//         registrationId: registrationId,
//         Brand: Brand,
//         type: type,
//         model: model,
//         transmissionType: transmissionType,
//         color: color,
//         noOfPassenger: noOfPassenger,
//         lastServiceMileage: lastServiceMileage,
//         freeServiceMileage: freeServiceMileage,
//         fuelType: fuelType,
//         dailyRate: dailyRate,
//         monthlyRate: monthlyRate,
//         priceForExtraKm: priceForExtraKm,
//         availability: availability,
//         image1: image1,
//         image2: image2,
//         image3: image3,
//         image4: image4
//     }
//
//     $.ajax({
//         url: baseUrl + "car",
//         method: 'post',
//         //async: true,
//         contentType: "application/json",
//         //processData: false,
//         data: JSON.stringify(CarDTO),
//         success: function (resp) {
//             if (resp.status === 200) {
//                 alert(resp.message);
//                 // loadAllCars("allCarDetail");
//                 loadImage();
//
//             }
//         },
//         error: function (err) {
//             alert(err.responseJSON.message)
//             console.log(err);
//         }
//     });
//     //clearSaveCarForm();
// }

function saveCar() {
    var data = new FormData();

    let front = $("#save-car-frontView")[0].files[0];
    let frontFileName = front.name;

    let back = $("#save-car-backView")[0].files[0];
    let backFileName = back.name;

    let side = $("#save-car-sideView")[0].files[0];
    let sideFileName = side.name;

    let interior = $("#save-car-interior")[0].files[0];
    let interiorFileName = interior.name;

    data.append("file", front);
    data.append("file", back);
    data.append("file", side);
    data.append("file", interior);



    var car = {
        registrationId: $("#save-car-registration-no").val(),
        brand: $("#new_car_brand").val(),
        type: $("#save-car-type").val(),
        model: $("#save-car-model").val(),
        transmissionType: $("#save-car-transmission").val(),
        color: $("#save-car-color").val(),
        noOfPassenger: $("#save-car-passengers").val(),
        lastServiceMileage: $("#save-car-mileage").val(),
        freeServiceMileage: $("#save-car-freeKm-month").val(),
        fuelType: $("#save-car-fuelType").val(),
        dailyRate: $("#save-car-daily").val(),
        monthlyRate: $("#save-car-monthly").val(),
        priceForExtraKm: $("#save-car-extraKm-price").val(),
        availability: $("#save-car-status").val(),
        image1: null,
        image2: null,
        image3: null,
        image4: null,
    }

    console.log(car.brand);

    $.ajax({
        url: baseUrl + "car",
        method: 'post',
        contentType: "application/json",
        data: JSON.stringify(car),
        success: function (resp) {
            if (resp.code === 200) {
                alert(resp.message);
                loadAllCars();

            }
        },
        error: function (err) {
            alert(err.resposeJSON.message);
            console.log(err);
        }
    });
    clearSaveCarForm();

}

function loadImage() {
    var data = new FormData();

    let front = $("#save-car-frontView")[0].files[0];
    let fileName = $("#save-car-frontView")[0].files[0].name;
    data.append("myFile", front, fileName);

    let back = $("#save-car-backView")[0].files[0];
    let backFileName = $("#save-car-backView")[0].files[0].name;
    data.append("myFile", back, backFileName);

    let side = $("#save-car-sideView")[0].files[0];
    let sideFileName = $("#save-car-sideView")[0].files[0].name;
    data.append("myFile", side, sideFileName);

    let interior = $("#save-car-interior")[0].files[0];
    let interiorFileName = $("#save-car-interior")[0].files[0].name;
    data.append("myFile", interior, interiorFileName);

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

function loadAllCars(path) {
    $("#admin-cars-table").empty();

    $.ajax({
        url: baseUrl + "car" + path,
        method: "GET",
        success: function (resp) {
            for (const car of resp.data) {
                let row = `<tr><td>${car.registrationId}</td><td>${car.brand}</td><td>${car.type}</td><td>${car.transmissionType}</td><td>${car.fuelType}</td></tr>`;
                $("#admin-cars-table").append(row);

                $("#admin-cars-table>tr").off("click");
                $("#admin-cars-table>tr").click(function () {
                    vehicle_no = $(this).children(":eq(0)").text();
                    $("#viewButton").prop('disabled', false);
                });
            }
        }
    });
}

function clearSaveCarForm() {
    $('#save-car-registration-no,#save-car-brand,#save-car-type,#save-car-transmission,#save-car-color,#save-car-passengers,#save-car-mileage,#save-car-fuelType,#save-car-daily,#save-car-monthly,#save-car-freeKm-day,#save-car-freeKm-month,#save-car-extraKm-price,#save-car-waiver-payment,#save-car-status').css({
        border: '1px solid #c4c4c4',
    })
    $('#save-car-registration-no,#save-car-brand,#save-car-type,#save-car-transmission,#save-car-color,#save-car-passengers,#save-car-mileage,#save-car-fuelType,#save-car-daily,#save-car-monthly,#save-car-freeKm-day,#save-car-freeKm-month,#save-car-extraKm-price,#save-car-waiver-payment,#save-car-status').val("")

    $("#save-car-type,#save-car-transmission,#save-car-status,#save-car-fuelType").val("Select One")
}

$("#viewButton").click(function () {
    if (vehicle_no == null) {
        return
    }
    $.ajax({
        url: baseUrl + "car/carDetail/" + vehicle_no,
        method: "GET",
        success: function (resp) {
            if (resp.code === 200) {
                setDataToUpdateModel(resp.data);
            }
        },
        error: function (err) {
            console.log(err);
        }
    });
})

function setDataToUpdateModel(data) {
    $("#admin-update-registration-no").val(data.registrationId)
    $("#admin-update-brand").val(data.brand)
    $("#admin-update-type").val(data.type)
    $("#admin-update-transmission").val(data.transmissionType)
    $("#admin-update-color").val(data.color)
    $("#admin-update-passengers").val(data.noOfPassenger)
    // $("#admin-update-mileage").val(data.mileage)
    $("#admin-update-fuel").val(data.fuelType)
    $("#admin-update-daily").val(data.dailyRate)
    $("#admin-update-monthly").val(data.monthlyRate)
    $("#admin-update-freeKm-day").val(data.lastServiceMileage)
    $("#admin-update-freeKn-month").val(data.freeServiceMileage)
    $("#admin-update-extraKm").val(data.priceForExtraKm)
    // $("#admin-update-waiverPayment").val(data.waiver_payment)
    $("#admin-update-status").val(data.status)
    $("#update-car-modal-front").attr("src", baseUrl + data.image1.image1)
    $("#update-car-modal-back").attr("src", baseUrl + data.image2.image2)
    $("#update-car-modal-side").attr("src", baseUrl + data.image3.image3)
    $("#update-car-modal-interior").attr("src", baseUrl + data.image4.image4)
}

$("#btnUpdateCar").click(function () {
    updateCar();
})

$("#customer-upcoming-reservationBtn").click(function (){
   // clearAllReservationDetails()
   // loadUpcomingReservation()
})
$("#customer-pending-reservationBtn").click(function (){
    //clearAllReservationDetails()
    //loadPendingReservation()
})

function updateCar(){
    var data = new FormData();

    let front = $("#admin-update-front")[0].files[0];
    let frontFileName = front.name;

    let back = $("#admin-update-back")[0].files[0];
    let backFileName = back.name;

    let side = $("#admin-update-side")[0].files[0];
    let sideFileName = side.name;

    let interior = $("#admin-update-interior")[0].files[0];
    let interiorFileName = interior.name;

    data.append("file", front);
    data.append("file", back);
    data.append("file", side);
    data.append("file", interior);



    var car = {
        registrationId: $("#admin-update-registration-no").val(),
        brand: $("#admin-update-brand").val(),
        type: $("#admin-update-type").val(),
        model: $("#admin-update-").val(),
        transmissionType: $("#admin-update-transmission").val(),
        color: $("#admin-update-color").val(),
        noOfPassenger: $("#admin-update-passengers").val(),
        lastServiceMileage: $("#admin-update-mileage").val(),
        freeServiceMileage: $("#admin-update-freeKn-month").val(),
        fuelType: $("#admin-update-fuel").val(),
        dailyRate: $("#admin-update-daily").val(),
        monthlyRate: $("#admin-update-monthly").val(),
        priceForExtraKm: $("#admin-update-extraKm").val(),
        availability: $("#admin-update-status").val(),
        image1: null,
        image2: null,
        image3: null,
        image4: null,
    }

    console.log(car.brand);

    $.ajax({
        url: baseUrl + "car/updateCarDetail",
        method: 'post',
        contentType: "application/json",
        data: JSON.stringify(car),
        success: function (resp) {
            if (resp.code === 200) {
                alert(resp.message);
                loadAllCars();

            }
        },
        error: function (err) {
            alert(err.resposeJSON.message);
            console.log(err);
        }
    });
    clearSaveCarForm();

}

$("#unavailableBtn").click(function () {
    var status = "Unavailable";
    setCarStatus(vehicle_no, status)
    loadAllCars("unavailableOrAvailableCarsByStatus/Available")
})
$("#availableBtn").click(function () {
    var status = "Available";
    setCarStatus(vehicle_no, status)

    loadAllCars("unavailableOrAvailableCarsByStatus/Unavailable")
})
$("#maintainBtn").click(function () {
    var status = "UnderMaintain";
    setCarStatus(vehicle_no, status)
    loadAllCars("carsNeedMaintain")
})
$("#underMaintainBtn").click(function () {
    var status = "Available";
    setCarStatus(vehicle_no, status)
    loadAllCars("carsUnderMaintain")
})

$("#btnDeleteCar").click(function (){
    let res = confirm("Do you really need to delete this Car ?");
    if (res) {
        $("#updateCarModel").modal("toggle");
        loadAllCars();
        //clearUpdateCarForm();
    }
})

function setCarStatus(id, status) {

    $.ajax({
        url: baseUrl + "car?id=" + id + "&status=" + status,
        method: "PUT",
        async: false,
        success: function (res) {
            if (res.code === 200) {
                alert(res.message)
            }
        },
        error: function (ob) {
            console.log(ob);
            alert("Sorry Cant Update This Car Status Right Now..Try Again Latter")
        }
    });
}