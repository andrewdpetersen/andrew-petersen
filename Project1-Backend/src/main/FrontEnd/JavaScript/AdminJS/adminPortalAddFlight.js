const adminAddForm = document.getElementById("add_flight");
adminAddForm.addEventListener("submit",function(event) {
    event.preventDefault();//prevents the default "submit" event
    SubmitAdminAddFlightForm();
    adminAddForm.reset();
});

async function SubmitAdminAddFlightForm() {
    const departure_city = adminAddForm.querySelector("#departureCity");
    const arrival_city = adminAddForm.querySelector("#arrivalCity");
    const flight_date = adminAddForm.querySelector("#flightDate");
    const flight_time = adminAddForm.querySelector("#flightTime");
    let newFlight = {//creates an object in JSON format
        "departureCity": departure_city.value,
        "arrivalCity": arrival_city.value
        //"flightDate": flight_date.value,
        //"flightTime": flight_time.value
    }
    let response = await fetch("http://localhost:8080/Project1-Backend/flights", {
        method: "POST",
        headers: {"Content-Type": "application/json",
        "Servlet-action": "AdminScheduleFlight"},
        body: JSON.stringify(newFlight)//makes the json into a string to send
    });
}

//The only times we need a response: user-Flights, admin-ViewManifest, admin-ViewFlights

//let json = response.json();
//And the response logic goes here