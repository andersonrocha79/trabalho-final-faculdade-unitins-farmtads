/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.farmacia.model;

/**
 *
 * @author anderson
 */
public class ItemEntrada
{
    private int idEntrada;
    private int quantidade;
    private double valorCompra;
    private Produto produto;

    public int getQuantidade() {
        return quantidade;
    }

    public void setQuantidade(int quantidade) {
        this.quantidade = quantidade;
    }

    public double getValorCompra() {
        return valorCompra;
    }

    public void setValorCompra(double valorCompra) {
        this.valorCompra = valorCompra;
    }

    public Produto getProduto() {
        return produto;
    }

    public void setProduto(Produto produto) {
        this.produto = produto;
    }

   public int getIdEntrada() {
      return idEntrada;
   }

   public void setIdEntrada(int idEntrada) {
      this.idEntrada = idEntrada;
   }

}
