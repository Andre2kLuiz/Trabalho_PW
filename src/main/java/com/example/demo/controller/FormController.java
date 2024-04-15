package com.example.demo.controller;

import java.io.IOException;
import java.util.List;

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
            session.setAttribute("logado", log.getNome());
            //response.sendRedirect("home.html" + "?msg=BemVindo" + log.getNome());
            var writer = response.getWriter();

            writer.println("<html>" + "<body>");
            writer.println("<h1>Home - Lojista</h1>");
            writer.println("<a href='/cadatroProduto'>Cadastra-Produto</a>" + "<br>" + "<br>");
            writer.println("<a href='/lista'>Exibe-Produto</a>" + "<br>" + "<br>");
            writer.println("<a href='/logout'>Deslogar</a>");

        }else if(logC != null && login.equals(logC.getNome()) && senha.equals(logC.getSenha())){
            HttpSession session = request.getSession();
            session.setAttribute("logado", logC.getNome());

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

    @RequestMapping(value ="/home", method = RequestMethod.GET)
    public void homePage(HttpServletRequest request, HttpServletResponse response) throws IOException {
        HttpSession session = request.getSession();
        Boolean logado = (Boolean) session.getAttribute("logado");

        if (logado != null && logado) {
                       
            response.sendRedirect("/home");
            
        } else {
            
            response.sendRedirect("index.html?msg=Façaloginprimeiro");
        }
    }

    @RequestMapping("/logout")
    public void doLogout(HttpServletRequest request, HttpServletResponse response) throws IOException {
        HttpSession session = request.getSession();
        session.invalidate();

        response.sendRedirect("index.html?msg=Usuario deslogado");
    }

    @RequestMapping(value="/lista", method = RequestMethod.GET)
    public void listarProduto(HttpServletRequest request, HttpServletResponse response) throws IOException {
        ProdutoDAO pd = new ProdutoDAO();
        List<Produto> a = pd.buscarTodosProdutos();

        var writer = response.getWriter();
        writer.println("<html>" + "<head></head>" + "<body>");
        writer.println("<a href='/volta'>Voltar</a>" + "<br>" + "<br>");

        for(int i  = 0; i < a.size(); i++){            
            writer.println("<table border='1'>");
            writer.println("<tr>");
            writer.println("<td>Nome: </td><td>" + a.get(i).getNome() + "</td>");
            writer.println("</tr>");
            writer.println("<tr>");
            writer.println("<td>Descrição: </td><td>" + a.get(i).getDescricao() + "</td>");
            writer.println("</tr>");
            writer.println("<tr>");
            writer.println("<td>Preço: </td><td>" + a.get(i).getPreco() + "</td>");
            writer.println("</tr>");
            writer.println("<tr>");
            writer.println("<td>Estoque: </td><td>" + a.get(i).getEstoque() + "</td>");
            writer.println("</tr>");
            writer.println("<br>");
            writer.println("</table>");
            
        }
 
        writer.println("</body></html>");
    }

    @RequestMapping(value="/listaCli", method = RequestMethod.GET)
    public void listarProdutoCli(HttpServletRequest request, HttpServletResponse response) throws IOException {
        ProdutoDAO pd = new ProdutoDAO();
        List<Produto> a = pd.buscarTodosProdutos();

        var writer = response.getWriter();
        writer.println("<html>" + "<head></head>" + "<body>");
        writer.println("<a href='/voltaCli'>Voltar</a>" + "<br>" + "<br>");

        for(int i  = 0; i < a.size(); i++){            
            writer.println("<table border='1'>");
            writer.println("<tr>");
            writer.println("<td>Nome: </td><td>" + a.get(i).getNome() + "</td>");
            writer.println("</tr>");
            writer.println("<tr>");
            writer.println("<td>Descrição: </td><td>" + a.get(i).getDescricao() + "</td>");
            writer.println("</tr>");
            writer.println("<tr>");
            writer.println("<td>Preço: </td><td>" + a.get(i).getPreco() + "</td>");
            writer.println("</tr>");
            writer.println("<tr>");
            writer.println("<td>Estoque: </td><td>" + a.get(i).getEstoque() + "</td>");
            writer.println("</tr>");
            writer.println("<br>");
            writer.println("</table>");
            
        }
 
        writer.println("</body></html>");
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

        if(pd.buscarProdutoPorId(nome) == null) {
            pd.cadastrarProduto(p);
            response.sendRedirect("/cadatroProduto" + "?msg=ProdutoCadastrado");
        }else {
            response.sendRedirect("/cadatroProduto" + "?msg=ProdutoJaExiste");
        }
        
    }

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

