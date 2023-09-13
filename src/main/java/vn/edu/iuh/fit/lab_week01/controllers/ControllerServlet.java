package vn.edu.iuh.fit.lab_week01.controllers;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import vn.edu.iuh.fit.lab_week01.models.Account;
import vn.edu.iuh.fit.lab_week01.repositories.AccountRepository;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

@WebServlet(name = "ControllerServlet", urlPatterns ={"/"})
public class ControllerServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String action = req.getParameter("action");
        AccountRepository accountRepository = new AccountRepository();
        if (action.equals("listAccount")){
            List<Account> accountList = accountRepository.getAllAccount(); // Thay thế phương thức và nguồn dữ liệu thực tế của bạn ở đây.
            req.setAttribute("accountList", accountList);
            RequestDispatcher requestDispatcher = req.getRequestDispatcher("/account/listAccount.jsp"); // Thay thế đường dẫn JSP thực tế của bạn ở đây.
            requestDispatcher.forward(req, resp);
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String action = req.getParameter("action");
        AccountRepository accountRepository = new AccountRepository();

        if (action.equals("login")){
            String username = req.getParameter("username");
            String password = req.getParameter("password");
            Account account = new Account();
            account = accountRepository.login(username, password);
            if (account != null) {
                HttpSession session = req.getSession();
                session.setAttribute("session_name", account.getFull_name());
                RequestDispatcher requestDispatcher = req.getRequestDispatcher("/dashboard.jsp");
                requestDispatcher.include(req,resp);
                PrintWriter out = resp.getWriter();
                out.println("<script type=\"text/javascript\">");
                out.println("alert('Login sucess!');");
                out.println("</script>");
            }
            else {
                PrintWriter out = resp.getWriter();
                out.println("<script type=\"text/javascript\">");
                out.println("alert('Login failse! Please login again!');");
                out.println("location = 'index.jsp';");
                out.println("</script>");
            }

        }
    }


}
