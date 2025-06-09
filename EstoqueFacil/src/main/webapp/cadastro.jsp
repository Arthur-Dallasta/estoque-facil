<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
  <head>
    <title>EstoqueFácil - Cadastro</title>
    <link
      href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css"
      rel="stylesheet"
    />
    <style>
      body {
        background-color: #f8f9fa;
      }
      .cadastro-container {
        max-width: 500px;
        margin: 50px auto;
        padding: 20px;
        background-color: white;
        border-radius: 5px;
        box-shadow: 0 0 10px rgba(0, 0, 0, 0.1);
      }
      .logo {
        text-align: center;
        margin-bottom: 30px;
      }
      .logo h1 {
        color: #0d6efd;
      }
    </style>
  </head>
  <body>
    <div class="container">
      <div class="cadastro-container">
        <div class="logo">
          <h1>EstoqueFácil</h1>
          <h4>Criar nova conta</h4>
        </div>
        <form action="usuario" method="post">
          <input type="hidden" name="action" value="cadastrar" />
          <div class="mb-3">
            <label for="nome" class="form-label">Nome completo</label>
            <input
              type="text"
              class="form-control"
              id="nome"
              name="nome"
              required
            />
          </div>
          <div class="mb-3">
            <label for="email" class="form-label">Email</label>
            <input
              type="email"
              class="form-control"
              id="email"
              name="email"
              required
            />
          </div>
          <div class="mb-3">
            <label for="senha" class="form-label">Senha</label>
            <input
              type="password"
              class="form-control"
              id="senha"
              name="senha"
              required
            />
          </div>
          <div class="mb-3">
            <label for="telefone" class="form-label">Telefone</label>
            <input
              type="tel"
              class="form-control"
              id="telefone"
              name="telefone"
              required
            />
          </div>
          <div class="d-grid">
            <button type="submit" class="btn btn-primary">Cadastrar</button>
          </div>
          <div class="text-center mt-3">
            <a href="login.jsp">Já tem uma conta? Faça login</a>
          </div>
        </form>
      </div>
    </div>
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/js/bootstrap.bundle.min.js"></script>
  </body>
</html>
