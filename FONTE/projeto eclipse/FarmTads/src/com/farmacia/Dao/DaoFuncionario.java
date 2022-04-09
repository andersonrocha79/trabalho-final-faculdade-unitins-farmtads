package com.farmacia.Dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.PreparedStatement;
import java.util.Vector;

import com.farmacia.model.Funcionario;
import java.util.logging.Level;
import java.util.logging.Logger;

public class DaoFuncionario 
{

   private com.farmacia.Dao.Dao dao;

   public DaoFuncionario(Dao dao)
   {
      this.dao = dao;
   }

   public com.farmacia.Dao.Dao getDao() {
      return dao;
   }

   public void setDao(com.farmacia.Dao.Dao Dao) {
      this.dao = Dao;
   }

   public void incluir(Funcionario registro) throws Exception
   {

      dao.conectar();

      try
      {

         PreparedStatement stmt = dao.conexao.prepareStatement("INSERT INTO Funcionario (IdFuncionario, endereco, senha, administrador, Pessoa_Fisica_Pessoa_idPessoa) VALUES (NULL, ?, ?, ?, ?)");

         stmt.setString(1, registro.getEndereco());
         stmt.setString(2, registro.getSenha());
         stmt.setBoolean(3, registro.isAdministrador());
         stmt.setInt(4, registro.getIdPessoa());

         stmt.executeUpdate();
         registro.setIdFuncionario(dao.getIdUltimaInclusao());
         stmt.close();
      }
      catch (SQLException ex)
      {
         Logger.getLogger(DaoFuncionario.class.getName()).log(Level.SEVERE, null, ex);
         throw new Exception("Falha ao Incluir o Registro - " + ex.getMessage());
      }
      finally
      {
         dao.desconectar();
      }

   }

   public void atualizar(Funcionario registro) throws Exception
   {

      dao.conectar();

      try
      {

         PreparedStatement stmt = dao.conexao.prepareStatement("UPDATE Funcionario SET endereco = ?, senha = ?, administrador = ?, Pessoa_Fisica_Pessoa_IdPessoa = ? WHERE IdFuncionario = ?");

         stmt.setString(1, registro.getEndereco());
         stmt.setString(2, registro.getSenha());
         stmt.setBoolean(3, registro.isAdministrador());
         stmt.setInt(4, registro.getIdPessoa());
         stmt.setInt(5, registro.getIdFuncionario());

         stmt.executeUpdate();

         stmt.close();

      }
      catch (SQLException ex)
      {
         Logger.getLogger(DaoFuncionario.class.getName()).log(Level.SEVERE, null, ex);
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
         PreparedStatement stmt = dao.conexao.prepareStatement("DELETE FROM Funcionario WHERE IdFuncionario = ?");
         stmt.setInt(1, pk);
         stmt.execute();
         stmt.close();
      }
      catch (SQLException ex)
      {
         Logger.getLogger(DaoFuncionario.class.getName()).log(Level.SEVERE, null, ex);
         throw new Exception("Falha ao Excluir o Registro - " + ex.getMessage());
      }
      finally
      {
         dao.desconectar();
      }
   }

   private void setarCampos(Funcionario registro, ResultSet rs) throws SQLException
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

      // Funcionario
      registro.setIdFuncionario(rs.getInt("IdFuncionario"));
      registro.setEndereco(rs.getString("endereco"));
      registro.setSenha(rs.getString("senha"));
      registro.setAdministrador(rs.getBoolean("administrador"));

   }

   /**
    * Busca o Funcionario pelos Campos que são Chave (Id ou email)
    * @param idFuncionario : PK da Tabela de funcionario
    * @param email         : email do funcionario
   */
   public Funcionario buscar(int IdFuncionario, String email) throws Exception
   {

      Funcionario registro = new Funcionario();
      ResultSet rs;
      String clausulaWhere;

      dao.conectar();

      try
      {

         // Define se pesquisa pelo id ou email
         if (IdFuncionario > 0)
            clausulaWhere = " WHERE IdFuncionario = " + Integer.toString(IdFuncionario);
         else
            clausulaWhere = " WHERE email = '"        + email + "'";

         PreparedStatement stmt = dao.conexao.prepareStatement("SELECT IdFuncionario, endereco, senha, administrador," +
                                                               " Pessoa_Fisica_Pessoa_IdPessoa, pessoa_IdPessoa, cpf, rg, idPessoa, nome, telefone, email" +
                                                               " FROM Funcionario" +
                                                               " INNER JOIN Pessoa_Fisica on Pessoa_Fisica_Pessoa_IdPessoa = pessoa_IdPessoa" +
                                                               " INNER JOIN Pessoa on pessoa_IdPessoa = IdPessoa" +
                                                               clausulaWhere);
         rs = stmt.executeQuery();

         if (rs.next())
         {
             setarCampos(registro, rs);
         }
      }
      catch (SQLException ex)
      {
         Logger.getLogger(DaoFuncionario.class.getName()).log(Level.SEVERE, null, ex);
         throw new Exception("Falha ao Buscar o Registro - " + ex.getMessage());
      }
      finally
      {
         dao.desconectar();
      }

      return registro;

   }

   public Vector <Funcionario> buscarTodos() throws Exception
   {

      Vector <Funcionario> lista = new Vector<Funcionario>();
      ResultSet rs;

      dao.conectar();

      try
      {
         PreparedStatement stmt = dao.conexao.prepareStatement("SELECT IdFuncionario, endereco, senha, administrador," +
                                                               " Pessoa_Fisica_Pessoa_IdPessoa, pessoa_IdPessoa, cpf, rg, idPessoa, nome, telefone, email" +
                                                               " FROM Funcionario" +
                                                               " INNER JOIN Pessoa_Fisica on Pessoa_Fisica_Pessoa_IdPessoa = pessoa_IdPessoa" +
                                                               " INNER JOIN Pessoa on pessoa_IdPessoa = IdPessoa" +
                                                               " ORDER BY nome");

         rs = stmt.executeQuery();
         while (rs.next())
         {
            Funcionario registro = new Funcionario();
            setarCampos(registro, rs);
            lista.add(registro);
         }
         stmt.close();
      }
      catch (SQLException ex)
      {
         Logger.getLogger(DaoFuncionario.class.getName()).log(Level.SEVERE, null, ex);
         throw new Exception("Falha ao Selecionar os Registros - " + ex.getMessage());
      }
      finally
      {
         dao.desconectar();
      }

      return lista;

   }

}