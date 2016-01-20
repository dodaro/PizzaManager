<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>

<nav class="navbar navbar-default navbar-fixed-top">
	<div class="container">
		<div class="navbar-header">
			<button type="button" class="navbar-toggle collapsed" data-toggle="collapse"
					data-target="#id-navbar-collapse">
				<span class="icon-bar"></span>
				<span class="icon-bar"></span>
				<span class="icon-bar"></span>
			</button>
			<a class="navbar-brand" href="/">PizzaManager</a>
		</div>

		<div class="collapse navbar-collapse" id="id-navbar-collapse">
			<ul class="nav navbar-nav">
			</ul>
			<ul class="nav navbar-nav navbar-right">
				<li>
					<form:form class="navbar-form form-inline" action="login" method="post" modelAttribute="logInForm">
						<div class="form-group">
							<input type="text" class="form-control" name="email" placeholder="Email">
						</div>
						<div class="form-group">
							<input type="text" class="form-control" name="password" placeholder="Password">
						</div>
						<input type="submit" class="btn btn-primary button-login" value="Log in" />
						<a href="signup" class="btn btn-success button-login">
							Create an account
						</a>
					</form:form>
				</li>
			</ul>
		</div>
	</div>
</nav>