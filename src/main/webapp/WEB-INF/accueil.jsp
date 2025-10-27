<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<c:import url="/WEB-INF/partial/_links.jsp"></c:import>
<title>Liste</title>
</head>
<body class="d-flex flex-column min-vh-100">
    <c:import url="/WEB-INF/partial/_menu.jsp"></c:import>
    <main class="container my-5">
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
                                    class="accordion-button collapsed"
                                    type="button"
                                    data-bs-toggle="collapse"
                                    data-bs-target="#collapse${status.index}"
                                    aria-expanded="false"
                                    aria-controls="collapse${status.index}"
                                >${v.marque}${v.modele}â€”${v.prix}
                                    &euro;</button>
                            </h2>
                            <div id="collapse${status.index}"
                                class="accordion-collapse collapse"
                                aria-labelledby="heading${status.index}"
                                data-bs-parent="#voituresAccordion"
                            >
                                <div class="accordion-body bg-white">
                                    <ul class="list-unstyled mb-0">
                                        <li><strong>Marque
                                                :</strong> ${v.marque}</li>
                                        <li><strong>Modèle
                                                :</strong> ${v.modele}</li>
                                        <li><strong>Année
                                                :</strong> ${v.annee}</li>
                                        <li><strong>Prix :</strong>
                                            ${v.prix} â‚¬</li>
                                        <li><strong>Ville
                                                :</strong> ${v.ville}</li>
                                        <li><strong>Description
                                                :</strong> ${v.description}</li>
                                    </ul>
                                    <div class="text-end mt-3">
                                        <a
                                            href="${pageContext.request.contextPath}/details?id=${v.id}"
                                            class="btn btn-outline-primary btn-sm"
                                        >Voir plus</a>
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
