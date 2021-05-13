$(() => {

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
      borderColor: 'rgb(125, 255, 99)',
      data: [16, 10, 5, 2, 20, 30, 45, 50],
    }]
  };

  const config = {
    type: 'line',
    data,
    options: {}
  };




  var infoTexts = {
    kislorodnaSaturacija: 'Нормалните вредности на сатурација на кислород во артериската крв се 96-99 проценти. Ако вредноста падне под 90 проценти, состојбата се нарекува хипоксемија.',
    otcukuvanjaNaSrceVoMinuta: 'Бројот на срцеви отчукувања во 30 секунди се смета, на така добиените резултати треба да се множи со два. Нормално, на број на удари во здраво машко треба да достигне 70, жените - 80 отчукувања во минута. ',
    varijacijaNaSrcebienje: 'Кај луѓето на возраст од 50-60 г. нормално е да очекуваме варијација и покачување на крвниот притисок, но денес 40% од пациентите се под 60 г., односно 20-25% од пациентите се на возраст од 30-45 г., од кои почесто на удар се мажите.'
  }


  var menuSection = document.querySelector('ul');

  var menu = document.querySelector('.hamburger-menu');
  var closeMenu = document.querySelector('.close-menu');
  closeMenu.style.display = 'none';

  var show = false;

  var btn = document.querySelector('button');

  var myChart = new Chart($('#myChart'), config);
  var typeInfoTitle = document.querySelector('#type-info-title');
  var typeInfoText = document.querySelector('#type-info-text');

  var type = document.querySelector('#type')

  menu.addEventListener('click', () => {
    show = true;
    menuSection.style.width = '90%';
    closeMenu.style.display = 'block';

  })

  closeMenu.addEventListener('click', () => {
    show = false;
    menuSection.style.width = '0';
    closeMenu.style.display = 'none';
  })

  type.addEventListener('change', () => {
    var option = document.getElementById("type").options[document.getElementById("type").selectedIndex];
    typeTitle = option.label;
    typeValue = option.value;



    typeInfoTitle.innerText = typeTitle;

    typeInfoText.innerText = infoTexts[typeValue]

  })

  document.querySelector('form').addEventListener('submit', (e) => {
    e.preventDefault();
  })
  btn.addEventListener('click', () => {

    document.documentElement.scrollTop = 1000; 
  })






  // {
  //   type:type,
  //   date1:date1,
  //   date2:date2,
  //   forOneDay:true/false,
  //   hours:
  // }
  // http://jsonplaceholder.typicode.com/posts?type=${input1}&date1=&{date1}
})