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
        mileage: $("#save-car-mileage").val(),
        freeKmMonth: $("#save-car-freeKm-month").val(),
        freeKmDay: $("#save-car-freeKm-day").val(),
        fuelType: $("#save-car-fuelType").val(),
        dailyRate: $("#save-car-daily").val(),
        monthlyRate: $("#save-car-monthly").val(),
        waiver_payment: $("#save-car-waiver-payment").val(),
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
                loadAllCars("allCarDetail");

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
        url: baseUrl + "car/" + path,
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
     $("#admin-update-mileage").val(data.mileage)
    $("#admin-update-fuel").val(data.fuelType)
    $("#admin-update-daily").val(data.dailyRate)
    $("#admin-update-monthly").val(data.monthlyRate)
    $("#admin-update-freeKm-day").val(data.freeKmDay)
    $("#admin-update-freeKn-month").val(data.freeKmMonth)
    $("#admin-update-extraKm").val(data.priceForExtraKm)
    $("#admin-update-waiverPayment").val(data.waiver_payment)
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
        mileage: $("#admin-update-mileage").val(),
        freeKmMonth: $("#admin-update-freeKn-month").val(),
        freeKmDay: $("#admin-update-freeKm-day").val(),
        fuelType: $("#admin-update-fuel").val(),
        dailyRate: $("#admin-update-daily").val(),
        monthlyRate: $("#admin-update-monthly").val(),
        priceForExtraKm: $("#admin-update-extraKm").val(),
        waiver_payment:  $("#admin-update-waiverPayment"),
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
                loadAllCars("allCarDetail");

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
        loadAllCars("allCarDetail");
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

// let homeDivArray = ["#divOne", "#divTwo", "#divThree"];
//
// var listNo = 0;
// var displayDiv = 0;
//
// function setCarDetailsToHomeDiv() {
//     displayDiv = 0
//
//     for (var i = 0; listNo <= carList.length - 1; i++, listNo++, displayDiv++) {
//         $("#homeUnavailableTag").css("display", "none")
//         $(homeDivArray[i]).css("display", "block")
//
//         if (i > 2) {
//             break
//         }
//
//         let img = "#" + $(homeDivArray[i]).children()[0].id
//         let type = "#" + $(homeDivArray[i]).children().children()[0].id;
//         let brand = "#" + $(homeDivArray[i]).children().children()[1].id;
//         let daily = "#" + $(homeDivArray[i]).children().children()[4].id
//         let monthly = "#" + $(homeDivArray[i]).children().children()[7].id
//
//         let fuel = "#" + $("#" + $(homeDivArray[i]).children().children()[9].id).children()[1].id;
//         let transmission = "#" + $("#" + $(homeDivArray[i]).children().children()[10].id).children()[1].id;
//
//         let car_id = "#" + $(homeDivArray[i]).children().children()[12].id
//
//         $(img).attr("src", baseUrl + carList[listNo].image1.image1)
//         $(img).attr("src", baseUrl + carList[listNo].image2.image2)
//         $(img).attr("src", baseUrl + carList[listNo].image3.image3)
//         $(img).attr("src", baseUrl + carList[listNo].image4.image4)
//         $(type).text(carList[listNo].type)
//         $(brand).text(carList[listNo].brand)
//         $(daily).text(carList[listNo].dailyRate)
//         $(monthly).text(carList[listNo].monthlyRate)
//         $(fuel).text(carList[listNo].fuelType)
//         $(transmission).text(carList[listNo].transmissionType)
//         $(car_id).text(carList[listNo].registrationId)
//
//     }
// }
//
// $("#card-one-bookBtn").click(function () {
//     let id = $("#card-one-car-id").text();
//     let obj = carList.find(o => o.registrationId === id);
//     setCarDetailsToModal(obj)
//
// })
// $("#card-two-bookBtn").click(function () {
//     let id = $("#card-two-car-id").text();
//     let obj = carList.find(o => o.registrationId === id);
//     setCarDetailsToModal(obj)
//
// })
// $("#card-three-bookBtn").click(function () {
//     let id = $("#card-three-car-id").text();
//     let obj = carList.find(o => o.registrationId === id);
//     setCarDetailsToModal(obj)
//
// })
//
// $("#customer-home-nextCarBtn").click(function () {
//     if (carList.length === listNo) {
//         return
//     }
//     $('#divOne, #divTwo,#divThree').css({
//         display: 'none'
//     })
//
//     setCarDetailsToHomeDiv()
//
// })
// $("#customer-home-previousCarBtn").click(function () {
//     if (3 >= listNo) {
//         return
//     }
//     $('#divOne, #divTwo,#divThree').css({
//         display: 'none'
//     })
//     listNo = listNo - (displayDiv + 3)
//     setCarDetailsToHomeDiv()
// })
//
//
//
// function setCarDetailsToModal(obj) {
//     getReservationId()
//     $("#customer-reservation-car-id").text(obj.registrationId)
//     $("#customer-reservation-car-brand").text(obj.brand)
//     $("#customer-reservation-car-color").text(obj.color)
//     $("#customer-reservation-car-type").text(obj.color)
//     $("#customer-reservation-car-extraKm").text(obj.priceForExtraKm)
//     $("#customer-reservation-car-freeKmDay").text(obj.freeServiceMileage)
//     $("#customer-reservation-car-freeKmMonth").text(obj.lastServiceMileage)
//     $("#customer-reservation-car-fuel").text(obj.fuelType)
//     $("#customer-reservation-car-waiverPayment").text(obj.waiver_payment)
//     $("#customer-reservation-car-transmission").text(obj.transmissionType)
//     $("#customer-reservation-car-monthly").text(obj.monthlyRate)
//     $("#customer-reservation-car-passengers").text(obj.noOfPassenger)
//     $("#customer-reservation-car-mileage").text(obj.lastServiceMileage)
//     $("#customer-reservation-car-daily").text(obj.dailyRate)
//     $("#customer-reservation-car-img1").attr("src", baseUrl + obj.image1.image1)
//     $("#customer-reservation-car-img2").attr("src", baseUrl + obj.image1.image2)
//     $("#customer-reservation-car-img3").attr("src", baseUrl + obj.image1.image3)
//     $("#customer-reservation-car-img4").attr("src", baseUrl + obj.image1.image4)
//
//     var date1 = $("#customer-home-pickup").val();
//     var date2 = $("#customer-home-return").val()
//
//     var diff = Math.floor((Date.parse(date2) - Date.parse(date1)) / 86400000);
//
//     $("#customer-reservation-customer-days").val(diff)
//     $("#customer-reservation-customer-name").val(customer.name)
//     $("#customer-reservation-customer-vehicle-no").val(obj.registrationId)
//     $("#customer-reservation-customer-returnDate").val(date2)
//     $("#customer-reservation-customer-pickUpDate").val(date1)
//
//     $("#customer-reservation-customer-pickUpTime").text()
//     $("#customer-reservation-customer-driverCheck").text()
// }