function ajax_submit() {
	$.ajax({
		type : 'POST',
		url : '/ChlItPeng/user/login.action',
		data : {
			userId : $.trim($('#userId').val()),
			userPwd : $.trim($('#userPwd').val())
		},
		success : function(data) {
			if ("success" == data.message) {
				window.location.href = "/ChlItPeng/user/main.action";
				return;
			}
			$('#userPwd').val("");
			$.messager.show({
				title : '登录提示',
				iconCls : 'icon-no',
				msg : data.message
			});
		}
	});
	return false;
};