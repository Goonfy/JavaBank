window.onload = function () {
    let request = new XMLHttpRequest();

    request.onreadystatechange = function () {
        if (request.readyState !== 4 && request.status !== 200) {
            return;
        }

        let data = JSON.parse(request.responseText);

        let usersTable = document.getElementById('users-table');

        for (d in data) {
            // Insert a row at the end of the table
            let newRow = usersTable.insertRow(-1);

            let idCell = newRow.insertCell(-1);
            idCell.innerHTML = data[d].id;
            let nameCell = newRow.insertCell(-1);
            nameCell.innerHTML = "<a href=\"\"><img src=\"" + data[d].profilePicUrl + "\" class=\"avatar\" alt=\"Avatar\"></a>" + data[d].firstName + " " + data[d].lastName;
            let emailCell = newRow.insertCell(-1);
            emailCell.innerHTML = data[d].email;
            let phoneNumberCell = newRow.insertCell(-1);
            phoneNumberCell.innerHTML = data[d].phoneNumber;
            let actions = newRow.insertCell(-1);
            actions.innerHTML = "<a href=\"\" class=\"edit\" title=\"Edit\" data-toggle=\"tooltip\"><i class=\"material-icons\">edit</i></a>" +
                "<a onclick=\"return confirm('Are you sure?')\" href=\"\" class=\"delete\" title=\"Delete\" data-toggle=\"tooltip\"><i class=\"material-icons\">delete_forever</i></a>";
        }
    }

    request.open("GET", "http://localhost:8080/javabank/api/customer")
    request.send();
}

