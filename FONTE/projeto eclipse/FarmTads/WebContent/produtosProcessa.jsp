
<%@page contentType="text/html"
        pageEncoding="UTF-8"
        language="java"
        import="com.farmacia.controle.CtrlProduto,
                com.farmacia.model.Produto" %>                

<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title></title>
    </head>
    <body>

       <%

         // Verifica qual a Solicitação
         String funcao = request.getParameter("acao");

         // Inicializa a Variavel Generica de Retorno de Função
         session.setAttribute("msg"            , "");
         session.setAttribute("incluido"       , "" );

        if (funcao.equals("grava"))
        {

            // Realiza a Gravação 
            String operacao = (String) session.getAttribute("operacao");

            try
            {

               CtrlProduto ctrl = new CtrlProduto();
               Produto registro = new Produto();

               if (operacao.equals("alterar"))
                  registro = ctrl.buscar(Integer.parseInt(request.getParameter("txtCodigo")));

               registro.setNome(request.getParameter("txtNome"));
               registro.setDescricao(request.getParameter("txtDescricao"));
               registro.setLaboratorio(request.getParameter("txtLaboratorio"));
               registro.setQuantidade(Integer.parseInt(request.getParameter("txtSaldoEstoque")));
               registro.setValorVenda(Double.parseDouble(request.getParameter("txtPrecoVenda")));

               if (operacao.equals("alterar"))
                  ctrl.alterar(registro);
               else
                  ctrl.incluir(registro);
               pageContext.forward("produtos.jsp");

            }
            catch (Exception ex)
            {
               session.setAttribute("msg", ex.getMessage());
               pageContext.forward("produtosManutencao.jsp");
            }

        }
        else if (funcao.equals("exclui"))
        {

            // Realiza a Exclusao
            String Identificador = request.getParameter("id");

            try
            {

               CtrlProduto ctrl = new CtrlProduto();
               ctrl.excluir(Integer.parseInt(Identificador));
               pageContext.forward("produtos.jsp");

            }
            catch (Exception ex)
            {
               session.setAttribute("msg", ex.getMessage());
               pageContext.forward("produtos.jsp");
            }

        }

       %>

    </body>
</html>
