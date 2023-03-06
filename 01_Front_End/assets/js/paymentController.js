var data;


function generateBillId() {
    $.ajax({
        url: baseUrl + "payment/generateBillId",
        method: "GET",
        success: function (resp) {
            if (resp.code === 200) {
                $("#admin-payment-billId").val(resp.data)
            }
        },
        error: function (err) {
            alert("Wrong reservation ID you entered")
            console.log(err);
        }
    });
}

function loadDataToFields(data) {
    if (data.reservation_status === "Accept") {
        $("#admin-payment-customer-name").val(data.customer.user_name)
        $("#admin-payment-registrationNo").val(data.car.registrationId)
        $("#admin-payment-pickUp-date").val(data.pick_up_date)
        $("#admin-payment-return-date").val(data.return_date)
        $("#admin-payment-noOf-date").val(data.no_of_days)
        $("#admin-payment-pay-date").val(today)

        if (data.driver_status === "YES") {
            $("#admin-payment-driver-fee").val(data.no_of_days * 1000)
        } else {
            $("#admin-payment-driver-fee").val("Not Required")
        }
    } else {
        alert("This Reservation Transaction Is Already Done Or Canceled");
    }
}

function clearPaymentForm() {
    $('#admin-payment-totalKm,#admin-payment-damage-cost,#admin-payment-cash,#admin-payment-discount,#admin-payment-reservation-searchText').css({
        border: '1px solid #c4c4c4',
    })
    $('#admin-payment-balance,#admin-payment-subTotal,#admin-payment-total,#admin-payment-refund,#admin-payment-driver-fee,#admin-payment-billId,#admin-payment-pay-date,#admin-payment-noOf-date,#admin-payment-return-date,#admin-payment-pickUp-date,#admin-payment-registrationNo,#admin-payment-customer-name,#admin-payment-totalKm,#admin-payment-damage-cost,#admin-payment-cash,#admin-payment-discount,#admin-payment-reservation-searchText').val("")
}