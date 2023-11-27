const url = "http://localhost:8080/rest_api/user"

async function thisUser() {
    fetch(url)
        .then(res => res.json())
        .then(data => {
            $('#headerUser').append(data.username);
            let roles = data.roles.map(role => " " + role.name.substring(5));
            $('#headerRoles').append(roles);

            let user = `$(
            <tr>
                <td>${data.id}</td>
                <td>${data.username}</td>           
                <td>${data.lastName}</td>           
                <td>${data.email}</td>
                <td>${roles}</td>)`;
            $('#userPanelBody').append(user);
        })
}

$(async function () {
    await thisUser();
});