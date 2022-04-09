<%@page contentType="text/html"
        pageEncoding="UTF-8"
        language="java"
        import="com.farmacia.controle.CtrlProduto,com.farmacia.model.Produto,java.util.Vector" %>       
<html>

<head>
<meta http-equiv="Content-Language" content="pt-br">
<meta name="GENERATOR" content="Microsoft FrontPage 5.0">
<meta name="ProgId" content="FrontPage.Editor.Document">
<meta http-equiv="Content-Type" content="text/html; charset=windows-1252">
<title>Cadastro de Produtos</title>
</head>

<body link="#FFFF00" vlink="#CCCC00">

       <%

         // verifica se o Usuario esta logado
         Boolean validacaoOK = (Boolean) session.getAttribute("validacaoOK");
         if (!validacaoOK){pageContext.forward("index.jsp");}

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
            Produtos</font></b></p>
            </td>
  </tr>
  <tr>

    <td width="190%" height="387" colspan="3" valign="top">
    <div align="center">
    <center>

    <%

      // Gerar a Tabela para Visualizar os Produtos

      CtrlProduto ctrl = new CtrlProduto();
      Vector <Produto> lista = ctrl.buscarTodos();

      try
      {

         // Cria o Titulo da Tabela
         out.print("<table border=\"1\" cellpadding=\"0\" cellspacing=\"0\" style=\"border-collapse: collapse\" bordercolor=\"#111111\" width=\"95%\" id=\"AutoNumber2\">");

         out.print("<tr>");
         out.print("<td   width=\"5%\"  bgcolor=\"#999966\" align=\"center\">");
         out.print("<font size=\"2\"    color=\"#FFFFFF\"   >Código</font></td>");
         out.print("<td   width=\"35%\" bgcolor=\"#999966\" align=\"center\">");
         out.print("<font size=\"2\"    color=\"#FFFFFF\"   >Nome</font></td>");
         out.print("<td   width=\"24%\" bgcolor=\"#999966\" align=\"center\">");
         out.print("<font size=\"2\"    color=\"#FFFFFF\"   >Laboratório</font></td>");
         out.print("<td   width=\"15%\" bgcolor=\"#999966\" align=\"center\">");
         out.print("<font size=\"2\"    color=\"#FFFFFF\"   >Saldo em Estoque</font></td>");
         out.print("<td   width=\"15%\" bgcolor=\"#999966\" align=\"center\">");
         out.print("<font size=\"2\"    color=\"#FFFFFF\"   >Preço de Venda</font></td>");
         out.print("<td   width=\"3%\" bgcolor=\"#999966\" align=\"center\"></td>");
         out.print("<td   width=\"3%\" bgcolor=\"#999966\" align=\"center\"></td>");
         out.print("</tr>");

         // Percorre a Lista para Demonstrar os Registros
         if (lista.size() > 0)
         {

            for(int i = 0; i < lista.size(); i++)
            {

               Produto registro = lista.get(i);

               out.print("<tr>");
               out.print("<td width=\"5%\" bgcolor=\"#FFFFFF\" align=\"center\" height=\"28\"><font size=\"2\">");
               out.print(registro.getIdProduto());
               out.print("</font></td>");
               out.print("<td width=\"35%\" bgcolor=\"#FFFFFF\" align=\"center\" height=\"28\"><font size=\"2\">");
               out.print(registro.getNome());
               out.print("</font></td>");
               out.print("<td width=\"24%\" bgcolor=\"#FFFFFF\" align=\"center\" height=\"28\"><font size=\"2\">");
               out.print(registro.getLaboratorio());
               out.print("</font></td>");
               out.print("<td width=\"15%\" bgcolor=\"#FFFFFF\" align=\"center\" height=\"28\"><font size=\"2\">");
               out.print(registro.getQuantidade());
               out.print("</font></td>");
               out.print("<td width=\"15%\" bgcolor=\"#FFFFFF\" align=\"center\" height=\"28\"><font size=\"2\">");
               out.print(registro.getValorVenda());
               out.print("</font></td>");
               out.print("<td width=\"3%\" bgcolor=\"#FFFFFF\" align=\"center\">");
               out.print("<a href=\"produtosProcessa.jsp?acao=exclui&id=" + registro.getIdProduto() + "\">" +
                         "<img border=\"0\" src=\"images/excluir.jpg\" width=\"23\" height=\"24\"></a></td>");
               out.print("<td width=\"3%\" bgcolor=\"#FFFFFF\" align=\"center\">");
               out.print("<a href=\"produtosManutencao.jsp?operacao=alterar&id=" + registro.getIdProduto() + "\">" +
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
    <p align="center">&nbsp;<b><a href="produtosManutencao.jsp?operacao=incluir">Cadastrar</a></b></td>
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