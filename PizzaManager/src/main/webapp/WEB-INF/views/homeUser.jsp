<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page session="false"%>
<html>
<head>
<script type="text/javascript" src="resources/js/jquery.js"></script>
<script type="text/javascript" src="resources/js/bootstrap.js"></script>
<script type="text/javascript" src="https://maps.googleapis.com/maps/api/js"></script>

<script type="text/javascript" src="resources/js/maps.js"></script>

<script type="text/javascript" src="resources/js/user/homeUser.js"></script>

<link rel="stylesheet" type="text/css" href="resources/css/bootstrap.css" />
<link rel="stylesheet" type="text/css" href="resources/css/common.css" />
<link rel="stylesheet" type="text/css" href="resources/css/homeUser.css" />

<title>Home User</title>

<meta name="viewport" content="width=device-width" />

<style type="text/css">
</style>
</head>
<body>
	<jsp:include page="includes/navbarAccount.jsp" />

	<div class="container">
		<div class="row">
			<div class="col-xs-4">
				<div class="bubble">
					<div class="profile-image-container">
						<img src="resources/images/no-image.png" class="img-circle">
					</div>
					<div class="name-container">${user.firstName}<span> </span>${user.lastName}</div>
					<div class="username-container">${user.name}</div>
				</div>
				<div class="bubble feedbacks-bubble">
					<div class="bubble-title">Your latest feedbacks</div>
					<c:choose>
						<c:when test="${user.feedbacks.size() > 0}">
							<div class="feedbacks-header">
								<a href="usermainview?id=${user.id}">View all feedbacks.</a>
							</div>
							<div class="feedbacks">
								<c:forEach begin="1" end="${numberOfFeedbacks}" items="${user.feedbacks}" var="feedback">
									<div class="feedback">
										<div class="pizzeria-name">
											<a href="pizzeriamainview?id=${feedback.pizzeria.id}">${feedback.pizzeria.name}</a>
										</div>
										<div class="ratings">
											<div class="rating row">
												<div class="col-xs-3 rating-name">Quality</div>
												<div class="col-xs-9">
													<span class="stars"><c:forEach begin="1" end="${feedback.qualityRating}">
															<img src="resources/images/star.png">
														</c:forEach></span>
												</div>
											</div>
											<div class="rating row">
												<div class="col-xs-3 rating-name">Fastness</div>
												<div class="col-xs-9">
													<span class="stars"><c:forEach begin="1" end="${feedback.fastnessRating}">
															<img src="resources/images/star.png">
														</c:forEach></span>
												</div>
											</div>
											<div class="rating row">
												<div class="col-xs-3 rating-name">Hospitality</div>
												<div class="col-xs-9">
													<span class="stars"><c:forEach begin="1" end="${feedback.hospitalityRating}">
															<img src="resources/images/star.png">
														</c:forEach></span>
												</div>
											</div>
										</div>
										<div class="comment">"${feedback.comment}"</div>
									</div>
								</c:forEach>
							</div>
						</c:when>
						<c:otherwise>
							<div class="no-feedbacks">You didn't leave any feedback yet.</div>
						</c:otherwise>
					</c:choose>
				</div>
			</div>
			<div class="col-xs-8 wrapper">
				<div class="bubble">
					<div class="bubble-title">Book a pizza now!</div>
					<h4>Most Sold:</h4>
					<c:forEach begin="0" end="4" items="${top}" var="p">
						<div class="row">
							<a class="myref" href="pizza?id=${p.getId()}">${p.name}</a>
						</div>
					</c:forEach>
				</div>
				<div class="bubble">
					<div class="bubble-title">Find pizzerias near you</div>
					<div>
						Find pizzerias within <input type="number" value="${radius}" class="form-control form-radius">
						km
						<button class="btn btn-default button-radius">Update</button>
					</div>
					<div id="map"></div>
				</div>
			</div>
		</div>
	</div>
</body>

</html>