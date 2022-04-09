/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.farmacia.model;

/**
 *
 * @author anderson
 */
public class Fornecedor extends PessoaJuridica
{
    private int idFornecedor;
    private String contato;

    public String getContato() {
        return contato;
    }

    public void setContato(String contato) {
        this.contato = contato;
    }

   public int getIdFornecedor() {
      return idFornecedor;
   }

   public void setIdFornecedor(int idFornecedor) {
      this.idFornecedor = idFornecedor;
   }
}
