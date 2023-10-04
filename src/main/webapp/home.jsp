<%-- home.jsp --%>
<%
    boolean isCheckAdmin = (boolean) session.getAttribute("check_admin");

    if (isCheckAdmin){
        %><h1>Welcome to the Admin management page, please select tasks on the Menu </h1><%
    }else %><%{
        %><h1>Welcome to the customer management page, please select tasks on the Menu </h1><%
    }
%>


