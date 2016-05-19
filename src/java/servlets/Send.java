/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servlets;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Random;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.xml.ws.WebServiceRef;
import org.netbeans.j2ee.wsdl.serviciosventa.descriptorbpel.tiendaonlinewsdl.TiendaOnlineWSDLService;

/**
 *
 * @author LABDISMOV
 */
public class Send extends HttpServlet {
    @WebServiceRef(wsdlLocation = "WEB-INF/wsdl/148.205.38.103_9080/TiendaOnlineWSDLService/TiendaOnlineWSDLPort.wsdl")
    private TiendaOnlineWSDLService service;

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet Send</title>");            
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet Send at " + request.getContextPath() + "</h1>");
            String producto = request.getParameter("articulo");            
            String precioUnidad = request.getParameter("precio");
            String cantidad = request.getParameter("cantidad");
            out.println("Producto: "+producto+">br>");
            out.println("Precio: "+precioUnidad+"<br>");
            Random rand = new Random();
            //int unidades = rand.nextInt(20)+1;
            String idCliente = generaIdCliente();
            out.println("IDCliente: "+idCliente+"<br>");
            int precio = Integer.parseInt(precioUnidad);
            int unidades = Integer.parseInt(cantidad);
            String res = tiendaOnlineWSDLOperation(producto, idCliente, unidades, precio);
            out.println("Respuesta: "+res+"<br>");
            out.println("</body>");
            out.println("</html>");
        }
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

    private String tiendaOnlineWSDLOperation(java.lang.String producto, java.lang.String idCliente, int unidades, int precioUnidad) {
        // Note that the injected javax.xml.ws.Service reference as well as port objects are not thread safe.
        // If the calling of port operations may lead to race condition some synchronization is required.
        org.netbeans.j2ee.wsdl.serviciosventa.descriptorbpel.tiendaonlinewsdl.TiendaOnlineWSDLPortType port = service.getTiendaOnlineWSDLPort();
        return port.tiendaOnlineWSDLOperation(producto, idCliente, unidades, precioUnidad);
    }
    
    private String generaIdCliente(){
        Random rand = new Random();
        int n = rand.nextInt(25)+10;
        String res = "";
        for(int i=0;i<n;i++){
            if(rand.nextInt(10)>5){
                res += rand.nextInt(10);
            }else{
                res += ""+((char)(((int)'A')+rand.nextInt(26)));
            }
        }
        return res;
    }

}
