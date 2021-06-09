// Your web app's Firebase configuration
// For Firebase JS SDK v7.20.0 and later, measurementId is optional
var firebaseConfig = {
    apiKey: "AIzaSyAgDRh50Rs-0zqwi2z0eb4Af7h4n_xcxAA",
    authDomain: "capstone-project-312804.firebaseapp.com",
    databaseURL: "https://capstone-project-312804-default-rtdb.asia-southeast1.firebasedatabase.app/",
    projectId: "capstone-project-312804",
    storageBucket: "capstone-project-312804.appspot.com",
    messagingSenderId: "194955561187",
    appId: "1:194955561187:web:57760d32369622dc339dd6",
    measurementId: "G-FEGV13FWPN"
};

function pullData() {
    firebase.database().ref('patients').once('value',
    function(AllRecords) {
        AllRecords.forEach(
            function(CurrentRecord) {
                var registrationID = CurrentRecord.val().regId;
                var age = CurrentRecord.val().age_yrs;
                var gender = CurrentRecord.val().sex;
                var symptom = CurrentRecord.val().symptom0;
                var vaccine = CurrentRecord.val().vax_manu;
                var pred = CurrentRecord.val().prediction;

                AddItemsToTable(registrationID, age, gender, symptom, vaccine, pred);
            }
        )
    })
}

window.onload = pullData;

var No = 0;
function AddItemsToTable(registrationID, age, gender, symptom, vaccine, pred) {
    var tbody = document.getElementById('data');
    var trow = document.createElement('tr');
    var td1 = document.createElement('td');
    var td2 = document.createElement('td');
    var td3 = document.createElement('td');
    var td4 = document.createElement('td');
    var td5 = document.createElement('td');
    var td6 = document.createElement('td');
    var td7 = document.createElement('td');

    td1.innerHTML= ++No;
    td2.innerHTML= registrationID;
    td3.innerHTML= age;
    td4.innerHTML= gender;
    td5.innerHTML= symptom;
    td6.innerHTML= vaccine;
    td7.innerHTML= pred;

    trow.appendChild(td1);
    trow.appendChild(td2);
    trow.appendChild(td3);
    trow.appendChild(td4);
    trow.appendChild(td5);
    trow.appendChild(td6);
    trow.appendChild(td7);

    tbody.appendChild(trow);
}
