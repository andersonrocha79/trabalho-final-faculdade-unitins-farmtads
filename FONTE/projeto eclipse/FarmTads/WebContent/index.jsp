<%--
    Document   : index
    Created on : 02/05/2010, 20:02:52
    Author     : anderson
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
   "http://www.w3.org/TR/html4/loose.dtd">

<script language="JavaScript" type="text/javascript">

   function validaForm()
   {

      d = document.login;

      if (d.txtEmail.value == "")
      {
         alert("O e-mail deve ser informado");
         d.txtEmail.focus();
         return false;
      }

      if (d.txtSenha.value == "")
      {
         alert("A Senha deve ser informado");
         d.txtSenha.focus();
         return false;
      }

      // validar e-mail
      parte1 = d.txtEmail.value.indexOf("@");
      parte2 = d.txtEmail.value.indexOf(".");
      parte3 = d.txtEmail.value.length;
      if (!(parte1 >= 3 && parte2 >= 6 && parte3 >= 9))
      {
         alert("O e-mail informado é inválido");
         d.txtEmail.focus();
         return false;
      }

      return true;

   }

</script>

<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>FarmTads</title>
    </head>    

    <body bgcolor="#FFFFFF">

        <table border="0" cellpadding="0" cellspacing="0" style="border-collapse: collapse" bordercolor="#666633" width="51%" id="AutoNumber1" bgcolor="#666633" height="420">
          <tr>
            <td width="81%" bgcolor="#CC9900" align="center" height="25">
            &nbsp;</td>
            <td width="52%" bgcolor="#996600" align="center" height="25">
            &nbsp;</td>
          </tr>
          <tr>
            <td width="81%" height="370">
            <img border="0" src="images/remedio2.jpg" width="270" height="385"></td>
            <td width="52%" height="370">
            <p align="center"><font size="2" color="#FFFF00" face="Tahoma">
            Universidade do Tocantins - Unitins</font></p>
            <p align="center"><b><font size="5" color="#FFFFCC" face="Tahoma">
            FarmTads</font></b></p>
            <p align="center"><b><font size="4" color="#CCCC00" face="Tahoma">
            Login</font></b></p>

        <form name="login" method="POST" action=funcionariosProcessa.jsp?acao=login onsubmit="return validaForm()" >

            <p>&nbsp;</p>

            <table border="0" width="564" style="border-collapse: collapse" bordercolor="#111111" cellpadding="0" cellspacing="0" height="183" bgcolor="#666633">
            <tbody>
            <tr>
                <td width="659" height="67" align="center">
                <p><b><font size="2" face="Tahoma" color="#FFFFFF">e-mail:<br>
                </font></b>
                <input type=text name=txtEmail maxlength=50 size="59" style="font-family: Tahoma; font-size: 12 pt" value="${param.txtEmail}"></td>
            </tr>
            <tr>
                <td width="659" height="69" align="center">
                <p>
                <b>
                <font color="#FFFFFF" size="2" face="Tahoma">Senha:</font></b><font color="#FFFFFF" size="4" face="Terminal"><br>
                </font>
                <input type=password name=txtSenha maxlength=10 size="59" style="font-family: Tahoma; font-size: 12 pt" value="${param.txtSenha}"></td>
            </tr>
                 
            <tr>
                <td width="659" height="47" valign="bottom" align="center">
                <p align="center">
            <font face="Tahoma">
            <input type=submit value="    Efetuar Login     " style="position:relative; font-family:Tahoma; font-size:12 pt"></font></td>
            </tr>
                 
            </tbody>
            </table>

            <p>&nbsp;</p>

        </form>

            </td>
          </tr>
          <tr>
            <td width="81%" bgcolor="#CC9900" align="center" height="25">
            <font face="Tahoma" size="4" color="#FFFFCC">JavaBaby</font></td>
            <td width="52%" bgcolor="#996600" align="center" height="25">

            <%

                String msg = (String) session.getAttribute("msgValidacaoLogin");

                if ((msg!="") && (msg !=null))
                {
                    out.println("<font face=\"Tahoma\" size=\"3\" color=\"#FFFFCC\">VALIDAÇÃO: " + msg + "</font>");
                }
            %> &nbsp;</td>
          </tr>
        </table>

    </body>

</html>