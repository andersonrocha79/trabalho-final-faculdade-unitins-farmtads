package com.farmacia.Dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.PreparedStatement;
import java.util.Vector;

import com.farmacia.model.ItemEntrada;
import java.util.logging.Level;
import java.util.logging.Logger;

public class DaoItemEntrada
{

   private Dao dao;
   private DaoProduto daoProduto = new DaoProduto(dao);

   public DaoItemEntrada(Dao dao)
   {
      this.dao = dao;
   }

   public com.farmacia.Dao.Dao getDao() {
      return dao;
   }

   public void setDao(com.farmacia.Dao.Dao Dao) {
      this.dao = Dao;
   }

   public void incluir(ItemEntrada registro) throws Exception
   {

      dao.conectar();

      try
      {

         PreparedStatement stmt = dao.conexao.prepareStatement("INSERT INTO item_Entrada (Entrada_IdEntrada, Produto_IdProduto, quantidade, valor_compra)" +
                                                               " VALUES (?, ?, ?, ?)");

         stmt.setInt(1, registro.getIdEntrada());
         stmt.setInt(2, registro.getProduto().getIdProduto());
         stmt.setInt(3, registro.getQuantidade());
         stmt.setDouble(4, registro.getValorCompra());

         stmt.executeUpdate();
         stmt.close();
      }
      catch (SQLException ex)
      {
         Logger.getLogger(DaoItemEntrada.class.getName()).log(Level.SEVERE, null, ex);
         throw new Exception("Falha ao Incluir o Registro - " + ex.getMessage());
      }
      finally
      {
         dao.desconectar();
      }
   }

   public void atualizar(ItemEntrada registro) throws Exception
   {

      dao.conectar();

      try
      {

         PreparedStatement stmt = dao.conexao.prepareStatement("UPDATE Item_Entrada SET quantidade = ?, valor_compra = ?" +
                                                               " WHERE IdEntrada = ? AND Produto_IdProduto = ?");

         stmt.setInt(1, registro.getQuantidade());
         stmt.setDouble(2, registro.getValorCompra());
         stmt.setInt(3, registro.getIdEntrada());
         stmt.setInt(4, registro.getProduto().getIdProduto());

         stmt.executeUpdate();

         stmt.close();

      }
      catch (SQLException ex)
      {
         Logger.getLogger(DaoItemEntrada.class.getName()).log(Level.SEVERE, null, ex);
         throw new Exception("Falha ao Atualizar o Registro - " + ex.getMessage());
      }
      finally
      {
         dao.desconectar();
      }
   }

   public void excluir(int pkEntrada, int pkProduto) throws Exception
   {
      dao.conectar();
      try
      {
         PreparedStatement stmt = dao.conexao.prepareStatement("DELETE FROM Item_Entrada WHERE IdEntrada = ? AND Produto_IdProduto = ?");
         stmt.setInt(1, pkEntrada);
         stmt.setInt(2, pkProduto);
         stmt.execute();
         stmt.close();
      }
      catch (SQLException ex)
      {
         Logger.getLogger(DaoItemEntrada.class.getName()).log(Level.SEVERE, null, ex);
         throw new Exception("Falha ao Excluir o Registro - " + ex.getMessage());
      }
      finally
      {
         dao.desconectar();
      }
   }

   protected void setarCampos(ItemEntrada registro, ResultSet rs) throws SQLException, Exception
   {

      registro.setIdEntrada(rs.getInt("IdEntrada"));
      registro.setQuantidade(rs.getInt("quantidade"));
      registro.setValorCompra(rs.getDouble("valor_Compra"));
      registro.setProduto(daoProduto.buscar(rs.getInt("Produto_IdProduto")));

   }

   public ItemEntrada buscar(int IdEntrada) throws Exception
   {

      ItemEntrada registro = new ItemEntrada();
      ResultSet rs;

      dao.conectar();

      try
      {

         PreparedStatement stmt = dao.conexao.prepareStatement("SELECT IdEntrada, Produto_IdProduto, quantidade, valor_compra" +
                                                               " FROM Item_Entrada WHERE IdEntrada = ?");
         stmt.setInt(1, IdEntrada);
         rs = stmt.executeQuery();

         if (rs.next())
         {
             setarCampos(registro, rs);
         }
      }
      catch (SQLException ex)
      {
         Logger.getLogger(DaoItemEntrada.class.getName()).log(Level.SEVERE, null, ex);
         throw new Exception("Falha ao Buscar o Registro - " + ex.getMessage());
      }
      finally
      {
         dao.desconectar();
      }

      return registro;

   }

   public Vector <ItemEntrada> buscarTodos() throws Exception
   {

      Vector <ItemEntrada> lista = new Vector<ItemEntrada>();
      ResultSet rs;

      dao.conectar();

      try
      {
         PreparedStatement stmt = dao.conexao.prepareStatement("SELECT IdEntrada, Produto_IdProduto, quantidade, valor_compra" +
                                                               " FROM Item_Entrada ORDER BY IdEntrada, IdProduto");
         rs = stmt.executeQuery();
         while (rs.next())
         {
            ItemEntrada registro = new ItemEntrada();
            setarCampos(registro, rs);
            lista.add(registro);
         }
         stmt.close();
      }
      catch (SQLException ex)
      {
         Logger.getLogger(DaoItemEntrada.class.getName()).log(Level.SEVERE, null, ex);
         throw new Exception("Falha ao Selecionar os Registros - " + ex.getMessage());
      }
      finally
      {
         dao.desconectar();
      }

      return lista;

   }

}