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

// Initialize Firebase
firebase.initializeApp(firebaseConfig);
firebase.analytics();

const auth = firebase.auth();

let img = document.getElementById("img");
let file = {};

function chooseFile(e) {
    file = e.target.files[0];
}

function signUp() {
    var email = document.getElementById("email");
    var password = document.getElementById("password");

    firebase.auth().createUserWithEmailAndPassword(email.value, password.value).then(auth => {
        var uploadImg = firebase.storage().ref('users/' + auth.user.uid + '/profile.jpg').put(file);
        uploadImg.then(function() {
            console.log('Image upload success');
            alert("Signed Up");
            uploadImg.snapshot.ref.getDownloadURL().then(function(url){
                ImgUrl = url;
                
                firebase.database().ref("users").child(auth.user.uid).set({
                    name : document.getElementById("name").value,
                    email: auth.user.email,
                    empnum : document.getElementById("empnumb").value,
                    inst : document.getElementById("instance").value,
                    userId : auth.user.uid,
                    imgURL : ImgUrl
                }).catch(error => {
                    alert(error.message);
                });
                alert("Welcome " + email.value + "!");
                location.replace("dashboard.html");
            }).catch(error => {
                alert(error.message);
            });
        }).catch(error => {
            alert(error.message);
        });
    }).catch(error => {
        alert(error.message); 
    })
} 

function previewImage() {
    var file = document.getElementById("file").files;
    if (file.length > 0) {
        var fileReader = new FileReader();

        fileReader.onload = function (event) {
            document.getElementById("img").setAttribute("src", event.target.result);
        }

        fileReader.readAsDataURL(file[0]);
    }
}