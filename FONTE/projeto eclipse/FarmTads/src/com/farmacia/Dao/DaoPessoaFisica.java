package com.farmacia.Dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.PreparedStatement;
import java.util.Vector;

import com.farmacia.model.PessoaFisica;
import java.util.logging.Level;
import java.util.logging.Logger;

public class DaoPessoaFisica
{

   private com.farmacia.Dao.Dao dao;

   public DaoPessoaFisica(Dao dao)
   {
      this.dao = dao;
   }

   public com.farmacia.Dao.Dao getDao() {
      return dao;
   }

   public void setDao(com.farmacia.Dao.Dao Dao) {
      this.dao = Dao;
   }

  public void incluir(PessoaFisica registro) throws Exception
   {

      dao.conectar();

      try
      {

         PreparedStatement stmt = dao.conexao.prepareStatement("INSERT INTO Pessoa_Fisica (Pessoa_idPessoa, cpf, rg) VALUES (?, ?, ?)");

         stmt.setInt(1, registro.getIdPessoa());
         stmt.setString(2, registro.getCpf());
         stmt.setString(3, registro.getRg());
         
         registro.setPessoa_idPessoa(registro.getIdPessoa());

         stmt.executeUpdate();
         stmt.close();
      }
      catch (SQLException ex)
      {
         Logger.getLogger(DaoPessoaFisica.class.getName()).log(Level.SEVERE, null, ex);
         throw new Exception("Falha ao Incluir o Registro - " + ex.getMessage());
      }
      finally
      {
         dao.desconectar();
      }
   }

   public void atualizar(PessoaFisica registro) throws Exception
   {

      dao.conectar();

      try
      {

         PreparedStatement stmt = dao.conexao.prepareStatement("UPDATE Pessoa_Fisica SET cpf = ?, rg = ? WHERE Pessoa_idPessoa = ?");

         stmt.setString(1, registro.getCpf());
         stmt.setString(2, registro.getRg());
         stmt.setInt(3, registro.getPessoa_idPessoa());

         stmt.executeUpdate();

         stmt.close();

      }
      catch (SQLException ex)
      {
         Logger.getLogger(DaoPessoaFisica.class.getName()).log(Level.SEVERE, null, ex);
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
         PreparedStatement stmt = dao.conexao.prepareStatement("DELETE FROM Pessoa_Fisica WHERE pessoa_idPessoa = ?");
         stmt.setInt(1, pk);
         stmt.execute();
         stmt.close();
      }
      catch (SQLException ex)
      {
         Logger.getLogger(DaoPessoaFisica.class.getName()).log(Level.SEVERE, null, ex);
         throw new Exception("Falha ao Excluir o Registro - " + ex.getMessage());
      }
      finally
      {
         dao.desconectar();
      }
   }

   private void setarCampos(PessoaFisica registro, ResultSet rs) throws SQLException
   {
      registro.setPessoa_idPessoa(rs.getInt("pessoa_IdPessoa"));
      registro.setCpf(rs.getString("cpf"));
      registro.setRg(rs.getString("rg"));
   }

   public PessoaFisica buscar(int IdPessoaFisica) throws Exception
   {

      PessoaFisica registro = new PessoaFisica();
      ResultSet rs;

      dao.conectar();

      try
      {

         PreparedStatement stmt = dao.conexao.prepareStatement("SELECT Pessoa_idPessoa, cpf, rg FROM Pessoa_Fisica WHERE Pessoa_idPessoa = ?");
         stmt.setInt(1, IdPessoaFisica);
         rs = stmt.executeQuery();

         if (rs.next())
         {
             setarCampos(registro, rs);
         }
      }
      catch (SQLException ex)
      {
         Logger.getLogger(DaoPessoaFisica.class.getName()).log(Level.SEVERE, null, ex);
         throw new Exception("Falha ao Buscar o Registro - " + ex.getMessage());
      }
      finally
      {
         dao.desconectar();
      }

      return registro;

   }

   public Vector <PessoaFisica> buscarTodos() throws Exception
   {

      Vector <PessoaFisica> lista = new Vector<PessoaFisica>();
      ResultSet rs;

      dao.conectar();

      try
      {
         PreparedStatement stmt = dao.conexao.prepareStatement("SELECT Pessoa_idPessoa, cpf, rg FROM Pessoa_Fisica order by Pessoa_idPessoa");
         rs = stmt.executeQuery();
         while (rs.next())
         {
            PessoaFisica registro = new PessoaFisica();
            setarCampos(registro, rs);
            lista.add(registro);
         }
         stmt.close();
      }
      catch (SQLException ex)
      {
         Logger.getLogger(DaoPessoaFisica.class.getName()).log(Level.SEVERE, null, ex);
         throw new Exception("Falha ao Selecionar os Registros - " + ex.getMessage());
      }
      finally
      {
         dao.desconectar();
      }

      return lista;

   }
 
}