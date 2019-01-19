var timeOut = 3000; // 防止重复点击的时间
var menu1_1_flag = true;

$(function() {

	$.ajaxSetup( {
		//设置ajax请求结束后的执行动作
		complete : function(XMLHttpRequest, textStatus) {
			// 通过XMLHttpRequest取得响应头，sessionstatus
			var sessionstatus = XMLHttpRequest.getResponseHeader("sessionstatus");
			if (sessionstatus == "TIMEOUT") {
				var win = window;
				while (win != win.top){
					win = win.top;
				}
				win.location.href= XMLHttpRequest.getResponseHeader("CONTEXTPATH");
			}
		}
	});

	// 获取用户名
	$.ajax({
		type : 'POST',
		url : '/ChlItPeng/user/showUser.action',
		data : {},
		success : function(data) {
			if (data.message) {
				$.messager.alert('警告', data.message, 'warning');
			}
			if (data.userId) {
				$('#now_user').html(data.userId);
				$('#now_user2').html(data.userId);
			} else {
				$('#now_user').html("<font color='red'>未登陆！</font>");
			}
		}
	});

	// 退出登录
	$('#logout').click(function() {
		$.ajax({ 
			type : 'POST',
			url : '/ChlItPeng/user/logout.action',
			data : {},
			async : false, // 同步，不是异步
			success : function(data) {
				window.location.href="/ChlItPeng/login.html";
			}
		});
	});

	// Logo刷新
	$('#imgLogo').click(function() {
		// alert(window.location.href);
		window.location.reload();
	});

	// 查询
	$('#menu1_1').click(function() {
		if (menu1_1_flag) {
			menu1_1_flag = false;
			setTimeout("menu1_1_flag = true", timeOut);
			showCenterObject("tableBox");
		} else {
			$.messager.alert('警告', '请勿频繁点击！', 'warning');
		}
	});

	// 新增
	$('#menu1_2').click(function() {
		showCenterObject("add_window");
	});

	// 修改个人密码
	$('#menu2_1').click(function() {
		showCenterObject("update_pwd_div");
	});

	// 查询范围下拉框
	$('#system_type').combobox({
		editable : false, // 不可编辑
		valueField : 'id',
		textField : 'text',
		url : '../js/systemNameList1.json',
		onChange : function(newValue, oldValue) {
			$('#tableBox').datagrid({
				queryParams : {
					type : $('#system_type').combobox('getValue'),
					key : $.trim($('#select_key').searchbox('getValue'))
				}
			});
		},
	});

	// 表格中的模糊查询框
	$('#select_key').searchbox({
		prompt : '输入关键字',
		searcher : function(value, name) {
			$('#tableBox').datagrid({
				queryParams : {
					type : $('#system_type').combobox('getValue'),
					key : $.trim(value)
				}
			});
		}
	});

	obj = {
		editRow : undefined,// 正在编辑的行索引号
		dele : function() {
			var row = $('#tableBox').datagrid('getSelected');
			if (row == null) {
				$.messager.alert('警告', '请选中要删除的行！', 'warning');
				return;
			}
			if (this.editRow == undefined) {
				var index = $('#tableBox').datagrid('getRowIndex', row);
				$.messager.confirm('确认删除', '是否删除产品 ' + row.productNo, function(flag) {
					if (flag) {
						$.ajax({
							type : 'POST',
							url : '/ChlItPeng/ProductModel/deleteOne.action',
							data : {
								productNo : row.productNo,
								systemType : row.systemType
							},
							beforeSend : function() {
								$('#tableBox').datagrid('loading');
							},
							success : function(data) {
								$('#tableBox').datagrid('loaded');
								$.messager.show({
									title : '删除提示',
									msg : data.message
								});
								$('#tableBox').datagrid('load');
								$('#tableBox').datagrid('onselectAll');
							}
						});
					}
				});
			}
		},
		edit : function() {
			var row = $('#tableBox').datagrid('getSelected');
			if (row == null) {
				$.messager.alert('警告', '未选中任何数据行！', 'warning');
				return;
			}
			if (this.editRow == undefined) {
				var index = $('#tableBox').datagrid('getRowIndex', row);
				$('#tableBox').datagrid('beginEdit', index);
				obj.editRow = index;
				$('#tab_save').show();
				$('#tab_redo').show();
			}
		},
		save : function() {
			$('#tableBox').datagrid('endEdit', this.editRow);
			this.editRow = undefined;
			$('#tab_save').hide();
			$('#tab_redo').hide();
		},
		redo : function() {
			this.editRow = undefined;
			$('#tab_save').hide();
			$('#tab_redo').hide();
			$('#tableBox').datagrid('rejectChanges');// 取消编辑
		}
	};

	// 新增功能的窗口初始化
	$('#add_window').window({
		title : '新增产品型号',
		iconCls : 'icon-add',
		modal : true, // 背景不可操作
		minimizable : false,
		maximizable : false,
		closed : true, // 初始化时关闭
		draggable : false, // 是否能够拖拽窗口
		resizable : false, // 能够改变窗口大小
		inline : true, // 包含在父容器内
		fit : true, // 自适应父容器
		onClose : function() {
			$('#add_form').form('reset');
		}
	});

	// 新增功能表单提交
	$('#add_save_button').click(function() {
		var a = $('#add_form').form('validate');
		if (!a) {
			return;
		}
		var productNo = $.trim($('#productNo').val());
		var systemType = $('#systemType').combobox('getValue');
		$.ajax({ // 查询产品型号是否已存在
			type : 'POST',
			url : '/ChlItPeng/ProductModel/selectOne.action',
			data : {
				productNo : productNo,
				systemType : systemType
			},
			success : function(data) {
				if ("OK" != data.message) {
					$.messager.alert('警告', data.message, 'warning');
					return;
				}
				// 开始提交
				addFromSubmit(productNo, systemType);
			}
		});
	});
	// 下拉框初始化
	$('#systemType').combobox({
		editable : false, // 不可编辑
		valueField : 'id',
		textField : 'text',
		url : '../js/systemNameList2.json',
		value : 'RCB'
	});
	// 表单重置
	$('#add_redo_button').click(function() {
		$('#add_form').form('reset');
	});
	// 字段校验
	$('#productNo').validatebox({
		required : true,
		validType : "length[1, 5]"
	});

	// 校验两次密码是否相同
	$.extend($.fn.validatebox.defaults.rules, {
		equals : {
			validator : function(value, param) {
				return value == $(param[0]).val();
			},
			message : '两次输入的密码不同！'
		}
	});

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

	// 修改密码表单提交
	$('#update_pwd_save').click(function() {
		var a = $('#update_pwd_form').form('validate');
		if (!a) {
			return;
		}
		var pwd1 = $.trim($('#pwd1').val());
		var pwd2 = $.trim($('#pwd3').val());
		$.ajax({
			type : 'POST',
			url : '/ChlItPeng/user/updatePwd.action',
			data : {
				oldPwd : pwd1,
				newPwd : pwd2
			},
			success : function(data) {
				$('#update_pwd_form').form('reset');
				if (data.message == "success") {
					$.messager.show({
						title : '修改结果',
						msg : '新密码已启用'
					});
					showCenterObject();// 返回欢迎页
					return;
				}
				$.messager.show({
					title : '修改结果',
					msg : data.message
				});
			}
		});
	});
	// 表单重置
	$('#update_pwd_redo').click(function() {
		$('#update_pwd_form').form('reset');
	});
});

