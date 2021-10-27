let userportalcancelform = document.getElementById("user_cancel_tickets");
userportalcancelform.addEventListener("submit",function(event) {
    event.preventDefault();//prevents the default "submit" event
    SubmitUPCTForm();
    userportalcancelform.reset();
});

async function SubmitUPCTForm() {
    const ucts = userportalcancelform.querySelector("#user_cancel_ticket_id");
    let object = {//creates an object in JSON format
        "userCancelTickeID": ucts.value,//assigning the value to send to servlet via fetch.
    }

    let response = await fetch("http://localhost:8080/Project1-Backend/tickets", {
        method: "POST",
        headers: {"Content-Type": "application/json",
        "Servlet-action" : "UserCancelTicket"
        },
        //one to add an object, one to update, one to delete, one to get one object, one to get a list
        body: JSON.stringify(object)//makes the json into a string to send
    });

    return response.text().then(function(){
        if(response.status==200){
            alert(`Ticket cancelled. Thank you for using AirPortal.`);
        }else{
            alert("The flight you selected has already departed");
        }
    });
}