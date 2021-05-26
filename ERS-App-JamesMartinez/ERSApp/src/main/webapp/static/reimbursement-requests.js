let apiURL = 'http://localhost:8081/ERSApp/manager-reimbursement?userId=';

document.getElementById('getReimbursements').onclick = getReimbursements;  // Assign functionality

async function getReimbursements(){
    
    // Retrieving the user input, in this case the pokemon id to be retrieved
    let userId = document.getElementById('userId').value;

    let response = await fetch(apiURL+userId);

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
       <h6>Reimbursement ID: ${response[i].reimbursementId} \u00A0\u00A0\u00A0Author ID: ${response[i].authorId} \u00A0\u00A0\u00A0Amount: $${response[i].amount} \u00A0\u00A0\u00A0Time Submitted: ${response[i].timeSubmitted} \u00A0\u00A0\u00A0Reimbursement Type: ${response[i].reimbType}<h6>
       <h6>Description: ${response[i].description}<h6><br>
      `

   }
   

}