function showCenterObject(id) {
	$('#welcome_img').hide();// 隐藏欢迎图片
	// $('#tableDiv').hide();// 隐藏数据表格
	$('#add_window').window('close');// 关闭新增窗口
	$('#update_pwd_div').window('close');
	if (id == "tableBox") {
		loadTable();
	} else if (id == "add_window") {
		$('#add_form').form('reset');
		$('#add_window').window('open');
	} else if (id == "update_pwd_div") {
		$('#update_pwd_form').form('reset');
		$('#update_pwd_div').window('open');
	} else {
		$('#welcome_img').show();
	}
}

function loadTable() {
	$('#tableDiv').show();
	$('#system_type').combobox('setValue', 'ALL');
	$('#select_key').searchbox('reset');// 清空模糊查询关键字

	$('#tableBox').datagrid({
		title : '产品型号列表',
		iconCls : 'icon-search',
		fit : true,// 自适应父级容器
		pagination : true, // 显示分页按钮
		url : '/ChlItPeng/ProductModel/getOnePage.action',
		queryParams : {
			type : $('#system_type').combobox('getValue'),
			key : $.trim($('#select_key').searchbox('getValue'))
		},
		columns : [ [ {
			title : '产品型号',
			field : 'productNo',
			sortable : true, // 允许排序
			halign : 'center', // 标题居中
			width : 70,
			formatter : formatterKey
		}, {
			title : '所属系统',
			field : 'systemType',
			sortable : true, // 允许排序
			halign : 'center', // 标题居中
			width : 70,
			formatter : formatterKey
		}, {
			title : '可用性',
			field : 'usability',
			sortable : true, // 允许排序
			halign : 'center', // 标题居中
			width : 60,
			formatter : formatterKey,
			editor : {
				type : 'text',
				options : {}
			}
		}, {
			title : '产品名称',
			field : 'productName',
			sortable : true, // 允许排序
			halign : 'center', // 标题居中
			width : 120,
			formatter : formatterKey,
			editor : {
				type : 'text',
				options : {}
			}
		}, {
			title : '客户名称',
			field : 'customerName',
			sortable : true, // 允许排序
			halign : 'center', // 标题居中
			width : 150,
			formatter : formatterKey,
			editor : {
				type : 'text',
				options : {}
			}
		}, {
			title : '首用车型',
			field : 'firstCarType',
			sortable : true, // 允许排序
			halign : 'center', // 标题居中
			width : 150,
			formatter : formatterKey,
			editor : {
				type : 'text',
				options : {}
			}
		}, {
			title : '客户图号',
			field : 'customerPrintNo',
			sortable : true, // 允许排序
			halign : 'center', // 标题居中
			width : 150,
			formatter : formatterKey,
			editor : {
				type : 'text',
				options : {}
			}
		}, {
			title : '发号日期',
			field : 'grantNumDate',
			sortable : true, // 允许排序
			halign : 'center', // 标题居中
			width : 100,
			formatter : formatterKey,
			editor : {
				type : 'datebox',
				options : {
					editable : false
				}
			}
		}, {
			title : '设计人',
			field : 'designPeople',
			sortable : true, // 允许排序
			halign : 'center', // 标题居中
			width : 80,
			formatter : formatterKey,
			editor : {
				type : 'text',
				options : {}
			}
		}, {
			title : '开发单位',
			field : 'developCompany',
			sortable : true, // 允许排序
			halign : 'center', // 标题居中
			width : 80,
			formatter : formatterKey,
			editor : {
				type : 'text',
				options : {}
			}
		}, {
			title : '说明（派生特征）',
			field : 'explainAll',
			sortable : true, // 允许排序
			formatter : formatterKey,
			editor : {
				type : 'textarea',
				options : {}
			}
		} ] ],
		singleSelect : true, // 只能选择一行
		striped : true,// 显示斑马线
		rownumbers : true,// 显示行号
		toolbar : '#tab_button',
		loadFilter : function(data) {// 过滤出报错信息
			if (data.message) {
				$.messager.alert('警告', data.message, 'warning');
				return data;
			} else {
				return data;
			}
		},
		// 双击进入编辑
		onDblClickCell : function(index, field, value) {
			if (obj.editRow == undefined) {
				$('#tableBox').datagrid('beginEdit', index);
				obj.editRow = index;
				$('#tab_save').show();
				$('#tab_redo').show();
			}
		},
		// 完成编辑后
		onAfterEdit : function(index, row, changes) {
			obj.editRow = undefined;
			$('#tab_save').hide();
			$('#tab_redo').hide();
			var updated = $('#tableBox').datagrid('getChanges', 'updated');
			$.ajax({
				type : 'POST',
				url : '/ChlItPeng/ProductModel/updateOne.action',
				data : updated[0],
				beforeSend : function() {
					$('#tableBox').datagrid('loading');
				},
				success : function(data) {
					$('#tableBox').datagrid('loaded');
					$.messager.show({
						title : '修改提示',
						msg : data.message
					});
					$('#tableBox').datagrid('load');
					$('#tableBox').datagrid('onselectAll');
				}
			});
		}
	});
}

