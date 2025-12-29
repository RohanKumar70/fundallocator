document.getElementById("allocationForm").addEventListener("submit", function(event) {
    event.preventDefault(); // PREVENT PAGE RELOAD

    const payload = {
        symbols: document.getElementById("symbols").value
            .split(",")
            .map(s => s.trim()),
        totalAmount: parseFloat(document.getElementById("amount").value),
        riskLevel: document.getElementById("risk").value
    };

    console.log("Sending payload:", payload);

    fetch("/api/allocate", {
        method: "POST",
        headers: { "Content-Type": "application/json" },
        body: JSON.stringify(payload)
    })
        .then(response => {
            if (!response.ok) {
                throw new Error("HTTP error " + response.status);
            }
            return response.json();
        })
        .then(data => {
            const resultDiv = document.getElementById("result");
            resultDiv.innerHTML = `<pre>${JSON.stringify(data, null, 2)}</pre>`;
        })
        .catch(error => {
            console.error("Request failed:", error);
            alert("Error calling backend. Check console: " + error);
        });
});
