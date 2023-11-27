$('#delete').on('show.bs.modal', ev => {
    let button = $(ev.relatedTarget);
    let id = button.data('id');
    showDeleteModal(id);
})

async function showDeleteModal(id) {

    let user = await getUser(id);
    let form = document.forms["formDelete"];
    form.id.value = user.id;
    form.username.value = user.username;
    form.lastName.value = user.lastName;
    form.email.value = user.email;


    $('#rolesDeleteUser').empty();
    await fetch("http://localhost:8080/rest_api/admin/roles")
        .then(res => res.json())
        .then(roles => {
            roles.forEach(role => {
                let el = document.createElement("option");
                el.text = role.name.substring(5);
                el.value = role.id;
                $('#rolesDeleteUser')[0].appendChild(el);
            })
        });
}

function deleteUser() {
    const deleteForm = document.forms["formDelete"];
    deleteForm.addEventListener("submit", ev => {
        ev.preventDefault();
        fetch("http://localhost:8080/rest_api/admin/delete/" + deleteForm.id.value, {
            method: 'POST',
            headers: {
                'Content-Type': 'application/json'
            }
        })
            .then(() => {
                $('#deleteFormCloseButton').click();
                allUsers();
            })
    })
}