/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.farmacia.model;

/**
 *
 * @author anderson
 */
public class ItemVenda
{
    private Integer IdVenda;
    private Produto produto;
    private int quantidade;
    private Double valorVenda;

    public int getQuantidade() {
        return quantidade;
    }

    public void setQuantidade(int quantidade) {
        this.quantidade = quantidade;
    }

    public Produto getProduto() {
        return produto;
    }

    public void setProduto(Produto produto) {
        this.produto = produto;
    }

   public Integer getIdVenda() {
      return IdVenda;
   }

   public void setIdVenda(Integer IdVenda) {
      this.IdVenda = IdVenda;
   }

   public Double getValorVenda() {
      return valorVenda;
   }

   public void setValorVenda(Double valorVenda) {
      this.valorVenda = valorVenda;
   }

}
