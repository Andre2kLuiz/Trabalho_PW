package com.example.demo.controller;

import java.io.IOException;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.example.demo.DAO.LojistaDAO;
import com.example.demo.model.Lojista;

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

        log = logDAO.buscarPorNomeESenha(login, senha.toString());


        if(log != null && login.equals(log.getNome()) && senha.equals(log.getSenha())) {
            HttpSession session = request.getSession();
            session.setAttribute("logado", log.getNome());
            //response.sendRedirect("home.html" + "?msg=BemVindo" + log.getNome());
            var writer = response.getWriter();

            writer.println("<html>" + "<body>");
            writer.println("<h1>Home - Lojista</h1>");
            writer.println("<a href='/cadatroProduto'>Cadastra-Produto</a>" + "<br>" + "<br>");
            writer.println("<a href='#'>Exibe-Produto</a>" + "<br>" + "<br>");
            writer.println("<a href='/logout'>Deslogar</a>");
        }else {
            response.sendRedirect("index.html?msg=Loginfalhou");
        }
                
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

    @RequestMapping("/lista")
    public void listarProduto(HttpServletRequest request, HttpServletResponse response) throws IOException {
        
    }

    @RequestMapping(value = "/cadatroProduto", method = RequestMethod.GET)
    public void cadastrarProduto(HttpServletRequest request, HttpServletResponse response) throws IOException {
        var writer = response.getWriter();
        
        writer.println("<html>" + "<head><link rel='stylesheet' href='/assets/styles/pages/index.css'></head>" + "<body>");
        writer.println("<form action='/home' method='GET'>");
        writer.println("<div class='input-box-II'>");
        writer.println("<h1>Cadastro</h1>");
        writer.println("<input type='text' placeholder='Nome' id='username' name='login' required>" + "<br>" + "<br>");
        writer.println("<input type='text' placeholder='Descrição' id='username' name='login' required>"  + "<br>" + "<br>");
        writer.println("<input type='number' placeholder='Preço' id='username' name='login' required>"  + "<br>" + "<br>");
        writer.println("<input type='number' placeholder='Estoque' id='username' name='login' required>"  + "<br>" + "<br>");
        writer.println("<button type='submit' class='btn'>Cadastrar</button>"  + "<br>" + "<br>");
        writer.println("<div>");
        writer.println("</form>");

       
    }

}

