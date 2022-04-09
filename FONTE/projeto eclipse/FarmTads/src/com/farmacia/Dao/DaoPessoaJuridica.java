package com.farmacia.Dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.PreparedStatement;
import java.util.Vector;

import com.farmacia.model.PessoaJuridica;
import java.util.logging.Level;
import java.util.logging.Logger;

public class DaoPessoaJuridica
{

   private com.farmacia.Dao.Dao dao;

   public DaoPessoaJuridica(Dao dao)
   {
      this.dao = dao;
   }

   public com.farmacia.Dao.Dao getDao() {
      return dao;
   }

   public void setDao(com.farmacia.Dao.Dao Dao) {
      this.dao = Dao;
   }

  public void incluir(PessoaJuridica registro) throws Exception
   {

      dao.conectar();

      try
      {

         PreparedStatement stmt = dao.conexao.prepareStatement("INSERT INTO Pessoa_Juridica (Pessoa_idPessoa, cnpj, razaoSocial) VALUES (?, ?, ?)");

         stmt.setInt(1, registro.getIdPessoa());
         stmt.setString(2, registro.getCnpj());
         stmt.setString(3, registro.getRazaoSocial());

         registro.setPessoa_idPessoa(registro.getIdPessoa());

         stmt.executeUpdate();
         stmt.close();
      }
      catch (SQLException ex)
      {
         Logger.getLogger(DaoPessoaJuridica.class.getName()).log(Level.SEVERE, null, ex);
         throw new Exception("Falha ao Incluir o Registro - " + ex.getMessage());
      }
      finally
      {
         dao.desconectar();
      }
   }

   public void atualizar(PessoaJuridica registro) throws Exception
   {

      dao.conectar();

      try
      {

         PreparedStatement stmt = dao.conexao.prepareStatement("UPDATE Pessoa_Juridica SET cnpj = ?, razaoSocial = ? WHERE Pessoa_idPessoa = ?");

         stmt.setString(1, registro.getCnpj());
         stmt.setString(2, registro.getRazaoSocial());
         stmt.setInt(3, registro.getPessoa_idPessoa());

         stmt.executeUpdate();

         stmt.close();

      }
      catch (SQLException ex)
      {
         Logger.getLogger(DaoPessoaJuridica.class.getName()).log(Level.SEVERE, null, ex);
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
         PreparedStatement stmt = dao.conexao.prepareStatement("DELETE FROM Pessoa_Juridica WHERE pessoa_idPessoa = ?");
         stmt.setInt(1, pk);
         stmt.execute();
         stmt.close();
      }
      catch (SQLException ex)
      {
         Logger.getLogger(DaoPessoaJuridica.class.getName()).log(Level.SEVERE, null, ex);
         throw new Exception("Falha ao Excluir o Registro - " + ex.getMessage());
      }
      finally
      {
         dao.desconectar();
      }
   }

   private void setarCampos(PessoaJuridica registro, ResultSet rs) throws SQLException
   {
      registro.setPessoa_idPessoa(rs.getInt("pessoa_IdPessoa"));
      registro.setCnpj(rs.getString("cnpj"));
      registro.setRazaoSocial(rs.getString("razaoSocial"));
   }

   public PessoaJuridica buscar(int IdPessoaJuridica) throws Exception
   {

      PessoaJuridica registro = new PessoaJuridica();
      ResultSet rs;

      dao.conectar();

      try
      {

         PreparedStatement stmt = dao.conexao.prepareStatement("SELECT Pessoa_idPessoa, cnpj, razaoSocial FROM Pessoa_Juridica WHERE Pessoa_idPessoa = ?");
         stmt.setInt(1, IdPessoaJuridica);
         rs = stmt.executeQuery();

         if (rs.next())
         {
             setarCampos(registro, rs);
         }
      }
      catch (SQLException ex)
      {
         Logger.getLogger(DaoPessoaJuridica.class.getName()).log(Level.SEVERE, null, ex);
         throw new Exception("Falha ao Buscar o Registro - " + ex.getMessage());
      }
      finally
      {
         dao.desconectar();
      }

      return registro;

   }

   public Vector <PessoaJuridica> buscarTodos() throws Exception
   {

      Vector <PessoaJuridica> lista = new Vector<PessoaJuridica>();
      ResultSet rs;

      dao.conectar();

      try
      {
         PreparedStatement stmt = dao.conexao.prepareStatement("SELECT Pessoa_idPessoa, cnpj, razaoSocial FROM Pessoa_Juridica order by Pessoa_idPessoa");
         rs = stmt.executeQuery();
         while (rs.next())
         {
            PessoaJuridica registro = new PessoaJuridica();
            setarCampos(registro, rs);
            lista.add(registro);
         }
         stmt.close();
      }
      catch (SQLException ex)
      {
         Logger.getLogger(DaoPessoaJuridica.class.getName()).log(Level.SEVERE, null, ex);
         throw new Exception("Falha ao Selecionar os Registros - " + ex.getMessage());
      }
      finally
      {
         dao.desconectar();
      }

      return lista;

   }

}