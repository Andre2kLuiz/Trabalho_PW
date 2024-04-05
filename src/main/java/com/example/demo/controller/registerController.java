package com.example.demo.controller;

import java.io.IOException;
import java.net.URISyntaxException;
import java.sql.SQLException;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.example.demo.DAO.LojistaDAO;
import com.example.demo.model.Lojista;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@Controller
public class registerController {
    @SuppressWarnings("unused")
    @RequestMapping(value = "index", method = RequestMethod.POST)
    public void cadastrarLojista(HttpServletRequest request, HttpServletResponse response) throws IOException, SQLException, URISyntaxException {
        var login = request.getParameter("nomeR");
        var email = request.getParameter("emailR");
        var senha = request.getParameter("senhaR");

        LojistaDAO logistaDAO = new LojistaDAO();

        Lojista log1 = new Lojista(login, email, senha.toString());

        if(log1 != null){
            logistaDAO.adicionar(log1);
            response.sendRedirect("/index.html");
        }else{
            response.sendRedirect("/pages/register.html");
        }   
    }
}
