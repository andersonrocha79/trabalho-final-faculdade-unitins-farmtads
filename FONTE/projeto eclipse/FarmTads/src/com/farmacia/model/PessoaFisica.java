/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.farmacia.model;

/**
 *
 * @author anderson
 */
public class PessoaFisica extends Pessoa
{
    private int Pessoa_idPessoa;
    private String cpf;
    private String rg;

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public String getRg() {
        return rg;
    }

    public void setRg(String rg) {
        this.rg = rg;
    }

   public int getPessoa_idPessoa() {
      return Pessoa_idPessoa;
   }

   public void setPessoa_idPessoa(int Pessoa_idPessoa) {
      this.Pessoa_idPessoa = Pessoa_idPessoa;
   }

}
