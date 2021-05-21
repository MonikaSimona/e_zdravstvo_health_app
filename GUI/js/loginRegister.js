$(()=> {


//LOGIN

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

    $.ajax({  
        url: 'http://localhost:8080/middleware/webapi/auth/login',  
        type: 'PUT',  
        dataType: 'json', 
        data: JSON.stringify(userLogin),  
        headers: {
            'Content-Type': 'application/json',
            'Accept': 'application/json',
            'Accept-Encoding': 'gzip, deflate, br',
            'Connection': 'keep-alive'
        },
        success: function (data,textStatus, xhr) {  
            console.log("Success" + data);  
            console.log("TextStatus" + textStatus);  
            console.log("XHR" + xhr);  
        },  
        error: function (xhr, textStatus, errorThrown) {  
            console.log(xhr)
            console.log(textStatus)
            console.log(errorThrown)
            console.log('Error in Operation');  
        }  
    });  

    //logiranje preku localStorage
    // loginData = JSON.parse(localStorage.getItem('userData'));
    // console.log(loginData)
    // if (username === loginData.username && password === loginData.password) {
    //   location.assign('http://127.0.0.1:5500/GUI/app.html')
    // }
    //   console.log(userLogin);
    //   console.log(JSON.stringify(userLogin));
    // fetch('http://localhost:8080/middleware/webapi/auth/login', {
    //     method: 'PUT',
    //     headers: {
    //         'Content-Type': 'application/json',
    //         'Accept': 'application/json',
    //         'Accept-Encoding': 'gzip, deflate, br',
    //         'Connection': 'keep-alive'

    //     },
    //     body: JSON.stringify(userLogin)
    // }).then(response => response.json())
    //     .then(data => {
    //         console.log("Success:" + data)

    //     })
    //     .catch(err => {
    //         console.log("Error: " + err)
    //     });


})



//REGISTER

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
    //gi zima value na inputite od formata
    username = event.target[0].value
    firstName = event.target[1].value
    lastName = event.target[2].value
    birthDate = event.target[3].value
    mbr = event.target[4].value
    height = parseInt(event.target[5].value)
    weight = parseInt(event.target[6].value)
    email = event.target[7].value
    password = event.target[8].value

    //kreira object od inputite
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
        // mode:'cors',
        headers: {
            'Content-Type': 'application/json',
            'Accept': 'application/json',
            'Accept-Encoding': 'gzip, deflate, br',
            'Connection': 'keep-alive'


        },
        body: JSON.stringify(userRegistrationData)
    }).then(response => {
        console.log(response.status)
        return response.json()
    }).then(data => {
        console.log(data)

    })
        .catch(err => {
            console.log(err)
            console.log(err.response)
            console.log(err.message)
        });

    event.target[0].value = ''
    // event.target[1].value = ''
    // event.target[2].value = ''
    // event.target[3].value = ''
    event.target[4].value = ''
    // event.target[5].value = ''
    // event.target[6].value = ''
    event.target[7].value = ''
    event.target[8].value = ''
    // nosi na glavnata strana otkako kje se registrira(najverojatno nema da se koristi ova)
    // location.assign('http://127.0.0.1:5500/GUI/app.html')



})
})
