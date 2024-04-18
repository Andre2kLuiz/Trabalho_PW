package com.example.demo.model;

import java.util.ArrayList;

public class Carrinho {
    public Carrinho(ArrayList<Produto> produtos) {
        super();
        this.produtos = produtos;
    }

        ArrayList<Produto> produtos;

        public ArrayList<Produto> getProdutos() {
            return produtos;
        }

        public void setProdutos(ArrayList<Produto> produtos) {
            this.produtos = produtos;
        }

        public Produto getProduto (String produtoId){
            Produto mp = null;
            for (Produto p : produtos){
                if (p.getNome() == produtoId){
                return p;
                }
            }
            return mp;
        }

        public void removeProduto (String id){
            Produto p = getProduto(id);
            produtos.remove(p);
        }

        public void addProduto (Produto p){
            produtos.add(p);
        }
        
}
