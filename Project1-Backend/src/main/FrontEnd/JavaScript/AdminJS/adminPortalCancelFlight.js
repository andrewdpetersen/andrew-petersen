const adminCancelForm = document.getElementById("cancel_flight");
adminCancelForm.addEventListener("submit",function(event) {
    event.preventDefault();//prevents the default "submit" event
    SubmitAdminCancelForm();
    adminCancelForm.reset();
});

async function SubmitAdminCancelForm() {
    const flight_id = adminCancelForm.querySelector("#cancel_flightID");
    let cancelledFlight = {//creates an object in JSON format
        "flight_id": flight_id.value
    }
    let response = await fetch("http://localhost:8080/Project1-Backend/flights", {
        method: "POST",
        headers: {"Content-Type": "application/json",
            "Servlet-action": "AdminCancelFlight"},
        body: JSON.stringify(cancelledFlight)//makes the json into a string to send
    });
    return response.text().then(function(){
        if(response.status==200){
            alert(`Flight cancelled. Thank you for using AirPortal.`);
        }else{
            alert("The flight you selected has already departed");
        }
    });
}