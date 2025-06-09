<%@ page contentType="text/html;charset=UTF-8" language="java" %> <%@ taglib
prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
  <head>
    <title>EstoqueFácil - Sistema de Controle de Estoque</title>
    <link
      href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css"
      rel="stylesheet"
    />
    <link
      href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.7.2/font/bootstrap-icons.css"
      rel="stylesheet"
    />
    <style>
      .sidebar {
        min-height: 100vh;
        background-color: #212529;
        color: white;
      }
      .sidebar .nav-link {
        color: rgba(255, 255, 255, 0.75);
      }
      .sidebar .nav-link:hover {
        color: rgba(255, 255, 255, 1);
      }
      .sidebar .nav-link.active {
        color: white;
        background-color: rgba(255, 255, 255, 0.1);
      }
      .main-content {
        padding: 20px;
      }
      .product-card {
        margin-bottom: 20px;
      }
    </style>
  </head>
  <body>
    <div class="container-fluid">
      <div class="row">
        <!-- Sidebar -->
        <div class="col-md-3 col-lg-2 px-0 sidebar">
          <div class="p-3">
            <h3 class="text-center">EstoqueFácil</h3>
            <hr />
            <ul class="nav flex-column">
              <li class="nav-item">
                <a class="nav-link active" href="#">
                  <i class="bi bi-box-seam"></i> Produtos
                </a>
              </li>
              <c:if test="${sessionScope.usuario.nivelAcesso eq 'GERENTE'}">
                <li class="nav-item">
                  <a
                    class="nav-link"
                    href="#"
                    data-bs-toggle="modal"
                    data-bs-target="#novoProdutoModal"
                  >
                    <i class="bi bi-plus-circle"></i> Novo Produto
                  </a>
                </li>
              </c:if>
              <li class="nav-item">
                <a class="nav-link" href="logout">
                  <i class="bi bi-box-arrow-right"></i> Sair
                </a>
              </li>
            </ul>
          </div>
        </div>

        <!-- Main Content -->
        <div class="col-md-9 col-lg-10 main-content">
          <div class="d-flex justify-content-between align-items-center mb-4">
            <h2>Produtos em Estoque</h2>
            <div class="d-flex">
              <input
                type="text"
                class="form-control me-2"
                id="searchInput"
                placeholder="Buscar produtos..."
              />
              <select class="form-select" id="categoryFilter">
                <option value="">Todas as categorias</option>
                <option value="Alimentos">Alimentos</option>
                <option value="Bebidas">Bebidas</option>
                <option value="Limpeza">Limpeza</option>
                <option value="Higiene">Higiene</option>
              </select>
            </div>
          </div>

          <div class="row" id="productList">
            <!-- Products will be loaded here dynamically -->
          </div>
        </div>
      </div>
    </div>

    <!-- Novo Produto Modal -->
    <div class="modal fade" id="novoProdutoModal" tabindex="-1">
      <div class="modal-dialog">
        <div class="modal-content">
          <div class="modal-header">
            <h5 class="modal-title">Novo Produto</h5>
            <button
              type="button"
              class="btn-close"
              data-bs-dismiss="modal"
            ></button>
          </div>
          <div class="modal-body">
            <form id="novoProdutoForm">
              <div class="mb-3">
                <label for="nome" class="form-label">Nome</label>
                <input
                  type="text"
                  class="form-control"
                  id="nome"
                  name="nome"
                  required
                />
              </div>
              <div class="mb-3">
                <label for="descricao" class="form-label">Descrição</label>
                <textarea
                  class="form-control"
                  id="descricao"
                  name="descricao"
                ></textarea>
              </div>
              <div class="mb-3">
                <label for="preco" class="form-label">Preço</label>
                <input
                  type="number"
                  class="form-control"
                  id="preco"
                  name="preco"
                  step="0.01"
                  required
                />
              </div>
              <div class="mb-3">
                <label for="quantidade" class="form-label">Quantidade</label>
                <input
                  type="number"
                  class="form-control"
                  id="quantidade"
                  name="quantidade"
                  required
                />
              </div>
              <div class="mb-3">
                <label for="categoria" class="form-label">Categoria</label>
                <select
                  class="form-select"
                  id="categoria"
                  name="categoria"
                  required
                >
                  <option value="">Selecione uma categoria</option>
                  <option value="Alimentos">Alimentos</option>
                  <option value="Bebidas">Bebidas</option>
                  <option value="Limpeza">Limpeza</option>
                  <option value="Higiene">Higiene</option>
                </select>
              </div>
              <div class="mb-3">
                <label for="codigoBarras" class="form-label"
                  >Código de Barras</label
                >
                <input
                  type="text"
                  class="form-control"
                  id="codigoBarras"
                  name="codigoBarras"
                />
              </div>
            </form>
          </div>
          <div class="modal-footer">
            <button
              type="button"
              class="btn btn-secondary"
              data-bs-dismiss="modal"
            >
              Cancelar
            </button>
            <button
              type="button"
              class="btn btn-primary"
              onclick="salvarProduto()"
            >
              Salvar
            </button>
          </div>
        </div>
      </div>
    </div>

    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/js/bootstrap.bundle.min.js"></script>
    <script>
      // Função para carregar os produtos
      function carregarProdutos() {
        fetch("produto?action=listar")
          .then((response) => response.json())
          .then((produtos) => {
            const productList = document.getElementById("productList");
            productList.innerHTML = "";

            produtos.forEach((produto) => {
              const card = `
                            <div class="col-md-4 product-card">
                                <div class="card">
                                    <div class="card-body">
                                        <h5 class="card-title">${
                                          produto.nome
                                        }</h5>
                                        <p class="card-text">${
                                          produto.descricao || ""
                                        }</p>
                                        <p class="card-text">
                                            <strong>Preço:</strong> R$ ${produto.preco.toFixed(
                                              2
                                            )}<br>
                                            <strong>Quantidade:</strong> ${
                                              produto.quantidade
                                            }<br>
                                            <strong>Categoria:</strong> ${
                                              produto.categoria
                                            }
                                        </p>
                                        ${
                                          sessionScope.usuario.nivelAcesso ===
                                          "GERENTE"
                                            ? `
                                            <button class="btn btn-sm btn-primary me-2" onclick="editarProduto(${produto.id})">
                                                <i class="bi bi-pencil"></i> Editar
                                            </button>
                                            <button class="btn btn-sm btn-danger" onclick="deletarProduto(${produto.id})">
                                                <i class="bi bi-trash"></i> Deletar
                                            </button>
                                        `
                                            : ""
                                        }
                                    </div>
                                </div>
                            </div>
                        `;
              productList.innerHTML += card;
            });
          })
          .catch((error) => console.error("Erro ao carregar produtos:", error));
      }

      // Função para salvar novo produto
      function salvarProduto() {
        const form = document.getElementById("novoProdutoForm");
        const formData = new FormData(form);
        const produto = Object.fromEntries(formData.entries());

        fetch("produto?action=cadastrar", {
          method: "POST",
          headers: {
            "Content-Type": "application/json",
          },
          body: JSON.stringify(produto),
        })
          .then((response) => {
            if (response.ok) {
              bootstrap.Modal.getInstance(
                document.getElementById("novoProdutoModal")
              ).hide();
              form.reset();
              carregarProdutos();
            } else {
              alert("Erro ao cadastrar produto");
            }
          })
          .catch((error) => console.error("Erro ao cadastrar produto:", error));
      }

      // Função para deletar produto
      function deletarProduto(id) {
        if (confirm("Tem certeza que deseja deletar este produto?")) {
          fetch(`produto?action=deletar&id=${id}`, {
            method: "POST",
          })
            .then((response) => {
              if (response.ok) {
                carregarProdutos();
              } else {
                alert("Erro ao deletar produto");
              }
            })
            .catch((error) => console.error("Erro ao deletar produto:", error));
        }
      }

      // Carregar produtos ao iniciar a página
      document.addEventListener("DOMContentLoaded", carregarProdutos);

      // Filtro de busca
      document
        .getElementById("searchInput")
        .addEventListener("input", function (e) {
          const searchTerm = e.target.value.toLowerCase();
          const cards = document.querySelectorAll(".product-card");

          cards.forEach((card) => {
            const title = card
              .querySelector(".card-title")
              .textContent.toLowerCase();
            const description = card
              .querySelector(".card-text")
              .textContent.toLowerCase();

            if (
              title.includes(searchTerm) ||
              description.includes(searchTerm)
            ) {
              card.style.display = "";
            } else {
              card.style.display = "none";
            }
          });
        });

      // Filtro de categoria
      document
        .getElementById("categoryFilter")
        .addEventListener("change", function (e) {
          const category = e.target.value;
          const cards = document.querySelectorAll(".product-card");

          cards.forEach((card) => {
            const cardCategory = card
              .querySelector(".card-text")
              .textContent.split("Categoria:")[1]
              .trim();

            if (!category || cardCategory === category) {
              card.style.display = "";
            } else {
              card.style.display = "none";
            }
          });
        });
    </script>
  </body>
</html>
