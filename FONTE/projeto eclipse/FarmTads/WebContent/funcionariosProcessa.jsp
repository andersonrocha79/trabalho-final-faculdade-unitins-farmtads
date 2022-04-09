
<%@page contentType="text/html" 
        pageEncoding="UTF-8"
        language="java"
        import="com.farmacia.controle.CtrlFuncionario, com.farmacia.model.Funcionario" %>

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

         if (funcao.equals("login"))
         {

            // Realiza a Validação do Login

            try
            {

               // Inicializa as Variaveis de Sessão
               session.setAttribute("usuario"              , null );
               session.setAttribute("validacaoOK"          , false);
               session.setAttribute("usuarioAdministrador" , false);
               session.setAttribute("msgValidacaoLogin"    , ""   );

               // Verifica se o email e senha informados estão presentes e validos no Banco de Dados
               String sLogin = request.getParameter("txtEmail");
               String sSenha = request.getParameter("txtSenha");

               CtrlFuncionario ctrlFuncionario = new CtrlFuncionario();

               Funcionario registro = ctrlFuncionario.validaUsuario(sLogin, sSenha);
               session.setAttribute("usuario"              , registro);
               session.setAttribute("usuarioAdministrador" , (Boolean) registro.isAdministrador());
               session.setAttribute("validacaoOK"          , true);
               pageContext.forward("menu.jsp");
            }
            catch (Exception ex)
            {
               session.setAttribute("msgValidacaoLogin", ex.getMessage());
               pageContext.forward("index.jsp");
            }
        }
        else if (funcao.equals("logoff"))
        {

            // Realiza o LogOff
            try
            {
               session.setAttribute("usuario"              , null );
               session.setAttribute("validacaoOK"          , false);
               session.setAttribute("msgValidacaoLogin"    , ""   );
               session.setAttribute("usuarioAdministrador" , false);
               pageContext.forward("index.jsp");
            }
            catch (Exception ex)
            {
               session.setAttribute("msgValidacaoLogin", ex.getMessage());
               pageContext.forward("index.jsp");
            }
        }
        else if (funcao.equals("grava"))
        {

            // Realiza a Gravação do Funcionário
            String operacao = (String) session.getAttribute("operacao");

            try
            {

               CtrlFuncionario ctrl = new CtrlFuncionario();
               Funcionario registro = new Funcionario();

               if (operacao.equals("alterar"))
                  registro = ctrl.buscar(Integer.parseInt(request.getParameter("txtCodigo")));

               registro.setNome(request.getParameter("txtNome"));
               registro.setTelefone(request.getParameter("txtTelefone"));
               registro.setEmail(request.getParameter("txtEmail"));
               registro.setCpf(request.getParameter("txtCpf"));
               registro.setRg(request.getParameter("txtRG"));
               registro.setEndereco(request.getParameter("txtEndereco"));
               registro.setSenha(request.getParameter("txtSenha"));

               String texto = request.getParameter("chkAdministrador");

               if (texto == null)
                  registro.setAdministrador(false);
               else
                  registro.setAdministrador(true);

               if (operacao.equals("alterar"))
                  ctrl.alterar(registro);
               else
                  ctrl.incluir(registro);
               pageContext.forward("funcionarios.jsp");

            }
            catch (Exception ex)
            {
               session.setAttribute("msg", ex.getMessage());
               pageContext.forward("funcionariosManutencao.jsp");
            }
           
        }
        else if (funcao.equals("exclui"))
        {

            // Realiza a Exclusao do Funcionário Passado como Parametro
            String Identificador = request.getParameter("id");

            try
            {

               CtrlFuncionario ctrl = new CtrlFuncionario();
               ctrl.excluir(Integer.parseInt(Identificador));
               pageContext.forward("funcionarios.jsp");

            }
            catch (Exception ex)
            {
               session.setAttribute("msg", ex.getMessage());
               pageContext.forward("funcionarios.jsp");
            }
           
        }

       %>

    </body>
</html>
