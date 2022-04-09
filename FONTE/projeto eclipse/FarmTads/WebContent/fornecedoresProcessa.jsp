
<%@page contentType="text/html"
        pageEncoding="UTF-8"
        language="java"
        import="com.farmacia.controle.CtrlFornecedor,
                com.farmacia.model.Fornecedor
        " %>
        
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

               CtrlFornecedor ctrl = new CtrlFornecedor();
               Fornecedor registro = new Fornecedor();

               if (operacao.equals("alterar"))
                  registro = ctrl.buscar(Integer.parseInt(request.getParameter("txtCodigo")));

               registro.setNome(request.getParameter("txtNome"));
               registro.setRazaoSocial(request.getParameter("txtRazaoSocial"));
               registro.setTelefone(request.getParameter("txtTelefone"));
               registro.setEmail(request.getParameter("txtEmail"));
               registro.setCnpj(request.getParameter("txtCnpj"));
               registro.setContato(request.getParameter("txtContato"));

               if (operacao.equals("alterar"))
                  ctrl.alterar(registro);
               else
                  ctrl.incluir(registro);
               pageContext.forward("fornecedores.jsp");

            }
            catch (Exception ex)
            {
               session.setAttribute("msg", ex.getMessage());
               pageContext.forward("fornecedoresManutencao.jsp");
            }

        }
        else if (funcao.equals("exclui"))
        {

            // Realiza a Exclusao
            String Identificador = request.getParameter("id");

            try
            {

               CtrlFornecedor ctrl = new CtrlFornecedor();
               ctrl.excluir(Integer.parseInt(Identificador));
               pageContext.forward("fornecedores.jsp");

            }
            catch (Exception ex)
            {
               session.setAttribute("msg", ex.getMessage());
               pageContext.forward("fornecedores.jsp");
            }

        }

       %>

    </body>
</html>
