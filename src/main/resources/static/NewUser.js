const addUserForm = document.querySelector('.add-user-form');

$(async function () {
    await getRoles();
})

async function getRoles() {
    await fetch("http://localhost:8080/admin/api/roles")
        .then(res => res.json())
        .then(data => data.forEach(
            role => {
                let element = document.createElement("option");
                element.text = role.name;
                element.value = role.id;
                $('#roles-value')[0].appendChild(element)
            }
        ))
}

addUserForm.addEventListener('submit', (e) => {
    e.preventDefault();
    const rolesSelector = document.getElementById('roles-value').options;
    const rolesValue = [];
    for (let i = 0; i < rolesSelector.length; i++) {
        if (rolesSelector[i].selected) rolesValue.push({
            id: rolesSelector[i].value,
            name: rolesSelector[i].text
        })
    }
    fetch("http://localhost:8080/admin/api/", {
        method: 'POST',
        headers: {
            'Content-Type': 'application/json'
        },
        body: JSON.stringify({
            firstName: document.getElementById('first-name-value').value,
            lastName: document.getElementById('last-name-value').value,
            age: document.getElementById('age-value').value,
            email: document.getElementById('email-value').value,
            password: document.getElementById('password-value').value,
            roles: rolesValue
        })
    })
        .then(() => {
            addUserForm.reset();
            users();
            $('#allUsersTable').click();
        })
})