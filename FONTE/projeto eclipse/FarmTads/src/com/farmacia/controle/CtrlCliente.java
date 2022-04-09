/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.farmacia.controle;

import com.farmacia.Dao.Dao;
import com.farmacia.Dao.DaoCliente;
import com.farmacia.Dao.DaoPessoa;
import com.farmacia.Dao.DaoPessoaFisica;
import com.farmacia.model.Cliente;
import java.util.Vector;

/**
 *
 * @author anderson
 */
public class CtrlCliente
{

   private Dao dao = new Dao();

   public Cliente buscar(Integer idCliente) throws Exception
   {

      if (idCliente <= 0)
         throw new Exception("O código do Cliente deve ser informado");

      DaoCliente daoCliente = new DaoCliente(dao);

      Cliente registro = daoCliente.buscar(idCliente);

      return registro;

   }

   public Vector <Cliente> buscarTodos() throws Exception
   {

      DaoCliente daoCliente = new DaoCliente(dao);
      try
      {
         Vector <Cliente> lista = daoCliente.buscarTodos();
         return lista;
      }
      finally
      {
         dao = null;
      }

   }

   public void incluir(Cliente registro) throws Exception
   {

      DaoPessoa daoPessoa = new DaoPessoa(dao);
      DaoPessoaFisica daoPessoaFisica = new DaoPessoaFisica(dao);
      DaoCliente daoCliente = new DaoCliente(dao);

      try
      {

         dao.iniciaTransacao();

         daoPessoa.incluir(registro);
         daoPessoaFisica.incluir(registro);
         daoCliente.incluir(registro);

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
         daoCliente = null;
      }

   }

   public void alterar(Cliente registro) throws Exception
   {

      DaoPessoa daoPessoa = new DaoPessoa(dao);
      DaoPessoaFisica daoPessoaFisica = new DaoPessoaFisica(dao);
      DaoCliente daoCliente = new DaoCliente(dao);

      try
      {

         dao.iniciaTransacao();

         daoCliente.atualizar(registro);
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
         daoCliente = null;
      }

   }

   public void excluir(Integer idCliente) throws Exception
   {

      Cliente registro = buscar(idCliente);

      if (registro.getIdCliente() <= 0)
      {
         throw new Exception("O Cliente " + Integer.toString(idCliente) + " não foi encontrado no banco de dados");
      }

      DaoPessoa daoPessoa = new DaoPessoa(dao);
      DaoPessoaFisica daoPessoaFisica = new DaoPessoaFisica(dao);
      DaoCliente daoCliente = new DaoCliente(dao);

      try
      {

         dao.iniciaTransacao();

         daoCliente.excluir(registro.getIdCliente());
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
         daoCliente = null;
      }

   }

}
