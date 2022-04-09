/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.farmacia.controle;

import com.farmacia.Dao.Dao;
import com.farmacia.Dao.DaoFuncionario;
import com.farmacia.Dao.DaoPessoa;
import com.farmacia.Dao.DaoPessoaFisica;
import com.farmacia.model.Funcionario;
import java.util.Vector;

/**
 *
 * @author anderson
 */
public class CtrlFuncionario
{

   private Dao dao = new Dao();
   
   /**
   * Realiza a Valida√ß√£o do Funcion√°rio que quer ter acesso ao sistema
   * @param email: email do funcinario
   * @param senha: senha do funcionario
   * @return Retorna o Registro do Funcionario V√°lido
   */
   public Funcionario validaUsuario(String email, String senha) throws Exception
   {

      if (email.isEmpty())
         throw new Exception("O e@mail deve ser informado");

      if (senha.isEmpty())
         throw new Exception("A senha deve ser informado");

      DaoFuncionario daoFuncionario = new DaoFuncionario(dao);

      Funcionario registro = daoFuncionario.buscar(0, email);

      if (registro.getIdFuncionario() == 0)
      {
         throw new Exception("Funcion·rio n„o cadastrado no Sistema");
      }
      else
      {
         if (!registro.getSenha().equals(senha))
            throw new Exception("Senha incorreta");
         else
            return registro;
      }
       
   }

   public Funcionario buscar(Integer idFuncionario) throws Exception
   {

      if (idFuncionario <= 0)
         throw new Exception("O cÛdigo do funcion·rio deve ser informado");

      DaoFuncionario daoFuncionario = new DaoFuncionario(dao);

      Funcionario registro = daoFuncionario.buscar(idFuncionario, "");

      return registro;

   }

   public Vector <Funcionario> buscarTodos() throws Exception
   {

      DaoFuncionario daoFuncionario = new DaoFuncionario(dao);
      try
      {
         Vector <Funcionario> lista = daoFuncionario.buscarTodos();
         return lista;
      }
      finally
      {
         dao = null;
      }

   }

   public void incluir(Funcionario registro) throws Exception
   {

      DaoPessoa daoPessoa = new DaoPessoa(dao);
      DaoPessoaFisica daoPessoaFisica = new DaoPessoaFisica(dao);
      DaoFuncionario daoFuncionario = new DaoFuncionario(dao);      
      
      try
      {

         dao.iniciaTransacao();

         daoPessoa.incluir(registro);
         daoPessoaFisica.incluir(registro);
         daoFuncionario.incluir(registro);

         dao.finalizaTransacao(false);

      }
      catch (Exception ex)
      {
         dao.finalizaTransacao(true);
         throw new Exception(ex.getMessage());
      }            
      finally
      {
         daoPessoa = null;
         daoPessoaFisica = null;
         daoFuncionario = null;
      }
      
   }

   public void alterar(Funcionario registro) throws Exception
   {

      DaoPessoa daoPessoa = new DaoPessoa(dao);
      DaoPessoaFisica daoPessoaFisica = new DaoPessoaFisica(dao);
      DaoFuncionario daoFuncionario = new DaoFuncionario(dao);

      try
      {

         dao.iniciaTransacao();

         daoFuncionario.atualizar(registro);
         daoPessoaFisica.atualizar(registro);
         daoPessoa.atualizar(registro);

         dao.finalizaTransacao(false);

      }
      catch (Exception ex)
      {
         dao.finalizaTransacao(true);
         throw new Exception(ex.getMessage());
      }
      finally
      {
         daoPessoa = null;
         daoPessoaFisica = null;
         daoFuncionario = null;
      }

   }

   public void excluir(Integer idFuncionario) throws Exception
   {

      Funcionario registro = buscar(idFuncionario);

      if (registro.getIdFuncionario() <= 0)
      {
         throw new Exception("O Funcion·rio " + Integer.toString(idFuncionario) + " n„o foi encontrado no banco de dados");
      }

      DaoPessoa daoPessoa = new DaoPessoa(dao);
      DaoPessoaFisica daoPessoaFisica = new DaoPessoaFisica(dao);
      DaoFuncionario daoFuncionario = new DaoFuncionario(dao);

      try
      {

         dao.iniciaTransacao();

         daoFuncionario.excluir(registro.getIdFuncionario());
         daoPessoaFisica.excluir(registro.getIdPessoa());
         daoPessoa.excluir(registro.getIdPessoa());

         dao.finalizaTransacao(false);

      }
      catch (Exception ex)
      {
         dao.finalizaTransacao(true);
         throw new Exception(ex.getMessage());
      }
      finally
      {
         daoPessoa = null;
         daoPessoaFisica = null;
         daoFuncionario = null;
      }

   }

}
