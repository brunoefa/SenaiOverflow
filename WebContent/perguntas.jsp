<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<!DOCTYPE html>
<html lang="pt">
  <head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <meta name="description" content="Projeto Final">
    <meta name="author" content="Tecnico em Informática - Senai">

    <title>Narrow Jumbotron Template for Bootstrap</title>

    <!-- Bootstrap core CSS -->
    <link href="css/bootstrap.min.css" rel="stylesheet">

    <!-- Custom styles for this template -->
    <link href="css/estilo.css" rel="stylesheet">

    <!-- Just for debugging purposes. Don't actually copy this line! -->
    <!--[if lt IE 9]><script src="../../assets/js/ie8-responsive-file-warning.js"></script><![endif]-->

    <!-- HTML5 shim and Respond.js IE8 support of HTML5 elements and media queries -->
    <!--[if lt IE 9]>
      <script src="https://oss.maxcdn.com/libs/html5shiv/3.7.0/html5shiv.js"></script>
      <script src="https://oss.maxcdn.com/libs/respond.js/1.4.2/respond.min.js"></script>
    <![endif]-->
  </head>

  <body>

    <div class="container">
      <div class="header">
        <ul class="nav nav-pills pull-right">
          <li class="active"><a href="pergunta">Home</a></li>
          <li><a href="pergunta">Faça uma pergunta</a></li>
        </ul>
        <h3 class="text-muted">Senai Overflow</h3>
      </div>

      <form role="form" action="pergunta" method="post" >
        <input type="hidden" name="acao" value="perguntar" id="acao">
    
        <div class="row form">
          <div class="col-lg-12">
            <h2>Escreva sua pergunta abaixo</h2>
            <textarea class="form-control" rows="3" name="pergunta" placeholder="Seja criativo" id="pergunta" required="required" ></textarea>
          </div>
          <div class="col-xs-4">
            <input type="text" class="form-control" name="autor" placeholder="Quem é você" id="autor" required="required" value="">
          </div>
          <div class="col-xs-8">
            <button type="submit" class="btn btn-primary" id="perguntar">Perguntar</button>
          </div>
        </div>
      </form>

      <div class="row lista-perguntas">
      	<div class="col-lg-12">
          <h2>Colabore com seu conhecimento</h2>
         </div>
         
        <c:forEach items="${lista}" var="pergunta">
        <div class="col-lg-12">
          <h3>${pergunta.pergunta}</h3>
          <span class="label label-primary">2 respostas</span> ${pergunta.autor} - <a href="resposta?acao=listar&id=${pergunta.id}">Responder</a>
        </div>
        </c:forEach>
        

      </div>


      <div class="footer">
        <p>&copy; Company 2014</p>
      </div>

    </div> <!-- /container -->


    <!-- Bootstrap core JavaScript
    ================================================== -->
    <!-- Placed at the end of the document so the pages load faster -->
  </body>
</html>
