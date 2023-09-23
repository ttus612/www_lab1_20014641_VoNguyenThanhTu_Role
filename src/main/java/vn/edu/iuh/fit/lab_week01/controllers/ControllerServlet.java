    package vn.edu.iuh.fit.lab_week01.controllers;

    import jakarta.servlet.RequestDispatcher;
    import jakarta.servlet.ServletException;
    import jakarta.servlet.annotation.WebServlet;
    import jakarta.servlet.http.HttpServlet;
    import jakarta.servlet.http.HttpServletRequest;
    import jakarta.servlet.http.HttpServletResponse;
    import jakarta.servlet.http.HttpSession;
    import vn.edu.iuh.fit.lab_week01.models.*;
    import vn.edu.iuh.fit.lab_week01.repositories.AccountRepository;
    import vn.edu.iuh.fit.lab_week01.repositories.GrantAccessRepository;
    import vn.edu.iuh.fit.lab_week01.repositories.LogRepository;
    import vn.edu.iuh.fit.lab_week01.repositories.RoleRepository;

    import java.io.IOException;
    import java.io.PrintWriter;
    import java.sql.Timestamp;
    import java.text.ParseException;
    import java.util.List;

    //@WebServlet(name = "ControllerServlet", urlPatterns ={"/"})
    public class ControllerServlet extends HttpServlet {
        AccountRepository accountRepository = new AccountRepository();
        RoleRepository roleRepository = new RoleRepository();
        LogRepository logRepository = new LogRepository();

        GrantAccessRepository grantAccessRepository = new GrantAccessRepository();

        @Override
        protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
            System.out.println("doGet method is called.");
            String action = req.getParameter("action");

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
            } else if (action.equals("logout")) {
                HttpSession session = req.getSession(false);
                if (session != null) {
                    Account account = (Account) session.getAttribute("session_name"); // Lấy thông tin tài khoản đã đăng nhập
                    Logs log = (Logs) session.getAttribute("session_log");

                    Boolean isTimeLogOut = null;
                    try {
                        isTimeLogOut = logRepository.updateLogTime(log);
                    } catch (ParseException e) {
                        throw new RuntimeException(e);
                    }
                    System.out.println(isTimeLogOut);
                    session.invalidate();
                }
                resp.sendRedirect("index.jsp");
            }else if (action.equals("listPermission")) {
                List<GrantAccess> grantAccesses = grantAccessRepository.getAllGrantAccess();
                List<Role> roles = roleRepository.getAllRole();
                req.setAttribute("grantAccessesList", grantAccesses);
                req.setAttribute("roleList", roles);
                RequestDispatcher requestDispatcher = req.getRequestDispatcher("/dashboard.jsp");
                requestDispatcher.forward(req, resp);
            }
        }

        @Override
        protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
            System.out.println("doPost method is called.");

            String action = req.getParameter("action");

            if (action == null){
                System.out.println("Action null");
            }else {
                if (action.equals("login")){
                    String username = req.getParameter("username");
                    String password = req.getParameter("password");
                    Account account = new Account();
                    account = accountRepository.login(username, password);

                    Timestamp currentTimeLogin = new Timestamp(System.currentTimeMillis());
                    Timestamp currentTimeLogout = null;
                    String note = " ";

                    if (account != null) {
                        HttpSession session = req.getSession();
                        session.setAttribute("session_name", account);
                        RequestDispatcher requestDispatcher = req.getRequestDispatcher("/dashboard.jsp?page=home");
                        requestDispatcher.include(req,resp);
                        PrintWriter out = resp.getWriter();

                        note = "TAI KHOAN "+ username+" VOI PASSWORD "+password+" DANG NHAP THANH CONG  ";
                        Logs logs = new Logs(account.getAccount_id(), currentTimeLogin, currentTimeLogout,note);
                        session.setAttribute("session_log", logs);
                        Boolean isGetLogin = logRepository.getLogLogin(logs);
                        System.out.println(logs);

                        out.println("<script type=\"text/javascript\">");
                        out.println("alert('Login sucess!');");
                        out.println("</script>");
                    }
                    else {
                        PrintWriter out = resp.getWriter();
                        note = "TAI KHOAN "+ username+" VOI PASSWORD "+password+" DANG NHAP THAT BAI";
                        currentTimeLogout = new Timestamp(System.currentTimeMillis());
                        Logs logs = new Logs(username, currentTimeLogin, currentTimeLogout,note);
                        Boolean isGetLogin = logRepository.getLogLogin(logs);
                        System.out.println(logs);

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
                } else if (action.equals("delete_role")) {
                    String roleIdString = req.getParameter("roleId");
                    if (roleIdString != null) {
                        try {
                            int roleId = Integer.parseInt(roleIdString);
                            Boolean isDeleteRole = roleRepository.deleteRole(roleId);

                            if (isDeleteRole) {
                                List<Role> roles = roleRepository.getAllRole();
                                req.setAttribute("roleList", roles);
                                RequestDispatcher requestDispatcher = req.getRequestDispatcher("/dashboard.jsp?page=listR");
                                requestDispatcher.forward(req, resp);
                                PrintWriter out = resp.getWriter();
                                out.println("<script type=\"text/javascript\">");
                                out.println("alert('Success to delete role!');");
                                out.println("location = 'listRole?action=listRole';");
                                out.println("</script>");
                            } else {
                                List<Role> roles = roleRepository.getAllRole();
                                req.setAttribute("roleList", roles);
                                RequestDispatcher requestDispatcher = req.getRequestDispatcher("/dashboard.jsp?page=listR");
                                requestDispatcher.forward(req, resp);
                                PrintWriter out = resp.getWriter();
                                out.println("<script type=\"text/javascript\">");
                                out.println("alert('Failed to delete role!');");
                                out.println("location = 'listRole?action=listRole';");
                                out.println("</script>");
                            }
                        } catch (NumberFormatException e) {
                            // Handle the case where roleIdString is not a valid integer
                            e.printStackTrace();
                        }
                    }

                } else if (action.equals("listPermission")) {
                    String select = req.getParameter("selectedValue");
                    List<GrantAccess> grantAccesses = grantAccessRepository.getGrantAccessForCustomer(select);
                    List<Role> roles = roleRepository.getAllRole();
                    req.setAttribute("grantAccessesList", grantAccesses);
                    req.setAttribute("roleList", roles);
                    RequestDispatcher requestDispatcher = req.getRequestDispatcher("/dashboard.jsp");
                    requestDispatcher.forward(req, resp);

                }
            }

        }
    }
