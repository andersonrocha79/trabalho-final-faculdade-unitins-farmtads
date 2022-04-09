package com.farmacia.Dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.PreparedStatement;
import java.util.Vector;

import com.farmacia.model.Venda;
import java.util.logging.Level;
import java.util.logging.Logger;

public class DaoVenda
{

   private Dao dao;
   private DaoCliente daoCliente = new DaoCliente(dao);
   private DaoFuncionario daoFuncionario = new DaoFuncionario(dao);

   public DaoVenda(Dao dao)
   {
      this.dao = dao;
   }

   public com.farmacia.Dao.Dao getDao() {
      return dao;
   }

   public void setDao(com.farmacia.Dao.Dao Dao) {
      this.dao = Dao;
   }

   public void incluir(Venda registro) throws Exception
   {

      dao.conectar();

      try
      {

         PreparedStatement stmt = dao.conexao.prepareStatement("INSERT INTO Venda (IdVenda, data, formaPagto, desconto, cliente_Id_Cliente, funcionario_Id_Funcionario)" +
                                                               " VALUES (NULL, ?, ?, ?, ?, ?)");

         stmt.setString(1, registro.getData());
         stmt.setString(2, registro.getFormaPagto());
         stmt.setDouble(3, registro.getDesconto());
         stmt.setInt(4, registro.getCliente().getIdCliente());
         stmt.setInt(5, registro.getFuncionario().getIdFuncionario());

         stmt.executeUpdate();
         registro.setIdVenda(dao.getIdUltimaInclusao());
         stmt.close();
      }
      catch (SQLException ex)
      {
         Logger.getLogger(DaoVenda.class.getName()).log(Level.SEVERE, null, ex);
         throw new Exception("Falha ao Incluir o Registro - " + ex.getMessage());
      }
      finally
      {
         dao.desconectar();
      }
   }

   public void atualizar(Venda registro) throws Exception
   {

      dao.conectar();

      try
      {

         PreparedStatement stmt = dao.conexao.prepareStatement("UPDATE Venda SET data = ?, formaPagto = ?, desconto = ?, cliente_Id_Cliente = ?, funcionario_Id_Funcionario = ?" +
                                                               " WHERE IdVenda = ?");

         stmt.setString(1, registro.getData());
         stmt.setString(2, registro.getFormaPagto());
         stmt.setDouble(3, registro.getDesconto());
         stmt.setInt(4, registro.getCliente().getIdCliente());
         stmt.setInt(5, registro.getFuncionario().getIdFuncionario());
         stmt.setInt(6, registro.getIdVenda());

         stmt.executeUpdate();

         stmt.close();

      }
      catch (SQLException ex)
      {
         Logger.getLogger(DaoVenda.class.getName()).log(Level.SEVERE, null, ex);
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
         PreparedStatement stmt = dao.conexao.prepareStatement("DELETE FROM Venda WHERE IdVenda = ?");
         stmt.setInt(1, pk);
         stmt.execute();
         stmt.close();
      }
      catch (SQLException ex)
      {
         Logger.getLogger(DaoVenda.class.getName()).log(Level.SEVERE, null, ex);
         throw new Exception("Falha ao Excluir o Registro - " + ex.getMessage());
      }
      finally
      {
         dao.desconectar();
      }
   }

   protected void setarCampos(Venda registro, ResultSet rs) throws SQLException, Exception
   {

      registro.setIdVenda(rs.getInt("IdVenda"));
      registro.setData(rs.getString("Data"));
      registro.setFormaPagto(rs.getString("formaPagto"));
      registro.setDesconto(rs.getDouble("desconto"));
      registro.setCliente(daoCliente.buscar(rs.getInt("cliente_Id_Cliente")));
      registro.setFuncionario(daoFuncionario.buscar(rs.getInt("funcionario_id_Funcionario"),""));
     
   }

   public Venda buscar(int IdVenda) throws Exception
   {

      Venda registro = new Venda();
      ResultSet rs;

      dao.conectar();

      try
      {

         PreparedStatement stmt = dao.conexao.prepareStatement("SELECT IdVenda, data, formaPagto, desconto, cliente_Id_Cliente, funcionario_Id_Funcionario" +
                                                               " FROM Venda WHERE IdVenda = ?");
         stmt.setInt(1, IdVenda);
         rs = stmt.executeQuery();

         if (rs.next())
         {
             setarCampos(registro, rs);
         }
      }
      catch (SQLException ex)
      {
         Logger.getLogger(DaoVenda.class.getName()).log(Level.SEVERE, null, ex);
         throw new Exception("Falha ao Buscar o Registro - " + ex.getMessage());
      }
      finally
      {
         dao.desconectar();
      }

      return registro;

   }

   public Vector <Venda> buscarTodos() throws Exception
   {

      Vector <Venda> lista = new Vector<Venda>();
      ResultSet rs;

      dao.conectar();

      try
      {
         PreparedStatement stmt = dao.conexao.prepareStatement("SELECT IdVenda, data, formaPagto, desconto, cliente_Id_Cliente, funcionario_Id_Funcionario" +
                                                               " FROM Venda ORDER BY IdVenda");
         rs = stmt.executeQuery();
         while (rs.next())
         {
            Venda registro = new Venda();
            setarCampos(registro, rs);
            lista.add(registro);
         }
         stmt.close();
      }
      catch (SQLException ex)
      {
         Logger.getLogger(DaoVenda.class.getName()).log(Level.SEVERE, null, ex);
         throw new Exception("Falha ao Selecionar os Registros - " + ex.getMessage());
      }
      finally
      {
         dao.desconectar();
      }

      return lista;

   }

}