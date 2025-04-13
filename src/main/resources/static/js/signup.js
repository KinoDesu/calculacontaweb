if (c) {
    if (c == '0')
        document.getElementById('name_input_div').insertAdjacentHTML('afterend',
            `<div class="default_input_div" id="room_code_input_div">
                <label for="room_code_input" class="default_input_lbl">Código da sala:</label>
                <input type="text" name="room_code_input" id="room_code_input" class="default_input" required minlength="6"
                        maxlength="6">
            </div>`);
}

const baseUrl = window.location.origin;

const nameInput = document.getElementById("name_input");
const roomCodeInput = document.getElementById("room_code_input");
const signupBtn = document.getElementById("signup_btn");
const userId = getCookie("userId");

signupBtn.addEventListener("click", (e) => {
    e.preventDefault();

    if (c == '0') {
        saveUserWithRoomCreated();
    } else {
        saveUserAndRoom();
    }
});

function saveUserWithRoomCreated() {

    if (roomCodeInput) {

        if (!nameInput.checkValidity()) {
            nameInput.reportValidity();
            return;
        }

        if (!roomCodeInput.checkValidity()) {
            roomCodeInput.reportValidity();
            return;
        }

        console.log("nome =>" + nameInput.value);
        console.log("sala =>" + roomCodeInput.value);

        let nameValue = nameInput.value == "" ? null : nameInput.value;
        let roomCodeValue = roomCodeInput.value == "" ? null : roomCodeInput.value;

        let requestBody = {
            name: nameValue,
            roomCode: roomCodeValue
        };

        axios.post(`${baseUrl}/api/user`, requestBody)
            .then(response => {
                let getUserUri = response.headers.location;

                axios.get(getUserUri)
                    .then(response => {
                        document.cookie = `userId=${response.data.userId}; path=/; SameSite=Strict`;
                        location.href = `${baseUrl}/room/${response.data.room.code}`;
                    })
                    .catch(error => {
                        console.error(error);
                        window.alert("Falha ao localizar sala");
                    });
            });
    }

};

function saveUserAndRoom() {


    if (!nameInput.checkValidity()) {
        nameInput.reportValidity();
        return;
    }

    console.log("nome =>" + nameInput.value);

    let nameValue = nameInput.value == "" ? null : nameInput.value;

    let requestBody = {
        name: nameValue,
        roomCode: null
    };

    axios.post(`${baseUrl}/api/user`, requestBody)
        .then(response => {
            let getUserUri = response.headers.location;

            axios.get(getUserUri)
                .then(response => {
                    document.cookie = `userId=${response.data.userId}; path=/; SameSite=Strict`;
                    location.href = `${baseUrl}/room/${response.data.room.code}`;
                })
                .catch(error => {
                    console.error(error);
                    window.alert("Falha ao localizar sala");
                });

        })
        .catch(error => {
            console.error(error);
            window.alert("Falha ao registrar usuário ou sala");
        });

};

function reconnectToRoom() {
    if (!userId) {
        return;
    } else {

        axios.get(`${baseUrl}/api/user/${userId}`)
            .then(response => {
                let user = response.data;
                let reconnectRequest = confirm(`Olá, ${user.name}! Parece que você já está na sala ${user.room.code}\nDeseja reconectar?`)
                if (reconnectRequest) {
                    location.href = `${baseUrl}/room/${user.room.code}`;
                } else {
                    let confirmDeletion = confirm("Tem certeza que não quer reconectar?");

                    if (confirmDeletion) {
                        document.cookie = "userId=; path=/; expires=Thu, 01 Jan 1970 00:00:00 UTC;";
                    }
                    window.location.reload();
                }
            })

    }
}

reconnectToRoom();


function getCookie(name) {
    const cookies = document.cookie.split(';');
    for (let cookie of cookies) {
        const [key, value] = cookie.trim().split('=');
        if (key === name) return decodeURIComponent(value);
    }
    return null;
}