let selectedUser;

function successCallback(response) {
    for (d in response) {
        $("table").append("<tr>" +
            "<td>" + response[d].id + "</td>" +
            "<td><a href=\"#\" onclick=\"selectedUser = " + response[d].id + ";customerInfo();\" data-toggle=\"modal\" data-target=\"#modalEditCustomerForm\"><img src=\"" + response[d].profilePicUrl + "\" class =\"avatar\" alt =\"Avatar\">" + response[d].firstName + " " + response[d].lastName + "</a></td>" +
            "<td>" + response[d].email + "</td>" +
            "<td>" + response[d].phoneNumber + "</td>" +
            "<td><a href=\"\" onclick=\"selectedUser = " + response[d].id + ";populateEditMenu();\" data-toggle=\"modal\" data-target=\"#modalEditCustomerForm\" class=\"edit\" title=\"Edit\"><i class=\"material-icons\">edit</i></a><a href=\"\" onclick=\"deleteCustomer(" + response[d].id + ")\" class=\"delete\" title=\"Delete\" data-toggle=\"tooltip\"><i class=\"material-icons\">delete_forever</i></a></td>" +
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
    $(".customer-form").on("submit", function (event) {
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
            url: "http://localhost:8080/javabank/api/customers/" + selectedUser,
            type: "put",
            contentType: "application/json",
            dataType: "json",
            data: JSON.stringify({
                profilePicUrl: $('input[id=edit-pic-url]').val(),
                firstName: $('input[id=edit-first-name]').val(),
                lastName: $('input[id=edit-last-name]').val(),
                phoneNumber: $('input[id=edit-phone-number]').val(),
                email: $('input[id=edit-email]').val()
            }),
        });

        location.reload();
    });
});

function customerInfo() {
    populateEditMenu();

    $("input").prop('disabled', true);
    $("button").prop('disabled', true);

    $("h4[class='modal-title w-100 font-weight-bold']").text("Customer Info");
}

function populateEditMenu() {
    $("input").prop('disabled', false);
    $("button").prop('disabled', false);

    $("h4[class='modal-title w-100 font-weight-bold']").text("Edit Customer");

    function successCallback(response) {
        $('#edit-pic-url').val(response.profilePicUrl);
        $('#edit-first-name').val(response.firstName);
        $('#edit-last-name').val(response.lastName);
        $('#edit-email').val(response.email);
        $('#edit-phone-number').val(response.phoneNumber);

        $("img[id='edit-pic']").attr('src', response.profilePicUrl);
    }

    function errorCallback(request, status, error) {
        // do something with the error
    }

// perform an ajax http get request
    $.ajax({
        url: 'http://localhost:8080/javabank/api/customers/' + selectedUser,
        async: true,
        success: successCallback,
        error: errorCallback
    });
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
    });
};