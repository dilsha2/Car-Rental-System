$("#admin-driverBtn").click(function () {
    $("#admin-all-drivers-title").css("display", "block")
    $("#admin-all-driverSchedule-title").css("display", "none")

    $("#admin-driver-table").css("display", "block")
    $("#admin-driver-schedule-table").css("display", "none")

    $("#enableSaveDriverBtn").css("display", "block");
    $("#enableSearchDriverBtn").css("visibility", "hidden");
    $("#admin-driver-viewDetailsBtn").css("visibility", "visible");

    //loadAllDrivers()
})

$("#admin-scheduleBtn").click(function () {
    $("#admin-all-drivers-title").css("display", "none")
    $("#admin-all-driverSchedule-title").css("display", "block")

    $("#admin-driver-table").css("display", "none")
    $("#admin-driver-schedule-table").css("display", "block")

    $("#enableSaveDriverBtn").css("display", "none");
    $("#enableSearchDriverBtn").css("visibility", "visible");

    $("#admin-driver-viewDetailsBtn").css("visibility", "hidden");

    $("#admin-driver-start-date").val(today);
    $("#admin-driver-end-date").val(today);

    //loadDriverScheduleForAdmin();
})
