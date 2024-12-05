let editUserForm = document.forms["editUserForm"];

$('#edit').on('show.bs.modal', e => {
    let button = $(e.relatedTarget);
    showDataForEdit(button.data('id'));
})

async function showDataForEdit(id) {
    let user = await getUser(id);
    editUserForm.id.value = user.id;
    editUserForm.firstName.value = user.firstName;
    editUserForm.lastName.value = user.lastName;
    editUserForm.age.value = user.age;
    editUserForm.email.value = user.email;
    $('#roles-edit').empty();
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
                $('#roles-edit')[0].appendChild(element);
            })
        });
}

editUserForm.addEventListener('submit', (e) => {
    e.preventDefault();
    const rolesSelector = editUserForm.roles.options;
    const rolesValue = [];
    for (let i = 0; i < rolesSelector.length; i++) {
        if (rolesSelector[i].selected) rolesValue.push({
            id: rolesSelector[i].value,
            name: rolesSelector[i].text
        })
    }
    fetch("http://localhost:8080/admin/api/" + editUserForm.id.value, {
        method: 'PATCH',
        headers: {
            'Content-Type': 'application/json'
        },
        body: JSON.stringify({
            id: editUserForm.id.value,
            firstName: editUserForm.firstName.value,
            lastName: editUserForm.lastName.value,
            age: editUserForm.age.value,
            email: editUserForm.email.value,
            password: editUserForm.password.value,
            roles: rolesValue
        })
    }).then(() => {
        $('#edit-button').click();
        users();
    })
})
