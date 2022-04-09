package com.farmacia.Dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.PreparedStatement;
import java.util.Vector;

import com.farmacia.model.Fornecedor;
import java.util.logging.Level;
import java.util.logging.Logger;

public class DaoFornecedor
{

   private com.farmacia.Dao.Dao dao;

   public DaoFornecedor(Dao dao)
   {
      this.dao = dao;
   }

   public com.farmacia.Dao.Dao getDao() {
      return dao;
   }

   public void setDao(com.farmacia.Dao.Dao Dao) {
      this.dao = Dao;
   }

   public void incluir(Fornecedor registro) throws Exception
   {

      dao.conectar();

      try
      {

         PreparedStatement stmt = dao.conexao.prepareStatement("INSERT INTO Fornecedor (IdFornecedor, contato, Pessoa_Juridica_Pessoa_idPessoa) VALUES (NULL, ?, ?)");

         stmt.setString(1, registro.getContato());
         stmt.setInt(2, registro.getIdPessoa());

         stmt.executeUpdate();
         registro.setIdFornecedor(dao.getIdUltimaInclusao());
         stmt.close();
      }
      catch (SQLException ex)
      {
         Logger.getLogger(DaoFornecedor.class.getName()).log(Level.SEVERE, null, ex);
         throw new Exception("Falha ao Incluir o Registro - " + ex.getMessage());
      }
      finally
      {
         dao.desconectar();
      }

   }

   public void atualizar(Fornecedor registro) throws Exception
   {

      dao.conectar();

      try
      {

         PreparedStatement stmt = dao.conexao.prepareStatement("UPDATE Fornecedor SET contato = ?, Pessoa_Juridica_Pessoa_IdPessoa = ? WHERE IdFornecedor = ?");

         stmt.setString(1, registro.getContato());
         stmt.setInt(2, registro.getIdPessoa());
         stmt.setInt(3, registro.getIdFornecedor());

         stmt.executeUpdate();

         stmt.close();

      }
      catch (SQLException ex)
      {
         Logger.getLogger(DaoFornecedor.class.getName()).log(Level.SEVERE, null, ex);
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
         PreparedStatement stmt = dao.conexao.prepareStatement("DELETE FROM Fornecedor WHERE IdFornecedor = ?");
         stmt.setInt(1, pk);
         stmt.execute();
         stmt.close();
      }
      catch (SQLException ex)
      {
         Logger.getLogger(DaoFornecedor.class.getName()).log(Level.SEVERE, null, ex);
         throw new Exception("Falha ao Excluir o Registro - " + ex.getMessage());
      }
      finally
      {
         dao.desconectar();
      }
   }

   private void setarCampos(Fornecedor registro, ResultSet rs) throws SQLException
   {

      // Pessoa
      registro.setIdPessoa(rs.getInt("IdPessoa"));
      registro.setNome(rs.getString("nome"));
      registro.setTelefone(rs.getString("telefone"));
      registro.setEmail(rs.getString("email"));

      // Pessoa Juridica
      registro.setPessoa_idPessoa(rs.getInt("pessoa_IdPessoa"));
      registro.setCnpj(rs.getString("cnpj"));
      registro.setRazaoSocial(rs.getString("razaoSocial"));

      // Fornecedor
      registro.setIdFornecedor(rs.getInt("IdFornecedor"));
      registro.setContato(rs.getString("contato"));

   }

   public Fornecedor buscar(int IdFornecedor) throws Exception
   {

      Fornecedor registro = new Fornecedor();
      ResultSet rs;

      dao.conectar();

      try
      {

         PreparedStatement stmt = dao.conexao.prepareStatement("SELECT IdFornecedor, contato, Pessoa_Juridica_Pessoa_IdPessoa,"    +
                                                               " pessoa_IdPessoa, cnpj, razaoSocial, idPessoa, nome, telefone, email"  +
                                                               " FROM Fornecedor" +
                                                               " INNER JOIN Pessoa_Juridica on Pessoa_Juridica_Pessoa_IdPessoa = pessoa_IdPessoa" +
                                                               " INNER JOIN Pessoa on pessoa_IdPessoa = IdPessoa" +
                                                               " WHERE IdFornecedor = ?");
         stmt.setInt(1, IdFornecedor);
         rs = stmt.executeQuery();

         if (rs.next())
         {
             setarCampos(registro, rs);
         }
      }
      catch (SQLException ex)
      {
         Logger.getLogger(DaoFornecedor.class.getName()).log(Level.SEVERE, null, ex);
         throw new Exception("Falha ao Buscar o Registro - " + ex.getMessage());
      }
      finally
      {
         dao.desconectar();
      }

      return registro;

   }

   public Vector <Fornecedor> buscarTodos() throws Exception
   {

      Vector <Fornecedor> lista = new Vector<Fornecedor>();
      ResultSet rs;

      dao.conectar();

      try
      {

         PreparedStatement stmt = dao.conexao.prepareStatement("SELECT IdFornecedor, contato, Pessoa_Juridica_Pessoa_IdPessoa,"    +
                                                               " pessoa_IdPessoa, cnpj, razaoSocial, idPessoa, nome, telefone, email"  +
                                                               " FROM Fornecedor" +
                                                               " INNER JOIN Pessoa_Juridica on Pessoa_Juridica_Pessoa_IdPessoa = pessoa_IdPessoa" +
                                                               " INNER JOIN Pessoa on pessoa_IdPessoa = IdPessoa" +
                                                               " ORDER BY nome");
         rs = stmt.executeQuery();
         while (rs.next())
         {
            Fornecedor registro = new Fornecedor();
            setarCampos(registro, rs);
            lista.add(registro);
         }
         stmt.close();
      }
      catch (SQLException ex)
      {
         Logger.getLogger(DaoFornecedor.class.getName()).log(Level.SEVERE, null, ex);
         throw new Exception("Falha ao Selecionar os Registros - " + ex.getMessage());
      }
      finally
      {
         dao.desconectar();
      }

      return lista;

   }

}