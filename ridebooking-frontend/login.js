const loginForm = document.getElementById("loginForm");

loginForm.addEventListener("submit", async function (event) {
    event.preventDefault();

    const email = document.getElementById("email").value;
    const password = document.getElementById("password").value;

    const requestBody = { email: email, password: password };

    try {
        const response = await fetch(`${BASE_URL}/users/login`, {
            method: "POST",
            headers: { "Content-Type": "application/json" },
            body: JSON.stringify(requestBody)
        });

        const result = await response.json();
        const messageDiv = document.getElementById("responseMessage");

        if (result.success) {
            messageDiv.innerHTML = `<div class="message success">✅ ${result.message}! Welcome ${result.data.fullName}</div>`;

           
            localStorage.setItem("loggedInUser", JSON.stringify(result.data));

        } else {
            messageDiv.innerHTML = `<div class="message error">❌ ${result.message}</div>`;
        }

    } catch (error) {
        document.getElementById("responseMessage").innerHTML =
            `<div class="message error">❌ Could not connect to server. Is the backend running?</div>`;
    }
});