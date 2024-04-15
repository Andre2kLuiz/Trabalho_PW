package com.example.demo.controller;

import java.io.IOException;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@Controller
public class ProdutoController {

    @RequestMapping(value = "/cadatroProduto", method = RequestMethod.GET)
    public void cadastrarProduto(HttpServletRequest request, HttpServletResponse response) throws IOException {
        var writer = response.getWriter();
        
        writer.println("<html>" + "<head></head>" + "<body>");
        writer.println("<form action='/doCadastro' method='POST'>");
        writer.println("<div class='input-box-II'>");
        writer.println("<h1>Cadastro - Produtos</h1>");
        writer.println("<input type='text' placeholder='Nome' id='username' name='nome' required>" + "<br>" + "<br>");
        writer.println("<input type='text' placeholder='Descrição' id='username' name='desc' required>"  + "<br>" + "<br>");
        writer.println("<input type='number' placeholder='Preço' id='username' name='preco' required>"  + "<br>" + "<br>");
        writer.println("<input type='number' placeholder='Estoque' id='username' name='estoque' required>"  + "<br>" + "<br>");
        writer.println("<button type='submit' class='btn'>Cadastrar</button>"  + "<br>" + "<br>");
        writer.println("<a href='/volta'>Voltar</a>"  + "<br>" + "<br>");
        writer.println("<div>");
        writer.println("</form>");

       
    }
    
}
