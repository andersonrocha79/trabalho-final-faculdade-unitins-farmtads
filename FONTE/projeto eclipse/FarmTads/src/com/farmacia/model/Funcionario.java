/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.farmacia.model;

/**
 *
 * @author anderson
 */
public class Funcionario extends PessoaFisica
{
    private int IdFuncionario;
    private String endereco;
    private String senha;
    private boolean administrador;

    public String getEndereco() {
        return endereco;
    }

    public void setEndereco(String endereco) {
        this.endereco = endereco;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    public boolean isAdministrador() {
        return administrador;
    }

    public void setAdministrador(boolean administrador) {
        this.administrador = administrador;
    }

   public int getIdFuncionario() {
      return IdFuncionario;
   }

   public void setIdFuncionario(int IdFuncionario) {
      this.IdFuncionario = IdFuncionario;
   }

}
