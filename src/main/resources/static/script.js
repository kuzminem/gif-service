function getStatusGif() {
    var ratesSymbols = d("ratesSymbols").value.toUpperCase();
    if (ratesSymbols.length === 3) {
        d("statusGif").style.display = "none";
        sendRequest(ratesSymbols);
    }
}

async function sendRequest(ratesSymbols) {
    const response = await fetch("/status/" + ratesSymbols, {
        headers: {
            "Accept": "application/json",
            "Content-Type": "application/json; charset=utf-8"
        },
        method: "GET"
    });
    if (response.ok) {
        const a = await response.json();
        d("statusGif").src = a.data.url;
        d("statusGif").style.display = "block";
    } else {
        const a = await response.json();
        alert(a.status + ": " + a.data.exception);
    }
}

function d(id) {
    return document.getElementById(id);
}
