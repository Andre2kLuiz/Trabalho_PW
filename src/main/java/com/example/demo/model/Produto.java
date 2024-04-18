package com.example.demo.model;

public class Produto {
    public Produto(int id, int preco, String nome, String descricao, int estoque) {
        super();
        this.id = id;
        this.preco = preco;
        this.nome = nome;
        this.Descricao = descricao;
        this.estoque = estoque;
        }
        public Produto(int preco, String nome, String desc, int estoque) {
            // Defina o 'id' como -1 ou outro valor padrão, se necessário
            this.id = -1;
            this.preco = preco;
            this.nome = nome;
            this.Descricao = desc;
            this.estoque = estoque;
        }
        int id;
        int preco;
        String nome;
        String Descricao;
        int estoque;       
        public int getId() {
        return id;
        }
        public void setId(int id) {
        this.id = id;
        }
        public int getPreco() {
        return preco;
        }
        public void setPreco(int preco) {
        this.preco = preco;
        }
        public String getNome() {
        return nome;
        }
        public void setNome(String nome) {
        this.nome = nome;
        }
        public String getDescricao() {
        return Descricao;
        }
        public void setDescricao(String descricao) {
        Descricao = descricao;
        }
        public int getEstoque() {
        return estoque;
        }
        public void incrementaEstoque() {
        this.estoque++;
        }
        public void diminuiEstoque() {
        this.estoque--;
        }
        public Produto get(int i) {
            // TODO Auto-generated method stub
            throw new UnsupportedOperationException("Unimplemented method 'get'");
        }
        
}
