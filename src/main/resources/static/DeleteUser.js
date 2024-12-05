let deleteUserForm = document.forms["deleteUserForm"];

$('#delete').on('show.bs.modal', e => {
    let button = $(e.relatedTarget);
    showDataForDelete(button.data('id'));
})

async function showDataForDelete(id) {
    let user = await getUser(id);
    deleteUserForm.id.value = user.id;
    deleteUserForm.firstName.value = user.firstName;
    deleteUserForm.lastName.value = user.lastName;
    deleteUserForm.age.value = user.age;
    deleteUserForm.email.value = user.email;
    $('#roles-delete').empty();
    await fetch("http://localhost:8080/admin/api/roles")
        .then(res => res.json())
        .then(data => {
            data.forEach(role => {
                let selectedRoles = false;
                for (let i = 0; i < user.roles.length; i++) {
                    if (user.roles[i].name === role.name) {
                        selectedRoles = true;
                        break;
                    }
                }
                let element = document.createElement("option");
                element.text = role.name;
                element.value = role.id;
                if (selectedRoles) element.selected = true;
                $('#roles-delete')[0].appendChild(element);
            })
        });
}

deleteUserForm.addEventListener("submit", e => {
    e.preventDefault();
    fetch("http://localhost:8080/admin/api/" + deleteUserForm.id.value, {
        method: 'DELETE'
    })
        .then(() => {
            $('#delete-button').click();
            users();
        })
})

async function getUser(id) {
    return await (await fetch("http://localhost:8080/admin/api/" + id)).json();
}
