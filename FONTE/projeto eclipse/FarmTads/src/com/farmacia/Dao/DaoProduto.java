package com.farmacia.Dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.PreparedStatement;
import java.util.Vector;

import com.farmacia.model.Produto;
import java.util.logging.Level;
import java.util.logging.Logger;

public class DaoProduto
{

   private com.farmacia.Dao.Dao dao;

   public DaoProduto(Dao dao)
   {
      this.dao = dao;
   }

   public com.farmacia.Dao.Dao getDao() {
      return dao;
   }

   public void setDao(com.farmacia.Dao.Dao Dao) {
      this.dao = Dao;
   }

   public void incluir(Produto registro) throws Exception
   {

      dao.conectar();

      try
      {

         PreparedStatement stmt = dao.conexao.prepareStatement("INSERT INTO Produto (IdProduto, nome, descricao, laboratorio, quantidade, valor_venda)" +
                                                               " VALUES (NULL, ?, ?, ?, ?, ?)");

         stmt.setString(1, registro.getNome());
         stmt.setString(2, registro.getDescricao());
         stmt.setString(3, registro.getLaboratorio());
         stmt.setInt(4, registro.getQuantidade());
         stmt.setDouble(5, registro.getValorVenda());

         stmt.executeUpdate();
         registro.setIdProduto(dao.getIdUltimaInclusao());
         stmt.close();
      }
      catch (SQLException ex)
      {
         Logger.getLogger(DaoProduto.class.getName()).log(Level.SEVERE, null, ex);
         throw new Exception("Falha ao Incluir o Registro - " + ex.getMessage());
      }
      finally
      {
         dao.desconectar();
      }
   }

   public void atualizar(Produto registro) throws Exception
   {

      dao.conectar();

      try
      {

         PreparedStatement stmt = dao.conexao.prepareStatement("UPDATE Produto SET nome = ?, descricao = ?, laboratorio = ?, quantidade = ?, valor_venda = ? WHERE IdProduto = ?");

         stmt.setString(1, registro.getNome());
         stmt.setString(2, registro.getDescricao());
         stmt.setString(3, registro.getLaboratorio());
         stmt.setInt(4, registro.getQuantidade());
         stmt.setDouble(5, registro.getValorVenda());
         stmt.setInt(6, registro.getIdProduto());

         stmt.executeUpdate();

         stmt.close();

      }
      catch (SQLException ex)
      {
         Logger.getLogger(DaoProduto.class.getName()).log(Level.SEVERE, null, ex);
         throw new Exception("Falha ao Atualizar o Registro - " + ex.getMessage());
      }
      finally
      {
         dao.desconectar();
      }
   }

   public void excluir(int pk) throws Exception
   {
      dao.conectar();
      try
      {
         PreparedStatement stmt = dao.conexao.prepareStatement("DELETE FROM Produto WHERE IdProduto = ?");
         stmt.setInt(1, pk);
         stmt.execute();
         stmt.close();
      }
      catch (SQLException ex)
      {
         Logger.getLogger(DaoProduto.class.getName()).log(Level.SEVERE, null, ex);
         throw new Exception("Falha ao Excluir o Registro - " + ex.getMessage());
      }
      finally
      {
         dao.desconectar();
      }
   }

   protected void setarCampos(Produto registro, ResultSet rs) throws SQLException
   {
      registro.setIdProduto(rs.getInt("IdProduto"));
      registro.setNome(rs.getString("nome"));
      registro.setDescricao(rs.getString("descricao"));
      registro.setLaboratorio(rs.getString("laboratorio"));
      registro.setQuantidade(rs.getInt("quantidade"));
      registro.setValorVenda(rs.getDouble("valor_venda"));
   }

   public Produto buscar(int IdProduto) throws Exception
   {

      Produto registro = new Produto();
      ResultSet rs;

      dao.conectar();

      try
      {

         PreparedStatement stmt = dao.conexao.prepareStatement("SELECT IdProduto, nome, descricao, laboratorio, quantidade, valor_venda FROM produto WHERE IdProduto = ?");
         stmt.setInt(1, IdProduto);
         rs = stmt.executeQuery();

         if (rs.next())
         {
             setarCampos(registro, rs);
         }
      }
      catch (SQLException ex)
      {
         Logger.getLogger(DaoProduto.class.getName()).log(Level.SEVERE, null, ex);
         throw new Exception("Falha ao Buscar o Registro - " + ex.getMessage());
      }
      finally
      {
         dao.desconectar();
      }

      return registro;

   }

   public Vector <Produto> buscarTodos() throws Exception
   {

      Vector <Produto> lista = new Vector<Produto>();
      ResultSet rs;

      dao.conectar();

      try
      {
         PreparedStatement stmt = dao.conexao.prepareStatement("SELECT IdProduto, nome, descricao, laboratorio, quantidade, valor_venda FROM produto order by IdProduto");
         rs = stmt.executeQuery();
         while (rs.next())
         {
            Produto registro = new Produto();
            setarCampos(registro, rs);
            lista.add(registro);
         }
         stmt.close();
      }
      catch (SQLException ex)
      {
         Logger.getLogger(DaoProduto.class.getName()).log(Level.SEVERE, null, ex);
         throw new Exception("Falha ao Selecionar os Registros - " + ex.getMessage());
      }
      finally
      {
         dao.desconectar();
      }

      return lista;

   }

}