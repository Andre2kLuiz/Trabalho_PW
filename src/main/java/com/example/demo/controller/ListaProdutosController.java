package com.example.demo.controller;

import java.io.IOException;
import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.example.demo.DAO.ProdutoDAO;
import com.example.demo.model.Produto;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;


@Controller
public class ListaProdutosController {
    @RequestMapping(value= "/lista", method = RequestMethod.GET)
    public void listarProduto(HttpServletRequest request, HttpServletResponse response) throws IOException {
        HttpSession session = request.getSession(false);
        session.getAttribute("logado");
        
        ProdutoDAO pd = new ProdutoDAO();
        List<Produto> a = pd.buscarTodosProdutos();

        var writer = response.getWriter();
        writer.println("<html>" + "<head></head>" + "<body>");
        writer.println("<a href='/volta'>Voltar</a>" + "<br>" + "<br>");

        writer.println("<table border='1'>");
        writer.println("<tr>");
        writer.println("<td> Nome </td>" + "<td> Descrição </td>" + "<td> preço </td>" + "<td> Estoque </td>");
        writer.println("</tr>");

        for(int i  = 0; i < a.size(); i++){            
           
            writer.println("<tr>");
            writer.println("<td>" + a.get(i).getNome() + "</td><td>" + a.get(i).getDescricao() + "</td><td>" + a.get(i).getPreco() + "</td><td>" + a.get(i).getEstoque());
            writer.println("</tr>");
               
        }
        writer.println("</table>");
        writer.println("</body></html>");
    }

    @RequestMapping(value="/listaCli", method = RequestMethod.GET)
    public void listarProdutoCli(HttpServletRequest request, HttpServletResponse response) throws IOException {
        HttpSession session = request.getSession(false);
        session.getAttribute("logado");

            ProdutoDAO pd = new ProdutoDAO();
            List<Produto> a = pd.buscarTodosProdutos();

            var writer = response.getWriter();
            writer.println("<html>" + "<head></head>" + "<body>");
            writer.println("<a href='/voltaCli'>Voltar</a>" + "<br>" + "<br>");
            writer.println("<table border='1'>");
            writer.println("<tr>");
            writer.println("<td> Nome </td>" + "<td> Descrição </td>" + "<td> preço </td>" + "<td> Estoque </td>" + "<td> Carrinho </td>");
            writer.println("</tr>");

            for(int i  = 0; i < a.size(); i++){  
                if(a.get(i).getEstoque() > 0){
                    writer.println("<tr>");
                    writer.println("<td>" + a.get(i).getNome() + "</td><td>" + a.get(i).getDescricao() + "</td><td>" + a.get(i).getPreco() + "</td><td>" +  a.get(i).getEstoque() + "</td><td>" + "<a href='/addCarrinhoNew?produtoId=" + a.get(i).getNome() + "&comando=add'>Adicionar</a> </td>");
                    writer.println("</tr>"); 
                } else {
                    writer.println("<tr>");
                    writer.println("<td>" + a.get(i).getNome() + "</td><td>" + a.get(i).getDescricao() + "</td><td>" + a.get(i).getPreco() + "</td><td>" +  a.get(i).getEstoque() + "</td><td>" + "<p>Sem estoque</p> </td>");
                    writer.println("</tr>"); 
                } 
                
            }
    
            writer.println("</table>");
            writer.println("<a href='/verCarrinhoNew'>Ver Carrinho</a>");
            writer.println("</body></html>");

        }
}
