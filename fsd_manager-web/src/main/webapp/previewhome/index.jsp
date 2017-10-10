<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<META http-equiv=Content-Type content="text/html; charset=UTF-8">	
<title>壹平台彩票网站内容管理系统</title>
</head>
<body>
<%
	String strUserAgent = request.getHeader("user-agent").toLowerCase();    
	//out.println(strUserAgent);
	
	if(request.getHeader("user-agent")!=null){
		if(strUserAgent.toLowerCase().indexOf("mobile")!=-1){
			//out.println("手机wap访问");
			response.setContentType("text/html; charset=UTF-8");
			response.sendRedirect("wap");
		}else{
			//out.println("使用web浏览器");
			response.setContentType("text/html; charset=UTF-8");
			response.sendRedirect("admin");
		}
	}else{
		out.println("getHeader为null,使用web浏览器");
	}
%>
</body>
</html>