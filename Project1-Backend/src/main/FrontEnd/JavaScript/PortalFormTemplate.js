const form = document.getElementById("enter user id here");//TODO: formID to elementID
form.addEventListener("submit",function(event) {
    event.preventDefault();//prevents the default "submit" event
    const nameOfVariable = form.querySelector("#idOfInput");//TODO: assign variable names and
    const nameOfVariable2 = form.querySelector("#idOfInput2");//TODO: replace ids with correct ids
    const nameOfVariable3 = form.querySelector("#idOfInput3");//from HTML
    SubmitForm();
});

async function SubmitForm() {
    let object = {//creates an object in JSON format
        "key": nameOfVariable.value,//key is the variable we are assigning the value to
        "key2": nameOfVariable2.value,//the value comes from form input
        "key3": nameOfVariable3.value//TODO: make key similar to #idOfInput above
    }
    //TODO: Change end of URL depending on server we want to hit.. /people, /tickets, /flights
    let response = await fetch("http://localhost:8080/Project1-Backend/tickets", {
        method: "POST",//TODO: change method based on whether we are adding/updating data or getting data
        headers: {"Content-Type": "application/json"},//TODO: might want to add a header to tell the servlet what to do
        //one to add an object, one to update, one to delete, one to get one object, one to get a list
        body: JSON.stringify(object)//makes the json into a string to send
    });
}

// TODO: add response logic if necessary
//The only times we need a response: user-Flights, admin-Manifest, admin-Flights

//let json = response.json();
//And the response logic goes here