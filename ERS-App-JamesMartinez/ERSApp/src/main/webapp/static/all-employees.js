let apiURL = 'http://localhost:8081/ERSApp/manager-user';

document.getElementById('getAllEmps').onclick = getAllEmps; 

async function getAllEmps(){

    let response = await fetch(apiURL);

    if(response.status >= 200 && response.status <300){
        let data = await response.json();
        populateData(data);
       } else{
              document.getElementById('data').innerHTML = "<br><img src=https://loudtechie.com/wp-content/uploads/2017/06/screenshot-www.loudtechie.com-2017-06-27-23-03-46.png />";
        }
}

function populateData(response){

    console.log(response);
 
    let dataSection = document.getElementById('data');
 
    dataSection.innerHTML ='';
 
    for(var i = 0; i < response.length; i++){
        dataSection.innerHTML += `
        <br>
        <h6>\u00A0\u00A0\u00A0User ID: ${response[i].userId} \u00A0\u00A0\u00A0Name: ${response[i].lastName}, ${response[i].firstName}\u00A0\u00A0\u00A0Username: ${response[i].username} \u00A0\u00A0\u00A0Email: ${response[i].email}<h6>
        
       `
 
    }
    
 
 }