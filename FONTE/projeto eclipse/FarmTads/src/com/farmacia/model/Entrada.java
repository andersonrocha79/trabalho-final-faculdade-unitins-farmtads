/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.farmacia.model;

/**
 *
 * @author anderson
 */
public class Entrada
{
    private Integer idEntrada;
    private String data;
    private Fornecedor fornecedor;
    private Funcionario funcionario;

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }

    public Fornecedor getFornecedor() {
        return fornecedor;
    }

    public void setFornecedor(Fornecedor fornecedor) {
        this.fornecedor = fornecedor;
    }

    public Funcionario getFuncionario() {
        return funcionario;
    }

    public void setFuncionario(Funcionario funcionario) {
        this.funcionario = funcionario;
    }

   public Integer getIdEntrada() {
      return idEntrada;
   }

   public void setIdEntrada(Integer idEntrada) {
      this.idEntrada = idEntrada;
   }

}
