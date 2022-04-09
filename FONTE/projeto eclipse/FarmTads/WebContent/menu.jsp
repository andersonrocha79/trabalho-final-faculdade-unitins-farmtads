
<%@page contentType="text/html"
        pageEncoding="UTF-8"
        language="java"
        import="com.farmacia.model.Funcionario" %>        

<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Menu Principal</title>
    </head>
    <body bgcolor="#FFFFFF" link="#CCCC00" vlink="#FFFF00">

       <%
         // verifica se o Usuario esta logado
         Boolean validacaoOK = (Boolean) session.getAttribute("validacaoOK");
         if (!validacaoOK){pageContext.forward("index.jsp");}
       %>

       <table border="0" cellpadding="0" cellspacing="0" style="border-collapse: collapse" width="66%" id="AutoNumber1" bgcolor="#666633" height="296">
         <tr>
            <td width="32%" bgcolor="#CC9900" height="25" align="center">
            &nbsp;</td>
            <td width="93%" bgcolor="#996600" height="25" align="center">
            &nbsp;</td>
          </tr>
         <tr>
           <td width="3%" rowspan="2" height="287">
           <p align="center">
            <img border="0" src="images/remedio2.jpg" width="255" height="345"></td>
           <td width="160%" height="97">
            <p align="center"><font size="2" color="#FFFF00" face="Tahoma">
            Universidade do Tocantins - Unitins</font></p>
            <p align="center"><b><font size="5" color="#FFFFCC" face="Tahoma">
            FarmTads</font></b></p>
            <p align="center"><b><font face="Tahoma" size="4" color="#CCCC00">
            Menu Principal</font></b></p>
            </td>
         </tr>
         <tr>
           <td width="160%" height="190">
            <p align="center"><font color="#FFFFFF" face="Tahoma">
            <a href="clientes.jsp">Cadastro de 
            Clientes</a></font></p>
            <p align="center"><font color="#FFFFFF" face="Tahoma">
            <a href="fornecedores.jsp">Cadastro de 
            Fornecedores</a></font></p>

            <%

               // Se o Usuario não for ADMIN, não pode gerenciar funcionarios
               Boolean admin = (Boolean) session.getAttribute("usuarioAdministrador");
               if (admin)
               {
                  out.print("<p align=\"center\"><font color=\"#FFFFFF\" face=\"Tahoma\">");
                  out.print("<a href=\"funcionarios.jsp\">Cadastro de Funcionários</a></font></p>");
               }
            %>

            <p align="center"><font color="#FFFFFF" face="Tahoma">
            <a href="produtos.jsp">Cadastro de 
            Produtos</a></font></p>
            <p align="center"><font face="Tahoma" color="#FFFFFF">
            <a href="entradas.jsp">Entrada de Mercadorias</a></font></p>
            </td>
         </tr>
         <tr>
            <td width="32%" bgcolor="#CC9900" height="25" align="center">
            </td>
            <td width="93%" bgcolor="#996600" height="25" align="center">
            <%
               if (validacaoOK)
               {
                  Funcionario registro = (Funcionario) session.getAttribute("usuario");
                  out.println("<font face=\"Tahoma\" size=\"3\" color=\"#FFFFCC\">");
                  out.println("Usuário: " + registro.getNome() + " - e-mail: " + registro.getEmail() + " ");
                  out.println("<a href=\"funcionariosProcessa.jsp?acao=logoff\">[SAIR]</a>");
                  out.println("</font>");
               }
               %>
            </td>
          </tr>
       </table>

    </body>
</html>