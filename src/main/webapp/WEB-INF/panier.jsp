<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<c:import url="/WEB-INF/partial/_links.jsp"></c:import>
<title>Panier</title>
</head>
<body class="d-flex flex-column min-vh-100">
	
<c:import url="/WEB-INF/partial/_menu.jsp"></c:import>
	
<main class="container my-5 flex-grow-1">
	<h1 class="mb-4 text-center fw-bold">Votre Panier</h1>
		
	<c:choose>
		<c:when test="${not empty sessionScope.panier}">
			<div class="table-responsive">
				<table class="table table-striped align-middle text-center">
					<thead class="table-dark">
						<tr>
							<th>#</th>
							<th>Marque</th>
							<th>Modèle</th>
							<th>Année</th>
							<th>Prix (&euro;)</th>
							<th>Action</th>
						</tr>
					</thead>
					<tbody>
						<c:set var="total" value="0" />
						<c:forEach var="v" items="${sessionScope.panier}"
							varStatus="loop">
							<tr>
								<td>${loop.index + 1}</td>
								<td>${v.marque}</td>
								<td>${v.modele}</td>
								<td>${v.annee}</td>
								<td><fmt:formatNumber value="${v.prix}" type="currency" currencySymbol="&euro;" /></td>
								<td>
									<form action="${pageContext.request.contextPath}/panier" method="post" class="d-inline">
									<input type="hidden" name="action" value="remove"> 
									<input type="hidden" name="id" value="${v.idVoiture}">
									<button type="submit" class="btn btn-sm btn-outline-danger"><i class="fa-solid fa-trash"></i> Retirer</button>
									</form>
								</td>
							</tr>
								<c:set var="total" value="${total + v.prix}" />
							</c:forEach>
						</tbody>
						<tfoot>
							<tr class="table-secondary fw-bold">
								<td colspan="4" class="text-end">Total :</td>
								<td colspan="2"><fmt:formatNumber value="${total}"
										type="currency" currencySymbol="&euro;" /></td>
							</tr>
						</tfoot>
					</table>
				</div>
			</c:when>
			<c:otherwise>
				<div class="alert alert-info text-center">Votre panier est
					vide.</div>
			</c:otherwise>
		</c:choose>
	</main>
	<c:import url="/WEB-INF/partial/_footer.jsp"></c:import>
</body>
</html>
