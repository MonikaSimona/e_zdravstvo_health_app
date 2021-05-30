$(() => {


    var test = document.querySelector('.test')
    test.addEventListener('click', () => {
        location.assign('http://127.0.0.1:5500/GUI/app.html')
    })
    //LOGIN

    var username = ''
    var password = ''
    var loginFrom = document.querySelector('#login');
    var logedError = false;
    
    async function fetchUserData(url, data) {
        const response = await fetch(url, {
            method: 'PUT',
            headers: {
                'Content-Type': 'application/json',
                'Accept': 'application/json',
                'Accept-Encoding': 'gzip, deflate, br',
                'Connection': 'keep-alive'

            },
            body: JSON.stringify(data)
        });

        console.log(response.status)

        if(response.status === 200){
            logedError = false
        }else{
            logedError = true
        }
        const userData = await response.json();
        console.log(userData)
        return userData;
    }

    loginFrom.addEventListener('submit', (event) => {
        event.preventDefault();
        username = event.target[0].value;
        password = event.target[1].value;
        const userLogin = {
            username,
            password
        }



        //ZS// logiranje preku localStorage
        //momentalno simulira logiranje, localStorage kje se koristi vo app.html za da go najavi korisnikot vednas dokolku vekje se ima logirano vo toj browser prethodno
        // loginData = JSON.parse(localStorage.getItem('userData'));
        // console.log(loginData)
        // if (username === loginData.username && password === loginData.password) {
        //     logedError = false
        //     location.assign('http://127.0.0.1:5500/GUI/app.html')

        // } else {
        //     console.log('error')
        //     logedError = true
        // }
        // console.log(userLogin);
        // console.log(JSON.stringify(userLogin));


        

        fetchUserData('http://localhost:8080/middleware/webapi/auth/login', userLogin)
            .then(data => {
                console.log(data)
                localStorage.setItem("logedUser", JSON.stringify(data));
                logedError = false
                setTimeout(() => {location.assign('http://127.0.0.1:5500/GUI/app.html')},3000)
                
            })


        var x = document.getElementById("logedError");

        x.className = "show";

        if (logedError) {
            console.log(logedError)
            setTimeout(function () { x.className = x.className.replace("show", ""); }, 3000);

        }


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

    var registered = "";

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



        // console.log(userRegistrationData)


        if (
            username === '' ||
            firstName === '' ||
            lastName === '' ||
            birthDate === '' ||
            mbr === '' ||
            height === '' ||
            weight === '' ||
            password === '') {
            registered = "prazniPolinja"
        }
        //ZS // za simulacija
        // else {
        //     registered = "registriran"

        //     event.target[0].value = ''
        //     // event.target[1].value = ''
        //     // event.target[2].value = ''
        //     // event.target[3].value = ''
        //     event.target[4].value = ''
        //     // event.target[5].value = ''
        //     // event.target[6].value = ''
        //     event.target[7].value = ''
        //     event.target[8].value = ''
        // }

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
            console.log(response.status)
            console.log(response)
            if (response.status !== 201) {
                registered = "greska"
            } else {
                registered = "registriran";
            }

            event.target[0].value = ''
            //komentirani za da ne se brisat za testiranje
            // event.target[1].value = ''
            // event.target[2].value = ''
            // event.target[3].value = ''
            event.target[4].value = ''
            // event.target[5].value = ''
            // event.target[6].value = ''
            event.target[7].value = ''
            event.target[8].value = ''
            return response.json()
        }).catch(err => {
            console.log(err)
            console.log(err.response)
            console.log(err.message)

        });






        // ispisuva poraka deka korisnikot e registriran Успешна регистрација, премини кон најава!

        if (registered === "registriran") {
            var x = document.getElementById("snackbar-s");
            //ZS//za simulacija
            // localStorage.setItem('userData', JSON.stringify(userRegistrationData));
        } else if (registered === "prazniPolinja") {
            //popolni gi site polinja 
            var x = document.getElementById("snackbar-f");

        }
        else if (registered === "greska") {
            //korisnikot vekje postoi (apito vrakja error dokolku korisnik vekje postoi) 
            var x = document.getElementById("snackbar-e");
        }


        x.className = "show";

        if (registered) {
            setTimeout(function () { x.className = x.className.replace("show", ""); }, 3000);

        }














    })
})
