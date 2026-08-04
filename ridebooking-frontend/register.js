
const registerForm = document.getElementById("registerForm");


registerForm.addEventListener("submit", async function (event) {

    
    event.preventDefault();

    
    const fullName = document.getElementById("fullName").value;
    const email = document.getElementById("email").value;
    const phone = document.getElementById("phone").value;
    const password = document.getElementById("password").value;

    
    const requestBody = {
        fullName: fullName,
        email: email,
        phone: phone,
        password: password
    };

    try {
        
        const response = await fetch(`${BASE_URL}/users/register`, {
            method: "POST",
            headers: {
                "Content-Type": "application/json"
            },
            body: JSON.stringify(requestBody)
        });

        
        const result = await response.json();

        const messageDiv = document.getElementById("responseMessage");

        if (result.success) {
            messageDiv.innerHTML = `<div class="message success">✅ ${result.message}</div>`;
            registerForm.reset(); 
        } else {
            messageDiv.innerHTML = `<div class="message error">❌ ${result.message}</div>`;
        }

    } catch (error) {
        
        document.getElementById("responseMessage").innerHTML =
            `<div class="message error">❌ Could not connect to server. Is the backend running?</div>`;
    }
});