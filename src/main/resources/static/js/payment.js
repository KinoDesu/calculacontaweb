const serviceInput = document.getElementById("service_input");
const cancelBtn = document.getElementById("cancel_btn");
const endBtn = document.getElementById("end_btn");

const baseUrl = window.location.origin;

endBtn.addEventListener("click", (e) => {
    e.preventDefault();

    if (!serviceInput.checkValidity()) {
        serviceInput.reportValidity();
        return;
    }

    let end = window.confirm("Deseja encerrar a conta?");

    if (end) {
        document.cookie = "userId=; path=/; expires=Thu, 01 Jan 1970 00:00:00 UTC;";
        location.href = `${baseUrl}`;
    } else {
        return;
    }

});

cancelBtn.addEventListener("click", (e) => {
    e.preventDefault();

    location.href = `${baseUrl}/room/${roomCode}`;
});

serviceInput.addEventListener("input", (e) => {
    Array.from(document.querySelectorAll(".user_amount"))
        .filter(e => e.innerText)
        .forEach(e => {
            if (serviceInput.value < 0) {
                serviceInput.value = 0;
            }

            let serviceValue = serviceInput.value;

            axios.get(`${baseUrl}/api/billing/calculate/${e.id}?service=${serviceValue ? serviceValue : 0}`)
                .then(response => {
                    e.innerText = response.data.toString().replace(".", ",");
                    updateTotal(serviceValue ? serviceValue : 0);
                });
        })
});

function getUserList() {
    axios.get(`${baseUrl}/api/user?roomCode=${roomCode}`)
        .then(response => {
            console.log(response);

            response.data.forEach((user) => {
                showUser(user);
            });

        })
        .catch(error => {
            console.error(error);
        })
}

function showUser(user) {
    let userListSection = document.getElementById("user_box_section");

    let userBox = document.createElement("div");
    userBox.setAttribute("class", "user_billing_box");
    userListSection.appendChild(userBox);

    let userName = document.createElement("h3");
    userName.setAttribute("class", "secondary_text");
    userName.innerHTML = user.name;
    userBox.appendChild(userName);

    let userPriceDiv = document.createElement("div");
    userPriceDiv.setAttribute("class", "user_price_div");
    userBox.appendChild(userPriceDiv);

    let sign = document.createElement("h3");
    sign.setAttribute("class", "secondary_text");
    sign.innerText = "R$";
    userPriceDiv.appendChild(sign);

    let userAmount = document.createElement("h3");
    userAmount.setAttribute("class", "secondary_text user_amount");
    userAmount.setAttribute("id", `${user.userId}`);
    userAmount.innerText = user.totalAmount.toString().replace(".", ",");
    userPriceDiv.appendChild(userAmount);

}

function calculateSubtotal() {
    axios.get(`${baseUrl}/api/billing/subtotal/${roomCode}`)
        .then(response => {
            document.getElementById("subtotal_value").innerText = `R$ ${response.data.toString().replace(".", ",")}`;
        })
        .catch(error => {
            console.error(error);
        })
}

function updateTotal(serviceValue) {
    axios.get(`${baseUrl}/api/billing/total/${roomCode}?service=${serviceValue}`)
        .then(response => {
            document.getElementById("total_value").innerText = `R$ ${response.data.toString().replace(".", ",")}`;
        });
}

function setSubtotal() {
    axios.get(`${baseUrl}/api/billing/subtotal/${roomCode}`)
        .then(response => {
            document.getElementById("subtotal_value").innerText = `R$ ${response.data.toString().replace(".", ",")}`;
        });
}

getUserList();
updateTotal(0);
setSubtotal();