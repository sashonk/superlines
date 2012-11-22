<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<%@ page 

	import="java.util.*" 
	import="java.util.regex.Pattern"
	import="superlines.server.AccountDAO"
%>

<%  

 String confirmed = request.getParameter("confirm");
 String created = request.getParameter("create");
 
 if(created!=null){
	 %> 
	 
	<div style="padding: 20px;"> На указанный вами почтовый адрес отправлен email с ссылкой для активации аккаунта.</div>
	 
	 
	 <% 
 }
 else if(confirmed!=null){
	 
	 String token = request.getParameter("token");
	 int st =  AccountDAO.get().confirmAccount(token);
	 
	 if(st == 0) {
	 %> 	 
	 <div style="padding: 20px;">  Аккаунт подтвержден!</div>	 	 
	 <%
	 }
	 else{
		 %> 
		 	<div style="padding: 20px; color: red'">  Аккаунт не подтвержден!</div>	 	 
		 
		 <%
	 }


 }
 else{


String name = request.getParameter("name");
String surname = request.getParameter("surname");
String login = request.getParameter("login");
String password = request.getParameter("password");
String email = request.getParameter("email");
	

%>



<div style="padding: 20px;">
<form>
	<div>имя</div>
	<div><input type="text" name="name" value='<%= name == null ? "" : name %>'></div>

	<div>фамилия</div>
	<div><input type="text" name="surname" value='<%= surname == null ? "" : surname %>'></div>

	<div>логин</div>
	<div><input type="text" name="login" value='<%= login == null ? "" : login %>'></div>

	<div>пароль</div>
	<div><input type="password" name="password"></div>
	
	<div>почта</div>
	<div><input type="text" name="email" value='<%= email == null ? "" : email %>'></div>
	
	<div style="padding-top: 20px"><input type="submit" value="OK" style="width: 80px;"></div>
	<input type="hidden"  name="register" value="true">
	<input type="hidden"  name="page" value="registration">	
</form>

</div>

<%
	
	String action = request.getParameter("register");

	if(action!=null){
		
		List<String> messages = new LinkedList<String>();
		
		if(isBlank(name)){
			messages.add("не указано имя");
		}
		
		if(isBlank(surname)){
			messages.add("не указана фамилия");		
		}
		if(isBlank(login)){
			messages.add("не указан логин");		
		}		
		if(isBlank(password)){
			messages.add("не указан пароль");		
		}		
		if(isBlank(email)){
			messages.add("не указан почтовый адрес");		
		}	
		
		
		if(messages.size()==0){
			if(!isValidEmailAddress(email)){
				messages.add("введен некорректный почтовый адрес");							
			}
			
			if(!AccountDAO.get().isUniqueLogin(login)){
				messages.add("данный логин уже используется");						
			}			
		}
		

		if(messages.size()>0){
			%>
				<ul>
					<% for(String message : messages) { %>
					
						<li style="color:red;"><%= message  %></li>
				
					<%  } %>
				</ul>
			
			<%
			
		}
		else{
			AccountDAO.get().createAccount(login, password, name, surname, email);
			
			response.sendRedirect("?page=registration&create");
		}
		
	}


 }

%>

<%!
	private static boolean isBlank(final String param){
		return param== null || param.equals("");
	}

	private static boolean isValidEmailAddress(final String str) {
	    if (isBlank(str)) {
	        return false;
	    }
	    return EMAIL_ADDRESS_PATTERN.matcher(str).matches();
	}

	private static final Pattern EMAIL_ADDRESS_PATTERN =
			Pattern
			.compile("([0-9A-Za-z-_]+\\.)*[0-9A-Za-z-_]+@((([0-9A-Za-z-_]+\\.)+[A-Za-z]+)|(\\d{1,3}\\.\\d{1,3}\\.\\d{1,3}\\.\\d{1,3}))"); 


%>





<div style="text-align: center"><a href="?">на главную</a></div>

