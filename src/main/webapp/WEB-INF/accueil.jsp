<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<c:import url="/WEB-INF/partial/_links.jsp"></c:import>
<title>Liste</title>
</head>
<body class="d-flex flex-column min-vh-100">
    <c:import url="/WEB-INF/partial/_menu.jsp"></c:import>
    <main class="container my-5 flex-grow-1">
        <h1 class="mb-4 text-center fw-bold">Liste des voitures</h1>
        <div class="mb-4">
            <form method="get" action="${pageContext.request.contextPath}/accueil" class="row g-2">
                <div class="col-md-3">
                    <input type="text" name="marque" class="form-control" placeholder="Marque" value="${param.marque}">
                </div>
                <div class="col-md-3">
                    <input type="text" name="modele" class="form-control" placeholder="Modèle" value="${param.modele}">
                </div>
                <div class="col-md-2">
                    <input type="number" step="0.01" min="0" name="prixMin" class="form-control" placeholder="Prix min" value="${param.prixMin}">
                </div>
                <div class="col-md-2">
                    <input type="number" step="0.01" min="0" name="prixMax" class="form-control" placeholder="Prix max" value="${param.prixMax}">
                </div>
                <div class="col-md-2">
                    <button type="submit" class="btn btn-primary w-100">Rechercher</button>
                </div>
            </form>
        </div>
        <c:choose>
            <c:when test="${not empty voitures}">
                <div class="accordion" id="voituresAccordion">
                    <c:forEach var="v" items="${voitures}" varStatus="status">
                        <div class="accordion-item mb-3 border-0 shadow-sm rounded-3 overflow-hidden">
                            <h2 class="accordion-header" id="heading${status.index}">
                                <button class="accordion-button collapsed p-0" type="button" data-bs-toggle="collapse"
                                    data-bs-target="#collapse${status.index}" aria-expanded="false" aria-controls="collapse${status.index}"
                                >
                                    <div class="d-flex w-100 align-items-center p-3 bg-white">
                                        <div class="flex-grow-1">
                                            <span class="badge bg-primary me-2">${v.marque}</span> <span class="fw-bold fs-5">${v.modele}</span>
                                        </div>
                                        <div class="text-success fw-bold fs-5">${v.prix}&euro;</div>
                                    </div>
                                </button>
                            </h2>
                            <div id="collapse${status.index}" class="accordion-collapse collapse" aria-labelledby="heading${status.index}"
                                data-bs-parent="#voituresAccordion"
                            >
                                <div class="accordion-body bg-light p-4">
                                    <div class="row g-3">
                                        <div class="col-md-4">
                                            <img src="${pageContext.request.contextPath}/images/image_not_found.png" class="img-thumbnail" alt="...">
                                        </div>
                                        <div class="col-md-8">
                                            <div class="row h-100 g-3">
                                                <div class="col-md-4">
                                                    <ul class="list-unstyled mb-0">
                                                        <li><strong>Référence:</strong> ${v.reference}</li>
                                                        <li><strong>Année:</strong> ${v.annee}</li>
                                                        <li><strong>Couleur:</strong> ${v.couleur}</li>
                                                    </ul>
                                                </div>
                                                <div class="col-md-4">
                                                    <ul class="list-unstyled mb-0">
                                                        <li><strong>Catégorie:</strong> ${v.idCategorie}</li>
                                                        <li><strong>Marque:</strong> ${v.marque}</li>
                                                        <li><strong>Modèle:</strong> ${v.modele}</li>
                                                    </ul>
                                                </div>
                                                <div class="col-md-4 d-flex flex-column justify-content-between">
                                                    <p class="mb-3">
                                                        <strong>Description:</strong><br>${v.description}</p>
                                                </div>
                                                <form action="${pageContext.request.contextPath}/panier" method="post" class="mt-auto">
                                                    <input type="hidden" name="id" value="${v.idVoiture}"> <input type="hidden" name="action"
                                                        value="add"
                                                    >
                                                    <button type="submit" class="btn btn-success w-100">
                                                        <i class="fa-solid fa-cart-plus me-1"></i> Ajouter au panier
                                                    </button>
                                                </form>
                                            </div>
                                        </div>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </c:forEach>
                </div>
            </c:when>
            <c:otherwise>
                <div class="alert alert-warning text-center">Aucune voiture disponible pour le moment.</div>
            </c:otherwise>
        </c:choose>
    </main>
    <c:import url="/WEB-INF/partial/_footer.jsp"></c:import>
</body>
</html>
