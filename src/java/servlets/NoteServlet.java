/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servlets;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author nguye
 */
public class NoteServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
        try {
            String path = getServletContext().getRealPath("/WEB-INF/note.txt");
// to read files
            BufferedReader br = new BufferedReader(new FileReader(new File(path)));
            String title = br.readLine();
            String content = br.readLine();

            req.setAttribute("title", title);
            req.setAttribute("content", content);
            String editPage = req.getParameter("edit");
            if (editPage != null) {
                getServletContext().getRequestDispatcher("/WEB-INF/jsp/editnote.jsp").forward(req, res);
            } else {
                getServletContext().getRequestDispatcher("/WEB-INF/jsp/viewnote.jsp").forward(req, res);
            }

        } catch (FileNotFoundException fileNotFound) {
            System.out.println(fileNotFound);
        }

    }

}
