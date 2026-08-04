
document.addEventListener("DOMContentLoaded", loadDrivers);


async function loadDrivers() {
    try {
        const response = await fetch(`${BASE_URL}/drivers`);
        const result = await response.json();

        const tableBody = document.getElementById("driversTableBody");
        tableBody.innerHTML = ""; // आधीचं रिकामं कर

        result.data.forEach(driver => {
            const badgeClass = driver.status === "AVAILABLE" ? "badge-available" : "badge-busy";

            const row = `
                <tr>
                    <td>${driver.id}</td>
                    <td>${driver.driverName}</td>
                    <td>${driver.phone}</td>
                    <td>${driver.vehicleNumber} (${driver.vehicleType})</td>
                    <td><span class="badge ${badgeClass}">${driver.status}</span></td>
                    <td>${driver.rating}</td>
                    <td>
                        <button class="btn-small" onclick="toggleStatus(${driver.id}, '${driver.status}')">Toggle Status</button>
                        <button class="btn-small btn-danger" onclick="deleteDriver(${driver.id})">Delete</button>
                    </td>
                </tr>
            `;

            tableBody.innerHTML += row;
        });

    } catch (error) {
        console.error("Error loading drivers:", error);
    }
}


const addDriverForm = document.getElementById("addDriverForm");

addDriverForm.addEventListener("submit", async function (event) {
    event.preventDefault();

    const requestBody = {
        driverName: document.getElementById("driverName").value,
        phone: document.getElementById("phone").value,
        vehicleNumber: document.getElementById("vehicleNumber").value,
        vehicleType: document.getElementById("vehicleType").value
    };

    try {
        const response = await fetch(`${BASE_URL}/drivers`, {
            method: "POST",
            headers: { "Content-Type": "application/json" },
            body: JSON.stringify(requestBody)
        });

        const result = await response.json();
        const messageDiv = document.getElementById("responseMessage");

        if (result.success) {
            messageDiv.innerHTML = `<div class="message success">✅ ${result.message}</div>`;
            addDriverForm.reset();
            loadDrivers(); // table परत refresh कर, नवीन driver दिसेल
        } else {
            messageDiv.innerHTML = `<div class="message error">❌ ${result.message}</div>`;
        }

    } catch (error) {
        document.getElementById("responseMessage").innerHTML =
            `<div class="message error">❌ Could not connect to server.</div>`;
    }
});

// ===== Driver चा Status Toggle कर (AVAILABLE ↔ BUSY) =====
async function toggleStatus(driverId, currentStatus) {
    const newStatus = currentStatus === "AVAILABLE" ? "BUSY" : "AVAILABLE";

    try {
        const response = await fetch(`${BASE_URL}/drivers/${driverId}/status?status=${newStatus}`, {
            method: "PUT"
        });

        const result = await response.json();

        if (result.success) {
            loadDrivers(); // table refresh कर
        } else {
            alert(result.message);
        }

    } catch (error) {
        alert("Could not connect to server.");
    }
}

// ===== Driver Delete कर =====
async function deleteDriver(driverId) {
    const confirmDelete = confirm("Are you sure you want to delete this driver?");
    if (!confirmDelete) return;

    try {
        const response = await fetch(`${BASE_URL}/drivers/${driverId}`, {
            method: "DELETE"
        });

        const result = await response.json();

        if (result.success) {
            loadDrivers(); // table refresh कर
        } else {
            alert(result.message);
        }

    } catch (error) {
        alert("Could not connect to server.");
    }
}