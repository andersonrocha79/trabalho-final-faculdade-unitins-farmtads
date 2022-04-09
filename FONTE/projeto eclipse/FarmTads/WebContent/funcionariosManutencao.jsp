<script language="JavaScript" type="text/javascript">

      function validaForm()
      {

         d = document.cadastro;

         if (d.txtNome.value == "")
         {
            alert("O Nome do Funcionário deve ser informado");
            d.txtNome.focus();
            return false;
         }

         if (d.txtEmail.value == "")
         {
            alert("O e-mail do Funcionário deve ser informado");
            d.txtEmail.focus();
            return false;
         }

         // validar e-mail
         parte1 = d.txtEemail.value.indexOf("@");
         parte2 = d.txtEemail.value.indexOf(".");
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

<%@page contentType="text/html"
        pageEncoding="UTF-8"
        language="java"
        import="com.farmacia.controle.CtrlFuncionario, com.farmacia.model.Funcionario, com.Util.Util" %>                
<html>

<head>
<meta http-equiv="Content-Language" content="pt-br">
<meta name="GENERATOR" content="Microsoft FrontPage 5.0">
<meta name="ProgId" content="FrontPage.Editor.Document">
<meta http-equiv="Content-Type" content="text/html; charset=windows-1252">
<title>Manutenção Funcionário</title>
</head>
<body>

       <%

         // verifica se o Usuario esta logado
         Boolean validacaoOK = (Boolean) session.getAttribute("validacaoOK");
         if (!validacaoOK) pageContext.forward("index.jsp");

         // Se o Usuario não for ADMIN, não pode alterar/incluir
         Boolean admin = (Boolean) session.getAttribute("usuarioAdministrador");
         if (!admin) pageContext.forward("index.jsp");

         // verifica se e Alteracao ou Inclusao
         String funcao = request.getParameter("operacao");
         String id = request.getParameter("id");

         // Registra a Operação na Sessão
         session.setAttribute("operacao", funcao);

         // Define os Campos iniciais
         String codigo        = "\"\"";
         String nome          = "\"\"";
         String telefone      = "\"\"";
         String email         = "\"\"";
         String cpf           = "\"\"";
         String rg            = "\"\"";
         String endereco      = "\"\"";
         String senha         = "\"\"";
         Boolean userAdmin    = null;

         if (funcao.equals("alterar"))
         {

            // ALTERACAO

            if ((!id.isEmpty()) && (Util.textoTemSoNumeros(id)))
            {

               // Busca os Dados do Registro
               // Seta a Operação de Gravação como
               CtrlFuncionario ctrlFuncionario = new CtrlFuncionario();
               Funcionario registro = ctrlFuncionario.buscar(Integer.parseInt(id));

               if (registro.getIdFuncionario() > 0)
               {
                  codigo = Integer.toString(registro.getIdFuncionario());
                  if (registro.getNome()         !="") nome          = registro.getNome();
                  if (registro.getTelefone()     !="") telefone      = registro.getTelefone();
                  if (registro.getEmail()        !="") email         = registro.getEmail();
                  if (registro.getCpf()          !="") cpf           = registro.getCpf();
                  if (registro.getRg()           !="") rg            = registro.getRg();
                  if (registro.getEndereco()     !="") endereco      = registro.getEndereco();
                  if (registro.getSenha()        !="") senha         = registro.getSenha();
                  if (registro.isAdministrador()     ) userAdmin     = registro.isAdministrador();
               }

            }

         }

       %>

  <form name="cadastro" method="POST" action="funcionariosProcessa.jsp?acao=grava" onsubmit="return validaForm()" >
  <input TYPE="hidden" NAME="VTI-GROUP" VALUE="0"><table border="0" cellpadding="0" cellspacing="0" style="border-collapse: collapse" width="686" id="AutoNumber1" bgcolor="#666633" height="306">
  <tr>
            <td width="797" bgcolor="#996600" height="24" colspan="6">
            &nbsp;</td>
          </tr>
  <tr>
    <td width="797" height="48" colspan="6">
            <p align="center"><b><font size="5" color="#FFFFCC" face="Tahoma">
            FarmTads</font><br>
            <font face="Tahoma" size="4" color="#CCCC00">Manutenção de 
            Funcionários</font></b></p>
            </td>
  </tr>
  <tr>

    <td width="159" height="1" align="left">
    &nbsp;</td>

    <td width="24" height="1" align="left">
    &nbsp;</td>

    <td width="614" height="1" colspan="4" align="left">
    &nbsp;</td>

  </tr>
  <tr>

    <td width="159" height="30" align="left">
    <p align="right"><font color="#FFFFCC">Código:</font></td>

    <td width="24" height="30" align="left">
    &nbsp;</td>

    <td width="614" height="30" colspan="4" align="left">
       <input type="text" name="txtCodigo" size="8"<% if (codigo=="") out.print("value=${param.txtCodigo}"); else out.print("value=" + codigo); %> maxlength="6">
    </td>

  </tr>
  <tr>

    <td width="159" height="30" align="left">
    <p align="right"><font color="#FFFFCC">Nome:</font></td>

    <td width="24" height="30" align="left">
    &nbsp;</td>

    <td width="614" height="30" colspan="4" align="left">
    <input type="text" name="txtNome" size="69"<% if (nome=="") out.print("value=${param.txtNome}"); else out.print("value=" + nome); %> maxlength="60"></td>

  </tr>
  <tr>

    <td width="159" height="30" align="left">
    <p align="right"><font color="#FFFFCC">Telefone:</font></td>

    <td width="24" height="30" align="left">
    &nbsp;</td>

    <td width="614" height="30" colspan="4" align="left">
    <input type="text" name="txtTelefone" size="26" <% if (telefone=="") out.print("value=${param.txtTelefone}"); else out.print("value=" + telefone); %> maxlength="15"> </td>

  </tr>
  <tr>

    <td width="159" height="30" align="left">
    <p align="right"><font color="#FFFFCC">e-mail:</font></td>

    <td width="24" height="30" align="left">
    &nbsp;</td>

    <td width="614" height="30" colspan="4" align="left">
    <input type="text" name="txtEmail" size="69" <% if (email=="") out.print("value=${param.txtEmail}"); else out.print("value=" + email); %> maxlength="45"></td>

  </tr>
  <tr>

    <td width="159" height="30" align="left">
    <p align="right"><font color="#FFFFCC">CPF:</font></td>

    <td width="24" height="30" align="left">
    &nbsp;</td>

    <td width="614" height="30" colspan="4" align="left">
    <input type="text" name="txtCpf" size="26" <% if (cpf=="") out.print("value=${param.txtCpf}"); else out.print("value=" + cpf); %> maxlength="11"></td>

  </tr>
  <tr>

    <td width="159" height="30" align="left">
    <p align="right"><font color="#FFFFCC">RG:</font></td>

    <td width="24" height="30" align="left">
    &nbsp;</td>

    <td width="614" height="30" colspan="4" align="left">
    <input type="text" name="txtRG" size="26" <% if (rg=="") out.print("value=${param.txtRG}"); else out.print("value=" + rg); %> maxlength="8"></td>

  </tr>
  <tr>

    <td width="159" height="30" align="left">
    <p align="right"><font color="#FFFFCC">Endereço:</font></td>

    <td width="24" height="30" align="left">
    &nbsp;</td>

    <td width="614" height="30" colspan="4" align="left">
    <input type="text" name="txtEndereco" size="70" <% if (endereco=="") out.print("value=${param.txtEndereco}"); else out.print("value=" + endereco); %> maxlength="45"></td>

  </tr>
  <tr>

    <td width="159" height="30" align="left">
    <p align="right"><font color="#FFFFCC">Senha:</font></td>

    <td width="24" height="30" align="left">
    &nbsp;</td>

    <td width="614" height="30" colspan="4" align="left">
    <input type="password" name="txtSenha" size="26" <% if (senha=="") out.print("value=${param.txtSenha}"); else out.print("value=" + senha); %> maxlength="8"></td>

  </tr>
  <tr>

    <td width="159" height="30" align="left">
    <p align="right"><font color="#FFFFCC">Administrador:</font></td>

    <td width="24" height="30" align="left">
    &nbsp;</td>

    <td width="614" height="30" colspan="4" align="left">
    <input type="checkbox" name="chkAdministrador" <% if (userAdmin==null) out.print("value=${param.chkAdministrador}"); else out.print("value=" + "0"); %>value="ON"></td>

  </tr>
  <tr>

    <td width="159" height="30" align="left">
    &nbsp;</td>

    <td width="24" height="30" align="left">
    &nbsp;</td>

    <td width="614" height="30" colspan="4" align="left">
    &nbsp;</td>

  </tr>
  <tr>
    <td width="159" height="39">
    <p align="center">&nbsp;</td>
    <td width="24" height="39">
    &nbsp;</td>
    <td width="127" height="39">
    &nbsp;</td>
    <td width="243" height="39" bgcolor="#009933">
    <p align="center">
    <button name="btnGravar" style="width: 211; height: 32" type="submit">
    Gravar</button></td>
    <td width="240" height="39" bgcolor="#996600">
    <p align="center">
    <a href="funcionarios.jsp"><img border="0" src="images/botaoCancelar.JPG" width="210" height="29"></a></td>
    <td width="4" height="39">&nbsp;</td>
  </tr>
  <tr>
            <td width="797" bgcolor="#CC9900" height="14" colspan="6">

            <%

               // Demonstra o Retorno das funções
                String msg = (String) session.getAttribute("msg");

                if ((msg!="") && (msg !=null))
                {
                    out.println("<font face=\"Tahoma\" size=\"3\" color=\"#FFFFCC\">VALIDAÇÃO: " + msg + "</font>");
                }
            %>
               
            &nbsp;</td>
          </tr>
</table>

</form>

</body>

</html>