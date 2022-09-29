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
import models.Note;

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

            Note note = new Note(title, content);
            req.setAttribute("note", note);

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

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String path = getServletContext().getRealPath("/WEB-INF/note.txt");
        String title = request.getParameter("title");
        String content = request.getParameter("content");
        PrintWriter pw = new PrintWriter(new BufferedWriter(new FileWriter(path, false)));

        if (title == null || title.equals("") || content == null || content.equals("")) {
            request.setAttribute("title", title);
            request.setAttribute("content", content);

            request.setAttribute("message", "invalid entry, please fill out the form");

            getServletContext().getRequestDispatcher("/WEB-INF/jsp/editnote.jsp")
                    .forward(request, response);
            return;
        }

        Note note = new Note(title, content);
        request.setAttribute("note", note);
        pw.println(note.getTitle());
        pw.println(note.getContent());
        pw.close();
        getServletContext().getRequestDispatcher("/WEB-INF/jsp/viewnote.jsp")
                .forward(request, response);
    }

}
