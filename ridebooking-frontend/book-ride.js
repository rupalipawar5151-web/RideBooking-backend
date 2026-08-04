
const loggedInUser = JSON.parse(localStorage.getItem("loggedInUser"));

if (!loggedInUser) {
    alert("Please login first!");
    window.location.href = "login.html"; 
}


let currentRideId = null;

const bookRideForm = document.getElementById("bookRideForm");

bookRideForm.addEventListener("submit", async function (event) {
    event.preventDefault();

    const requestBody = {
        userId: loggedInUser.id,
        pickupLocation: document.getElementById("pickupLocation").value,
        dropLocation: document.getElementById("dropLocation").value,
        distance: parseFloat(document.getElementById("distance").value)
    };

    try {
        const response = await fetch(`${BASE_URL}/rides/book`, {
            method: "POST",
            headers: { "Content-Type": "application/json" },
            body: JSON.stringify(requestBody)
        });

        const result = await response.json();
        const messageDiv = document.getElementById("responseMessage");

        if (result.success) {
            messageDiv.innerHTML = `<div class="message success">✅ ${result.message}</div>`;
            currentRideId = result.data.id;
            showRideCard(result.data);
            bookRideForm.reset();
        } else {
            messageDiv.innerHTML = `<div class="message error">❌ ${result.message}</div>`;
        }

    } catch (error) {
        document.getElementById("responseMessage").innerHTML =
            `<div class="message error">❌ Could not connect to server.</div>`;
    }
});


function showRideCard(ride) {
    const cardDiv = document.getElementById("activeRideCard");

    cardDiv.innerHTML = `
        <div class="card-fare">
            <p>Fare Amount</p>
            <div class="amount">₹${ride.fare}</div>
            <p style="margin-top:10px;">
                🚕 Driver: <strong>${ride.driver.driverName}</strong> (${ride.driver.vehicleNumber})
            </p>
            <p style="margin-top:6px;">
                Status: <span class="badge badge-busy">${ride.rideStatus}</span>
            </p>

            <div style="margin-top:16px; display:flex; gap:8px;">
                <button class="btn-small" onclick="startRide()">Start Ride</button>
                <button class="btn-small" onclick="completeRide()">Complete</button>
                <button class="btn-small btn-danger" onclick="cancelRide()">Cancel</button>
            </div>
        </div>
    `;
}

// ===== Start Ride =====
async function startRide() {
    await updateRideStatus("start");
}

// ===== Complete Ride =====
async function completeRide() {
    await updateRideStatus("complete");
}

// ===== Cancel Ride =====
async function cancelRide() {
    await updateRideStatus("cancel");
}

// ===== Common function — Start/Complete/Cancel सगळ्यांसाठी वापरतो =====
async function updateRideStatus(action) {
    if (!currentRideId) {
        alert("No active ride found.");
        return;
    }

    try {
        const response = await fetch(`${BASE_URL}/rides/${currentRideId}/${action}`, {
            method: "PUT"
        });

        const result = await response.json();

        if (result.success) {
            showRideCard(result.data); // card refresh कर नवीन status सोबत
        } else {
            alert(result.message);
        }

    } catch (error) {
        alert("Could not connect to server.");
    }
}