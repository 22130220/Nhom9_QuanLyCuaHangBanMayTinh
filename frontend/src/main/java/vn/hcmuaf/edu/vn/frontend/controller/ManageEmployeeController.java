package vn.hcmuaf.edu.vn.frontend.controller;
import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;
import java.io.IOException;

@WebServlet(name = "EmployeeController", value = "/admin/employees")
public class ManageEmployeeController extends HttpServlet {

    /**
     * 1.1.4.  Trang web một request tới controller ManageEmployeeController
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.getRequestDispatcher("/admin_manage_employees.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}