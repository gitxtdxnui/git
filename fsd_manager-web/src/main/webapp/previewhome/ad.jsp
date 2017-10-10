<%@page import="java.io.InputStreamReader"%>
<%@page import="java.io.BufferedReader"%>
<%@page import="java.io.InputStream"%>
<%@page import="java.io.OutputStream"%>
<%@page import="java.net.URL"%>
<%@page import="java.net.HttpURLConnection"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<META http-equiv=Content-Type content="text/html; charset=UTF-8">
<title></title>
</head>
<body>
	<%
		String adId = request.getParameter("adPojo.advertisementid");
		
		URL url = new URL("http://10.4.2.40:8082/cms-web/admin/uuzzAd.action?adPojo.advertisementid=" + adId);
		HttpURLConnection huc = (HttpURLConnection) url.openConnection();
		
		InputStream in = huc.getInputStream();  
	    BufferedReader br = new BufferedReader(new InputStreamReader(in, "UTF-8"));
		OutputStream outputStream = response.getOutputStream();
	
	    String line = "";  
	    while((line = br.readLine()) != null) {  
	    	outputStream.write(line.getBytes());
	    }  
	    
		outputStream.flush();
		outputStream.close();
	
		in.close();
		
		outputStream = null;
		out.clear(); 
		out=pageContext.pushBody();
	%>
</body>
</html>