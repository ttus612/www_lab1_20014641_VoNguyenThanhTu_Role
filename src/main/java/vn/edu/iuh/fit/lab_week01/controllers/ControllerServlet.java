package vn.edu.iuh.fit.lab_week01.controllers;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import vn.edu.iuh.fit.lab_week01.models.Account;
import vn.edu.iuh.fit.lab_week01.models.Logs;
import vn.edu.iuh.fit.lab_week01.models.Role;
import vn.edu.iuh.fit.lab_week01.models.Status;
import vn.edu.iuh.fit.lab_week01.repositories.AccountRepository;
import vn.edu.iuh.fit.lab_week01.repositories.LogRepository;
import vn.edu.iuh.fit.lab_week01.repositories.RoleRepository;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

//@WebServlet(name = "ControllerServlet", urlPatterns ={"/"})
public class ControllerServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        System.out.println("doGet method is called.");
        String action = req.getParameter("action");
        AccountRepository accountRepository = new AccountRepository();
        LogRepository logRepository = new LogRepository();
        RoleRepository roleRepository = new RoleRepository();
        if (action.equals("listAccount")){
            List<Account> accountList = accountRepository.getAllAccount();
            req.setAttribute("accountList", accountList);
            RequestDispatcher requestDispatcher = req.getRequestDispatcher("/dashboard.jsp");
            requestDispatcher.forward(req, resp);
        } else if (action.equals("listLog")) {
            List<Logs> logs = logRepository.getAllLogs();
            req.setAttribute("logList", logs);
            RequestDispatcher requestDispatcher = req.getRequestDispatcher("/dashboard.jsp");
            requestDispatcher.forward(req, resp);
        }else if (action.equals("listRole")) {
            List<Role> roles = roleRepository.getAllRole();
            req.setAttribute("roleList", roles);
            RequestDispatcher requestDispatcher = req.getRequestDispatcher("/dashboard.jsp");
            requestDispatcher.forward(req, resp);
        }else if (action.equals("addRole")) {
            RequestDispatcher requestDispatcher = req.getRequestDispatcher("/dashboard.jsp");
            requestDispatcher.forward(req, resp);
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        System.out.println("doPost method is called.");

        String action = req.getParameter("action");
        AccountRepository accountRepository = new AccountRepository();
        RoleRepository roleRepository = new RoleRepository();
        if (action.equals("login")){
            String username = req.getParameter("username");
            String password = req.getParameter("password");
            Account account = new Account();
            account = accountRepository.login(username, password);
            if (account != null) {
                HttpSession session = req.getSession();
                session.setAttribute("session_name", account);
                RequestDispatcher requestDispatcher = req.getRequestDispatcher("/dashboard.jsp?page=home");
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

        } else if (action.equals("add_role")) {
            String role_name = req.getParameter("rolename");
            String description = req.getParameter("description");

            String statusString = req.getParameter("status");
            int statusInt = Integer.parseInt(statusString);
            Status status = null;
            try {
                status = Status.fromCode(statusInt);
            } catch (IllegalAccessException e) {
                throw new RuntimeException(e);
            }

            Role role = new Role(role_name, description, status);
            Boolean isAdd = roleRepository.addRole(role);
            if (isAdd == Boolean.TRUE){
                RequestDispatcher requestDispatcher = req.getRequestDispatcher("/dashboard.jsp?page=addR");
                requestDispatcher.include(req,resp);
                PrintWriter out = resp.getWriter();
                out.println("<script type=\"text/javascript\">");
                out.println("alert('ADD sucess!');");
                out.println("</script>");
            }else{
                RequestDispatcher requestDispatcher = req.getRequestDispatcher("/dashboard.jsp?page=addR");
                requestDispatcher.include(req,resp);
                PrintWriter out = resp.getWriter();
                out.println("<script type=\"text/javascript\">");
                out.println("alert('ADD FAILSE!');");
                out.println("</script>");
            }
        }
    }
}
