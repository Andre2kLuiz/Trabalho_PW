package com.example.demo.DAO;

import java.net.URISyntaxException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.example.demo.Conexao;
import com.example.demo.model.Cliente;

public class ClienteDAO {

    public void adicionar(Cliente cliente) {    
        try (Connection  connection = Conexao.getConnection();        
        PreparedStatement stmt = connection.prepareStatement("INSERT INTO cliente (nome, email, senha) VALUES (?, ?, ?)")) {
        stmt.setString(1, cliente.getNome());
        stmt.setString(2, cliente.getEmail());
        stmt.setString(3, cliente.getSenha());

        stmt.executeUpdate();
        connection.close();

        } catch (SQLException | URISyntaxException e) {

            e.printStackTrace();
        }
    }

    public Cliente buscarPorNomeESenha(String nome, String senha) {
        Cliente c = null;
    
        try (Connection connection = Conexao.getConnection();
             PreparedStatement stmt = connection.prepareStatement("SELECT * FROM cliente WHERE nome = ? AND senha = ?")) {
            
            stmt.setString(1, nome);
            stmt.setString(2, senha);
            ResultSet rs = stmt.executeQuery();
    
            if (rs.next()) {
                c = new Cliente();
                c.setNome(rs.getString("nome"));
                c.setEmail(rs.getString("email"));
                c.setSenha(rs.getString("senha"));
            }
    
            rs.close();
            connection.close();
    
        } catch (SQLException | URISyntaxException e) {
            e.printStackTrace();
        }
    
        return c;
    }
    
}
