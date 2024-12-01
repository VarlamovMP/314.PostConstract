// script.js

function activateButton(activeBtnId, buttons) {
    buttons.forEach(function (button) {
        button.classList.remove('active-button');
    });
    document.getElementById(activeBtnId).classList.add('active-button');
}

document.getElementById('adminBtn').addEventListener('click', function () {
    activateButton('adminBtn', [document.getElementById('adminBtn'), document.getElementById('userBtn')]);
    document.getElementById('adminPanel').style.display = 'block';
    document.getElementById('newUserContent').style.display = 'none';
    document.getElementById('userContent').style.display = 'none';
    document.getElementById('usersTable').style.display = 'none';
    document.getElementById('adminPanelTitle').style.display = 'block';
    document.getElementById('userControlButtons').style.display = 'block';
});

document.getElementById('userBtn').addEventListener('click', function () {
    activateButton('userBtn', [document.getElementById('adminBtn'), document.getElementById('userBtn')]);
    document.getElementById('adminPanel').style.display = 'none';
    document.getElementById('newUserContent').style.display = 'none';
    document.getElementById('userContent').style.display = 'block'; // Показать пустое окно
    document.getElementById('adminPanelTitle').style.display = 'none';
    document.getElementById('userControlButtons').style.display = 'none';
});

document.getElementById('userTableBtn').addEventListener('click', function () {
    activateButton('userTableBtn', [document.getElementById('userTableBtn'), document.getElementById('newUserBtn')]);
    document.getElementById('usersTable').style.display = 'table';
    document.getElementById('newUserContent').style.display = 'none';
    document.getElementById('userContent').style.display = 'none';
});

document.getElementById('newUserBtn').addEventListener('click', function () {
    activateButton('newUserBtn', [document.getElementById('userTableBtn'), document.getElementById('newUserBtn')]);
    document.getElementById('usersTable').style.display = 'none';
    document.getElementById('newUserContent').style.display = 'block';
    document.getElementById('userContent').style.display = 'none';
});

// Изначально показываем панель администратора с заголовками
document.getElementById('adminPanel').style.display = 'block';
document.getElementById('usersTable').style.display = 'none';
document.getElementById('newUserContent').style.display = 'none';
document.getElementById('userContent').style.display = 'none';
document.getElementById('adminPanelTitle').style.display = 'block';
document.getElementById('userControlButtons').style.display = 'block';