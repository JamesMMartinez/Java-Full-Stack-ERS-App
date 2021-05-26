let apiURL = 'http://localhost:8081/ERSApp/employee-user';

document.getElementById('viewAccInfo').onclick = viewAccInfo; 

async function viewAccInfo(){

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
 

    dataSection.innerHTML = `
    <br>
    <h6>User ID: ${response.userId} \u00A0\u00A0\u00A0Name: ${response.lastName}, ${response.firstName}\u00A0\u00A0\u00A0Username: ${response.username} \u00A0\u00A0\u00A0Email: ${response.email} \u00A0\u00A0\u00A0Password: ${response.password}<h6>
        
    `

    
    
 
 }