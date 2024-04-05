package com.example.demo.DAO;

import java.net.URISyntaxException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import com.example.demo.Conexao;
import com.example.demo.model.Lojista;

public class LojistaDAO {

    public void adicionar(Lojista lojista) {
        
        try (Connection  connection = Conexao.getConnection();        
            PreparedStatement stmt = connection.prepareStatement("INSERT INTO lojistas (nome, email, senha) VALUES (?, ?, ?)")) {
            stmt.setString(1, lojista.getNome());
            stmt.setString(2, lojista.getEmail());
            stmt.setString(3, lojista.getSenha());

            stmt.executeUpdate();
            connection.close();

        } catch (SQLException | URISyntaxException e) {

            e.printStackTrace();
        }
    }

    public Lojista buscarPorNomeESenha(String nome, String senha) {
        Lojista lojista = null;
    
        try (Connection connection = Conexao.getConnection();
             PreparedStatement stmt = connection.prepareStatement("SELECT * FROM lojistas WHERE nome = ? AND senha = ?")) {
            
            stmt.setString(1, nome);
            stmt.setString(2, senha);
            ResultSet rs = stmt.executeQuery();
    
            if (rs.next()) {
                lojista = new Lojista();
                lojista.setNome(rs.getString("nome"));
                lojista.setEmail(rs.getString("email"));
                lojista.setSenha(rs.getString("senha"));
            }
    
            rs.close();
            connection.close();
    
        } catch (SQLException | URISyntaxException e) {
            e.printStackTrace();
        }
    
        return lojista;
    }
    


}
