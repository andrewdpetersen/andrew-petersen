const adminManifestForm = document.getElementById("admin_flight_manifest");//
adminManifestForm.addEventListener("submit",function(event) {
    event.preventDefault();//prevents the default "submit" event
    SubmitAdminFlightManifestForm();
});

async function SubmitAdminFlightManifestForm() {
    const manifestFlight_id = adminManifestForm.querySelector("#manifest_flightID");
    let response = await fetch("http://localhost:8080/Project1-Backend/flights", {
        method: "GET",
        headers: {"Content-Type": "application/json",
            "Servlet-action": "AdminFlightManifest",
            "flightID": manifestFlight_id.value},
    });
    var queryString = "?flightID="+manifestFlight_id.value;
    window.location.href = "AdminViewManifest.html"+queryString;
}

// TODO: add response logic if necessary
//The only times we need a response: user-Flights, admin-Manifest, admin-Flights

//let json = response.json();
//And the response logic goes here