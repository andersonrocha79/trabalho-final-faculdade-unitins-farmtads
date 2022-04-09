
<%@page contentType="text/html"
        pageEncoding="UTF-8"
        language="java"
        import="com.farmacia.controle.CtrlEntrada,
                com.farmacia.model.Entrada,
                com.farmacia.controle.CtrlFornecedor,
                com.farmacia.controle.CtrlFuncionario" %>                

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

               CtrlEntrada ctrl = new CtrlEntrada();
               CtrlFornecedor ctrlFornecedor = new CtrlFornecedor();
               CtrlFuncionario ctrlFuncionario = new CtrlFuncionario();
               Entrada registro = new Entrada();

               if (operacao.equals("alterar"))
                  registro = ctrl.buscar(Integer.parseInt(request.getParameter("txtCodigo")));

               registro.setData(request.getParameter("txtData"));
               registro.setFornecedor(ctrlFornecedor.buscar(Integer.parseInt(request.getParameter("cboFornecedor"))));
               registro.setFuncionario(ctrlFuncionario.buscar(Integer.parseInt(request.getParameter("cboFuncionario"))));

               if (operacao.equals("alterar"))
                  ctrl.alterar(registro);
               else
                  ctrl.incluir(registro);
               pageContext.forward("entradas.jsp");

            }
            catch (Exception ex)
            {
               session.setAttribute("msg", ex.getMessage());
               pageContext.forward("entradasManutencao.jsp");
            }

        }
        else if (funcao.equals("exclui"))
        {

            // Realiza a Exclusao 
            String Identificador = request.getParameter("id");

            try
            {

               CtrlEntrada ctrl = new CtrlEntrada();
               ctrl.excluir(Integer.parseInt(Identificador));
               pageContext.forward("entradas.jsp");

            }
            catch (Exception ex)
            {
               session.setAttribute("msg", ex.getMessage());
               pageContext.forward("entradas.jsp");
            }

        }

       %>

    </body>
</html>
