/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.farmacia.controle;

import com.farmacia.Dao.Dao;
import com.farmacia.Dao.DaoEntrada;
import com.farmacia.model.Entrada;
import java.util.Vector;

/**
 *
 * @author anderson
 */
public class CtrlEntrada
{

   private Dao dao = new Dao();

   public Entrada buscar(Integer idEntrada) throws Exception
   {

      if (idEntrada <= 0)
         throw new Exception("O Código do Entrada deve ser informado");

      DaoEntrada daoEntrada = new DaoEntrada(dao);

      Entrada registro = daoEntrada.buscar(idEntrada);

      return registro;

   }

   public Vector <Entrada> buscarTodos() throws Exception
   {

      DaoEntrada daoEntrada = new DaoEntrada(dao);
      try
      {
         Vector <Entrada> lista = daoEntrada.buscarTodos();
         return lista;
      }
      finally
      {
         dao = null;
      }

   }

   public void incluir(Entrada registro) throws Exception
   {

      DaoEntrada daoEntrada = new DaoEntrada(dao);

      try
      {

         dao.iniciaTransacao();

         daoEntrada.incluir(registro);

         dao.finalizaTransacao(false);

      }
      catch (Exception ex)
      {
         dao.finalizaTransacao(true);
         throw new Exception(ex.getMessage());
      }
      finally
      {
         daoEntrada = null;
      }

   }

   public void alterar(Entrada registro) throws Exception
   {

      DaoEntrada daoEntrada = new DaoEntrada(dao);

      try
      {

         dao.iniciaTransacao();

         daoEntrada.atualizar(registro);

         dao.finalizaTransacao(false);

      }
      catch (Exception ex)
      {
         dao.finalizaTransacao(true);
         throw new Exception(ex.getMessage());
      }
      finally
      {
         daoEntrada = null;
      }

   }

   public void excluir(Integer idEntrada) throws Exception
   {

      Entrada registro = buscar(idEntrada);

      if (registro.getIdEntrada() <= 0)
      {
         throw new Exception("A Entrada numero " + Integer.toString(idEntrada) + " não foi encontrado no banco de dados");
      }

      DaoEntrada daoEntrada = new DaoEntrada(dao);

      try
      {

         dao.iniciaTransacao();

         daoEntrada.excluir(registro.getIdEntrada());

         dao.finalizaTransacao(false);

      }
      catch (Exception ex)
      {
         dao.finalizaTransacao(true);
         throw new Exception(ex.getMessage());
      }
      finally
      {
         daoEntrada = null;
      }

   }

}
