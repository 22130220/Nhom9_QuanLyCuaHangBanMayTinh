package vn.hcmuaf.edu.vn.frontend.controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

@WebServlet("/admin/stockIO")
public class StockIOController extends HttpServlet {
    // 13.1.0 - Người dùng ở trang "Quản lý nhập xuất"
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.getRequestDispatcher("/admin_stockIO.jsp").forward(request, response);
    }
}
