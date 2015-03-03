<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>  
<%
	String path = request.getContextPath();
	String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";  
%>
<html>  
    <head>  
        <base href="<%=basePath%>">
        <title>My JSP 'chat.jsp' starting page</title>  
  
        <meta http-equiv="pragma" content="no-cache">  
        <meta http-equiv="cache-control" content="no-cache">  
        <meta http-equiv="expires" content="0">  

        <script type="text/javascript" src="${pageContext.request.contextPath}/dwr/engine.js"></script>
        <script type="text/javascript" src="${pageContext.request.contextPath}/dwr/util.js"></script>
        <script type="text/javascript" src="${pageContext.request.contextPath}/dwr/interface/MessagePush.js"></script>
        <script type="text/javascript">
        	function onPageLoad(){
            	var userId = 'ryo';
            	MessagePush.onPageLoad(userId);
        	}

        	function showMessage(msg) {
            	//alert(dwr.util.toDescriptiveString(msg));
            	alert(msg);
        	}
        </script>
	</head>
	<body onload="dwr.engine.setActiveReverseAjax(true);dwr.engine.setNotifyServerOnPageUnload(true);onPageLoad();">
	</body>
</html>