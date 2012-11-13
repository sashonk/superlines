<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Superlines 2.0</title>

<style>
	body{
		font-family: times new roman;
		
	}
	
	td.content{
		font-size: 14px;
	}
	
	table{
		
		margin-top: 20px;
	}

</style>

</head>
<body>
	<table width="60%" border="1" align="center">
		<tr><td align="center"><h1>superlines 2.0</h1></td></tr>
		<tr><td class="content">
		
			<% 
				String p = request.getParameter("page");
				if(p==null){
					p = "";
				}
			
				
			%>
			
			<%  if(p.equals("rules")){ %>
				<%@ include file="rules.jsp" %>				
				<% 	} 
				else if(p.equals("rank")){ %>
				<%@ include file="rank.jsp" %>
				<% 	}
				else if(p.equals("standard")){ %>
				<%@ include file="standard.jsp" %>				
				<% 	}
				else{ %>
				<%@ include file="index.jsp" %>			
			<% 	}%>

		
		</td></tr>
		<tr><td align="center" style="font-size:11px;">&copysashonk 2012</td></tr>		
	</table>
</body>
</html>