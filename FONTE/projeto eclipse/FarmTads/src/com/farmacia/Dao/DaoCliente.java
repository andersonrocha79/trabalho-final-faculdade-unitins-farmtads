package com.farmacia.Dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.PreparedStatement;
import java.util.Vector;

import com.farmacia.model.Cliente;
import java.util.logging.Level;
import java.util.logging.Logger;

public class DaoCliente
{

   private com.farmacia.Dao.Dao dao;

   public DaoCliente(Dao dao)
   {
      this.dao = dao;
   }

   public com.farmacia.Dao.Dao getDao() {
      return dao;
   }

   public void setDao(com.farmacia.Dao.Dao Dao) {
      this.dao = Dao;
   }

   public void incluir(Cliente registro) throws Exception
   {

      dao.conectar();

      try
      {

         PreparedStatement stmt = dao.conexao.prepareStatement("INSERT INTO Cliente (IdCliente, pontos, Pessoa_Fisica_Pessoa_idPessoa) VALUES (NULL, ?, ?)");

         stmt.setInt(1, registro.getPontos());
         stmt.setInt(2, registro.getIdPessoa());

         stmt.executeUpdate();
         registro.setIdCliente(dao.getIdUltimaInclusao());
         stmt.close();
      }
      catch (SQLException ex)
      {
         Logger.getLogger(DaoCliente.class.getName()).log(Level.SEVERE, null, ex);
         throw new Exception("Falha ao Incluir o Registro - " + ex.getMessage());
      }
      finally
      {
         dao.desconectar();
      }

   }

   public void atualizar(Cliente registro) throws Exception
   {

      dao.conectar();

      try
      {

         PreparedStatement stmt = dao.conexao.prepareStatement("UPDATE Cliente SET pontos = ?, Pessoa_Fisica_Pessoa_IdPessoa = ? WHERE IdCliente = ?");

         stmt.setInt(1, registro.getPontos());
         stmt.setInt(2, registro.getIdPessoa());
         stmt.setInt(3, registro.getIdCliente());

         stmt.executeUpdate();

         stmt.close();

      }
      catch (SQLException ex)
      {
         Logger.getLogger(DaoCliente.class.getName()).log(Level.SEVERE, null, ex);
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
         PreparedStatement stmt = dao.conexao.prepareStatement("DELETE FROM Cliente WHERE IdCliente = ?");
         stmt.setInt(1, pk);
         stmt.execute();
         stmt.close();
      }
      catch (SQLException ex)
      {
         Logger.getLogger(DaoCliente.class.getName()).log(Level.SEVERE, null, ex);
         throw new Exception("Falha ao Excluir o Registro - " + ex.getMessage());
      }
      finally
      {
         dao.desconectar();
      }
   }

   private void setarCampos(Cliente registro, ResultSet rs) throws SQLException
   {

      // Pessoa
      registro.setIdPessoa(rs.getInt("IdPessoa"));
      registro.setNome(rs.getString("nome"));
      registro.setTelefone(rs.getString("telefone"));
      registro.setEmail(rs.getString("email"));

      // Pessoa Fisica
      registro.setPessoa_idPessoa(rs.getInt("pessoa_IdPessoa"));
      registro.setRg(rs.getString("rg"));
      registro.setCpf(rs.getString("cpf"));

      // Cliente
      registro.setIdCliente(rs.getInt("IdCliente"));
      registro.setPontos(rs.getInt("pontos"));

   }

   public Cliente buscar(int IdCliente) throws Exception
   {

      Cliente registro = new Cliente();
      ResultSet rs;

      dao.conectar();

      try
      {

         PreparedStatement stmt = dao.conexao.prepareStatement("SELECT IdCliente, pontos, Pessoa_Fisica_Pessoa_IdPessoa,"    +
                                                               " pessoa_IdPessoa, cpf, rg, idPessoa, nome, telefone, email"  +
                                                               " FROM Cliente" +
                                                               " INNER JOIN Pessoa_Fisica on Pessoa_Fisica_Pessoa_IdPessoa = pessoa_IdPessoa" +
                                                               " INNER JOIN Pessoa on pessoa_IdPessoa = IdPessoa" +
                                                               " WHERE IdCliente = ?");
         stmt.setInt(1, IdCliente);
         rs = stmt.executeQuery();

         if (rs.next())
         {
             setarCampos(registro, rs);
         }
      }
      catch (SQLException ex)
      {
         Logger.getLogger(DaoCliente.class.getName()).log(Level.SEVERE, null, ex);
         throw new Exception("Falha ao Buscar o Registro - " + ex.getMessage());
      }
      finally
      {
         dao.desconectar();
      }

      return registro;

   }
   
   public Vector <Cliente> buscarTodos() throws Exception
   {

      Vector <Cliente> lista = new Vector<Cliente>();
      ResultSet rs;

      dao.conectar();

      try
      {

         PreparedStatement stmt = dao.conexao.prepareStatement("SELECT IdCliente, pontos, Pessoa_Fisica_Pessoa_IdPessoa,"    +
                                                               " pessoa_IdPessoa, cpf, rg, idPessoa, nome, telefone, email"  +
                                                               " FROM Cliente" +
                                                               " INNER JOIN Pessoa_Fisica on Pessoa_Fisica_Pessoa_IdPessoa = pessoa_IdPessoa" +
                                                               " INNER JOIN Pessoa on pessoa_IdPessoa = IdPessoa" +
                                                               " ORDER BY nome");
         rs = stmt.executeQuery();
         while (rs.next())
         {
            Cliente registro = new Cliente();
            setarCampos(registro, rs);
            lista.add(registro);
         }
         stmt.close();
      }
      catch (SQLException ex)
      {
         Logger.getLogger(DaoCliente.class.getName()).log(Level.SEVERE, null, ex);
         throw new Exception("Falha ao Selecionar os Registros - " + ex.getMessage());
      }
      finally
      {
         dao.desconectar();
      }

      return lista;

   }
   
}