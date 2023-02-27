var reservation_Id;
var data;
$("#admin-pending-reservation").click(function () {
    $("#admin-reservation-title").css("display", "block")
    $("#admin-todayPickups-title").css("display", "none")

    $("#admin-update-reservation").css("display", "block")
    $("#admin-view-reservation").css("display", "none")

    //loadPendingReservations();
})

$("#admin-today-pickups").click(function () {
    $("#admin-reservation-title").css("display", "none")
    $("#admin-todayPickups-title").css("display", "block")

    $("#admin-update-reservation").css("display", "none")
    $("#admin-view-reservation").css("display", "block")

    //loadTodayPickUps()
})

$("#admin-view-reservation").click(function () {
    if (reservation_Id == null) {
        return
    }
    $.ajax({
        url: baseUrl + "controller/reservation/getReservation/" + reservation_Id,
        method: "GET",
        success: function (resp) {
            if (resp.status === 200) {
                data = resp.data
                setDataToViewReservationModal()
            }
        },
        error: function (err) {
            console.log(err);
        }
    });
})

function setDataToViewReservationModal() {
    $("#admin-view-reservation-id").val(data.reserve_id)
    $("#admin-view-reservation-driverNic").val(data.driver)
    $("#admin-view-reservation-name").val(data.customer.nic)
    $("#admin-view-reservation-pickUpDate").val(data.pick_up_date)
    $("#admin-view-reservation-pickUpTime").val(data.pick_up_time)
    $("#admin-view-reservation-venue").val(data.pick_up_and_return_venue)
    $("#admin-view-reservation-returnDate").val(data.return_date)
    $("#admin-view-reservation-reserveDate").val(data.reserve_date)
    $("#admin-view-reservation-days").val(data.no_of_days)
    $("#admin-view-reservation-vehicle").val(data.car.registration_no)
    $("#admin-view-reservation-img").attr("src", baseUrl + data.bank_slip_img)

    if (data.driver_status === "YES") {
        getReservationDriver(data.reserve_id)
    } else {
        $("#admin-view-reservation-driverNic").val("Not Required");
    }
}
