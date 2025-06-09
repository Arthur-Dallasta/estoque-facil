<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
  <head>
    <title>EstoqueFácil - Login</title>
    <link
      href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css"
      rel="stylesheet"
    />
    <style>
      body {
        background-color: #f8f9fa;
      }
      .login-container {
        max-width: 400px;
        margin: 100px auto;
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
      <div class="login-container">
        <div class="logo">
          <h1>EstoqueFácil</h1>
        </div>
        <form action="login" method="post">
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
          <div class="d-grid">
            <button type="submit" class="btn btn-primary">Entrar</button>
          </div>
          <div class="text-center mt-3">
            <a href="cadastro.jsp">Não tem uma conta? Cadastre-se</a>
          </div>
        </form>
      </div>
    </div>
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/js/bootstrap.bundle.min.js"></script>
  </body>
</html>
