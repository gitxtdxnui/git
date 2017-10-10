$(function() {

	var analysisBox = $(window.parent.document).find('.analysisBoxList');//网站新闻
	if(channel_1&&analysisBox){
		$.each(channel_1, function(i, item) {
			if(i>10){
				return;
			}
			var title='';
			if(item.orderBy==1){
				title+='[置顶]';
			}
			if(title.length>41){
				title=title.substring(0,40)+'...';
			}
			title+=item.title
			analysisBox.append('<li><div><span class="NoiceTitleText"><a href="news/detail.html?name='+item.homeFile+'" target="_target">'+title+'</a></span><span class="NoiceQiText">'+getFormatDate(item.publishTime)+'</span></div></li>');
		});
	
	}
	var downHelpTitleList = $(window.parent.document).find('.downHelpTitleList');//相关文章
	if(channel_1&&downHelpTitleList){
		$.each(channel_1, function(i, item) {
			if(i>20){
				return;
			}
			var title='';
			title+=item.title
			downHelpTitleList.append('<li><a href="../news/detail.html?name='+item.homeFile+'" target="_target">'+title+'</li>');
		});
	
	}
	var NoiceBox = $(window.parent.document).find('.NoiceBox');//公告
	if(channel_2&&NoiceBox){
		$.each(channel_2, function(i, item) {
			if(i>20){
				return;
			}
			var title='';
			if(item.orderBy==1){
				title+='[置顶]';
			}
			title+=item.title
			if(title.length>15){
				title=title.substring(0,14)+'...';
			}
			NoiceBox.append('<li><div class="NoiceTitle"><span class="NoiceTitleText"><a href="news/detail.html?name='+item.homeFile+'" target="_target">'+title+'</a></span><span class="NoiceQiText">'+getFormatDate(item.publishTime)+'</span></div></li>');
		});
	
	}

})
