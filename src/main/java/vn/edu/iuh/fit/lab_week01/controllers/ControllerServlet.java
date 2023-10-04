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
    import java.sql.SQLException;
    import java.sql.Timestamp;
    import java.text.ParseException;
    import java.util.List;
    import java.util.Objects;

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
                List<Account> accounts = accountRepository.getAllAccount();
                req.setAttribute("grantAccessesList", grantAccesses);
                req.setAttribute("roleList", roles);
                RequestDispatcher requestDispatcher = req.getRequestDispatcher("/dashboard.jsp");
                requestDispatcher.forward(req, resp);
            }else if (action.equals("listPerOfAnAccountAction")) {
                List<GrantAccess> grantAccesses = grantAccessRepository.getAllGrantAccess();
                List<Role> roles = roleRepository.getAllRole();
                List<Account> accounts = accountRepository.getAllAccount();
                req.setAttribute("grantAccessesList", grantAccesses);
                req.setAttribute("accounts", accounts);
                RequestDispatcher requestDispatcher = req.getRequestDispatcher("/dashboard.jsp");
                requestDispatcher.forward(req, resp);
            }else if (action.equals("editRole")) {
                int roleIdString = Integer.parseInt(req.getParameter("roleId"));
                Role role = roleRepository.getRoleOne(roleIdString);
                req.setAttribute("role", role);
                RequestDispatcher requestDispatcher = req.getRequestDispatcher("/dashboard.jsp");
                requestDispatcher.forward(req, resp);
            }else if (action.equals("addAccount")){
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
                        boolean isCheckAmin = accountRepository.checkAdmin(account);
                        List<Role> roles = roleRepository.getAllRole();
                        if (isCheckAmin){
                            HttpSession session = req.getSession();
                            session.setAttribute("session_name", account);
                            session.setAttribute("check_admin", isCheckAmin);
                            session.setAttribute("roles", roles);

                            RequestDispatcher requestDispatcher = req.getRequestDispatcher("/dashboard.jsp?page=home");
                            requestDispatcher.include(req,resp);
                            PrintWriter out = resp.getWriter();

                            note = "ADMIN: TAI KHOAN "+ username+" VOI PASSWORD "+password+" DANG NHAP THANH CONG  ";
                            Logs logs = new Logs(account.getAccount_id(), currentTimeLogin, currentTimeLogout,note);
                            session.setAttribute("session_log", logs);
                            Boolean isGetLogin = logRepository.getLogLogin(logs);
                            System.out.println(logs);

                            out.println("<script type=\"text/javascript\">");
                            out.println("alert('Login sucess!');");
                            out.println("</script>");
                        }else {
                            HttpSession session = req.getSession();
                            session.setAttribute("session_name", account);
                            session.setAttribute("check_admin", isCheckAmin);

                            RequestDispatcher requestDispatcher = req.getRequestDispatcher("/dashboard.jsp?page=home");
                            requestDispatcher.include(req,resp);
                            PrintWriter out = resp.getWriter();

                            note = "KHACH HANG: TAI KHOAN "+ username+" VOI PASSWORD "+password+" DANG NHAP THANH CONG  ";
                            Logs logs = new Logs(account.getAccount_id(), currentTimeLogin, currentTimeLogout,note);
                            session.setAttribute("session_log", logs);
                            Boolean isGetLogin = logRepository.getLogLogin(logs);
                            System.out.println(logs);

                            out.println("<script type=\"text/javascript\">");
                            out.println("alert('Login sucess!');");
                            out.println("</script>");
                        }

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
                    List<Role> roles = roleRepository.getAllRole();
                    if (Objects.equals(select, "0")){
                        List<GrantAccess> grantAccesses = grantAccessRepository.getAllGrantAccess();
                        req.setAttribute("grantAccessesList", grantAccesses);
                    }else {
                        List<GrantAccess> grantAccesses = grantAccessRepository.getGrantAccessForCustomer(select);
                        req.setAttribute("grantAccessesList", grantAccesses);
                    }

                    req.setAttribute("roleList", roles);
                    RequestDispatcher requestDispatcher = req.getRequestDispatcher("/dashboard.jsp");
                    requestDispatcher.forward(req, resp);

                }else if (action.equals("listPerOfAnAccountAction")) {

                    String select = req.getParameter("selectedValue");
                    List<Account> accounts = accountRepository.getAllAccount();
                    if (Objects.equals(select, "0")){
                        List<GrantAccess> grantAccesses = grantAccessRepository.getAllGrantAccess();
                        req.setAttribute("grantAccessesList", grantAccesses);
                    }else {
                        List<GrantAccess> grantAccesses = grantAccessRepository.getGrantAccessForNameAccount(select);
                        req.setAttribute("grantAccessesList", grantAccesses);

                    }
                    req.setAttribute("accounts", accounts);

                    RequestDispatcher requestDispatcher = req.getRequestDispatcher("/dashboard.jsp");
                    requestDispatcher.forward(req, resp);

                } else if (action.equals("edit_role")) {
                    int id = Integer.parseInt(req.getParameter("role_id"));
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

                    Role role = new Role(id, role_name, description, status);


                    boolean isEdit =roleRepository.edit(role);
                    if (isEdit == Boolean.TRUE){
                        resp.sendRedirect(req.getContextPath() + "/list-role?page=listR&action=listRole");
                        PrintWriter out = resp.getWriter();
                        out.println("<script type=\"text/javascript\">");
                        out.println("alert('Edit sucess!');");
                        out.println("</script>");
                    }else{
                        resp.sendRedirect(req.getContextPath() + "/list-role?page=listR&action=listRole");
                        PrintWriter out = resp.getWriter();
                        out.println("<script type=\"text/javascript\">");
                        out.println("alert('Edit FAILSE!');");
                        out.println("</script>");
                    }
                }else if (action.equals("delete_account")) {
                    String accountIdString = req.getParameter("accountID");
                    if (accountIdString != null) {
                        try {
                            Boolean isDeleteAccount = accountRepository.deleteAccount(accountIdString);

                            if (isDeleteAccount) {
                                List<Account> accounts = accountRepository.getAllAccount();
                                req.setAttribute("accountList", accounts);
                                RequestDispatcher requestDispatcher = req.getRequestDispatcher("/dashboard.jsp?page=listAc");
                                requestDispatcher.forward(req, resp);
                                PrintWriter out = resp.getWriter();
                                out.println("<script type=\"text/javascript\">");
                                out.println("alert('Success to delete role!');");
                                out.println("location = 'listRole?action=listRole';");
                                out.println("</script>");
                            } else {

                                List<Account> accounts = accountRepository.getAllAccount();
                                req.setAttribute("accountList", accounts);
                                RequestDispatcher requestDispatcher = req.getRequestDispatcher("/dashboard.jsp?page=listAc");
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

                } else if (action.equals("add_account")) {
                    String[] selectedRoles = req.getParameterValues("roles[]");
                    String fullName = req.getParameter("fullName");
                    String password = req.getParameter("password");
                    String email = req.getParameter("email");
                    String phone = req.getParameter("phone");
                    String selected = req.getParameter("selectedValue");

                    if (selectedRoles != null && selectedRoles.length > 0) {
                        try {
                            boolean isAdd = accountRepository.addAccount(fullName,password,email,phone,selected);
                            Account account = accountRepository.findAccount(fullName,password,email,phone,selected);
                            for (String role : selectedRoles) {
                                System.out.println("Selected Role: " + role);
                                Role role1 = roleRepository.getRoleOneName(role);
                                accountRepository.addGrant(account,role1);
                            }
                            if (isAdd == Boolean.TRUE){
                                RequestDispatcher requestDispatcher = req.getRequestDispatcher("/dashboard.jsp?page=addAccount");
                                requestDispatcher.include(req,resp);
                                PrintWriter out = resp.getWriter();
                                out.println("<script type=\"text/javascript\">");
                                out.println("alert('ADD sucess!');");
                                out.println("</script>");
                            }else{
                                RequestDispatcher requestDispatcher = req.getRequestDispatcher("/dashboard.jsp?page=addAccount");
                                requestDispatcher.include(req,resp);
                                PrintWriter out = resp.getWriter();
                                out.println("<script type=\"text/javascript\">");
                                out.println("alert('ADD FAILSE!');");
                                out.println("</script>");
                            }
                        } catch (SQLException e) {
                            throw new RuntimeException(e);
                        }

                    } else {
                        System.out.println("Không có vai trò nào được chọn.");
                    }
                }
            }

        }
    }
