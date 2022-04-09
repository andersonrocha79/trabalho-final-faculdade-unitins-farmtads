package com.farmacia.Dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.PreparedStatement;
import java.util.Vector;

import com.farmacia.model.ItemVenda;
import java.util.logging.Level;
import java.util.logging.Logger;

public class DaoItemVenda
{

   private Dao dao;
   private DaoProduto daoProduto = new DaoProduto(dao);

   public DaoItemVenda(Dao dao)
   {
      this.dao = dao;
   }

   public com.farmacia.Dao.Dao getDao() {
      return dao;
   }

   public void setDao(com.farmacia.Dao.Dao Dao) {
      this.dao = Dao;
   }

   public void incluir(ItemVenda registro) throws Exception
   {

      dao.conectar();

      try
      {

         PreparedStatement stmt = dao.conexao.prepareStatement("INSERT INTO Item_Venda (IdVenda, IdProduto, quantidade, valor_venda)" +
                                                               " VALUES (?, ?, ?, ?)");

         stmt.setInt(1, registro.getIdVenda());
         stmt.setInt(2, registro.getProduto().getIdProduto());
         stmt.setInt(3, registro.getQuantidade());
         stmt.setDouble(4, registro.getValorVenda());

         stmt.executeUpdate();
         stmt.close();
      }
      catch (SQLException ex)
      {
         Logger.getLogger(DaoItemVenda.class.getName()).log(Level.SEVERE, null, ex);
         throw new Exception("Falha ao Incluir o Registro - " + ex.getMessage());
      }
      finally
      {
         dao.desconectar();
      }
   }

   public void atualizar(ItemVenda registro) throws Exception
   {

      dao.conectar();

      try
      {

         PreparedStatement stmt = dao.conexao.prepareStatement("UPDATE Item_Venda SET quantidade = ?, valor_venda = ?" +
                                                               " WHERE IdVenda = ? AND IdProduto = ?");

         stmt.setInt(1, registro.getQuantidade());
         stmt.setDouble(2, registro.getValorVenda());
         stmt.setInt(3, registro.getIdVenda());
         stmt.setInt(4, registro.getProduto().getIdProduto());

         stmt.executeUpdate();

         stmt.close();

      }
      catch (SQLException ex)
      {
         Logger.getLogger(DaoItemVenda.class.getName()).log(Level.SEVERE, null, ex);
         throw new Exception("Falha ao Atualizar o Registro - " + ex.getMessage());
      }
      finally
      {
         dao.desconectar();
      }
   }

   public void excluir(int pkVenda, int pkProduto) throws Exception
   {
      dao.conectar();
      try
      {
         PreparedStatement stmt = dao.conexao.prepareStatement("DELETE FROM Item_Venda WHERE IdVenda = ? AND IdProduto = ?");
         stmt.setInt(1, pkVenda);
         stmt.setInt(2, pkProduto);
         stmt.execute();
         stmt.close();
      }
      catch (SQLException ex)
      {
         Logger.getLogger(DaoItemVenda.class.getName()).log(Level.SEVERE, null, ex);
         throw new Exception("Falha ao Excluir o Registro - " + ex.getMessage());
      }
      finally
      {
         dao.desconectar();
      }
   }

   protected void setarCampos(ItemVenda registro, ResultSet rs) throws SQLException, Exception
   {

      registro.setIdVenda(rs.getInt("IdVenda"));
      registro.setQuantidade(rs.getInt("quantidade"));
      registro.setValorVenda(rs.getDouble("valor_Venda"));
      registro.setProduto(daoProduto.buscar(rs.getInt("IdProduto")));

   }

   public ItemVenda buscar(int IdVenda) throws Exception
   {

      ItemVenda registro = new ItemVenda();
      ResultSet rs;

      dao.conectar();

      try
      {

         PreparedStatement stmt = dao.conexao.prepareStatement("SELECT IdVenda, IdProduto, quantidade, valor_venda" +
                                                               " FROM Item_Venda WHERE IdVenda = ?");
         stmt.setInt(1, IdVenda);
         rs = stmt.executeQuery();

         if (rs.next())
         {
             setarCampos(registro, rs);
         }
      }
      catch (SQLException ex)
      {
         Logger.getLogger(DaoItemVenda.class.getName()).log(Level.SEVERE, null, ex);
         throw new Exception("Falha ao Buscar o Registro - " + ex.getMessage());
      }
      finally
      {
         dao.desconectar();
      }

      return registro;

   }

   public Vector <ItemVenda> buscarTodos() throws Exception
   {

      Vector <ItemVenda> lista = new Vector<ItemVenda>();
      ResultSet rs;

      dao.conectar();

      try
      {
         PreparedStatement stmt = dao.conexao.prepareStatement("SELECT IdVenda, IdProduto, quantidade, valor_venda" +
                                                               " FROM Item_Venda ORDER BY IdVenda, IdProduto");
         rs = stmt.executeQuery();
         while (rs.next())
         {
            ItemVenda registro = new ItemVenda();
            setarCampos(registro, rs);
            lista.add(registro);
         }
         stmt.close();
      }
      catch (SQLException ex)
      {
         Logger.getLogger(DaoItemVenda.class.getName()).log(Level.SEVERE, null, ex);
         throw new Exception("Falha ao Selecionar os Registros - " + ex.getMessage());
      }
      finally
      {
         dao.desconectar();
      }

      return lista;

   }

}