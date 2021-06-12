/*
(function () {
    var currentUser = "";
    var lastMessage=0;
    document.addEventListener('DOMContentLoaded', fetchMessage(),fetchParticipant(),getCurrentUser());

     function fetchMessage (){
             fetch("getMessages?message="+lastMessage,{
                 method: 'GET',
                 headers: {
                     "Content-Type": "application/json; charset=utf-8",
                 },})
                 .then(res => res.json())
                 .then(resp => {
                     if(resp.isEmptyObject()){
                         console.log("iam empty snifffffffffffff");
                         }
                     else {
                         lastMessage=resp[0].id;
                     var mess = resp.reverse();
                     var message ="";
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

         setTimeout(fetchMessage, 10000);
    }



    function fetchParticipant (){
        fetch("getParticipants?message="+lastMessage,{
            method: 'GET',
            headers: {
                "Content-Type": "application/json; charset=utf-8",
            },})
            .then(res => res.json())
            .then(resp => {
                var message ="";
                    for (var item of resp){
                        message += "<p>" + item.userName + "</p>";

                    }
                    document.getElementById("showParticipants").innerHTML = message;

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
            .then(resp => {
                currentUser =resp[0].user;
                console.log(resp.user)
            })
            .catch(e => {
                document.getElementById("showParticipants").innerHTML = "Some error occured!";
            });

    }

})();
*/