// 模糊查询的结果中，让关键字显示为红色
function formatterKey(value, row, index) {
	var key = $.trim($('#select_key').searchbox('getValue'));
	if (-1 == value.indexOf(key)) {
		return value;
	}
	return value.replace(key, '<font style="color:red;">' + key + '</font>');
}

// 新增产品型号表单提交
function addFromSubmit(productNo, systemType) {
	$.messager.confirm('确认新增', '是否新增' + systemType + '产品 ' + productNo, function(flag) {
		if (!flag) {
			return;
		}
		$('#add_form').form('submit', {
			url : '/ChlItPeng/ProductModel/insertOne.action',
			onSubmit : function(param) {
				param.productNo = productNo;
				param.systemType = systemType;
				param.usability = $.trim($('#usability').val());
				param.productName = $.trim($('#productName').val());
				param.customerName = $.trim($('#customerName').val());
				param.firstCarType = $.trim($('#firstCarType').val());
				param.customerPrintNo = $.trim($('#customerPrintNo').val());
				param.grantNumDate = $.trim($('#grantNumDate').val());
				param.designPeople = $.trim($('#designPeople').val());
				param.developCompany = $.trim($('#developCompany').val());
				param.explainAll = $.trim($('#explainAll').val());
			},
			success : function(data) {
				var obj = JSON.parse(data)
				$.messager.show({
					title : '新增结果',
					msg : obj.message
				});
				if (obj.success) {
					// 新增成功就切换到查询窗口
					// showCenterObject("tableBox");
					$('#productNo').val("");
				}
			}
		});
	});
}
