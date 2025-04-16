const orderSection = document.getElementById("order_section");
const roomCodeText = document.getElementById("room_code");

const baseUrl = window.location.origin;

function getOrderList() {
    axios.get(`${baseUrl}/api/order/${roomCode}`)
        .then(response => {
            console.log(response);

            if (response.data.length > 0) {
                payBtn.disabled = false;
            }

            response.data.forEach(order => {
                showOrder(order);
            });

        })
        .catch(error => {
            console.error(error);
        })
}

getOrderList();

function showOrder(order) {

    let orderDetail = document.createElement("details");
    orderDetail.setAttribute("class", "order_details");
    orderDetail.setAttribute("id", `detail_${order.orderId}`);
    let orderSummary = document.createElement("summary");
    let orderName = document.createElement("h3");
    orderName.setAttribute("class", "secondary_text order_name");
    orderName.innerText = order.name;


    let orderPrice = document.createElement("h3");
    orderPrice.setAttribute("class", "secondary_text order_price");
    orderPrice.innerText = `${order.quantity}x R$${order.unitPrice.toString().replace(".", ",")}`;

    orderSummary.appendChild(orderName);
    orderSummary.appendChild(document.createElement("div"));
    orderSummary.appendChild(orderPrice);
    orderDetail.appendChild(orderSummary);

    let orderDetailsUserSection = document.createElement("section");
    orderDetailsUserSection.setAttribute("class", "order_details_users");
    orderDetail.appendChild(orderDetailsUserSection);

    order.userList.forEach(user => {
        let userBox = document.createElement("div");
        userBox.setAttribute("class", "order_user_box");

        let userName = document.createElement("h3");
        userName.setAttribute("class", "secondary_text user_name");
        userName.innerText = `${user.name}`

        let userOrderPrice = document.createElement("h3");
        userOrderPrice.setAttribute("class", "secondary_text user_price");
        userOrderPrice.innerText = `R$ ${order.pricePerPerson.toString().replace(".", ",")}`

        userBox.appendChild(userName);
        userBox.appendChild(document.createElement("div"));
        userBox.appendChild(userOrderPrice);

        orderDetailsUserSection.appendChild(userBox);
    });

    let buttonMenu = document.createElement("div");
    buttonMenu.setAttribute("class", "order_detail_btn_menu");
    orderDetail.appendChild(buttonMenu);

    let editButton = document.createElement("button");
    editButton.setAttribute("class", "edit_order_btn");
    editButton.innerText = "EDITAR";

    let deleteButton = document.createElement("button");
    deleteButton.setAttribute("class", "remove_order_btn");
    deleteButton.innerText = "REMOVER";

    buttonMenu.appendChild(editButton);
    buttonMenu.appendChild(deleteButton);

    orderSection.appendChild(orderDetail);

    deleteButton.addEventListener("click", (e) => {
        e.preventDefault();

        axios.delete(`${baseUrl}/api/order/${order.orderId}`)
            .then(() => alert("Pedido removido com sucesso!"))
            .catch(() => alert("Falha ao remover pedido..."));
    });

    editButton.addEventListener("click", (e) => {
        e.preventDefault();

        alert("Em desenvolvimento!");
    });
}

function removeOrder(orderId) {
    document.getElementById(`detail_${orderId}`).remove();
}

const orderBtn = document.getElementById("make_order_btn");
const payBtn = document.getElementById("pay_btn");

orderBtn.addEventListener("click", (e) => {
    e.preventDefault();

    location.href = `${baseUrl}/order/${roomCode}`;

});

payBtn.addEventListener("click", (e) => {
    e.preventDefault();
    location.href = `${baseUrl}/billing/${roomCode}`;
});

roomCodeText.addEventListener("click", (e) => {

    const texto = roomCodeText.innerText;

    const tempTextArea = document.createElement("textarea");
    tempTextArea.value = texto;
    document.body.appendChild(tempTextArea);
    tempTextArea.select();
    tempTextArea.setSelectionRange(0, 99999);

    try {
        const sucesso = document.execCommand("copy");
        alert(sucesso ? "Código da sala copiado!" : "Erro ao copiar.");
    } catch (err) {
        alert("Erro ao copiar.");
    }

    document.body.removeChild(tempTextArea);
});

const socket = new SockJS('/ws');
const stompClient = Stomp.over(socket);

stompClient.connect({}, () => {
    stompClient.subscribe(`/topic/add/room/${roomCode}`, (message) => {
        let order = JSON.parse(message.body);

        showOrder(order);
    });
});

stompClient.connect({}, () => {
    stompClient.subscribe(`/topic/remove/room/${roomCode}`, (message) => {
        removeOrder(message.body);
    });
});