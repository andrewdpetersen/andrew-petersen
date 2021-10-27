let userportalviewflightsform = document.getElementById("user_view_flights");//
userportalviewflightsform.addEventListener("submit",function(event) {
    event.preventDefault();//prevents the default "submit" event
    SubmitUPVFForm();
});

function SubmitUPVFForm() {
    const selectDepCity = userportalviewflightsform.querySelector("#select_departureCity");
    const sac = userportalviewflightsform.querySelector("#select_arrivalCity");

    let arrive_City = sac.value;
    let depart_City = selectDepCity.value;

    window.location.href = "UserFlights.html?arrival_city="+`${arrive_City}`+"&departure_city="+`${depart_City}`
}