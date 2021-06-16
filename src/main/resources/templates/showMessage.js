(function () {
    var currentUser = "";
var lastMessage = 0;
var lastConnectUser = 0;
document.addEventListener('DOMContentLoaded', fetchMessage(),fetchParticipant(),getCurrentUser());

function fetchMessage (){
    fetch("getMessages/"+lastMessage,{
        method: 'GET',
        headers: {
            "Content-Type": "application/json; charset=utf-8",
        },})
        .then(res => res.json())
        .then(resp => {
            if(resp.length === 0){
            }
            else {
                lastMessage=resp[0].id;
                var mess = resp.reverse();
                var message = "";
                if (resp.length > 0) {
                    for (var item of mess){
                        message += "<p>" + item.message + "</p>"+"<p>";
                        console.log( item.userName ,currentUser);
                        if( item.userName === currentUser)
                            message += "you" +"</p>";
                        else
                            message += item.userName +"</p>";
                    }
                    document.getElementById("showMessages").innerHTML = message;
                }
            }
        })
        .catch(e => {
            document.getElementById("showMessages").innerHTML = "Some error occured!";
        });
    setTimeout(fetchMessage,10000);
}



function fetchParticipant (){
    fetch("getParticipants/"+lastConnectUser,{
        method: 'GET',
        headers: {
            "Content-Type": "application/json; charset=utf-8",
        },})
        .then(res => res.json())
        .then(resp =>
        {
            if(resp.length === 0){
            }
            else
            {console.log("its work well");
                console.log(resp[resp.length-1].id);
                lastConnectUser = resp[resp.length-1].id;
                var message ="";
                for (var item of resp)
                {
                    message += "<p>" + item.userName + "</p>";

                }
                document.getElementById("showParticipants").innerHTML = message;
            }
        })
        .catch(e => {
            document.getElementById("showParticipants").innerHTML = "Some error occured!";
        });
    setTimeout(fetchParticipant, 10000);
}

function getCurrentUser (){
    console.log("resp.user")
    fetch("getUserName",{
        method: 'GET',
        headers: {
            "Content-Type": "application/json; charset=utf-8",
        },})
        .then(res => res.json())
        .then(resp => {
            currentUser =resp.user;
            console.log(resp.user);
        })
        .catch(e => {
            document.getElementById("showParticipants").innerHTML = "Some error occured!";
        });

}

})();