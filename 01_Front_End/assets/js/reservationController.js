var reservation_Id;
var data;
$("#admin-pending-reservation").click(function () {
    $("#admin-reservation-title").css("display", "block")
    $("#admin-todayPickups-title").css("display", "none")

    $("#admin-update-reservation").css("display", "block")
    $("#admin-view-reservation").css("display", "none")

    loadPendingReservations();
})

$("#admin-today-pickups").click(function () {
    $("#admin-reservation-title").css("display", "none")
    $("#admin-todayPickups-title").css("display", "block")

    $("#admin-update-reservation").css("display", "none")
    $("#admin-view-reservation").css("display", "block")

    loadTodayPickUps()
})

function loadPendingReservations() {
    $("#admin-reservation-table").empty();

    $.ajax({
        url: baseUrl + "reservation/pendingReservation",
        method: "GET",
        success: function (resp) {
            for (const reservation of resp.data) {
                let row = `<tr style="text-align: center"><td>${reservation.reserve_id}</td><td>${reservation.customer.nic}</td><td>${reservation.car.registrationId}</td>
                    <td>${reservation.no_of_days}</td></tr>`;
                $("#admin-reservation-table").append(row);
                $("#admin-reservation-table>tr").off("click");
                $("#admin-reservation-table>tr").click(function () {
                    reservation_Id = $(this).children(":eq(1)").text();
                    $("#admin-update-reservation").prop('disabled', false);
                    console.log(reservation_Id)
                });
            }
        }
    });
}

function loadTodayPickUps() {
    $("#admin-reservation-table").empty();

    $.ajax({
        url: baseUrl + "reservation/todayPickUps",
        method: "GET",
        success: function (resp) {
            for (const reservation of resp.data) {
                let row = `<tr style="text-align: center"><td>${reservation.reserve_id}</td><td>${reservation.customer.nic}</td><td>${reservation.car.registrationId}</td>
                    <td>${reservation.no_of_days}</td></tr>`;
                $("#admin-reservation-table").append(row);
                $("#admin-reservation-table>tr").off("click");
                $("#admin-reservation-table>tr").click(function () {
                    reservation_Id = $(this).children(":eq(0)").text();
                    $("#admin-view-reservation").prop('disabled', false);
                    $("#admin-update-reservation").prop('disabled', false);
                });
            }
        }
    });
}

$("#admin-view-reservation").click(function () {
    if (reservation_Id == null) {
        return
    }
    $.ajax({
        url: baseUrl + "reservation/getReservation/" + reservation_Id,
        method: "GET",
        success: function (resp) {
            if (resp.code === 200) {
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
    $("#admin-view-reservation-driverNic").val(data.driverNic)
    $("#admin-view-reservation-name").val(data.customer.nic)
    $("#admin-view-reservation-pickUpDate").val(data.pick_up_date)
    $("#admin-view-reservation-pickUpTime").val(data.pick_up_time)
    $("#admin-view-reservation-venue").val(data.pick_up_and_return_venue)
    $("#admin-view-reservation-returnDate").val(data.return_date)
    $("#admin-view-reservation-reserveDate").val(data.reserve_date)
    $("#admin-view-reservation-days").val(data.no_of_days)
    $("#admin-view-reservation-vehicle").val(data.car.registrationId)
    $("#admin-view-reservation-img").attr("src", baseUrl + data.bank_slip_img)

    if (data.driver_status === "YES") {
        getReservationDriver(data.reserve_id)
    } else {
        $("#admin-view-reservation-driverNic").val("Not Required");
    }
}

function getReservationDriver(reserve_id) {
    $.ajax({
        url: baseUrl + "driver/getSchedule/" + reserve_id,
        method: "GET",
        success: function (resp) {
            if (resp.status === 200) {
                $("#admin-view-reservation-driverNic").val(resp.data);
                $("#admin-update-reservation-driver").val(resp.data);
            }
        },
        error: function (err) {
            console.log(err);
        }
    });
}


