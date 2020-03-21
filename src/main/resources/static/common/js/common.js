
function postJson(url,data,success,error) {
    $.ajax({
        url: url,
        method: "post",
        dataType: 'json',
        data: JSON.stringify(data),
        success: function (result) {
            success(result)
        },
        error: function () {
            error()
        },
        contentType: "application/json"

})
}

function putJson(url,success,error) {
    $.ajax({
        url: url,
        method: "put",
        success: function () {
            success()
        },
        error: function () {
            error()
        },
        contentType: "application/json"
    })
}

function deleteJson(url,success,error) {
    $.ajax({
        url: url,
        method: "delete",
        success: function () {
            success()
        },
        error: function () {
            error()
        },
        contentType: "application/json"

    })
}

$.postJSON = postJson;
$.putJson = putJson;
$.deleteJson = deleteJson;