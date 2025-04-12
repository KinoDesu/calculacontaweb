const createButton = document.getElementById('create_room_btn');
const enterButton = document.getElementById('enter_room_btn');
const baseUrl = window.location.origin;

createButton.addEventListener('click', (e) => {
    e.preventDefault();
    location.href = `${baseUrl}/create`;
});

enterButton.addEventListener('click', (e) => {
    e.preventDefault();
    location.href = `${baseUrl}/enter`;
});
