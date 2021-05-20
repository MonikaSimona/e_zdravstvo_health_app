

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


userStorage = localStorage.getItem('userData');
if (userData) {
    if (userStorage) {
        // location.assign('http://127.0.0.1:5500/GUI/app.html')
        var userData = JSON.parse(userStorage);

    }

}

var username = ''
var password = ''
var loginFrom = document.querySelector('#login');

loginFrom.addEventListener('submit', (event) => {
    event.preventDefault();
    username = event.target[0].value;
    password = event.target[1].value;
    const userLogin = {
        username,
        password
    }

    loginData = JSON.parse(localStorage.getItem('userData'));
    console.log(loginData)
    if(username === loginData.username && password === loginData.password){
        location.assign('http://127.0.0.1:5500/GUI/app.html')
    }
 console.log(userLogin);
 console.log(JSON.stringify(userLogin));
    // fetch('http://localhost:8080/middleware/webapi/auth/login', {
    //     method: 'PUT',
    //     headers: {
    //         'Content-Type': 'application/json',
    //         'Accept': 'application/json',
    //         'Accept-Encoding': 'gzip, deflate, br',
    //         'Connection': 'keep-alive'



    //     },
    //     body: JSON.stringify(userLogin)
    // }).then(response => {
    //     // console.log(response)
    //     return response
    // }).then(data => {
    //     console.log(data)

    // })
    //     .catch(err => {
    //         console.log(err)
    //         console.log(err.response)
    //         console.log(err.message)
    //     });


})



//registration 
var firstName = ''
var lastName = ''
var birthDate = ''
var mbr = ''
var height
var weight
var password = ''
var email = ''



var registrationForm = document.querySelector('#register');
registrationForm.addEventListener('submit', (event) => {


    event.preventDefault();
    username = event.target[0].value
    firstName = event.target[1].value
    lastName = event.target[2].value
    birthDate = event.target[3].value
    mbr = event.target[4].value
    height = parseInt(event.target[5].value)
    weight = parseInt(event.target[6].value)
    email = event.target[7].value
    password = event.target[8].value

    const userRegistrationData = {
        mbr,
        username,
        password,
        firstName,
        lastName,
        birthDate,
        height,
        weight,
        email
    }
    localStorage.setItem('userData', JSON.stringify(userRegistrationData));

    console.log(userRegistrationData)

    fetch('http://localhost:8080/middleware/webapi/auth/register', {
        method: 'PUT',
        headers: {
            'Content-Type': 'application/json',
            'Accept': 'application/json',
            'Accept-Encoding': 'gzip, deflate, br',
            'Connection': 'keep-alive'



        },
        body: JSON.stringify(userRegistrationData)
    }).then(response => {
        console.log(response)
    }).then(data => {
        console.log(data)
    })
        .catch(err => {
            console.log(err)
            console.log(err.response)
            console.log(err.message)
        });

    location.assign('http://127.0.0.1:5500/GUI/app.html')



})