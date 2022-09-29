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
            //read text files and set values 
            String title = br.readLine();
            String content = br.readLine();
            br.close();
            //put values into note obj
            Note note = new Note(title, content);
            //set note in req
            req.setAttribute("note", note);

            //check if editpage is valid
            String editPage = req.getParameter("edit");
            if (editPage != null) {
                //redirects user to edit page
                getServletContext().getRequestDispatcher("/WEB-INF/jsp/editnote.jsp").forward(req, res);
            } else {
                //redirects user to view page
                getServletContext().getRequestDispatcher("/WEB-INF/jsp/viewnote.jsp").forward(req, res);
            }

        } catch (FileNotFoundException fileNotFound) {
            System.out.println(fileNotFound);
        }

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            String path = getServletContext().getRealPath("/WEB-INF/note.txt");
            String title = request.getParameter("title");
            String content = request.getParameter("content");
            PrintWriter pw = new PrintWriter(new BufferedWriter(new FileWriter(path, false)));

            //if contents not filled return error message
            if (title == null || title.equals("") || content == null || content.equals("")) {
                request.setAttribute("title", title);
                request.setAttribute("content", content);

                request.setAttribute("message", "invalid entry, please fill out the form");

                getServletContext().getRequestDispatcher("/WEB-INF/jsp/editnote.jsp")
                        .forward(request, response);
                return;
            }

            Note note = new Note(title, content);
            //set note in req
            request.setAttribute("note", note);
            //write new data into txt file
            pw.println(note.getTitle());
            pw.println(note.getContent());
            pw.close();
            //redirects user back to view page
            getServletContext().getRequestDispatcher("/WEB-INF/jsp/viewnote.jsp")
                    .forward(request, response);
        } catch (FileNotFoundException fileNotFound) {
            System.out.println(fileNotFound);
        }
    }

}
