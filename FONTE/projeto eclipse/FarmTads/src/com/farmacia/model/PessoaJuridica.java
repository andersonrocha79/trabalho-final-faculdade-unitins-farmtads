/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.farmacia.model;

/**
 *
 * @author anderson
 */
public class PessoaJuridica extends Pessoa
{
    private int Pessoa_idPessoa;
    private String cnpj;
    private String razaoSocial;

   public int getPessoa_idPessoa() {
      return Pessoa_idPessoa;
   }

   public void setPessoa_idPessoa(int Pessoa_idPessoa) {
      this.Pessoa_idPessoa = Pessoa_idPessoa;
   }

   public String getCnpj() {
      return cnpj;
   }

   public void setCnpj(String cnpj) {
      this.cnpj = cnpj;
   }

   public String getRazaoSocial() {
      return razaoSocial;
   }

   public void setRazaoSocial(String razaoSocial) {
      this.razaoSocial = razaoSocial;
   }

}
