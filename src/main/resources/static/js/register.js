;
! function() {
	var layer = layui.layer,
		form = layui.form;

	// 当勾选不同意协议按钮后禁止注册
	form.on("checkbox(agreen-checkbox)", function(data) {
		if(!data.elem.checked) {
			$("#reg").addClass("layui-btn-disabled");
			$("#reg").prop("disabled", "disabled");
		} else {
			$("#reg").removeClass("layui-btn-disabled");
			$("#reg").removeAttr("disabled");
		}
	});

	// 验证码值存储变量
	//var vailCode;
	// 执行获取验证码
	//refCode();
	refCode();
	var vailCode; //在全局 定义验证码
	// 点击刷新验证码
	$("#refCode_reg_img").on("click", function() {
		refCode();
	});

	// 获取图片验证码
	function refCode() {
		/*$.ajax({
			url: "user/imageVailCode.do",
			type: "post",
			success: function(result) {
				vailCode = result.data.rand;
				$("#refCode_reg_img").prop("src", "data:image/jpg;base64," + result.data.image);
				$("#code").val("");
			}
		});*/
		$.ajax({
			async: false,
			type: "get",
			url: "user/imageVerify",
			success: function () {
				$("#refCode_reg_img").prop("src", 'user/imageVerify');
			},error:function () {
				alert("错误!")
			}
		});
	}

	// 发送短信验证码
	var InterValObj; // 定时器变量
	var count = 60; // 间隔函数，1秒执行
	var curCount; // 当前剩余秒数
	var msg_send_count = 0;
	// 发送验证码
	/*$("#msg-btn").on("click", function() {
		if($(this).prop("disabled") != "disabled") {
			vailPhone();
			if(!/^1[34578]\d{9}$/.test($("#phone").val())) {
				layer.msg("请先输入正确的手机号", {
					icon: 5
				});
				return false;
			} else if(phone_status == 0) {
				layer.msg("该手机号已被注册", {
					icon: 5
				});
				return false;
			}
			msg_send_count++;
			curCount = count;
			// 设置button效果，开始计时
			$("#msg-btn").addClass("layui-btn-disabled");
			$("#msg-btn").prop("disabled", "disabled"); // 设置按钮为禁用状态
			$("#msg-btn").text("正在发送..."); // 更改按钮文字
			// 向后台发送处理数据
			$.ajax({
				url: "user/sendSMS.do",
				type: "post",
				data: {
					"account": $("#phone").val(),
					"type": "info"
				},
				success: function(result) {
					if(result.status == 0) {
						$("#msg-btn").text(curCount + "秒后再次获取"); // 更改按钮文字
						InterValObj = setInterval(SetRemainTime, 1000); // 启动计时器timer处理函数，1秒执行一次
					} else {
						layui.msg(result.msg);
						$("#msg-btn").val("重新发送验证码");
						$("#msg-btn").removeClass("layui-btn-disabled");
						$("#msg-btn").removeAttr("disabled"); // 移除禁用状态改为可用
					}
				}
			});
		}
	});*/

	// timer处理函数
	/*function SetRemainTime() {
		if(curCount == 0) {
			clearInterval(InterValObj); // 停止计时器
			$("#msg-btn").removeClass("layui-btn-disabled");
			$("#msg-btn").removeAttr("disabled"); // 移除禁用状态改为可用
			$("#msg-btn").text("重新发送验证码");
		} else {
			curCount--;
			$("#msg-btn").text(curCount + "秒后再次获取");
		}
	}*/

	var phone_status;

	// 验证手机号唯一
	/*function vailPhone() {
		$.ajax({
			url: "user/checkAccount.do",
			type: "post",
			async: false,
			data: {
				"account": $("#phone").val(),
				"type": "phone"
			},
			success: function(result) {
				phone_status = result.status;
			}
		});
	}*/


	// 手机号输入框改变时验证
	/*$("#phone").on("change", function() {
		vailPhone();
		if(phone_status == 0) {
			layer.msg("该手机号已被注册", {
				icon: 5
			});
		}
	});*/

	var email_status;

	//邮箱号输入框改变时验证
	$("#email").on("change", function() {
		$.ajax({
			url: "user/checkAccount.do",
			type: "post",
			data:{"email": $("#email").val(),
				"type": "email"},
			success: function(result) {
				email_status = result.code;
			}
		});
		if(email_status == 203) {
			layer.msg("该邮箱号已被注册", {
				icon: 5
			});
		}
	});

	// 自定义验证规则
	form.verify({
		regexit: function() {
			$.ajax({
				url: "user/checkAccount.do",
				type: "post",
				data:{"email": $("#email").val(),
					"type": "email"},
				success: function(result) {
					email_status = result.code;
				}
			});
			if(email_status === 203) {
				layer.msg("该邮箱号已被注册", {
					icon: 5
				});
			}
		},
		pwd: function(value) {
			if(value.length < 8) {
				return "密码长度不能少于8位";
			} else if(!/^(\w){8,20}$/.test(value)) {
				return "密码只能包含字母、数字或下划线";
			}
		},
		rePwd: function(value) {
			if(value != $("#password").val()) {
				return "两次输入的密码不一致";
			}
		},
		code: function(value) {
			var msg=1;
			$.ajax({
				url: "user/verify",
				type: "post",
				async: false,
				data:{"code": value
					},
				success: function(result) {
					 msg=result.message;


				}
			});


			if (msg==="验证码不能为空!"){
				return "验证码不能为空!";
			}else if(msg==="验证码不存在!"){
				return "验证码不存在!";
			}else if(msg==="验证码已过期!"){
				refCode();
				return "验证码已过期!";
			}else if (msg==="验证码不正确!"){
				refCode();
				return "验证码不正确!";
			}

			/*alert(value)
			if(value.toUpperCase() != vailCode) {
				refCode();
				return "图品验证码错误";
			}*/
		}
		/*msgcode: function(value) {
			if(value.trim().length != 6) {
				return "短信验证码错误";
			} else if(msg_send_count == 0) {
				layer.msg("请点击获取验证码");
			} else {
				$.ajax({
					url: "user/vailSMSCode.do",
					type: "post",
					async: false,
					data: {
						"code": value,
						"type": "info"
					},
					success: function(result) {
						if(result.status == 0) {
							return result.msg;
						}
					}
				});
			}
		}*/
	});

	//监听提交  
	form.on("submit(register)", function() {
		$.ajax({
			url: "user/checkAccount.do",
			type: "post",
			data:{"email": $("#email").val(),
				"type": "email"},
			success: function(result) {
				email_status = result.code;
			}
		});
		if(email_status == 203) {
			layer.msg("该邮箱号已被注册", {
				icon: 5
			});
		} /*else if(msg_send_count == 0) {
			layer.msg("请点击获取验证码");
		}*/ else {
			// 发送注册请求到后台匹配
			$.ajax({
				url: "user/register.do",
				type: "post",
				data: {
					"usrEmail": $("#email").val(),
					"usrPassword": $("#password").val(),
					"usrName":$("#usrName").val()
				},
				success: function(result) {
					if(result.code === 0) {
						alert(result.data.usrAccount);
						layer.msg("注册成功");
						location.href='/registersuccess.html?usrAccount='+result.data.usrAccount;
					} else {
						$("form")[0].reset();
						layer.msg(result.msg, {
							icon: 5
						});
					}
				}
			});
			/*$.ajax({
				url: "user/vailSMSCode.do",
				type: "post",
				async: false,
				data: {
					"code": $("#msg-code").val()
				},
				success: function(result) {
					if(result.status == 1) {
						layer.msg(result.msg);
						return false;
					} else {
						// 发送注册请求到后台匹配
						$.ajax({
							url: "user/register.do",
							type: "post",
							data: {
								"email": $("#email").val(),
								"password": $("#password").val()
							},
							success: function(result) {
								if(result.status == 0) {
									layer.msg("注册成功");
									setTimeout("location='login.html'", 2000);
								} else {
									$("form")[0].reset();
									layer.msg(result.msg, {
										icon: 5
									});
								}
							}
						});
					}
				}
			});*/
		}

		return false;
	});
}();