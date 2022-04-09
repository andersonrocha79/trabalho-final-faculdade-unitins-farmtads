<%@page contentType="text/html"
        pageEncoding="UTF-8"
        language="java"
        import="com.farmacia.controle.CtrlFuncionario,com.farmacia.model.Funcionario,java.util.Vector" %>
<html>

<head>
<meta http-equiv="Content-Language" content="pt-br">
<meta name="GENERATOR" content="Microsoft FrontPage 5.0">
<meta name="ProgId" content="FrontPage.Editor.Document">
<meta http-equiv="Content-Type" content="text/html; charset=windows-1252">
<title>Cadastro de Funcionários</title>
</head>

<body link="#FFFF00" vlink="#CCCC00">

       <%

         // verifica se o Usuario esta logado
         Boolean validacaoOK = (Boolean) session.getAttribute("validacaoOK");
         if (!validacaoOK){pageContext.forward("index.jsp");}

         // Se o Usuario não for ADMIN, não pode alterar/incluir
         Boolean admin = (Boolean) session.getAttribute("usuarioAdministrador");
         if (!admin) pageContext.forward("index.jsp");

       %>

<table border="0" cellpadding="0" cellspacing="0" style="border-collapse: collapse" width="66%" id="AutoNumber1" bgcolor="#666633" height="483">
  <tr>
            <td width="123%" bgcolor="#996600" height="24" colspan="3">
            &nbsp;</td>
          </tr>
  <tr>
    <td width="190%" height="48" colspan="3">
            <p align="center"><b><font size="5" color="#FFFFCC" face="Tahoma">
            FarmTads</font><br>
            <font face="Tahoma" size="4" color="#CCCC00">Cadastro de 
            Funcionarios</font></b></p>
            </td>
  </tr>
  <tr>

    <td width="190%" height="387" colspan="3" valign="top">
    <div align="center">
    <center>

    <%

      // Gerar a Tabela para Visualizar os Funcionários Cadastrados

      CtrlFuncionario ctrl = new CtrlFuncionario();
      Vector <Funcionario> lista = ctrl.buscarTodos();

      try
      {

         // Cria o Titulo da Tabela
         out.print("<table border=\"1\" cellpadding=\"0\" cellspacing=\"0\" style=\"border-collapse: collapse\" bordercolor=\"#111111\" width=\"95%\" id=\"AutoNumber2\">");

         out.print("<tr>");
         out.print("<td   width=\"5%\"  bgcolor=\"#999966\" align=\"center\">");
         out.print("<font size=\"2\"    color=\"#FFFFFF\"   >Código</font></td>");
         out.print("<td   width=\"35%\" bgcolor=\"#999966\" align=\"center\">");
         out.print("<font size=\"2\"    color=\"#FFFFFF\"   >Nome</font></td>");
         out.print("<td   width=\"10%\" bgcolor=\"#999966\" align=\"center\">");
         out.print("<font size=\"2\"    color=\"#FFFFFF\"   >Cpf</font></td>");
         out.print("<td   width=\"8%\"  bgcolor=\"#999966\" align=\"center\">");
         out.print("<font size=\"2\"    color=\"#FFFFFF\"   >RG</font></td>");
         out.print("<td   width=\"12%\" bgcolor=\"#999966\" align=\"center\">");
         out.print("<font size=\"2\"    color=\"#FFFFFF\"   >Telefone</font></td>");
         out.print("<td   width=\"24%\" bgcolor=\"#999966\" align=\"center\">");
         out.print("<font size=\"2\"    color=\"#FFFFFF\"   >e-mail</font></td>");
         out.print("<td   width=\"3%\" bgcolor=\"#999966\" align=\"center\"></td>");
         out.print("<td   width=\"3%\" bgcolor=\"#999966\" align=\"center\"></td>");
         out.print("</tr>");

         // Percorre a Lista para Demonstrar os Registros
         if (lista.size() > 0)
         {

            for(int i = 0; i < lista.size(); i++)
            {

               Funcionario registro = lista.get(i);

               out.print("<tr>");
               out.print("<td width=\"5%\" bgcolor=\"#FFFFFF\" align=\"center\" height=\"28\"><font size=\"2\">");
               out.print(registro.getIdFuncionario());
               out.print("</font></td>");
               out.print("<td width=\"35%\" bgcolor=\"#FFFFFF\" align=\"center\" height=\"28\"><font size=\"2\">");
               out.print(registro.getNome());
               out.print("</font></td>");
               out.print("<td width=\"10%\" bgcolor=\"#FFFFFF\" align=\"center\" height=\"28\"><font size=\"2\">");
               out.print(registro.getCpf());
               out.print("</font></td>");
               out.print("<td width=\"10%\" bgcolor=\"#FFFFFF\" align=\"center\" height=\"28\"><font size=\"2\">");
               out.print(registro.getRg());
               out.print("</font></td>");
               out.print("<td width=\"10%\" bgcolor=\"#FFFFFF\" align=\"center\" height=\"28\"><font size=\"2\">");
               out.print(registro.getTelefone());
               out.print("</font></td>");
               out.print("<td width=\"24%\" bgcolor=\"#FFFFFF\" align=\"center\" height=\"28\"><font size=\"2\">");
               out.print(registro.getEmail());
               out.print("</font></td>");
               out.print("<td width=\"3%\" bgcolor=\"#FFFFFF\" align=\"center\">");
               out.print("<a href=\"funcionariosProcessa.jsp?acao=exclui&id=" + registro.getIdFuncionario() + "\">" +
                         "<img border=\"0\" src=\"images/excluir.jpg\" width=\"23\" height=\"24\"></a></td>");
               out.print("<td width=\"3%\" bgcolor=\"#FFFFFF\" align=\"center\">");
               out.print("<a href=\"funcionariosManutencao.jsp?operacao=alterar&id=" + registro.getIdFuncionario() + "\">" +
                         "<img border=\"0\" src=\"images/alterar.JPG\" width=\"23\" height=\"24\"></a></td>");
               out.print("</tr>");

            }

            out.print("</table>");

         }
         else
         {

            out.print("</table>");

            out.print("<p>&nbsp;</p><p>&nbsp;</p>");
            out.print("<p><font color=\"#FFFF99\"><b>Não Existem Registros Cadastrados.</b></font></p>");

         }

      }
      finally
      {
         ctrl = null;
         lista = null;
      }

    %>
   
    </center>
    </div>&nbsp;
    </td>

  </tr>
  <tr>
    <td width="17%" height="39">
    <p align="center">&nbsp;<b><a href="funcionariosManutencao.jsp?operacao=incluir">Cadastrar</a></b></td>
    <td width="18%" height="39">
    <p align="center"><b><a href="menu.jsp">&nbsp;Menu Principal</a></b></td>
    <td width="155%" height="39">&nbsp;</td>
  </tr>
  <tr>
            <td width="123%" bgcolor="#CC9900" height="14" colspan="3">
            &nbsp;

            <%

               // Demonstra o Retorno das funções
                String msg = (String) session.getAttribute("msg");

                if ((msg!="") && (msg !=null))
                {
                    out.println("<font face=\"Tahoma\" size=\"3\" color=\"#FFFFCC\">" + msg + "</font>");
                }
            %>

            </td>
          </tr>
</table>

</body>

</html>