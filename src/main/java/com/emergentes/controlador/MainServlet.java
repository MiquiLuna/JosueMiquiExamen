package com.emergentes.controlador;

import com.emergentes.modelo.Calificacion;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet(name = "MainServlet", urlPatterns = {"/MainServlet"})
public class MainServlet extends HttpServlet {
    
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String op = request.getParameter("op");
        Calificacion session = new Calificacion();
        int id, pos;
        
        HttpSession ses = request.getSession();
        ArrayList<Calificacion> lista = (ArrayList<Calificacion>)ses.getAttribute("listacal");
        
        switch(op){
            case "nuevo":
                request.setAttribute("misession", session);
                request.getRequestDispatcher("editar.jsp").forward(request, response);
                break;
                
            case "editar":
                id = Integer.parseInt(request.getParameter("id"));
                pos = buscarPorIndice(request,id);
                session = lista.get(pos);
                request.setAttribute("misession", session);
                request.getRequestDispatcher("editar.jsp").forward(request, response);
                break;
                
            case "eliminar":
                id = Integer.parseInt(request.getParameter("id"));
                pos = buscarPorIndice(request,id);
                if (pos >=0) {
                    lista.remove(pos);
                } 
                request.setAttribute("misession", lista);
                response.sendRedirect("index.jsp");
                break;
            default:
                
        }
    }
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
       int id = Integer.parseInt(request.getParameter("id"));
       HttpSession ses = request.getSession();
       ArrayList<Calificacion> lista = (ArrayList<Calificacion>) ses.getAttribute("listacal");
       Calificacion session = new Calificacion();
       session.setId(id);
       session.setNombre(request.getParameter("nombre"));
       session.setP1(Integer.parseInt(request.getParameter("p1")));
       session.setP2(Integer.parseInt(request.getParameter("p2")));
       session.setEf(Integer.parseInt(request.getParameter("ef")));
       session.NotaTotal(); //suma las calificaciones
       
       if(id==0){
           int idNuevo = obtenerId(request);
           session.setId(idNuevo);
           lista.add(session);
       }else{
           int pos = buscarPorIndice(request, id);
           lista.set(pos, session);
       }
       request.setAttribute("listacal", lista);
       response.sendRedirect("index.jsp");
    }
    
    public int buscarPorIndice(HttpServletRequest request, int id){
        HttpSession ses = request.getSession();
        ArrayList<Calificacion> lista = (ArrayList<Calificacion>) ses.getAttribute("listacal");
    
        int pos =-1;
        
        if(lista != null){
            for(Calificacion ele:lista){
                ++pos;
                if(ele.getId() == id){
                  break;  
                }
            }
        }
        return pos;
    }
    
    public int obtenerId(HttpServletRequest request){
        HttpSession ses = request.getSession();
        ArrayList<Calificacion> lista = (ArrayList<Calificacion>) ses.getAttribute("listacal");
        int idn = 0;
        for(Calificacion ele:lista){
            idn = ele.getId();
        }
        return idn+1;
    }

}
