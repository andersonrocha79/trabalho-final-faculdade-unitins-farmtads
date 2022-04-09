/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.farmacia.controle;

import com.farmacia.Dao.Dao;
import com.farmacia.Dao.DaoProduto;
import com.farmacia.model.Produto;
import java.util.Vector;

/**
 *
 * @author anderson
 */
public class CtrlProduto
{

   private Dao dao = new Dao();

   public Produto buscar(Integer idProduto) throws Exception
   {

      if (idProduto <= 0)
         throw new Exception("O Código do Produto deve ser informado");

      DaoProduto daoProduto = new DaoProduto(dao);

      Produto registro = daoProduto.buscar(idProduto);

      return registro;

   }

   public Vector <Produto> buscarTodos() throws Exception
   {

      DaoProduto daoProduto = new DaoProduto(dao);
      try
      {
         Vector <Produto> lista = daoProduto.buscarTodos();
         return lista;
      }
      finally
      {
         dao = null;
      }

   }

   public void incluir(Produto registro) throws Exception
   {

      DaoProduto daoProduto = new DaoProduto(dao);

      try
      {

         dao.iniciaTransacao();

         daoProduto.incluir(registro);

         dao.finalizaTransacao(false);

      }
      catch (Exception ex)
      {
         dao.finalizaTransacao(true);
         throw new Exception(ex.getMessage());
      }
      finally
      {
         daoProduto = null;
      }

   }

   public void alterar(Produto registro) throws Exception
   {

      DaoProduto daoProduto = new DaoProduto(dao);

      try
      {

         dao.iniciaTransacao();

         daoProduto.atualizar(registro);

         dao.finalizaTransacao(false);

      }
      catch (Exception ex)
      {
         dao.finalizaTransacao(true);
         throw new Exception(ex.getMessage());
      }
      finally
      {
         daoProduto = null;
      }

   }

   public void excluir(Integer idProduto) throws Exception
   {

      Produto registro = buscar(idProduto);

      if (registro.getIdProduto() <= 0)
      {
         throw new Exception("O Produto " + Integer.toString(idProduto) + " não foi encontrado no banco de dados");
      }

      DaoProduto daoProduto = new DaoProduto(dao);

      try
      {

         dao.iniciaTransacao();

         daoProduto.excluir(registro.getIdProduto());

         dao.finalizaTransacao(false);

      }
      catch (Exception ex)
      {
         dao.finalizaTransacao(true);
         throw new Exception(ex.getMessage());
      }
      finally
      {
         daoProduto = null;
      }

   }

   public void atualizarEstoque(Integer idProduto, Integer quantidade, Boolean entrada) throws Exception
   {

      DaoProduto daoProduto = new DaoProduto(dao);
      Produto registro = new Produto();

      registro = daoProduto.buscar(idProduto);

      if (registro.getIdProduto()<=0)
         throw new Exception("Produto " + Integer.toString(idProduto) + " não Cadastrado" );

      try
      {
         // Realiza a AtualizaÃ§Ã£o do Estoque
         if (entrada)
         {
            // ENTRADA
            registro.setQuantidade(registro.getQuantidade() + quantidade);
         }
         else
         {
            // SAIDA
            registro.setQuantidade(registro.getQuantidade() - quantidade);
         }

         // Atualiza
         daoProduto.atualizar(registro);

      }
      catch (Exception ex)
      {
         throw new Exception("Falha Durante Atualizacao do Estoque: " + ex.getMessage());
      }
      finally
      {
         daoProduto = null;
      }

   }

}
