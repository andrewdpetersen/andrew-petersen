SubmitUVFlights();

async function SubmitUVFlights() {
    const queryString = window.location.search;

    const depart_City = new URLSearchParams(queryString).get("departure_city");
    const arrive_City = new URLSearchParams(queryString).get("arrival_city");

    let response = await fetch("http://localhost:8080/Project1-Backend/flights", {
        method: "GET",//NO BODY on GET requests
        headers: {
            "Content-Type": "application/json",
            "Servlet-action": "UserViewFlights",
            "selectDepartureCity": depart_City,
            "selectArrivalCity": arrive_City
        },
    });
    let jsonFlightSchedule = await response.json();

    let flightSchedule = document.getElementById("userFlightScheduleTable");
    for (let element of jsonFlightSchedule) {
        let tr = flightSchedule.insertRow(-1);
        for (let key in element) {
            let cell = tr.insertCell(-1);
            cell.innerHTML = element[key];

        }
    }
}