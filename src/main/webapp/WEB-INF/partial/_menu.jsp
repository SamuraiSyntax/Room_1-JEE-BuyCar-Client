<nav
    class="navbar navbar-expand-lg navbar-light bg-light px-3 shadow-sm"
>
    <div class="container-fluid">
        <a class="navbar-brand fw-bold d-flex gap-2 align-items-center"
            href="${pageContext.request.contextPath}/accueil"
        ><i class="fa-solid fa-car-side"></i><span>BuyCar</span></a>
        <button class="navbar-toggler" type="button"
            data-bs-toggle="collapse" data-bs-target="#navbarNav"
            aria-controls="navbarNav" aria-expanded="false"
            aria-label="Toggle navigation"
        >
            <span class="navbar-toggler-icon"></span>
        </button>
        <div class="collapse navbar-collapse" id="navbarNav">
            <ul
                class="nav nav-underline navbar-nav me-auto mb-2 mb-lg-0"
            >
                <c:if test="${not empty sessionScope.utilisateur}">
                    <li class="nav-item"><c:choose>
                            <c:when
                                test="${pageContext.request.servletPath == '/WEB-INF/accueil.jsp'}"
                            >
                                <a class="nav-link active"
                                    href="${pageContext.request.contextPath}/accueil"
                                >Accueil</a>
                            </c:when>
                            <c:otherwise>
                                <a class="nav-link"
                                    href="${pageContext.request.contextPath}/accueil"
                                >Accueil</a>
                            </c:otherwise>
                        </c:choose></li>
                    <%-- <li class="nav-item"><c:choose>
                            <c:when
                                test="${pageContext.request.servletPath == '/WEB-INF/profil.jsp'}"
                            >
                                <a class="nav-link active"
                                    href="${pageContext.request.contextPath}/profil"
                                >Mon profil</a>
                            </c:when>
                            <c:otherwise>
                                <a class="nav-link"
                                    href="${pageContext.request.contextPath}/profil"
                                >Mon profil</a>
                            </c:otherwise>
                        </c:choose></li> --%>
                </c:if>
            </ul>
            <ul class="navbar-nav gap-3">
                <c:choose>
                    <c:when test="${not empty sessionScope.utilisateur}">
                        <li class="nav-item"><a class="nav-link"
                            href="${pageContext.request.contextPath}/panier"
                        ><i class="fa-solid fa-cart-shopping"></i></a></li>
                        <li class="nav-item dropdown"><a
                            class="nav-link dropdown-toggle d-flex gap-2 align-items-center"
                            href="#" role="button"
                            data-bs-toggle="dropdown"
                            aria-expanded="false"
                        ><i class="fa-solid fa-user"></i><span>${sessionScope.utilisateur.prenom}</span>
                        </a>
                            <ul class="dropdown-menu dropdown-menu-end">
                                <li><span
                                    class="dropdown-item-text text-muted d-flex gap-2 align-items-center"
                                ><i class="fa-solid fa-envelope"></i><span>${sessionScope.utilisateur.email}</span></span></li>
                                <li><hr class="dropdown-divider"></li>
                                <li><a
                                    class="dropdown-item text-danger"
                                    href="${pageContext.request.contextPath}/deconnexion"
                                ><i
                                        class="fa-solid fa-right-from-bracket"
                                    ></i> Déconnexion</a></li>
                            </ul></li>
                    </c:when>
                    <c:otherwise>
                        <li class="nav-item"><a class="nav-link"
                            href="${pageContext.request.contextPath}"
                        >Connexion / Inscription</a></li>
                    </c:otherwise>
                </c:choose>
            </ul>
        </div>
    </div>
</nav>
