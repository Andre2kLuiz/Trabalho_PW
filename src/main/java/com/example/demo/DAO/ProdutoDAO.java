package com.example.demo.DAO;

import java.net.URISyntaxException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import com.example.demo.Conexao;
import com.example.demo.model.Produto;

public class ProdutoDAO {
    public void cadastrarProduto(Produto p) {
        try (Connection connection = Conexao.getConnection();
             PreparedStatement stmt = connection.prepareStatement("INSERT INTO produtos (preco, nome, descricao, estoque) VALUES (?, ?, ?, ?)")) {
            
            stmt.setInt(1, p.getPreco());
            stmt.setString(2, p.getNome());
            stmt.setString(3, p.getDescricao());
            stmt.setInt(4, p.getEstoque());

            stmt.executeUpdate();
        } catch (SQLException | URISyntaxException e) {
            e.printStackTrace();
        }
    }

    public Produto buscarProdutoPorNome(String nome) {
        try (Connection connection = Conexao.getConnection();
             PreparedStatement stmt = connection.prepareStatement("SELECT * FROM produtos WHERE nome = ?")) {
            stmt.setString(1, nome);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                return new Produto(rs.getInt("id"), rs.getInt("preco"),
                        rs.getString("nome"), rs.getString("descricao"), rs.getInt("estoque"));
            }
        } catch (SQLException | URISyntaxException e) {
            e.printStackTrace();
        }
        return null; 
    }

    public Produto buscarProdutoPorId(int id) {
        try (Connection connection = Conexao.getConnection();
             PreparedStatement stmt = connection.prepareStatement("SELECT * FROM produtos WHERE id = ?")) {
            stmt.setInt(1, id);
            ResultSet rs = stmt.executeQuery();
    
            if (rs.next()) {
                return new Produto(rs.getInt(id), rs.getInt("preco"),
                        rs.getString("nome"), rs.getString("descricao"), rs.getInt("estoque"));
            }
        } catch (SQLException | URISyntaxException e) {
            e.printStackTrace();
        }
        return null; 
    }
    

    public ArrayList<Produto> buscarTodosProdutos() {
        ArrayList<Produto> produtos = new ArrayList<>();
        try (Connection connection = Conexao.getConnection();
             PreparedStatement stmt = connection.prepareStatement("SELECT * FROM produtos")) {
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                produtos.add(new Produto(rs.getInt("id"), rs.getInt("preco"),
                        rs.getString("nome"), rs.getString("descricao"), rs.getInt("estoque")));
            }
        } catch (SQLException | URISyntaxException e) {
            e.printStackTrace();
        }
        return produtos;
    }

    public void removerDoEstoque(String produtoId, int quantidade) {
        try (Connection connection = Conexao.getConnection();
             PreparedStatement stmt = connection.prepareStatement("UPDATE produtos SET estoque = estoque - ? WHERE nome = ?")) {
            
            stmt.setInt(1, quantidade);
            stmt.setString(2, produtoId);

            stmt.executeUpdate();
        } catch (SQLException | URISyntaxException e) {
            e.printStackTrace();
        }
    }

    public void adicionarAoEstoque(String produtoId, int quantidade) {
        try (Connection connection = Conexao.getConnection();
             PreparedStatement stmt = connection.prepareStatement("UPDATE produtos SET estoque = estoque + ? WHERE nome = ?")) {
            
            stmt.setInt(1, quantidade);
            stmt.setString(2, produtoId);

            stmt.executeUpdate();
        } catch (SQLException | URISyntaxException e) {
            e.printStackTrace();
        }
    }

}
