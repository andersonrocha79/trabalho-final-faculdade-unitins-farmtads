<script language="JavaScript" type="text/javascript">

      function validaForm()
      {

         d = document.cadastro;

         if (d.txtData.value == "")
         {
            alert("A Data deve ser informada");
            d.txtData.focus();
            return false;
         }

         if (d.cboFornecedor.value == "")
         {
            alert("O Fornecedor deve ser informado");
            d.cboFornecedor.focus();
            return false;
         }

         if (d.cboFuncionario.value == "")
         {
            alert("O Funcionário deve ser informado");
            d.cboFuncionario.focus();
            return false;
         }

         return true;

      }

   </script>

<%@page contentType="text/html"
        pageEncoding="UTF-8"
        language="java"
        import="com.farmacia.controle.CtrlEntrada, com.farmacia.model.Entrada, com.farmacia.controle.CtrlFornecedor, com.farmacia.model.Fornecedor, com.farmacia.controle.CtrlFuncionario, com.farmacia.model.Funcionario, java.util.Vector, com.Util.Util" %>        

<html>

<head>
<meta http-equiv="Content-Language" content="pt-br">
<meta name="GENERATOR" content="Microsoft FrontPage 5.0">
<meta name="ProgId" content="FrontPage.Editor.Document">
<meta http-equiv="Content-Type" content="text/html; charset=windows-1252">
<title>Manutenção Entrada</title>
</head>
<body>

       <%

         // verifica se o Usuario esta logado
         Boolean validacaoOK = (Boolean) session.getAttribute("validacaoOK");
         if (!validacaoOK) pageContext.forward("index.jsp");

         // verifica se e Alteracao ou Inclusao
         String funcao = request.getParameter("operacao");
         String id = request.getParameter("id");

         // Registra a Operação na Sessão
         session.setAttribute("operacao", funcao);

         // Define os Campos iniciais
         String codigo        = "\"\"";
         String data          = "\"\"";
         String fornecedor    = "\"\"";
         String funcionario   = "\"\"";

         if (funcao.equals("alterar"))
         {

            // ALTERACAO

            if ((!id.isEmpty()) && (Util.textoTemSoNumeros(id)))
            {

               // Busca os Dados do Registro
               // Seta a Operação de Gravação como
               CtrlEntrada ctrlEntrada = new CtrlEntrada();
               Entrada registro = ctrlEntrada.buscar(Integer.parseInt(id));

               if (registro.getIdEntrada() > 0)
               {
                  codigo      = Integer.toString(registro.getIdEntrada());
                  data        = registro.getData();
                  fornecedor  = Integer.toString(registro.getFornecedor().getIdFornecedor());
                  funcionario = Integer.toString(registro.getFuncionario().getIdFuncionario());
               }

            }

         }

       %>

  <form name="cadastro" method="POST" action="entradasProcessa.jsp?acao=grava" onsubmit="return validaForm()" >
  <input TYPE="hidden" NAME="VTI-GROUP" VALUE="0"><table border="0" cellpadding="0" cellspacing="0" style="border-collapse: collapse" width="686" id="AutoNumber1" bgcolor="#666633" height="306">
  <tr>
            <td width="797" bgcolor="#996600" height="24" colspan="6">
            &nbsp;</td>
          </tr>
  <tr>
    <td width="797" height="48" colspan="6">
            <p align="center"><b><font size="5" color="#FFFFCC" face="Tahoma">
            FarmTads</font><br>
            <font face="Tahoma" size="4" color="#CCCC00">Entrada de Mercadorias</font></b></p>
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
    <p align="right"><font color="#FFFFCC">C�digo:</font></td>

    <td width="24" height="30" align="left">
    &nbsp;</td>

    <td width="614" height="30" colspan="4" align="left">
       <input type="text" name="txtCodigo" size="8"<% if (codigo=="") out.print("value=${param.txtCodigo}"); else out.print("value=" + codigo); %> maxlength="6">
    </td>

  </tr>
  <tr>

    <td width="159" height="30" align="left">
    <p align="right"><font color="#FFFFCC">Data:</font></td>

    <td width="24" height="30" align="left">
    &nbsp;</td>

    <td width="614" height="30" colspan="4" align="left">
       <input type="text" name="txtData" size="17"<% if (data=="") out.print("value=${param.txtData}"); else out.print("value=" + data); %> maxlength="10">
    </td>

  </tr>
  <tr>

    <td width="159" height="30" align="left">
    <p align="right"><font color="#FFFFCC">Fornecedor:</font></td>

    <td width="24" height="30" align="left">
    &nbsp;</td>

    <td width="614" height="30" colspan="4" align="left">
    <select size="1" name="cboFornecedor">
    <%

      // Preenche o Combo com os Fornecedores Cadastrados
      CtrlFornecedor ctrlFornecedor = new CtrlFornecedor();
      Vector <Fornecedor> listaFornecedor = ctrlFornecedor.buscarTodos();

      // Percorre a Lista para Demonstrar os Registros
      if (listaFornecedor.size() > 0)
      {
         for(int i = 0; i < listaFornecedor.size(); i++)
         {
            Fornecedor registro = listaFornecedor.get(i);
            out.print("<option selected value=" + registro.getIdFornecedor() + ">" + registro.getNome() + "</option>");
         }
      }

    %>
    </select></td>

  </tr>
  <tr>

    <td width="159" height="30" align="left">
    <p align="right"><font color="#FFFFCC">Funcion�rio:</font></td>

    <td width="24" height="30" align="left">
    &nbsp;</td>

    <td width="614" height="30" colspan="4" align="left">
    <select size="1" name="cboFuncionario">

    <%

      // Preenche o Combo com os Funcionários Cadastrados
      CtrlFuncionario ctrlFuncionario = new CtrlFuncionario();
      Vector <Funcionario> listaFuncionario = ctrlFuncionario.buscarTodos();

      // Percorre a Lista para Demonstrar os Registros
      if (listaFuncionario.size() > 0)
      {
         for(int i = 0; i < listaFuncionario.size(); i++)
         {
            Funcionario registro = listaFuncionario.get(i);
            out.print("<option selected value=" + registro.getIdFuncionario() + ">" + registro.getNome() + "</option>");
         }
      }

    %>

    </select></td>

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
    <a href="entradas.jsp"><img border="0" src="images/botaoCancelar.JPG" width="210" height="29"></a></td>
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