package com.ksu;

import db.manager.DBmanager;
import security.DBsecurity;


import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.PrintWriter;
import java.net.URLDecoder;
import java.sql.*;

@WebServlet("/managing")
public class TestServlet extends HttpServlet {

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        response.setContentType("text/html");
        response.setCharacterEncoding("UTF-8");
        PrintWriter pwriter = response.getWriter();

        //getParameter() uses ISO-8559-1 instead of UTF-8.
        request.setCharacterEncoding("UTF-8"); // решение проблемы с  кодировкой
        String name = request.getParameter("name");
        String writer = request.getParameter("writer");
        String price = request.getParameter("price");
        //int price = Integer.parseInt(request.getParameter("price"));





        try {

            DBmanager dbMng = new DBmanager();

            //Вызываем метод Insert для вставки строки в таблицу БД
            dbMng.insert(name,writer,price);
            pwriter.println("<p>Данные занесены: "+"</p>");
            pwriter.println("<p>"+name+" " +writer+" "+price+"</p>");
            pwriter.println("<p>Текущие записи: "+"</p>");
            pwriter.println(dbMng.select());
            dbMng.closeConncetion();



        }
        catch (Exception e)
        {
            pwriter.println("<p>Ошибка! "+ e +"</p>");
        }

    }
}