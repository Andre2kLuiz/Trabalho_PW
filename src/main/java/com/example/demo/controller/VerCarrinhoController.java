package com.example.demo.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Map;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import com.example.demo.DAO.ProdutoDAO;
import com.example.demo.model.Produto;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;   


@Controller
public class VerCarrinhoController {
    
    @GetMapping("/verCarrinho")
    public void verCarrinho(HttpServletRequest request, HttpServletResponse response) throws IOException {
        response.setContentType("text/html");
        PrintWriter writer = response.getWriter();

        // Cria uma instância de CarrinhoController
        CarrinhoController carrinhoController = new CarrinhoController();

        // Obtém o carrinho do cliente dos cookies usando o método de instância
        Map<Integer, Integer> carrinho = carrinhoController.getCarrinhoFromCookiesPublic(request);
        System.out.println("Carrinho do cliente: " + carrinho);

        writer.println("<html><body>");
        writer.println("<a href='/listaCli'>Voltar</a>");
        writer.println("<h1>Carrinho de Compras</h1>");
        writer.println("<table border='1'>");
        writer.println("<tr><th>Nome</th><th>Preço</th><th>Quantidade</th><th>Remover</th></tr>");

        // Verifica se o carrinho não está vazio
        if (carrinho != null && !carrinho.isEmpty()) {
            // Itera sobre os itens do carrinho e os exibe na tabela
            for (Map.Entry<Integer, Integer> entry : carrinho.entrySet()) {
                int productId = entry.getKey();
                int quantity = entry.getValue();

                ProdutoDAO pd = new ProdutoDAO();

                Produto produto = pd.buscarProdutoPorId(productId);

                // Exibir o produto na tabela
                writer.println("<tr>");
                writer.println("<td>" + produto.getNome() + "</td>");
                writer.println("<td>" + produto.getPreco() + "</td>");
                writer.println("<td>" + quantity + "</td>");
                writer.println("<td>" + "<a href='/verCarrinho?produtoId=" + produto.getId() + "&comando=remove'>Remover</a>");
                writer.println("</tr>");
            }
        } else {
            writer.println("<tr><td colspan='4'>Carrinho vazio</td></tr>");
        }

        writer.println("</table>");
        writer.println("</body></html>");
    }

}

