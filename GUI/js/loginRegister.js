var x = document.getElementById("login");
var y = document.getElementById("register");
var z = document.getElementById("btn");

if (window.innerWidth <= 768) {
    x.style.width = '200px'
    y.style.width = '200px'
   

}


function register() {
    x.style.left = "-400px";
    y.style.left = "40px";
    z.style.left = "110px";
    if (window.innerWidth <= 768) {
        y.style.left = "40px";
        x.style.width = '200px'
        y.style.width = '200px'

    }
}

function login() {
    x.style.left = "40px";
    y.style.left = "450px";
    z.style.left = "0px";
    if (window.innerWidth <= 768) {


        x.style.width = '200px'
        y.style.width = '200px'
    }
}
//registration 
var firstName = ''
var lastName = ''
var birthDate = ''
var mbr = ''
var height = ''
var weight = ''
var password = ''
var email = ''

var userRegistrationData = {
    firstName:'',
    lastName:'', 
    birthDate:'',
    mbr:'',
    height:'',
    weight:'',
    password:'',
    email:''

}

var registrationForm = document.querySelector('#register');
registrationForm.addEventListener('submit', (event) => {
    
    

    firstName = event.target[0].value
    lastName = event.target[1].value
    birthDate = event.target[2].value
    mbr = event.target[3].value
    height = event.target[4].value
    weight = event.target[5].value
    email = event.target[6].value
    password = event.target[7].value

    userRegistrationData = {
        firstName,
        lastName, 
        birthDate,
        mbr,
        height,
        weight,
        password,
        email
    }
    fetch('')
    
    console.log(userRegistrationData)
    
    event.preventDefault();

 
})