

//CHART

const labels = [
  'Monday',
  'Thuesday',
  'Wednesday',
  'Thursday',
  'Friday',
  'Saturday',
  'Sunday',
];
const data = {
  labels: labels,
  datasets: [{
    label: 'My First dataset',
    backgroundColor: 'rgb(182, 255, 99)',
    borderColor: 'rgb(46, 61, 43)',
    data: [16, 10, 5, 2, 20, 30, 45, 50],
  }]
};

const config = {
  type: 'line',
  data,
  options: {}
};

var chart = document.querySelector('#myChart');
var myChart = new Chart(chart, config);
var btn = document.querySelector('button');

btn.addEventListener('click', () => {

  document.documentElement.scrollTop = 1000;
})

//LOGED USER

//korisnikot koga se najavuva dokolku uspesno se najavi podatocite zemeni od baza se zacuvuaat vo localStorage i app.html od tamu gi povlekuva
var userInfo = localStorage.getItem("logedUser");

//ZS//userData se podatoci zacuvani vo localStorage pri register -> za simulacija
// var userInfo = localStorage.getItem("userData");
if(userInfo){
  var firstName = JSON.parse(userInfo).firstName; 
  var lastName = JSON.parse(userInfo).lastName; 
  var userNameTitle = document.querySelector('#userNameTitle');
  userNameTitle.innerHTML = `Здраво ${firstName} ${lastName}`;
}

//LOGOUT
var logoutButton = document.querySelector('#logout');

  logoutButton.addEventListener('click', () => {
    if (localStorage.getItem('logedUser')) {
      localStorage.removeItem('logedUser')
    }
  })




//HAMBURGER MENU
var menuSection = document.querySelector('ul');

var menu = document.querySelector('.hamburger-menu');
var closeMenu = document.querySelector('.close-menu');



var show = false;

if (menu) {
  menu.addEventListener('click', () => {
    show = true;
    menuSection.style.width = '90%';
    closeMenu.style.display = 'block';

  })
}

  closeMenu.style.display = 'none';
  closeMenu.addEventListener('click', () => {
    show = false;
    menuSection.style.width = '0';
    closeMenu.style.display = 'none';
  })



//TYPE INFO SECTION
var typeInfoTitle = document.querySelector('#type-info-title');
var typeInfoText = document.querySelector('#type-info-text');
var type = document.querySelector('#type')

var infoTexts = {
  kislorodnaSaturacija: 'Нормалните вредности на сатурација на кислород во артериската крв се 96-99 проценти. Ако вредноста падне под 90 проценти, состојбата се нарекува хипоксемија.',
  otcukuvanjaNaSrceVoMinuta: 'Бројот на срцеви отчукувања во 30 секунди се смета, на така добиените резултати треба да се множи со два. Нормално, на број на удари во здраво машко треба да достигне 70, жените - 80 отчукувања во минута. ',
  varijacijaNaSrcebienje: 'Кај луѓето на возраст од 50-60 г. нормално е да очекуваме варијација и покачување на крвниот притисок, но денес 40% од пациентите се под 60 г., односно 20-25% од пациентите се на возраст од 30-45 г., од кои почесто на удар се мажите.'
}

  type.addEventListener('change', () => {
    var option = document.getElementById("type").options[document.getElementById("type").selectedIndex];
    typeTitle = option.label;
    typeValue = option.value;

    typeInfoTitle.innerText = typeTitle;
    typeInfoText.innerText = infoTexts[typeValue]

  })




