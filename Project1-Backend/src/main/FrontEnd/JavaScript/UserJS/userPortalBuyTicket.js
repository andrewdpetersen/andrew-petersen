var dbg = true;
if(dbg){console.log("DEBUG- ScriptReached");}
let userportalbuyticketsform = document.getElementById("user_purchase_tickets");//T
userportalbuyticketsform.addEventListener("submit",function(event) {
    event.preventDefault();//prevents the default "submit" event
    if(dbg){console.log("DEBUG- EventListener Added");}
    SubmitUPBTForm();
    userportalbuyticketsform.reset();
});

async function SubmitUPBTForm() {
    const nots = userportalbuyticketsform.querySelector("#numberOfTickets");
    const ufid = userportalbuyticketsform.querySelector("#userFlightID");
    const queryString = window.location.search;
    const user_ID = new URLSearchParams(queryString).get("userID");
    console.log(user_ID);
    let object = {//creates an object in JSON format
        "numberOfTickets": nots.value,//key is the variable we are assigning the value to
        "userFlightID": ufid.value,//the value comes from form input
    }
    let response = await fetch("http://localhost:8080/Project1-Backend/tickets", {
        method: "POST",
        headers: {
            "Content-Type": "application/json",
            "Servlet-action": "UserPurchaseTickets",
            "user_id":user_ID
        },
        //one to add an object, one to update, one to delete, one to get one object, one to get a list
        body: JSON.stringify(object)//makes the json into a string to send
    });

    return response.text().then(function(){
        if(response.status==200) {
            alert("Ticket successfully purchased! Please go to the counter to get your confirmation number.");
        }else{
            alert("The flight you selected has already departed");
        }
    });
}

// TODO: add response logic if necessary - confirmation number... or "You've purchased xx tickets for flight y from Chicago to Miami
//The only times we need a response: user-Flights, admin-Manifest, admin-Flights

//let json = response.json();
//And the response logic goes here