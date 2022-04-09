<script language="JavaScript" type="text/javascript">

      function validaForm()
      {

         d = document.cadastro;

         if (d.txtNome.value == "")
         {
            alert("O Nome do Produto deve ser informado");
            d.txtNome.focus();
            return false;
         }

         if (d.txtDescricao.value == "")
         {
            alert("A Descrição do Produto deve ser informado");
            d.txtEmail.focus();
            return false;
         }

         if (d.txtLaboratorio.value == "")
         {
            alert("O Nome do Laboratório deve ser informado");
            d.txtLaboratorio.focus();
            return false;
         }

         if (d.txtPrecoVenda.value == "")
         {
            alert("O Preço de Venda deve ser informado");
            d.txtPrecoVenda.focus();
            return false;
         }

         return true;

      }

   </script>

<%@page contentType="text/html"
        pageEncoding="UTF-8"
        language="java"
        import="com.farmacia.controle.CtrlProduto, com.farmacia.model.Produto, com.Util.Util" %>

<html>

<head>
<meta http-equiv="Content-Language" content="pt-br">
<meta name="GENERATOR" content="Microsoft FrontPage 5.0">
<meta name="ProgId" content="FrontPage.Editor.Document">
<meta http-equiv="Content-Type" content="text/html; charset=windows-1252">
<title>Manutenção Produto</title>
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
         String nome          = "\"\"";
         String descricao     = "\"\"";
         String laboratorio   = "\"\"";
         String quantidade    = "\"\"";
         String valor_venda   = "\"\"";

         if (funcao.equals("alterar"))
         {

            // ALTERACAO

            if ((!id.isEmpty()) && (Util.textoTemSoNumeros(id)))
            {

               // Busca os Dados do Registro
               // Seta a Operação de Gravação como
               CtrlProduto ctrlProduto = new CtrlProduto();
               Produto registro = ctrlProduto.buscar(Integer.parseInt(id));

               if (registro.getIdProduto() > 0)
               {
                  codigo      = Integer.toString(registro.getIdProduto());
                  quantidade  = Integer.toString(registro.getQuantidade());
                  valor_venda = Double.toString(registro.getValorVenda());
                  if (registro.getNome()         !="") nome          = registro.getNome();
                  if (registro.getDescricao()    !="") descricao     = registro.getDescricao();
                  if (registro.getLaboratorio()  !="") laboratorio   = registro.getLaboratorio();

               }

            }

         }

       %>

  <form name="cadastro" method="POST" action="produtosProcessa.jsp?acao=grava" onsubmit="return validaForm()" >
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
            Produtos</font></b></p>
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
    <input type="text" name="txtNome" size="69"<% if (nome=="") out.print("value=${param.txtNome}"); else out.print("value=" + nome); %> maxlength="45"></td>

  </tr>
  <tr>

    <td width="159" height="30" align="left">
    <p align="right"><font color="#FFFFCC">Descri��o:</font></td>

    <td width="24" height="30" align="left">
    &nbsp;</td>

    <td width="614" height="30" colspan="4" align="left">
    <input type="text" name="txtDescricao" size="69" <% if (descricao=="") out.print("value=${param.txtDescricao}"); else out.print("value=" + descricao); %> maxlength="45"> </td>

  </tr>
  <tr>

    <td width="159" height="30" align="left">
    <p align="right"><font color="#FFFFCC">Laborat�rio:</font></td>

    <td width="24" height="30" align="left">
    &nbsp;</td>

    <td width="614" height="30" colspan="4" align="left">
    <input type="text" name="txtLaboratorio" size="69" <% if (laboratorio=="") out.print("value=${param.txtLaboratorio}"); else out.print("value=" + laboratorio); %> maxlength="45"></td>

  </tr>
  <tr>

    <td width="159" height="30" align="left">
    <p align="right"><font color="#FFFFCC">Saldo Estoque:</font></td>

    <td width="24" height="30" align="left">
    &nbsp;</td>

    <td width="614" height="30" colspan="4" align="left">
    <input type="text" name="txtSaldoEstoque" size="26" <% if (quantidade=="") out.print("value=${param.txtSaldoEstoque}"); else out.print("value=" + quantidade); %> maxlength="10"></td>

  </tr>
  <tr>

    <td width="159" height="30" align="left">
    <p align="right"><font color="#FFFFCC">Pre�o Venda:</font></td>

    <td width="24" height="30" align="left">
    &nbsp;</td>

    <td width="614" height="30" colspan="4" align="left">
    <input type="text" name="txtPrecoVenda" size="26" <% if (valor_venda=="") out.print("value=${param.txtPrecoVenda}"); else out.print("value=" + valor_venda); %> maxlength="10"></td>

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
    <a href="produtos.jsp"><img border="0" src="images/botaoCancelar.JPG" width="210" height="29"></a></td>
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