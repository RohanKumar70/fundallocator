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
        headers: {"Content-Type": "application/json"},
        body: JSON.stringify(payload)
    })
        .then(response => {
            if (!response.ok) {
                throw new Error("HTTP error " + response.status);
            }
            return response.json();
        })
        .then(data => {
            console.log(data);
            const resultDiv = document.getElementById("result");
            if (!Array.isArray(data) || data.length == 0) {
                resultDiv.innerHTML = "<p> No allocation results, please try again.</p>";
                return;
            }

            let html = `
                <h2>Allocation Results</h2>
                <table>
                    <thead>
                        <tr>
                            <th>Symbol</th>
                            <th>Risk Level</th>
                            <th>Allocated Amount ($)</th>
                            <th> Price ($)</th>
                            <th>Volatility</th>
                        </tr>
                    </thead>
                    <tbody>
            `;
            data.forEach(stock => {
                html += `
                    <tr>
                        <td>${stock.symbol}</td>
                        <td>${stock.riskLevel}</td>
                        <td>${stock.allocatedAmount.toFixed(2)}</td>
                        <td>${stock.currentPrice.toFixed(2)}</td>
                        <td>${(stock.volatility * 100).toFixed(2)}%</td>
                    </tr>
                `;
            });
            html += `
                    </tbody>
                </table>
            `;
            resultDiv.innerHTML = html;

        });
});
