const form = document.getElementById("order_form");

const nameInput = document.getElementById("name_input");
const priceInput = document.getElementById("price_input");
const quantityInput = document.getElementById("quantity_input");
const cancelBtn = document.getElementById("cancel_btn");
const orderBtn = document.getElementById("order_btn");

const baseUrl = window.location.origin;

orderBtn.addEventListener("click", (e) => {
    e.preventDefault();

    if (!nameInput.checkValidity()) {
        nameInput.reportValidity();
        return;
    }

    if (!priceInput.checkValidity()) {
        priceInput.reportValidity();
        return;
    }

    if (!quantityInput.checkValidity()) {
        quantityInput.reportValidity();
        return;
    }

    let checkboxList = document.querySelectorAll(".user_input");
    let selectedList = Array.from(checkboxList).filter(checkbox => checkbox.checked).map(x => x.defaultValue)

    if (selectedList.length <= 0) {
        alert('Selecione pelo menos um usuário!');
        return;
    }

    let order = {
        name: nameInput.value,
        unitPrice: priceInput.value,
        quantity: quantityInput.value,
        roomCode: roomCode,
        userList: selectedList
    }

    axios.post(`${baseUrl}/api/order`, order)
        .then(response => {
            location.href = `${baseUrl}/room/${roomCode}`;
        })
        .catch(error => {
            console.error(error);
            if (error.status == 404) {
                window.alert("Falha ao registrar pedido");
            }
        });
});

cancelBtn.addEventListener("click", (e) => {
    e.preventDefault();

    location.href = `${baseUrl}/room/${roomCode}`
});

function getUserList() {
    axios.get(`${baseUrl}/api/user?roomCode=${roomCode}`)
        .then(response => {
            console.log(response);

            response.data.forEach((user, index) => {
                showUser(user, index);
            });

        })
        .catch(error => {
            console.error(error);
        })
}

function showUser(user, index) {
    let userListSpace = document.getElementById("user_list");

    let userInputDiv = document.createElement("div");
    userInputDiv.setAttribute("class", "user_input_div");
    userListSpace.appendChild(userInputDiv);

    let checkbox = document.createElement("input");
    checkbox.setAttribute("type", "checkbox");
    checkbox.setAttribute("name", "user_input");
    checkbox.setAttribute("class", "user_input");
    checkbox.setAttribute("id", `user_${index}_input`);
    checkbox.setAttribute("value", `${user.userId}`);

    userInputDiv.appendChild(checkbox);

    let label = document.createElement("label");
    label.setAttribute("for", `user_${index}_input`);
    label.setAttribute("class", "user_input_lbl default_input_lbl");
    label.innerText = user.name;

    userInputDiv.appendChild(label);
}

getUserList();

if (editOrderId) {
    orderBtn.innerText = "ATUALIZAR PEDIDO";
    axios.get(`${baseUrl}/api/order/${editOrderId}`)
        .then(response => {
            console.log(response);

            
        })
        .catch(error => {
            console.error(error);
        })

}