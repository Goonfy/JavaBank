function successCallback(response) {
    for (d in response) {
        $("table").append("<tr>" +
            "<td>" + response[d].id + "</td>" +
            "<td><a href=\"#\"><img src=\"" + response[d].profilePicUrl + "\" class =\"avatar\" alt =\"Avatar\">" + response[d].firstName + " " + response[d].lastName + "</a></td>" +
            "<td>" + response[d].email + "</td>" +
            "<td>" + response[d].phoneNumber + "</td>" +
            "<td><a href=\"\" class=\"btn btn-default btn-rounded mb-4\" data-toggle=\"modal\" data-target=\"#modalEditForm\" class=\"edit\" title=\"Edit\" data-toggle=\"tooltip\"><i class=\"material-icons\">edit</i></a><a href=\"\" onclick=\"deleteCustomer(" + response[d].id + ")\" class=\"delete\" title=\"Delete\" data-toggle=\"tooltip\"><i class=\"material-icons\">delete_forever</i></a></td>" +
            "</tr>"
        );
    }
}

function errorCallback(request, status, error) {
    // do something with the error
}

// perform an ajax http get request
$.ajax({
    url: 'http://localhost:8080/javabank/api/customers',
    async: true,
    success: successCallback,
    error: errorCallback
});

$(function () {
    $(".create-customer-form").on("submit", function (event) {
        event.preventDefault();

        $.ajax({
            url: "http://localhost:8080/javabank/api/customers/",
            type: "post",
            contentType: "application/json",
            dataType: "json",
            data: JSON.stringify({
                profilePicUrl: $('input[id=pic-url]').val(),
                firstName: $('input[id=first-name]').val(),
                lastName: $('input[id=last-name]').val(),
                phoneNumber: $('input[id=phone-number]').val(),
                email: $('input[id=email]').val()
            }),
        });

        location.reload();
    });
});

$(function () {
    $(".edit-customer-form").on("submit", function (event) {
        event.preventDefault();

        $.ajax({
            url: "http://localhost:8080/javabank/api/customers/",
            type: "put",
            contentType: "application/json",
            dataType: "json",
            data: JSON.stringify({
                profilePicUrl: $('input[id=pic-url]').val(),
                firstName: $('input[id=first-name]').val(),
                lastName: $('input[id=last-name]').val(),
                phoneNumber: $('input[id=phone-number]').val(),
                email: $('input[id=email]').val()
            }),
        });

        location.reload();
    });
});

function editCustomer(id) {

};

function deleteCustomer(id) {
    if (!confirm('Are you sure?')) {
        return;
    }

    $.ajax({
        url: "http://localhost:8080/javabank/api/customers/" + id,
        type: "delete",
        contentType: "application/json",
        dataType: "json",
        success: function (response) {
            alert("Deleted");
        },
        error: function (response) {
            alert("Error");
        }
    });
};