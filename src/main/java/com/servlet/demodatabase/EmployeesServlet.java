package com.servlet.demodatabase;

import com.servlet.dbconnection.DBConnection;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Objects;

@WebServlet(name = "EmployeesServlet", value = "/EmployeesServlet")
public class EmployeesServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html");
        PrintWriter out = response.getWriter(); // get PrintWriter from HTTPServletResponse

        out.println("<html>");
        out.println("<head>");
        out.println("<link href='style.css' rel='stylesheet'>"); // include CSS stylesheet
        out.println("</head>");
        out.println("<body>");

        if(Objects.equals(request.getParameter("action"), "getEmployee")) { // check action did client request
            out.println("<h1>Here are the employee data</h1>");
            out.println("<table>");

            out.println("<tr>");

            out.println("<th>Employee ID</th>");
            out.println("<th>Employee Name</th>");
            out.println("<th>Employee Email</th>");
            out.println("<th>Employee Job</th>");
            out.println("<th>Department Name</th>");

            out.println("</tr>");
            try {
                Connection conn = DBConnection.getInstance(); // get Connection instance from Oracle Driver

                Statement stmt = conn.createStatement(); // create statement to execute

                // execute query and return rows
                ResultSet rs = stmt.executeQuery(
                        "select employee_id, first_name, last_name, email, job_title, department_name " +
                                "from employees " +
                                "join jobs using (job_id) " +
                                "join departments using (department_id)"
                );

                // check if there is any next rows in result
                while(rs.next()) {
                    out.println("<tr>");

                    // rs.getString(index) - get column by using index column
                    out.println("<td>" + rs.getString(1) + "</td>");
                    out.println("<td>" + rs.getString(2) + " " + rs.getString(3) + "</td>");
                    out.println("<td>" + rs.getString(4) + "</td>");
                    out.println("<td>" + rs.getString(5) + "</td>");
                    out.println("<td>" + rs.getString(6) + "</td>");

                    out.println("</tr>");
                }
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
            out.println("</table>");
        } else {
            out.println("<h1>Cannot retrieve data</h1>");
        }
        out.println("<body></html>");
    }
}
