//错误提示
function showError(id,msg) {
	$("#"+id+"Ok").hide();
	$("#"+id+"Err").html("<i></i><p>"+msg+"</p>");
	$("#"+id+"Err").show();
	$("#"+id).addClass("input-red");
}
//错误隐藏
function hideError(id) {
	$("#"+id+"Err").hide();
	$("#"+id+"Err").html("");
	$("#"+id).removeClass("input-red");
}
//显示成功
function showSuccess(id) {
	$("#"+id+"Err").hide();
	$("#"+id+"Err").html("");
	$("#"+id+"Ok").show();
	$("#"+id).removeClass("input-red");
}


//打开注册协议弹层
function alertBox(maskid,bosid){
	$("#"+maskid).show();
	$("#"+bosid).show();
}
//关闭注册协议弹层
function closeBox(maskid,bosid){
	$("#"+maskid).hide();
	$("#"+bosid).hide();
}

//注册协议确认
$(function() {
	$("#agree").click(function(){
		var ischeck = document.getElementById("agree").checked;
		if (ischeck) {
			$("#btnRegist").attr("disabled", false);
			$("#btnRegist").removeClass("fail");
		} else {
			$("#btnRegist").attr("disabled","disabled");
			$("#btnRegist").addClass("fail");
		}
	});

	$(function() {
		$("#phone").on("blur", function () {
			var phone = $.trim($("#phone").val());
			if (phone == "") {
				showError("phone", "手机不能为空")
			} else if(!/^1[1-9]\d{9}$/.test(phone)) {
				showError("phone","请输入正确的手机号")
			} else {
				$.ajax( {
					url:"/loan/checkPhone",
					type:"get",
					data: "phone=" + phone,
					success: function(data) {
						if(data.code == 1) {
							showSuccess("phone")
						} else {
							showError("phone",data.message)
						}
					},
					error:function() {
						showError("phone","系统繁忙");
					}
				})
			}
		})
	})

	// 密码输入框失去焦点事件
	$("#loginPassword").on("blur",function () {
		var loginPassword = $.trim($("#loginPassword").val())
		if (loginPassword == "") {
			showError("loginPassword","密码不能为空")
		} else if (loginPassword.length < 6 || loginPassword.length > 20) {
			showError("loginPassword", "密码的长度只能在6-20位之间");
		} else if (!/^[0-9a-zA-Z]+$/.test(loginPassword)) {
			showError("loginPassword","密码字符只可使用数字和大小写英文字母")
		} else if (!/^(([a-zA-Z]+[0-9]+)|([0-9]+[a-zA-Z]+))[a-zA-Z0-9]*/.test(loginPassword)) {
			showError("loginPassword","密码应同时包含英文和数字")
		} else {
			showSuccess("loginPassword")
		}
	})

	// 验证码输入框失去焦点事件
	$("#messageCode").on("blur", function () {
		var messageCode = $.trim($("#messageCode").val());
		if (messageCode == "") {
			showError("messageCode","验证码不能为空")
		}
	})

	// 注册按钮的单击事件
	$("#btnRegist").on("click",function () {

		$("#phone").blur();
		$("#loginPassword").blur();
		//以Err为结尾的id e.g: <div id="phoneErr" className="form-hint" style="display:none;"></div>
		var errorText = $("div[id$='Err']").text();
		if(errorText == "") {
			//Start registering
			var phone = $.trim($("#phone").val());
			var password = $.trim($("#loginPassword").val());
			//encrypt password
			password = $.md5(password);
			$("#loginPassword").val(password);
			$.ajax({
				url:"/loan/register",
				type:"get",
				data: {
					"phone": phone,
					"password": password
				},
				success:function(data) {
					if(data.code == 1) {
						//register successful
						window.location.href="/page/realName";
					} else {
						showError("loginPassword",data.message);
					}
				},
				error:function (data) {
					showError("loginPassword",data.message);
				}
			})
		}
	})

	// 获取短信验证码
	$("#messageCodeBtn").on("click",function () {
		// 隐藏验证码提示错误
		hideError("messageCode")
		// 对手机号和密码输入框进行验证
		$("#phone").blur();
		$("#loginPassword").blur();

		var errorText = $("div[id$='Err']").text();
		if (errorText == "") {
			var phone = $.trim($("#phone").val())
			// 请求后台发送短信验证码
			$.ajax({
				url:"/p2p/user/messageCode",
				type:"get",
				data: {
					"phone":phone
				},
				success:function (data) {
					if (data.code == 1) {
						// 短信发送成功,开始倒计时
						alert('短信验证码是:'+data.messageCode)
						// 输入框通过了验证，没有错误
						if (!$("#messageCodeBtn").hasClass("on")) {
							$.leftTime(60,function(d){
								//d.status,值true||false,倒计时是否结束;
								if (d.status) {
									//d.s,倒计时秒;
									$("#messageCodeBtn").text(d.s == '00' ? '60秒后获取' : d.s+'秒后获取');
									$("#messageCodeBtn").addClass("on")
								} else {
									$("#messageCodeBtn").text("获取验证码");
									$("#messageCodeBtn").removeClass("on")
								}

							});
						}
					} else {
						showError("messageCode",data.message)
					}
				},
				error:function () {
					showError("messageCode","系统异常")
				}
			})
		}
	});
});
