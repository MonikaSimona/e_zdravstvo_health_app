

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