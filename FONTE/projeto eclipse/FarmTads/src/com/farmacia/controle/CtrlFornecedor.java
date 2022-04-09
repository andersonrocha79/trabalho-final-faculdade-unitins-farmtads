/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.farmacia.controle;

import com.farmacia.Dao.Dao;
import com.farmacia.Dao.DaoFornecedor;
import com.farmacia.Dao.DaoPessoa;
import com.farmacia.Dao.DaoPessoaJuridica;
import com.farmacia.model.Fornecedor;
import java.util.Vector;

/**
 *
 * @author anderson
 */
public class CtrlFornecedor
{

   private Dao dao = new Dao();

   public Fornecedor buscar(Integer idFornecedor) throws Exception
   {

      if (idFornecedor <= 0)
         throw new Exception("O código do Fornecedor deve ser informado");

      DaoFornecedor daoFornecedor = new DaoFornecedor(dao);

      Fornecedor registro = daoFornecedor.buscar(idFornecedor);

      return registro;

   }

   public Vector <Fornecedor> buscarTodos() throws Exception
   {

      DaoFornecedor daoFornecedor = new DaoFornecedor(dao);
      try
      {
         Vector <Fornecedor> lista = daoFornecedor.buscarTodos();
         return lista;
      }
      finally
      {
         dao = null;
      }

   }

   public void incluir(Fornecedor registro) throws Exception
   {

      DaoPessoa daoPessoa = new DaoPessoa(dao);
      DaoPessoaJuridica daoPessoaJuridica = new DaoPessoaJuridica(dao);
      DaoFornecedor daoFornecedor = new DaoFornecedor(dao);

      try
      {

         dao.iniciaTransacao();

         daoPessoa.incluir(registro);
         daoPessoaJuridica.incluir(registro);
         daoFornecedor.incluir(registro);

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
         daoPessoaJuridica = null;
         daoFornecedor = null;
      }

   }

   public void alterar(Fornecedor registro) throws Exception
   {

      DaoPessoa daoPessoa = new DaoPessoa(dao);
      DaoPessoaJuridica daoPessoaJuridica = new DaoPessoaJuridica(dao);
      DaoFornecedor daoFornecedor = new DaoFornecedor(dao);

      try
      {

         dao.iniciaTransacao();

         daoFornecedor.atualizar(registro);
         daoPessoaJuridica.atualizar(registro);
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
         daoPessoaJuridica = null;
         daoFornecedor = null;
      }

   }

   public void excluir(Integer idFornecedor) throws Exception
   {

      Fornecedor registro = buscar(idFornecedor);

      if (registro.getIdFornecedor() <= 0)
      {
         throw new Exception("O Fornecedor " + Integer.toString(idFornecedor) + " não foi encontrado no banco de dados");
      }

      DaoPessoa daoPessoa = new DaoPessoa(dao);
      DaoPessoaJuridica daoPessoaJuridica = new DaoPessoaJuridica(dao);
      DaoFornecedor daoFornecedor = new DaoFornecedor(dao);

      try
      {

         dao.iniciaTransacao();

         daoFornecedor.excluir(registro.getIdFornecedor());
         daoPessoaJuridica.excluir(registro.getIdPessoa());
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
         daoPessoaJuridica = null;
         daoFornecedor = null;
      }

   }

}
