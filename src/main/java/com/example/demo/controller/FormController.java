package com.example.demo.controller;

import java.io.IOException;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.example.demo.DAO.ClienteDAO;
import com.example.demo.DAO.LojistaDAO;
import com.example.demo.DAO.ProdutoDAO;
import com.example.demo.model.Cliente;
import com.example.demo.model.Lojista;
import com.example.demo.model.Produto;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import lombok.var;

@Controller
public class FormController {

    @RequestMapping(value = "/home", method = RequestMethod.POST)
    public void doLogin(HttpServletRequest request, HttpServletResponse response) throws IOException {
        var login = request.getParameter("login");
        var senha = request.getParameter("senha");

        Lojista log = new Lojista();
        LojistaDAO logDAO = new LojistaDAO();

        Cliente logC = new Cliente();
        ClienteDAO logCDAO = new ClienteDAO();

        log = logDAO.buscarPorNomeESenha(login, senha.toString());
        logC = logCDAO.buscarPorNomeESenha(login, senha.toString());


        if(log != null && login.equals(log.getNome()) && senha.equals(log.getSenha())) {
            HttpSession session = request.getSession();
            session.setAttribute("logado", true);
            
            var writer = response.getWriter();

            writer.println("<html>" + "<body>");
            writer.println("<h1>Home - Lojista</h1>");
            writer.println("<a href='/cadatroProduto'>Cadastra-Produto</a>" + "<br>" + "<br>");
            writer.println("<a href='/lista'>Exibe-Produto</a>" + "<br>" + "<br>");
            writer.println("<a href='/logout'>Deslogar</a>");

        }else if(logC != null && login.equals(logC.getNome()) && senha.equals(logC.getSenha())){
            HttpSession session = request.getSession();
            session.setAttribute("logado", true);

            var writer = response.getWriter();

            writer.println("<html>" + "<body>");
            writer.println("<h1>Home - Cliente</h1>");
            writer.println("<a href='/listaCli'>Lista de Produtos</a>" + "<br>" + "<br>");
            writer.println("<a href='/logout'>Deslogar</a>");
        }else {
            response.sendRedirect("index.html?msg=Loginfalhou");
        }
                
    }

    @RequestMapping(value ="/volta", method = RequestMethod.GET)
    public void voltar(HttpServletRequest request, HttpServletResponse response) throws IOException {

        var writer = response.getWriter();
            writer.println("<html>" + "<body>");
            writer.println("<h1>Home - Lojista</h1>");
            writer.println("<a href='/cadatroProduto'>Cadastra-Produto</a>" + "<br>" + "<br>");
            writer.println("<a href='/lista'>Exibe-Produto</a>" + "<br>" + "<br>");
            writer.println("<a href='/logout'>Deslogar</a>");
    }

    @RequestMapping(value ="/voltaCli", method = RequestMethod.GET)
    public void voltarCliente(HttpServletRequest request, HttpServletResponse response) throws IOException {
        var writer = response.getWriter();

        writer.println("<html>" + "<body>");
        writer.println("<h1>Home - Cliente</h1>");
        writer.println("<a href='/listaCli'>Lista de Produtos</a>" + "<br>" + "<br>");
        writer.println("<a href='/logout'>Deslogar</a>");
    }

    

    @RequestMapping("/logout")
    public void doLogout(HttpServletRequest request, HttpServletResponse response) throws IOException {
        HttpSession session = request.getSession();
        session.invalidate();

        response.sendRedirect("index.html?msg=Usuario deslogado");
    }

    @RequestMapping(value = "/doCadastro", method = RequestMethod.POST)
    public void doCadastro(HttpServletRequest request, HttpServletResponse response) throws IOException {
        var nome = request.getParameter("nome");
        var desc = request.getParameter("desc");
        var precoStr = request.getParameter("preco");
        var estoqueStr = request.getParameter("estoque");

        int preco = Integer.parseInt(precoStr);
        int estoque = Integer.parseInt(estoqueStr);

        Produto p = new Produto(preco, nome, desc, estoque);
        ProdutoDAO pd = new ProdutoDAO();

        if(pd.buscarProdutoPorNome(nome) == null) {
            pd.cadastrarProduto(p);
            response.sendRedirect("/cadatroProduto" + "?msg=ProdutoCadastrado");
        }else {
            response.sendRedirect("/cadatroProduto" + "?msg=ProdutoJaExiste");
        }
        
    } 

}

