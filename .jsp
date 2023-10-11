<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="java.sql.*" %>
<!DOCTYPE html>
<html>
<head>
    <title>Drink Selection</title>
</head>
<body>
    <h1>Choose a Drink</h1>
    
    <form action="processSelectedDrink.jsp" method="post">
        <select name="selectedDrink">
            <option value="">Select a Drink</option>
            
            <!-- Java code to fetch drink names and rates from the database -->
            <%
                Connection conn = null;
                Statement stmt = null;
                ResultSet rs = null;

                try {
                    Class.forName("com.mysql.jdbc.Driver"); // Change this to your database driver
                    conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/Database", "yourUsername", "yourPassword"); // Update your database connection details

                    stmt = conn.createStatement();
                    rs = stmt.executeQuery("SELECT drinkname, rate FROM beverages");

                    while (rs.next()) {
                        String drinkName = rs.getString("drinkname");
                        int rate = rs.getInt("rate");
            %>
                        <option value="<%= drinkName %>"><%= drinkName %> - $<%= rate %></option>
            <%
                    }
                } catch (SQLException e) {
                    e.printStackTrace();
                } finally {
                    if (rs != null) rs.close();
                    if (stmt != null) stmt.close();
                    if (conn != null) conn.close();
                }
            %>
        </select>
        <br>
        <input type="submit" value="Submit">
    </form>
</body>
</html>
