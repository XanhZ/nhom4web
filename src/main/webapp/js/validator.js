function Validator(options) {
	function validate(input, check, rule) {
		var mess = rule.test(input.value);
		if (mess) {
			check.style.color = "#ee7896";
			input.classList.remove('hopLe');
			input.classList.add('khongHopLe');
		} else {
			check.style.color = "#0d6efd";
			input.classList.remove('khongHopLe');
			input.classList.add('hopLe');
		}
		return !mess;
	}

	var formatElement = document.querySelector(options.form);
	if (formatElement) {
		formatElement.onsubmit = function (e) {
			e.preventDefault();
			var isFormValid = true;
			options.rules.forEach(function (rule) {
				var field = formatElement.querySelector(rule.selector);
				var check = field.querySelector(".danhGia");
				var input = field.getElementsByTagName("input")[0];
				var isValid = validate(input, check, rule);
				if (!isValid) {
					isFormValid = false;
				}
			});
		}

		options.rules.forEach(function (rule) {
			var field = formatElement.querySelector(rule.selector);
			var check = field.querySelector(".danhGia");

			if (field) {
				var input = field.getElementsByTagName("input")[0];
				if (input) {
					// xử lí blur
					input.onblur = function () {
						validate(input, check, rule);
					}
					// xử lí oninput(user nhập thông tin)
					input.oninput = function () {
						input.classList.remove('hopLe');
						input.classList.add('khongHopLe');
					}
				}
			}
		});
	}
}

Validator.isRequired = function (selector) {
	return {
		selector: selector,
		test: function (value) {
			return value.trim() ? undefined : 'error';
		}
	};
}

Validator.isEmail = function (selector) {
	return {
		selector: selector,
		test: function (value) {
			var regex = /^\w+([\.-]?\w+)*@\w+([\.-]?\w+)*(\.\w{2,3})+$/;
			return regex.test(value) ? undefined : 'error';
		}
	}
}


Validator.minLength = function (selector, min) {
	return {
		selector: selector,
		test: function (value) {
			return value.length >= min ? undefined : 'error';
		}
	}
}

Validator.cnfPass = function (selector, getPass, minLen) {
	return {
		selector: selector,
		test: function (value) {
			if (value != "" & value.length >= minLen)
				return value === getPass() ? undefined : 'error';
			else
				return 'error';
		}
	}
}