var data;


function generateBillId() {
    $.ajax({
        url: baseUrl + "payment/generateBillId",
        method: "GET",
        success: function (resp) {
            if (resp.status === 200) {
                $("#admin-payment-billId").val(resp.data)
            }
        },
        error: function (err) {
            alert("Wrong reservation ID you entered")
            console.log(err);
        }
    });
}