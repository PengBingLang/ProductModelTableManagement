// 修改密码窗口初始化
$('#update_pwd_div').window({
	title : '修改个人密码',
	iconCls : 'icon-man',
	modal : true, // 背景不可操作
	minimizable : false,
	maximizable : false,
	closed : true, // 初始化时关闭
	draggable : false, // 是否能够拖拽窗口
	resizable : false, // 能够改变窗口大小
	inline : true, // 包含在父容器内
	fit : true, // 自适应父容器
	onClose : function() {
		$('#update_pwd_form').form('reset');
	}
});