// na pocetok dodadov object so devices niza za da imam nesto vo localstorage da proveram dali raboti, otkomentiraj gi dolnite dva komentara i napravi save i kje se dodade vo localStorage devices listata za lokalno testiranje

// logedUser = { devices: [{ type: "phone", name: "sony" }, { type: "watch", name: "fitbit" }] }
// localStorage.setItem("logedUser", JSON.stringify(logedUser))











//LOGED USER

//korisnikot koga se najavuva dokolku uspesno se najavi podatocite zemeni od baza se zacuvuaat vo localStorage i app.html od tamu gi povlekuva
var userInfo = localStorage.getItem("logedUser");

//ZS//userData se podatoci zacuvani vo localStorage pri register -> za simulacija
// var userInfo = localStorage.getItem("userData");
if (userInfo) {
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

var deviceNameString = ""
var typeTitle = ""
var typeValue = ""

//TYPE INFO SECTION
var typeInfoTitle = document.querySelector('#type-info-title');
var typeInfoText = document.querySelector('#type-info-text');
var type = document.querySelector('#type')
type.value = ""


var infoTexts = {
  kislorodnaSaturacija: 'Нормалните вредности на сатурација на кислород во артериската крв се 96-99 проценти. Ако вредноста падне под 90 проценти, состојбата се нарекува хипоксемија.',
  Heartrate: 'Бројот на срцеви отчукувања во 30 секунди се смета, на така добиените резултати треба да се множи со два. Нормално, на број на удари во здраво машко треба да достигне 70, жените - 80 отчукувања во минута. ',
  varijacijaNaSrcebienje: 'Кај луѓето на возраст од 50-60 г. нормално е да очекуваме варијација и покачување на крвниот притисок, но денес 40% од пациентите се под 60 г., односно 20-25% од пациентите се на возраст од 30-45 г., од кои почесто на удар се мажите.'
}

type.addEventListener('change', () => {
  var option = document.getElementById("type").options[document.getElementById("type").selectedIndex];
  typeTitle = option.label;
  typeValue = option.value;

  typeInfoTitle.innerText = typeTitle;
  typeInfoText.innerText = infoTexts[typeValue]

})


// GET USER DEVICES
//dokolku ima vekje uredi (od localStorage go zima celoto userInfo) gi vrti site so map funkcijata i za sekoj ured kreira option element i gi stava vo edna niza, nizata options ja dodava posle elemntot so klasa defaultOption
var localDevices = []
var defaultOption = document.querySelector(".defaultOption")
// console.log(JSON.parse(userInfo))
//proveruva dali vo localStorage e najaven korisnik i ako e najaven proveruva dali negoavata devices lista e prazna
if (userInfo) {
  if (JSON.parse(userInfo).devices) {
    localDevices = JSON.parse(userInfo).devices

    console.log("SITE UREDI NA KORISNIKOT", localDevices)
    //ako ne e prazna vo default ipisuva slednoto
    defaultOption.innerHTML = "Одбери паметен уред"
    // i go polnuva selektot
    localDevices.forEach((item, index) => {
      let option = document.createElement("option")
      option.setAttribute("value", `${item.id}`)
      option.innerHTML = item.name
      // console.log(option)
      defaultOption.insertAdjacentElement('afterend', option)
    });

    // ako e prаzna go ispisuva slednoto
  } else {
    defaultOption.innerHTML = "Нема додадено уреди"
  }
}


var addDevice = document.querySelector(".button_add");
var deviceType = document.querySelector(".dropdown")
var deviceName = document.querySelector(".inputdevice")

var userDevice = {}
var deviceError

//funkcija za dodavanje ured vo bazata
async function fetchUserDevices(url, data) {
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

  if (response.status === 200) {
    deviceError = false
  } else {
    deviceError = true
  }
  const userData = await response.json();
  console.log(userData)
  return userData;
}

// i da e prazna i da ne e praza devices listata moze da se doade nov ured na kraj na listata
var devicesList = document.querySelector(".dropdown1")
devicesList.value = ""
addDevice.addEventListener('click', (event) => {
  event.preventDefault();
  var type = deviceType[deviceType.options.selectedIndex].innerHTML
  var name = deviceName.value

  var userId = JSON.parse(userInfo).id
  // console.log(deviceType.value)
  userDevice = {
    type,
    name,
    userId
  }

  var device = {}
  //ako inputite se prazni ne pravi nisto
  if (type === "" || name === "") {
    var x = document.getElementById("snackbar-f");


  } else {
    fetchUserDevices('https://localhost:8443/middleware/webapi/ehealth/addDevice', userDevice)
      .then(data => {
        device = data
        console.log("DEVICE", data)

      })
    if (deviceError) {
      var x = document.getElementById("snackbar-e");
    } else {
      //ako inputite se polni go dodava noviot ured vo selektot so uredi  i pojavuva poraka za dodavanje

      devicesList.options[devicesList.options.length] = new Option(`${name} - ${type}`, `${device.id}`)
      var x = document.getElementById("snackbar");

    }

  }
  x.className = "show";
  setTimeout(function () { x.className = x.className.replace("show", ""); }, 3000);



});

//CHART

var date = document.querySelector("#date");
var startHour = document.querySelector("#start-hour");
var endHour = document.querySelector("#end-hour");

var hourLabels = [];
var startHourIndex;
var endHourIndex;
var dateValue;
var mesurements;
var mesureError = false;


var btn = document.querySelector('.btn');

data = {
  labels: [],
  datasets: [{
    label: '',
    backgroundColor: 'rgb(182, 255, 99)',
    borderColor: 'rgb(46, 61, 43)',
    data: [],
  }]
}
var config = {
  type: 'line',
  data,
  options: {}
}
var chart = document.querySelector('#myChart').getContext('2d');
var myChart = new Chart(chart, config);

//funkcija za zimanje na merenja od ured
async function fetchDeviceMesurement(url, data) {
  const response = await fetch(url, {
    method: 'PUT',
    headers: {
      'Content-Type': 'application/json',
      'Accept': 'application/json',
      'Accept-Encoding': 'gzip, deflate, br',
      'Connection': 'keep-alive'

    },
    body: JSON.stringify(data)
  })
  console.log("RESPONSE STATUS", response.status);
  if (response.status === 200) {
    deviceError = false
  } else {
    deviceError = true
  }
  const mesurementData = await response.json();
  console.log("MESUREMENTS DATA", mesurementData)
  mesurements = mesurementData;
  return mesurementData;

}
function updateChart(newLabels, labelsLength, mesurements) {
  console.log("UPDATE CHART", newLabels, labelsLength, mesurements)
  var newData = []
  for (let index = 0; index < labelsLength; index++) {
    newData.push(mesurements[index].value)
  }
  console.log("UPDATE CHART VALUES", newData)
  myChart.data.labels = newLabels
  myChart.data.datasets[0].data = newData
  myChart.update()
}
btn.addEventListener('click', () => {
  document.documentElement.scrollTop = 1000;

  startHourIndex = parseInt(startHour.value.split(":")[0]);
  endHourIndex = parseInt(endHour.value.split(":")[0]);
  dateValue = date.value;
  console.log("DATE VALUE", dateValue)
  console.log("TYPE VALUE", typeValue)
  console.log("Device,", devicesList[devicesList.options.selectedIndex].value)
  console.log("startHour ", startHourIndex, "|endHour ", endHourIndex, "|deviceName ", devicesList[devicesList.options.selectedIndex].innerHTML, "|typeValue ", typeValue, "|date", dateValue, startHour.value, endHour.value)
  //proveruva dali site polinja se popolneti
  if (
    devicesList[devicesList.options.selectedIndex].innerHTML !== "Одбери паметен уред"
    && typeTitle !== ""
    && typeTitle !== "Одбери параметар"
    && dateValue !== ""
    && startHour.value !== ""
    && endHour.value !== ""
  ) {
    hourLabels = []
    var typeV = ""
    console.log("USER INFO", userInfo)
    if (typeValue === "Heartrate") {
      typeV = "Heart rate"
    }
    const formatedDate = dateValue.split("-")
    console.log("FORMATED DATE", formatedDate)
    const data = {
      userId: `${JSON.parse(userInfo).id}`,
      deviceId: devicesList[devicesList.options.selectedIndex].value,
      type: typeV,
      fromDate: `${formatedDate[1]}-${formatedDate[2]}-${formatedDate[0]} ${startHour.value}:00.000`,
      toDate: `${formatedDate[1]}-${formatedDate[2]}-${formatedDate[0]} ${endHour.value}:00.000`
    }
    console.log("DATA TO SEND TO object ENDPOINT", JSON.stringify(data))
    var fetchData = {}
    fetchDeviceMesurement('https://localhost:8443/middleware/webapi/ehealth/object', data)
      .then(data => {
        if (data) {
          for (let index = 0; index <= data.measurements.length - 1; index++) {
            console.log(data.measurements[index].time.split("T")[1].split("+")[0])
            hourLabels.push(data.measurements[index].time.split("T")[1].split("+")[0])

          }
          // console.log(document.querySelector(".typeName"), typeTitle)
          document.querySelector(".typeName").innerHTML = typeTitle
          updateChart(hourLabels, hourLabels.length, data.measurements)
          console.log("FETCH DATA IN PROMISE", data)
          fetchData = data
        }


      })
    // console.log("FETCH DATA", fetchData)


  } else {


    var x = document.getElementById("snackbar-g");
    x.className = "show";
    setTimeout(function () { x.className = x.className.replace("show", ""); }, 3000);
  }




})

