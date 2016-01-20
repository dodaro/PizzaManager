<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page session="false"%>
<html>
<head>
<script type="text/javascript" src="resources/js/jquery.js"></script>
<script type="text/javascript" src="resources/js/bootstrap.js"></script>

<link rel="stylesheet" type="text/css" href="resources/css/bootstrap.css" />
<link rel="stylesheet" type="text/css" href="resources/css/common.css" />
<link rel="stylesheet" type="text/css" href="resources/css/userMainView.css" />

<title>User View</title>

<meta name="viewport" content="width=device-width" />

</head>
<body>
	<jsp:include page="includes/navbar${typeSession}.jsp" />

	<div class="container">
		<div class="row">
			<div class="col-xs-4">
				<div class="bubble">
					<div class="profile-image-container">
						<img src="resources/images/no-image.png" class="img-circle">
					</div>
					<div class="username-container">${searchedUser.name}</div>
				</div>
			</div>
			<div class="col-xs-8 wrapper">
				<div class="bubble bubble-feedbacks">
					<div class="bubble-title">Feedbacks</div>
					<div class="feedbacks">
						<c:forEach items="${searchedUser.feedbacks}" var="feedback">
							<div class="feedback">
								<div class="pizzeria-name">
									<a href="pizzeriamainview?id=${feedback.pizzeria.id}">${feedback.pizzeria.name}</a>
								</div>
								<div class="ratings">
									<div class="rating row">
										<div class="col-xs-2 rating-name">Quality</div>
										<div class="col-xs-10">
											<span class="stars"><c:forEach begin="1" end="${feedback.qualityRating}">
													<img src="resources/images/star.png">
												</c:forEach> <c:forEach begin="${feedback.qualityRating}" end="4">
													<img src="resources/images/star_grey.png">
												</c:forEach></span>
										</div>
									</div>
									<div class="rating row">
										<div class="col-xs-2 rating-name">Fastness</div>
										<div class="col-xs-10">
											<span class="stars"><c:forEach begin="1" end="${feedback.fastnessRating}">
													<img src="resources/images/star.png">
												</c:forEach> <c:forEach begin="${feedback.fastnessRating}" end="4">
													<img src="resources/images/star_grey.png">
												</c:forEach></span>
										</div>
									</div>
									<div class="rating row">
										<div class="col-xs-2 rating-name">Hospitality</div>
										<div class="col-xs-10">
											<span class="stars"><c:forEach begin="1" end="${feedback.hospitalityRating}">
													<img src="resources/images/star.png">
												</c:forEach> <c:forEach begin="${feedback.hospitalityRating}" end="4">
													<img src="resources/images/star_grey.png">
												</c:forEach></span>
										</div>
									</div>
								</div>
								<c:if test="${feedback.comment.length() > 0}">
									<div class="comment">"${feedback.comment}"</div>
								</c:if>
							</div>
						</c:forEach>

					</div>
				</div>
			</div>
		</div>
	</div>










	<!--  
	<div class="container">

		<div class="row">

			<div id="wrapper" class="col-xs-10">
				<div class="card">
					<div id="content">
						<h2>User ${searchedUser.name}</h2>
					</div>
				</div>
			</div>
			<div class="col-sm-3 col-sm-push-3">
				<div>Conviene mettere nella colonna a sinistra immagine e info, e nella colonna a destra
					una lista di tutti i feedback che l'utente ha rilasciato</div>
			</div>
			<div class="col-xs-3 col-sm-3 col-sm-pull-3">
				<h2>Latest Reviews</h2>
				<div class="pre-scrollable">
					<div id="boxReview">
						<c:forEach var="r" items="${feedbacksuser}">
							<div class="row">
								<a class="myref" href="pizzeriamainview?id=${r.pizzeria.id}">${r.pizzeria.name}</a>
								<div>Fastnes: ${r.fastnessRating}</div>
								<div>Hospitality: ${r.hospitalityRating}</div>
								<div>Quality: ${r.qualityRating}</div>
								<div>Comment:</div>
								<div>${r.comment}</div>

							</div>
						</c:forEach>
					</div>
				</div>
			</div>

		</div>
	</div>-->
</body>
</html>

