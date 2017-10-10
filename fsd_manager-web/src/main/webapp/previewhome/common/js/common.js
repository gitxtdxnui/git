/**
 * Created by admin on 2014/12/12.
 */
//用于菜单栏弹出提示信息
showMsg = function(msg) {
    alert(msg);
    return false;
};
$(document).ready(function() {

    /**
     * 首页焦点图切换
     */
    if ($("#focusImgBox").length > 0) {
        var img = $("#focusImgBox").find("a");
		for(var i=0;i<img.length;i++){
			$("#focusImgBox").find("ul").append("<li></li>");
		}
        var imgnum = $("#focusImgBox").find("li");
        var biaoshifu = 1;
        img.eq(0).css("display","inline");
		imgnum.eq(0).css("background-image", "url(../common/img/li_img01.png)").siblings("li").css("background-image", "url(../common/img/li_img02.png)");

        function lunbo() {
            if (biaoshifu < imgnum.length) {
                img.stop(true,true).eq(biaoshifu).fadeIn(1000).siblings("a").fadeOut(500);
                imgnum.eq(biaoshifu).css("background-image", "url(../common/img/li_img01.png)").siblings("li").css("background-image", "url(../common/img/li_img02.png)");
                biaoshifu++;
                if (biaoshifu >= imgnum.length) {
                    biaoshifu = 0;
                }
            }

        };
        var t = setInterval(lunbo, 4000);
        imgnum.on("click", function(e) {
            var index = $(this).index();
            biaoshifu = index;
            img.stop(true,true).eq(index).fadeIn(1000).siblings("a").fadeOut(500);
            imgnum.eq(index).css("background-image", "url(../common/img/li_img01.gif)").siblings("li").css("background-image", "url(../common/img/li_img02.gif)");
        })

        $("#focusImgBox").hover(function() {
            clearInterval(t);
        }, function() {
            t = setInterval(lunbo, 4000);
        })
    }


})

window.onload = function() {
    /**
     * 纵向不够一屏幕，底部浏览器底边对齐
     */
    if ($(document).height() == $(window).height()) {
        $(".footerIframe").css("position", "absolute").css("bottom", "0");
    }
}

//取地址栏参数
function getUrlParam(param) {
    var reg = new RegExp("(^|&)" + param + "=([^&]*)(&|$)"); //构造一个含有目标参数的正则表达式对象
    var r = window.location.search.substr(1).match(reg);  //匹配目标参数
    if (r != null) return decodeURI(r[2]); return null; //返回参数值
}
//页面跳转
function windowLoad(url){
	window.parent.location.href=url;
}
function getFormatDateTime(time) {
	if(time){
		var date = new Date(time);
		var y = date.getFullYear();
		var m = date.getMonth() + 1;
		var d = date.getDate();
		var hour = date.getHours();
		var min = date.getMinutes();
		var sec = date.getSeconds();
		return y + '-' + (m < 10 ? '0' + m : m) + '-' + (d < 10 ? '0' + d : d)
				+ ' ' + (hour < 10 ? '0' + hour : hour) + ':'
				+ (min < 10 ? '0' + min : min) + ':'
				+ (sec < 10 ? '0' + sec : sec);
	}
	return null;
}
function getFormatDate(time) {
	if(time){
		var date = new Date(time);
		var y = date.getFullYear();
		var m = date.getMonth() + 1;
		var d = date.getDate();
		return y + '-' + (m < 10 ? '0' + m : m) + '-' + (d < 10 ? '0' + d : d);
	}
	return null;
}
