<html>
<body>
	<h1>Maven + Spring MVC Web Project Example</h1>
	<h2>Login Page</h2>

	<form action="/dologin" method="POST">
		<div>Username: 
			<input type="text" name="j_username" />
		</div>
		
		<div>Password:
			<input type="text" name="j_password" />
		</div>
		
		<div>
			Remember Me:
			<input type="checkbox" name="remember" value="yes" />
		</div>
		
		<button type="submit">Login</button>
	</form>
</body>
</html>