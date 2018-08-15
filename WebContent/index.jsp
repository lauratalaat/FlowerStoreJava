<!DOCTYPE html> 
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Add Flower Form</title>
</head>
<body>

<div style="margin-top: 30vh;;">
  <div align="center"><font size="5" style="color:red">Add Flower Form</font></div><br />
  <br />  
	<form action="${pageContext.request.contextPath}/add" method="get">
		<div style="vertical-align: middle; margin-right: auto; margin-left: auto; text-align: center;">
			Name: <input type="text" name="name" /><br /> 
			<br />
			Price: <input type="text" name="price" /><br /> 
			<br />
	  		<input type="submit" value="Add Flower" />		
	  	</div>		
	</form>
</div>				
</body>
</html>