$(async function () {
    await user();
});

async function user() {
    fetch("http://localhost:8080/user/api")
        .then(res => res.json())
        .then(data => {
            $('#username').append(data.email);
            let roles = data.roles.map(role => " " + role.name);
            $('#roles').append(roles);
            let user = `$(
            <tr>
                <td>${data.id}</td>
                <td>${data.firstName}</td>
                <td>${data.lastName}</td>
                <td>${data.age}</td>
                <td>${data.email}</td>
                <td>${roles}</td>)`;
            $('#userInfo').append(user);
        })
}