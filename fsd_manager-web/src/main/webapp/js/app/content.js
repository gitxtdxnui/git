var com = {};
com.cms = {};
com.cms.content = {
	init : function(){
		$.get('./user/init',function(res){
			try{
				if(res.status != 200){
					$.messager.alert({
						title : '提示', 
						msg: '登录超时！请重新登录！',
						fn: function(){
							window.location = './login.html';
						}
					});
				}else{
					com.cms.content.channelEnum = res.channelEnum;
					com.cms.content.view();
				}
			}catch(e){
				window.location = './login.html';
			}
		});
	},
	_openContenWin: function(obj){
		var win = $('#window-content').dialog({
			width: 800,
			height: 600,
			modal: true,
			title: obj.winTitle,
			href : './contentForm.html',
			buttons : [{
				text: '保存',
				iconCls : 'icon-ok',
				handler: function(){
					$('#manager_content_window_form').form('submit',{
		            	url: obj.url,
		            	success: function(res){
		            		var json = eval('('+res+')');
		            		var msg = json.msg || '保存成功';
		            		if(json.status == 200){
		            			win.dialog('close');
		            		}
	            			$.messager.alert('提示', msg);
	            			$('#manager_content_grid').datagrid('load');
		            	}
		            });
				}
			},{
				text: '关闭',
				iconCls : 'icon-cancel',
				handler: function(){
					win.dialog('close');
				}
			}],
			onLoad: function(){
				$('#manager_content_window_form').form('load',obj);
			}
		});
		$('#window-content').window('center');
	},
	add : function(){
		var obj = {
			url : './content/save',
			winTitle : '新建'
		};
		com.cms.content._openContenWin(obj);
	},
	search : function(){
		UTILS.searchGridData('manager_content_grid_form', 'manager_content_grid');
	},
	modify : function(id){
		$.get('./content/selectOne?id=' + id,function(res){
			if(res.status == 200){
				var obj = {
					id: id,
					url : './content/modify?id=' + id,
					winTitle : '修改',
					title: res.content.title,
					content: res.content.content,
					channel: res.content.channel
				};
				com.cms.content._openContenWin(obj);
			}
		})
	},
	publish : function(id){
		$.messager.confirm('提示', '是否确定发布内容?', function(r){
			if (r){
				$.get('./content/publish?id=' + id,function(res){
					if(res.status == 200){
		    			$('#manager_content_grid').datagrid('load');
					}else{
						$.messager.alert('提示', res.msg);
					}
				})
			}
		})
	},
	offline : function(id){
		$.messager.confirm('提示', '是否确定下线内容?', function(r){
			if (r){
				$.get('./content/offline?id=' + id,function(res){
					if(res.status == 200){
		    			$('#manager_content_grid').datagrid('load');
					}else{
						$.messager.alert('提示', res.msg);
					}
				})
			}
		})
	},
	del : function(id){
		$.messager.confirm('提示', '是否确定删除?', function(r){
			if (r){
				$.get('./content/delete?id=' + id,function(res){
					if(res.status == 200){
		    			$('#manager_content_grid').datagrid('load');
					}else{
						$.messager.alert('提示', res.msg);
					}
				})
			}
		})
	},
	preview : function(id){
		var b=true;
		var homeFile='';
		$.ajax({
			url:'./content/preview?id=' + id,
			async:false,
			type:'get',
			dataType:'json',
			success:function(res){
				if(res.status == 200){
					homeFile=res.homeFile;
				}else{
					$.messager.alert('提示', res.msg);
					b=false;
				}
			
			}
		});
		if(b){
			window.open("./previewhome/html/news/detail.html?name="+homeFile,"_blank");
		}
	},
	view: function(){
		$('#manager_content_grid').datagrid({
			url: './content/select',
			title: '内容发布',
			fit: true,
			fitColumns:true,
			idField : 'id',
			pagination: true,
			pageSize : 20,
			pageList : [10, 20, 50],
			nowrap: false,
			singleSelect : true,
			rownumbers : true,
		    columns:[[
				{title:'标题',field:'title',align: 'left', width: '45%'},
				{title:'频道',field:'channel',align: 'center', width: '10%', formatter:function(v, r, i){
					return com.cms.content.channelEnum[v];
				}},
				{title:'是否置顶',field:'orderBy',align: 'center', width: '10%', formatter:function(v, r, i){
					if(v == 1){
						return '已置顶';
					}
					return '未置顶';
				}},
				{title:'发布状态',field:'status',align: 'center', width: '10%', formatter:function(v, r, i){
					if(v == 1){
						return '已发布';
					}
					return '未发布';
				}},
				{title:'发布时间',field:'publishTime',align: 'center', width: '15%', formatter:function(v, r, i){
					return UTILS.getFormatDateTime(v) || '';
				}},
				{
					title: '操作',
					field: 'opt',
					width: '10%',
					align: 'center',
					formatter:function(v, r, i){
						var a = '<a href="javascript:void(0);" onclick="com.cms.content.preview(\''+r.id+'\');" style="padding-right:10px;" id="manager_content_grid_view">预览</a>';
						if(r.status == 1){
							a += '<a href="javascript:void(0);" onclick="com.cms.content.offline(\''+r.id+'\');" style="padding-right:10px;" id="manager_content_grid_publish">下线</a>';
						}else{
							a += '<a href="javascript:void(0);" onclick="com.cms.content.publish(\''+r.id+'\');" style="padding-right:10px;" id="manager_content_grid_publish">发布</a>' +
							'<a href="javascript:void(0);" onclick="com.cms.content.modify(\''+r.id+'\');" style="padding-right:10px;" id="manager_content_grid_modify">修改</a>' +
							'<a href="javascript:void(0);" onclick="com.cms.content.del(\''+r.id+'\');" id="manager_content_grid_del">删除</a>';
						}
						return a;
					}
				}
		    ]]
		});
	},
	modifypassword: function(){
		var win = $('#window-content').dialog({
			width: 500,
			height: 300,
			modal: true,
			title: '修改密码',
			href : './modifypassword.html',
			buttons : [{
				text: '保存',
				iconCls : 'icon-ok',
				handler: function(){
					$('#manager_user_modifypassword_form').form('submit',{
		            	url: './user/modifyPassword',
		            	success: function(res){
		            		var json = eval('('+res+')');
		            		var msg = json.msg || '保存成功';
		            		if(json.status == 200){
		            			win.dialog('close');
		            		}
	            			$.messager.alert('提示', msg);
		            	}
		            });
				}
			},{
				text: '关闭',
				iconCls : 'icon-cancel',
				handler: function(){
					win.dialog('close');
				}
			}]
		});
		$('#window-content').window('center');
	},
	loginout: function(){
		$.get('./user/loginout', function(res){
			window.location = './login.html';
		});
	}
};

$(function(){
	com.cms.content.init();
});