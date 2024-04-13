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
            response.sendRedirect("home.html" + "?msg=BemVindo" + log.getNome());
        }else {
            response.sendRedirect("index.html?msg=Loginfalhou");
        }
                
    }

    @RequestMapping(value ="/home", method = RequestMethod.GET)
    public void homePage(HttpServletRequest request, HttpServletResponse response) throws IOException {
        HttpSession session = request.getSession();
        Boolean logado = (Boolean) session.getAttribute("logado");

        if (logado != null && logado) {
            
            response.sendRedirect("home.html");
            
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

}

