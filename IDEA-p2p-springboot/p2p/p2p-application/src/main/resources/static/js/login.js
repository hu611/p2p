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


var referrer = "";//登录后返回页面
referrer = document.referrer;
if (!referrer) {
	try {
		if (window.opener) {                
			// IE下如果跨域则抛出权限异常，Safari和Chrome下window.opener.location没有任何属性              
			referrer = window.opener.location.href;
		}  
	} catch (e) {
	}
}

//按键盘Enter键即可登录
$(document).keyup(function(event){
	if(event.keyCode == 13){
		login();
	}
});

$(function () {
	// 给手机号添加失去焦点事件
	$("#phone").on("blur", function () {
		var phone = $.trim($("#phone").val());
		if (phone == "") {
			showError("phone","账号不能为空")
		} else if (!/^1[1-9]\d{9}$/.test(phone)) {
			showError("phone", "请输入正确的手机号")
		} else {
			showSuccess("phone")
		}
	});

	// 给密码框添加失去焦点事件
	$("#loginPassword").on("blur", function () {
		var loginPassword = $.trim($("#loginPassword").val());
		if (loginPassword == "") {
			showError("loginPassword","密码不能为空")
		}  else {
			showSuccess("loginPassword")
		}
	});

	// 给验证码框添加失去焦点事件
	$("#messageCode").on("blur", function () {
		var messageCode = $.trim($("#messageCode").val());
		if (messageCode == "") {
			showError("messageCode","验证码不能为空")
		}  else {
			showSuccess("messageCode")
		}
	});

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
				url:"/user/messageCode",
				type:"get",
				data: {
					"phone":phone
				},
				success:function (data) {
					if (data.code == 1) {
						// 短信发送成功,开始倒计时
						alert('短信验证码是:'+data.message)
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
})

// 登录功能
function Login() {
	$("#phone").blur();
	$("#loginPassword").blur();
	$("#messageCode").blur();
	var errorText = $("div[id$='Err']").text();
	if(errorText == "") {
		var phone = $.trim($("#phone").val());
		var password = $.trim($("#loginPassword").val());
		var messageCode = $.trim($("#messageCode").val());
		password = $.md5(password)
		$.ajax({
			url: "/loan/login",
			type: "get",
			data: {
				phone: phone,
				password: password,
				messageCode: messageCode
			},
			success: function (data) {
				if(data.code == 1) {
					alert("登录成功")
					window.location.href="/"
				} else {
					showError("loginPassword",data.message);
				}
			},
			error: function (data) {
				showError("loginPassword",data.message);
			}
		})
	}
}
