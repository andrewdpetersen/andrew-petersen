const form = document.getElementById("login");
form.addEventListener("submit",function(event) {
    event.preventDefault();//prevents the default "submit" event
    SubmitForm();
});

async function SubmitForm() {
    const unm = form.querySelector("#username");
    const pwd = form.querySelector("#password");
    let object = {//creates an object in JSON format
        "username": unm.value,//key is the variable we are assigning the value to
        "password": pwd.value,//the value comes from form input
    }

    let response = await fetch("http://localhost:8080/Project1-Backend/people", {
        method: "POST",
        headers: {"Content-Type": "application/json",
        "Servlet-action" : "Login"
        },
        body: JSON.stringify(object)
        //one to add an object, one to update, one to delete, one to get one object, one to get a list
    });

    return response.json().then(function(json){
        let access = json.access_level;
        let userID = json.people_id;
        console.log(access);
            if(access ==1){
                window.location.href="UserPortal/UserPortal.html?userID="+`${userID}`;
            }else if(access ==2){
                window.location.href="PilotPortal/PilotPortal.html";
            }else if(access ==3){
                window.location.href="AdminPortal/AdminPortal.html";
            }else{
                alert("Username does not exist!");
            }
    });
}