package com.farmacia.Dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.PreparedStatement;
import java.util.Vector;

import com.farmacia.model.Pessoa;
import java.util.logging.Level;
import java.util.logging.Logger;

public class DaoPessoa
{

   private com.farmacia.Dao.Dao dao;

   public DaoPessoa(Dao dao)
   {
      this.dao = dao;
   }

   public com.farmacia.Dao.Dao getDao() {
      return dao;
   }

   public void setDao(com.farmacia.Dao.Dao Dao) {
      this.dao = Dao;
   }

   public void incluir(Pessoa registro) throws Exception
   {

      dao.conectar();

      try
      {

         PreparedStatement stmt = dao.conexao.prepareStatement("INSERT INTO Pessoa (idPessoa, nome, telefone, email) VALUES (NULL, ?, ?, ?)");

         stmt.setString(1, registro.getNome());
         stmt.setString(2, registro.getTelefone());
         stmt.setString(3, registro.getEmail());

         stmt.executeUpdate();
         registro.setIdPessoa(dao.getIdUltimaInclusao());
         stmt.close();
      }
      catch (SQLException ex)
      {
         Logger.getLogger(DaoPessoa.class.getName()).log(Level.SEVERE, null, ex);
         throw new Exception("Falha ao Incluir o Registro - " + ex.getMessage());
      }
      finally
      {
         dao.desconectar();
      }
   }

   public void atualizar(Pessoa registro) throws Exception
   {

      dao.conectar();

      try
      {

         PreparedStatement stmt = dao.conexao.prepareStatement("UPDATE Pessoa SET nome = ?, telefone = ?, email = ? WHERE idPessoa = ?");

         stmt.setString(1, registro.getNome());
         stmt.setString(2, registro.getTelefone());
         stmt.setString(3, registro.getEmail());
         stmt.setInt(4, registro.getIdPessoa());

         stmt.executeUpdate();

         stmt.close();

      }
      catch (SQLException ex)
      {
         Logger.getLogger(DaoPessoa.class.getName()).log(Level.SEVERE, null, ex);
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
         PreparedStatement stmt = dao.conexao.prepareStatement("DELETE FROM Pessoa WHERE IdPessoa = ?");
         stmt.setInt(1, pk);
         stmt.execute();
         stmt.close();
      }
      catch (SQLException ex)
      {
         Logger.getLogger(DaoPessoa.class.getName()).log(Level.SEVERE, null, ex);
         throw new Exception("Falha ao Excluir o Registro - " + ex.getMessage());
      }
      finally
      {
         dao.desconectar();
      }
   }

   protected void setarCampos(Pessoa registro, ResultSet rs) throws SQLException
   {
      registro.setIdPessoa(rs.getInt("idPessoa"));
      registro.setNome(rs.getString("nome"));
      registro.setTelefone(rs.getString("telefone"));
      registro.setEmail(rs.getString("email"));      
   }

   public Pessoa buscar(int IdPessoa) throws Exception
   {

      Pessoa registro = new Pessoa();
      ResultSet rs;

      dao.conectar();

      try
      {

         PreparedStatement stmt = dao.conexao.prepareStatement("SELECT idPessoa, nome, telefone, email FROM Pessoa WHERE idPessoa = ?");
         stmt.setInt(1, IdPessoa);
         rs = stmt.executeQuery();

         if (rs.next())
         {
             setarCampos(registro, rs);
         }
      }
      catch (SQLException ex)
      {
         Logger.getLogger(DaoPessoa.class.getName()).log(Level.SEVERE, null, ex);
         throw new Exception("Falha ao Buscar o Registro - " + ex.getMessage());
      }
      finally
      {
         dao.desconectar();
      }

      return registro;

   }

   public Vector <Pessoa> buscarTodos() throws Exception
   {

      Vector <Pessoa> lista = new Vector<Pessoa>();
      ResultSet rs;

      dao.conectar();

      try 
      {
         PreparedStatement stmt = dao.conexao.prepareStatement("SELECT idPessoa, nome, telefone, email FROM Pessoa order by idPessoa");
         rs = stmt.executeQuery();
         while (rs.next())
         {
            Pessoa registro = new Pessoa();
            setarCampos(registro, rs);
            lista.add(registro);
         }
         stmt.close();
      } 
      catch (SQLException ex)
      {
         Logger.getLogger(DaoPessoa.class.getName()).log(Level.SEVERE, null, ex);
         throw new Exception("Falha ao Selecionar os Registros - " + ex.getMessage());
      }
      finally
      {
         dao.desconectar();
      }

      return lista;

   }

}