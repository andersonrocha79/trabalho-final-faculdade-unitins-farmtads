/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.farmacia.model;

/**
 *
 * @author anderson
 */
public class Venda
{
    private Integer idVenda;
    private String data;
    private String formaPagto;
    private double desconto;
    private Cliente cliente;
    private Funcionario funcionario;

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }

    public String getFormaPagto() {
        return formaPagto;
    }

    public void setFormaPagto(String formaPagto) {
        this.formaPagto = formaPagto;
    }

    public double getDesconto() {
        return desconto;
    }

    public void setDesconto(double desconto) {
        this.desconto = desconto;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    public Funcionario getFuncionario() {
        return funcionario;
    }

    public void setFuncionario(Funcionario funcionario) {
        this.funcionario = funcionario;
    }

   public Integer getIdVenda() {
      return idVenda;
   }

   public void setIdVenda(Integer idVenda) {
      this.idVenda = idVenda;
   }


}
