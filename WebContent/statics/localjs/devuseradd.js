$(function(){
	$("#sub").click(function() {
		if ($("#password2").val()!=$("#devPassword").val()) {
			alert("两次密码不一致");
		}else if ($("#devCode").val()!=null) {
			$.ajax({
				type:"GET",//请求类型
				url:"selectByDevCode.json",//请求的url
				data:{devCode:$("#devCode").val()},//请求参数
				dataType:"json",//ajax接口（请求url）返回的数据类型
				success:function(data){//data：返回数据（json对象）
					if(data.devCode == "empty"){//参数devCode为空，错误提示
						alert("账号为不能为空！");
						$("#sub").attr("disabled",true);
					}else if(data.devCode == "exist"){//账号不可用，错误提示
						alert("该账号已存在，不能使用！");
					}else if(data.devCode == "noexist"){//账号可用，正确提示
						alert("该账号可以使用！");
						$("#form1").submit();
					}
				},
				error:function(data){//当访问时候，404，500 等非200的错误状态码
					alert("请求错误！");
				}
			});
		}
	});
	/*$("#devCode").bind("blur",function(){
		//ajax后台验证--APKName是否已存在
		$.ajax({
			type:"GET",//请求类型
			url:"selectByDevCode.json",//请求的url
			data:{devCode:$("#devCode").val()},//请求参数
			dataType:"json",//ajax接口（请求url）返回的数据类型
			success:function(data){//data：返回数据（json对象）
				if(data.devCode == "empty"){//参数devCode为空，错误提示
					alert("账号为不能为空！");
					$("#sub").attr("disabled",true);
				}else if(data.devCode == "exist"){//账号不可用，错误提示
					alert("该账号已存在，不能使用！");
					$("#sub").attr("disabled",true);
				}else if(data.devCode == "noexist"){//账号可用，正确提示
					alert("该账号可以使用！");
					$("#sub").removeAttr("disabled");
				}
			},
			error:function(data){//当访问时候，404，500 等非200的错误状态码
				alert("请求错误！");
			}
		});
	});
	$("#password2").bind("blur",function(){
		if ($("#password2").val()!=$("#devPassword").val()) {
			$("#sub").attr("disabled",true);
			return false;
		}else {
			$("#sub").removeAttr("disabled");
		}
	});*/
 });