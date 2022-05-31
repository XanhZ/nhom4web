const menu = document.querySelectorAll(".dieuHuong li");

function hoatDong() {
  menu.forEach((item) => {
    item.classList.remove("hoatDong");
  });
  this.classList.add("hoatDong");
}

menu.forEach((item) => item.addEventListener("click", hoatDong));

// Cong tac menu
let congTac = document.querySelector(".congTac");
let dieuHuong = document.querySelector(".dieuHuong");
let manChinh = document.querySelector(".manChinh");

congTac.onclick = function () {
  dieuHuong.classList.toggle("hoatDong");
  manChinh.classList.toggle("hoatDong");
};


