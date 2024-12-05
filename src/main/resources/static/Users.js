$(async function() {
    await users();
});
const table = $('#users');
async function users() {
    table.empty()
    fetch("http://localhost:8080/admin/api")
        .then(res => res.json())
        .then(data => {
            data.forEach(user => {
                let users = `$(
                        <tr>
                            <td>${user.id}</td>
                            <td>${user.firstName}</td>
                            <td>${user.lastName}</td>
                            <td>${user.age}</td>
                            <td>${user.email}</td>
                            <td>${user.roles.map(role => " " + role.name)}</td>
                            <td>
                                <button type="button" class="btn btn-info" data-toggle="modal" data-action="edit"
                                 data-id="${user.id}" data-target="#edit">Edit</button>
                            </td>
                            <td>
                                <button type="button" class="btn btn-danger" data-toggle="modal" data-action="delete"
                                 data-id="${user.id}" data-target="#delete">Delete</button>
                            </td>
                        </tr>)`;
                table.append(users);
            })
        })
}