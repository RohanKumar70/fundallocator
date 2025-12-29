function allocate() {
    const payload = {
        test: "hello",
        number: 123
    };

    fetch("/api/allocate", {
        method: "POST",
        headers: {
            "Content-Type" : "applications/json"
        },
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
            console.error("Request failed: " , error);
            alert("Error calling backend. Check console.");
        });
}