package com.farmacia.Dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.PreparedStatement;
import java.util.Vector;

import com.farmacia.model.Entrada;
import java.util.logging.Level;
import java.util.logging.Logger;

public class DaoEntrada
{

   private Dao dao;
   private DaoFornecedor daoFornecedor;
   private DaoFuncionario daoFuncionario;

   public DaoEntrada(Dao dao)
   {
      this.dao = dao;
      this.daoFornecedor = new DaoFornecedor(dao);
      this.daoFuncionario = new DaoFuncionario(dao);
   }

   public com.farmacia.Dao.Dao getDao() {
      return dao;
   }

   public void setDao(com.farmacia.Dao.Dao Dao) {
      this.dao = Dao;
   }

   public void incluir(Entrada registro) throws Exception
   {

      dao.conectar();

      try
      {

         PreparedStatement stmt = dao.conexao.prepareStatement("INSERT INTO Entrada (idEntrada, data, Fornecedor_IdFornecedor, Funcionario_IdFuncionario)" +
                                                               " VALUES (NULL, ?, ?, ?)");

         stmt.setString(1, registro.getData());
         stmt.setInt(2, registro.getFornecedor().getIdFornecedor());
         stmt.setInt(3, registro.getFuncionario().getIdFuncionario());

         stmt.executeUpdate();
         registro.setIdEntrada(dao.getIdUltimaInclusao());
         stmt.close();
      }
      catch (SQLException ex)
      {
         Logger.getLogger(DaoEntrada.class.getName()).log(Level.SEVERE, null, ex);
         throw new Exception("Falha ao Incluir o Registro - " + ex.getMessage());
      }
      finally
      {
         dao.desconectar();
      }
   }

   public void atualizar(Entrada registro) throws Exception
   {

      dao.conectar();

      try
      {

         PreparedStatement stmt = dao.conexao.prepareStatement("UPDATE Entrada SET data = ?, Fornecedor_IdFornecedor = ?, Funcionario_IdFuncionario = ?" +
                                                               " WHERE IdEntrada = ?");

         stmt.setString(1, registro.getData());
         stmt.setInt(2, registro.getFornecedor().getIdFornecedor());
         stmt.setInt(3, registro.getFuncionario().getIdFuncionario());
         stmt.setInt(4, registro.getIdEntrada());

         stmt.executeUpdate();

         stmt.close();

      }
      catch (SQLException ex)
      {
         Logger.getLogger(DaoEntrada.class.getName()).log(Level.SEVERE, null, ex);
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
         PreparedStatement stmt = dao.conexao.prepareStatement("DELETE FROM Entrada WHERE IdEntrada = ?");
         stmt.setInt(1, pk);
         stmt.execute();
         stmt.close();
      }
      catch (SQLException ex)
      {
         Logger.getLogger(DaoEntrada.class.getName()).log(Level.SEVERE, null, ex);
         throw new Exception("Falha ao Excluir o Registro - " + ex.getMessage());
      }
      finally
      {
         dao.desconectar();
      }
   }

   protected void setarCampos(Entrada registro, ResultSet rs) throws SQLException, Exception
   {
      registro.setIdEntrada(rs.getInt("IdEntrada"));
      registro.setData(rs.getString("Data"));
      registro.setFuncionario(daoFuncionario.buscar(rs.getInt("Funcionario_IdFuncionario"),""));
      registro.setFornecedor(daoFornecedor.buscar(rs.getInt("Fornecedor_IdFornecedor")));
   }

   public Entrada buscar(int IdEntrada) throws Exception
   {

      Entrada registro = new Entrada();
      ResultSet rs;

      dao.iniciaTransacao();

      try
      {

         PreparedStatement stmt = dao.conexao.prepareStatement("SELECT IdEntrada, data, Fornecedor_IdFornecedor, Funcionario_IdFuncionario" +
                                                               " FROM Entrada WHERE IdEntrada = ?");
         stmt.setInt(1, IdEntrada);
         rs = stmt.executeQuery();

         if (rs.next())
         {
             setarCampos(registro, rs);
         }
      }
      catch (SQLException ex)
      {
         Logger.getLogger(DaoEntrada.class.getName()).log(Level.SEVERE, null, ex);
         throw new Exception("Falha ao Buscar o Registro - " + ex.getMessage());
      }
      finally
      {
         dao.finalizaTransacao(false);
      }

      return registro;

   }

   public Vector <Entrada> buscarTodos() throws Exception
   {

      Vector <Entrada> lista = new Vector<Entrada>();
      ResultSet rs;

      dao.iniciaTransacao();

      try
      {
         PreparedStatement stmt = dao.conexao.prepareStatement("SELECT IdEntrada, data, Fornecedor_IdFornecedor, Funcionario_IdFuncionario" +
                                                               " FROM Entrada ORDER BY IdEntrada");
         rs = stmt.executeQuery();
         while (rs.next())
         {
            Entrada registro = new Entrada();
            setarCampos(registro, rs);
            lista.add(registro);
         }
         stmt.close();
      }
      catch (SQLException ex)
      {
         Logger.getLogger(DaoEntrada.class.getName()).log(Level.SEVERE, null, ex);
         throw new Exception("Falha ao Selecionar os Registros - " + ex.getMessage());
      }
      finally
      {
         dao.finalizaTransacao(false);
      }

      return lista;

   }

}