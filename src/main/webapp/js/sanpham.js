const album = document.querySelectorAll(".hinh-anh--don")
const hinhAnhHienThi = document.querySelector(".hinh-anh-hien-thi img")

album.forEach(img => {
  img.addEventListener("click", () => {
    const url = img.firstElementChild.getAttribute("src")
    album.forEach(ele => ele.classList.remove("dang-hien-thi"))
    img.classList.add("dang-hien-thi")
    hinhAnhHienThi.setAttribute("src", url)
  })
})