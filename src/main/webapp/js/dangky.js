function XacNhan(trang) {
	function xacNhan(input, kiemTra, luat) {
		var tinNhanTraVe = luat.boKiemTra(input.value);
		if (tinNhanTraVe) {
			kiemTra.style.color = "#ee7896";
			input.classList.remove('hopLe');
			input.classList.add('khongHopLe');
		} else {
			kiemTra.style.color = "#0d6efd";
			input.classList.remove('khongHopLe');
			input.classList.add('hopLe');
		}
		return !tinNhanTraVe;
	}

	var bieuMau = document.querySelector(trang.bieuMau);
	if (bieuMau) {
		bieuMau.onsubmit = function (e) {
			e.preventDefault();
			var bieuMauDung = true;
			trang.luats.forEach(function (luat) {
				var truongNhapLieu = bieuMau.querySelector(luat.boChon);
				var kiemTra = truongNhapLieu.querySelector(".danhGia");
				var input = truongNhapLieu.getElementsByTagName("input")[0];
				var xacNhanDung = xacNhan(input, kiemTra, luat);
				if (!xacNhanDung) {
					bieuMauDung = false;
				}
			});

			if (bieuMauDung) {
				if (typeof trang.onSubmit === 'function') {
					var cacThongTinDauVao = bieuMau.querySelectorAll('[name]')
					var thongTinBieuMau = Array.from(cacThongTinDauVao).reduce(function (cacGiaTri, input) {
						cacGiaTri[input.name] = input.value;
						return cacGiaTri;
					}, {});
					trang.onSubmit(thongTinBieuMau);
				}
			}
		}

		trang.luats.forEach(function (luat) {
			var truongNhapLieu = bieuMau.querySelector(luat.boChon);
			var kiemTra = truongNhapLieu.querySelector(".danhGia");

			if (truongNhapLieu) {
				var input = truongNhapLieu.getElementsByTagName("input")[0];
				if (input) {

					input.onblur = function () {
						xacNhan(input, kiemTra, luat);
					}

					input.oninput = function () {
						input.classList.remove('hopLe');
						input.classList.add('khongHopLe');
					}
				}
			}
		});
	}
}

XacNhan.batBuoc = function (boChon) {
	return {
		boChon: boChon,
		boKiemTra: function (giaTri) {
			return giaTri.trim() ? undefined : 'loi';
		}
	};
}

XacNhan.email = function (boChon) {
	return {
		boChon: boChon,
		boKiemTra: function (giaTri) {
			var regex = /^\w+([\.-]?\w+)*@\w+([\.-]?\w+)*(\.\w{2,3})+$/;
			return regex.test(giaTri) ? undefined : 'loi';
		}
	}
}


XacNhan.doDaiNhoNhat = function (boChon, giaTriNhoNhat) {
	return {
		boChon: boChon,
		boKiemTra: function (giaTri) {
			return giaTri.length >= giaTriNhoNhat ? undefined : 'loi';
		}
	}
}

XacNhan.xacNhanMatKhau = function (boChon, layMatKhau, doDaiNhoNhat) {
	return {
		boChon: boChon,
		boKiemTra: function (giaTri) {
			if (giaTri != "" & giaTri.length >= doDaiNhoNhat)
				return giaTri === layMatKhau() ? undefined : 'loi';
			else
				return 'loi';
		}
	}
}