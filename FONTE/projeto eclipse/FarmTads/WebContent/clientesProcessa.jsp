
<%@page contentType="text/html"
        pageEncoding="UTF-8"
        language="java"
        import="com.farmacia.controle.CtrlCliente, com.farmacia.model.Cliente" %>

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

            // Realiza a Gravação do Funcionário
            String operacao = (String) session.getAttribute("operacao");

            try
            {

               CtrlCliente ctrl = new CtrlCliente();
               Cliente registro = new Cliente();

               if (operacao.equals("alterar"))
                  registro = ctrl.buscar(Integer.parseInt(request.getParameter("txtCodigo")));

               registro.setNome(request.getParameter("txtNome"));
               registro.setTelefone(request.getParameter("txtTelefone"));
               registro.setEmail(request.getParameter("txtEmail"));
               registro.setCpf(request.getParameter("txtCpf"));
               registro.setRg(request.getParameter("txtRG"));
               registro.setPontos(Integer.parseInt(request.getParameter("txtPontos")));

               if (operacao.equals("alterar"))
                  ctrl.alterar(registro);
               else
                  ctrl.incluir(registro);
               pageContext.forward("clientes.jsp");

            }
            catch (Exception ex)
            {
               session.setAttribute("msg", ex.getMessage());
               pageContext.forward("clientesManutencao.jsp");
            }

        }
        else if (funcao.equals("exclui"))
        {

            // Realiza a Exclusao do Cliente Passado como Parametro
            String Identificador = request.getParameter("id");

            try
            {

               CtrlCliente ctrl = new CtrlCliente();
               ctrl.excluir(Integer.parseInt(Identificador));
               pageContext.forward("clientes.jsp");

            }
            catch (Exception ex)
            {
               session.setAttribute("msg", ex.getMessage());
               pageContext.forward("clientes.jsp");
            }

        }

       %>

    </body>
</html>
