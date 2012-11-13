<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<%@ page 
	import="superlines.server.*"
	import="superlines.core.*"
	%>



<div class="title"><b>правила</b></div>
<table border="1" cellpadding="2">
	<tr> 
		<th>Ранг</th> 
		<th>Шаров в линии</th> 
		<th>Нормальное вознаграждение</th> 
		<th>Дополнительное вознаграждение</th> 
		<th>Разброс шаров</th> 
		<th>Количество цветов</th> 		
		<th>Ширина поля</th> 		
		<th>Минимум очков за игру</th> 			
		
		<th>Прогрессив</th> 		
		<th>Прогрессив-1 порог</th> 	
		<th>Прогрессив-2 порог</th> 	
		<th>Прогрессив-1 множитель</th> 		
		<th>Прогрессив-2 множитель</th> 			
	
		
	</tr>
	
	<%
		for(Rank r : Rank.values()){
			SuperlinesRules rules = superlines.server.RulesHelper.createRules(r);
			
			%> <tr> 
				<td class="rulestd" align="center"><%= r %></td>			
				<td class="rulestd" align="center"><%= rules.getMinWinBalls() %> </td>
				<td class="rulestd" align="center"><%= rules.getNormalAward() %> </td>
				<td class="rulestd" align="center"><%= rules.getExtraAward() %></td>
				<td class="rulestd" align="center"><%= rules.getScatterBallsCount() %></td>
				<td class="rulestd" align="center"><%= rules.getColorCount() %></td>
				<td class="rulestd" align="center"><%= rules.getTableWidth() %></td>
				<td class="rulestd" align="center"><%= rules.getMinScore() %></td>
				<td class="rulestd" align="center"><%= rules.isProgressiveEnabled() ? "да" : "нет" %></td>
				<td class="rulestd" align="center"><%= rules.getProgressive1Threshold() %></td>
				<td class="rulestd" align="center"><%= rules.getProgressive2Threshold() %></td>
				<td class="rulestd" align="center"><%= rules.getProgressive1Multiplier() %></td>		
				<td class="rulestd" align="center"><%= rules.getProgressive2Multiplier() %></td>						
			</tr> <%
			
		}
	
	%>
	

</table>


<div class="title"><b>нормативы</b></div>
<table  border="1" align="center" width="100%" cellpadding="10">
<tr>
	<td>Ранг</td>
<%	
	for(Rank r : Rank.values()){
		%> 
			<td  align="center"><%= r %></td>					
		<%
	}	
%>
</tr>


<tr>
<td>Очки</td>
<%	
for(Rank r : Rank.values()){
	%> 
		<td align="center"><b><%= PromotionHelper.getQualifiedRate(r) %></b></td>					
	<%
}	
%>
</tr>

</table>

<div style="text-align: center"><a href="?">на главную</a></div>