<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<c:import url="/WEB-INF/partial/_links.jsp"></c:import>
<title>BuyCar - Connexion / Inscription</title>
</head>
<body class="d-flex flex-column min-vh-100">
	<c:import url="/WEB-INF/partial/_menu.jsp"></c:import>
	<main class="container mt-5">
		<h1 class="mb-4 text-center fw-bold">Bienvenue sur BuyCar</h1>
		<!-- Onglets -->
		<ul class="nav nav-tabs justify-content-center mb-4" id="authTabs"
			role="tablist">
			<li class="nav-item" role="presentation">
				<button class="nav-link active" id="login-tab" data-bs-toggle="tab"
					data-bs-target="#login-tab-pane" type="button" role="tab"
					aria-controls="login-tab-pane" aria-selected="true">Connexion</button>
			</li>
			<li class="nav-item" role="presentation">
				<button class="nav-link" id="register-tab" data-bs-toggle="tab"
					data-bs-target="#register-tab-pane" type="button" role="tab"
					aria-controls="register-tab-pane" aria-selected="false">Inscription</button>
			</li>
		</ul>
		<div class="tab-content" id="authTabsContent">
			<div class="tab-pane fade show active" id="login-tab-pane"
				role="tabpanel" aria-labelledby="login-tab" tabindex="0">
				<form action="${pageContext.request.contextPath}/connexion"
					method="post"
					class="p-4 border rounded shadow-sm bg-light w-50 mx-auto">
					<div class="form-floating mb-3">
						<input type="email" class="form-control" id="emailConnexion"
							name="email"
							value="${param.email != null ? param.email : (email != null ? email : '')}"
							placeholder="Email" required> <label
							for="emailConnexion">Email</label>
					</div>
					<div class="form-floating mb-3">
						<input type="password" class="form-control"
							id="motDePasseConnexion" name="motDePasse"
							placeholder="Mot de passe" required>
						<label for="motDePasseConnexion">Mot de passe</label>
					</div>
					<button type="submit" class="btn btn-primary w-100">Se
						connecter</button>
					<c:if test="${not empty errorMessage}">
						<div class="alert alert-danger mt-3">${errorMessage}</div>
					</c:if>
				</form>
			</div>
			<div class="tab-pane fade" id="register-tab-pane" role="tabpanel"
				aria-labelledby="register-tab" tabindex="0">
				<form action="${pageContext.request.contextPath}/inscription"
					method="post"
					class="p-4 border rounded shadow-sm bg-light w-50 mx-auto">
					<div class="row g-3 mb-3">
						<div class="col">
							<div class="form-floating">
								<input type="text" class="form-control" id="nom" name="nom"
									value="${param.nom != null ? param.nom : ''}" placeholder="Nom"
									required> <label
									for="nom">Nom</label>
							</div>
						</div>
						<div class="col">
							<div class="form-floating">
								<input type="text" class="form-control" id="prenom"
									name="prenom"
									value="${param.prenom != null ? param.prenom : ''}"
									placeholder="Prénom" required>
								<label for="prenom">Prénom</label>
							</div>
						</div>
					</div>
					<div class="form-floating mb-3">
						<input type="email" class="form-control" id="emailInscription"
							name="email" value="${param.email != null ? param.email : ''}"
							placeholder="Email" required> <label
							for="emailInscription">Email</label>
					</div>
					<div class="form-floating mb-3">
						<input type="password" class="form-control"
							id="motDePasseInscription" name="motDePasse"
							placeholder="Mot de passe" required>
						<label for="motDePasseInscription">Mot de passe</label>
					</div>
					<button type="submit" class="btn btn-success w-100">Créer
						un compte</button>
					<c:if test="${not empty errorMessage}">
						<div class="alert alert-danger mt-3">${errorMessage}</div>
					</c:if>
				</form>
			</div>
		</div>
	</main>
	<c:import url="/WEB-INF/partial/_footer.jsp"></c:import>
</body>
</html>
