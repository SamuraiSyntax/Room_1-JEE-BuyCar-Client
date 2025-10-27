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
        <c:choose>
            <c:when test="${not empty voitures}">
                <div class="accordion" id="voituresAccordion">
                    <c:forEach var="v" items="${voitures}"
                        varStatus="status"
                    >
                        <div class="accordion-item mb-3 shadow-sm">
                            <h2 class="accordion-header"
                                id="heading${status.index}"
                            >
                                <button
                                    class="accordion-button collapsed fw-semibold"
                                    type="button"
                                    data-bs-toggle="collapse"
                                    data-bs-target="#collapse${status.index}"
                                    aria-expanded="false"
                                    aria-controls="collapse${status.index}"
                                >${v.marque}${v.modele} à
                                    ${v.prix}&euro;</button>
                            </h2>
                            <div id="collapse${status.index}"
                                class="accordion-collapse collapse"
                                aria-labelledby="heading${status.index}"
                                data-bs-parent="#voituresAccordion"
                            >
                                <div class="accordion-body bg-white">
                                    <ul class="list-unstyled mb-0">
                                        <li><strong>Référence
                                                :</strong> ${v.reference}</li>
                                        <li><strong>Marque
                                                :</strong> ${v.marque}</li>
                                        <li><strong>Modèle
                                                :</strong> ${v.modele}</li>
                                        <li><strong>Année
                                                :</strong> ${v.annee}</li>
                                        <li><strong>Couleur
                                                :</strong> ${v.couleur}</li>
                                        <li><strong>Prix :</strong>
                                            ${v.prix} &euro;</li>
                                        <li><strong>Description
                                                :</strong> ${v.description}</li>
                                        <li><strong>Date
                                                d'ajout :</strong> ${v.dateAjoutFormatee}</li>
                                    </ul>
                                    <div class="text-end mt-3">
                                        <a
                                            href="${pageContext.request.contextPath}/panier/add?id=${v.idVoiture}"
                                            class="btn btn-outline-primary btn-sm"
                                        > Ajouter au panier</a>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </c:forEach>
                </div>
            </c:when>
            <c:otherwise>
                <div class="alert alert-warning text-center">
                    Aucune voiture disponible pour le moment.</div>
            </c:otherwise>
        </c:choose>
    </main>
    <c:import url="/WEB-INF/partial/_footer.jsp"></c:import>
</body>
</html>
