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

function logOutNav() {
    auth.signOut();
    alert("Signed Out");
    location.replace("index.html");
}