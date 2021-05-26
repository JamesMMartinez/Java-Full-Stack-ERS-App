let apiURL = 'http://localhost:8081/ERSApp/all-requests?status=';

document.getElementById('getAllRequests').onclick = getAllRequests; 
document.getElementById('getPendRequests').onclick = getPendRequests; 
document.getElementById('getResoRequests').onclick = getResoRequests; 

async function getAllRequests(){
    
    // Retrieving the user input, in this case the pokemon id to be retrieved
    let status = 'all';

    let response1 = await fetch(apiURL+status);

    if(response1.status >= 200 && response1.status <300){
        let data = await response1.json();
        populateData(data);
       } else{
              document.getElementById('data').innerHTML = "<br><img src=https://loudtechie.com/wp-content/uploads/2017/06/screenshot-www.loudtechie.com-2017-06-27-23-03-46.png />";
        }
}

async function getPendRequests(){
    
    // Retrieving the user input, in this case the pokemon id to be retrieved
    let status = 'pending';

    let response2 = await fetch(apiURL+status);

    if(response2.status >= 200 && response2.status <300){
        let data = await response2.json();
        populateData(data);
       } else{
              document.getElementById('data').innerHTML = "<br><img src=https://loudtechie.com/wp-content/uploads/2017/06/screenshot-www.loudtechie.com-2017-06-27-23-03-46.png />";
        }
}

async function getResoRequests(){
    
    // Retrieving the user input, in this case the pokemon id to be retrieved
    let status = 'resolved';

    let response3 = await fetch(apiURL+status);

    if(response3.status >= 200 && response3.status <300){
        let data = await response3.json();
        populateData(data);
       } else{
              document.getElementById('data').innerHTML = "<br><img src=https://loudtechie.com/wp-content/uploads/2017/06/screenshot-www.loudtechie.com-2017-06-27-23-03-46.png />";
        }
}

function populateData(response2){

    console.log(response2);
 
    let dataSection = document.getElementById('data');
 
    dataSection.innerHTML ='';
 
    for(var i = 0; i < response2.length; i++){
        dataSection.innerHTML += `
        <br>
        <h6>Reimbursement ID: ${response2[i].reimbursementId} \u00A0\u00A0\u00A0Author ID: ${response2[i].authorId} \u00A0\u00A0\u00A0Amount: $${response2[i].amount} \u00A0\u00A0\u00A0Time Submitted: ${response2[i].timeSubmitted} \u00A0\u00A0\u00A0Reimbursement Type: ${response2[i].reimbType} \u00A0\u00A0\u00A0Status: ${response2[i].reimbStatus}<h6>
        <h6>Resolved By: ${response2[i].resolverIdAndName} \u00A0\u00A0\u00A0Time Resolved: ${response2[i].timeResolved}<h6>
        <h6>Description: ${response2[i].description}<h6><br>
       `
 
    }
    
 
 }