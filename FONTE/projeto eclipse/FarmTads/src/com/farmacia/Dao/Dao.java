/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.farmacia.Dao;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.ResultSet;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Dao
{

   protected Connection conexao;

   private final String URL      = "jdbc:mysql://localhost/farmtads";
   private final String USUARIO  = "root";
   private final String SENHA    = "";

   private Boolean transacaoAtiva = false;

   public void iniciaTransacao()
   {

      try
      {

         // Se Habilitar a Transação os Comandos SQL não serão mais AutoComit
         // e o Metodo Close não será poderá ser chamado
         conectar();
         conexao.setAutoCommit(false);
         transacaoAtiva = true;

      } catch (SQLException ex)
      {
         Logger.getLogger(Dao.class.getName()).log(Level.SEVERE, null, ex);
      }

   }

   public void finalizaTransacao(Boolean cancelar)
   {

      try
      {

         // Se Habilitar a Transação os Comandos SQL não serão mais AutoComit
         // e o Metodo Close não será poderá ser chamado
         if (cancelar)
            conexao.rollback();
         else
            conexao.commit();

         conexao.setAutoCommit(true);
         transacaoAtiva = false;

         this.desconectar();

      } catch (SQLException ex)
      {
         Logger.getLogger(Dao.class.getName()).log(Level.SEVERE, null, ex);
      }

   }

   public void conectar()
   {
   
      try
      {
    	  
         if ((conexao == null) || (conexao.isClosed()))
         {
            conexao = ConFactory.conexao(URL, USUARIO, SENHA);
         }
         
      }
      catch (SQLException e)
      {
         e.printStackTrace();
      }

   }

   public void desconectar()
   {

      try
      {
         if (!conexao.isClosed())
         {
            // Se tiver com Transação ativa não desconecta
            // A conexao sera fechada pelo objeto que criou a transcao
            if (!transacaoAtiva)
            {
               conexao.close();
            }
         }
      }
      catch (SQLException e)
      {
         e.printStackTrace();
      }
   }

   protected int getIdUltimaInclusao() throws SQLException
   {

      int ultimoID = 0;

      conectar();

      Statement query = conexao.createStatement();
      ResultSet rs = query.executeQuery("SELECT LAST_INSERT_ID()");

      try
      {
         if(rs.next())
            ultimoID = rs.getInt(1);
         return ultimoID;
      }
      finally
      {
         rs = null;
      }
      
   }

}
