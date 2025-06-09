<%@ page contentType="text/html;charset=UTF-8" language="java"
isErrorPage="true" %>
<!DOCTYPE html>
<html>
  <head>
    <title>EstoqueFácil - Erro</title>
    <link
      href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css"
      rel="stylesheet"
    />
    <style>
      body {
        background-color: #f8f9fa;
        display: flex;
        align-items: center;
        justify-content: center;
        min-height: 100vh;
        margin: 0;
      }
      .error-container {
        text-align: center;
        padding: 40px;
        background-color: white;
        border-radius: 5px;
        box-shadow: 0 0 10px rgba(0, 0, 0, 0.1);
        max-width: 500px;
      }
      .error-icon {
        font-size: 64px;
        color: #dc3545;
        margin-bottom: 20px;
      }
    </style>
  </head>
  <body>
    <div class="error-container">
      <div class="error-icon">⚠️</div>
      <h1 class="mb-4">Ops! Algo deu errado</h1>
      <p class="mb-4">
        Desculpe, ocorreu um erro ao processar sua solicitação. Por favor, tente
        novamente mais tarde.
      </p>
      <a href="javascript:history.back()" class="btn btn-primary me-2"
        >Voltar</a
      >
      <a href="login.jsp" class="btn btn-outline-primary">Ir para o Login</a>
    </div>
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/js/bootstrap.bundle.min.js"></script>
  </body>
</html>
