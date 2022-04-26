package com.amaderu.SpringArtifact;

import org.h2.value.ValueUuid;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.sql.*;
import java.util.UUID;

@SpringBootApplication
public class SpringServiceApplication {

    private static final String url = "jdbc:h2:D:/Project/Java/IDEA/SpringArtifact/db";
    private static final String user = "admin";
    private static final String password = "admin";

    private static Connection con;
    private static Statement stmt;
    private static ResultSet rs;

    public static void main(String[] args) {

        SpringApplication.run(SpringServiceApplication.class, args);
        try {
            /*Class.forName("org.h2.Driver");*/
            con = DriverManager.getConnection(url, user, password);
            stmt = con.createStatement();
            System.out.println(con.getSchema());


            /*UUID uuid = UUID.randomUUID();
            System.out.println(uuid.toString().replace("-",""));*/
            /*String sql = "INSERT INTO PUBLIC.ARTIFACT (ID, CREATED, USERID, CATEGORY, DESCRIPTION) VALUES (RANDOM_UUID()"+",'2022-04-25 23:30:44.000000', 'asdas', 'asdas', 'asdas');";
            stmt.executeUpdate(sql);*/
            rs = stmt.executeQuery("Select * FROM PUBLIC.ARTIFACT;");

            System.out.println(rs);
            while (rs.next()) {
                int i = 2;
                System.out.println(rs.getObject(1, UUID.class));
                System.out.println(rs.getObject(i++));
                System.out.println(rs.getObject(i++));
                System.out.println(rs.getObject(i++));
                System.out.println(rs.getObject(i));
                System.out.println("------------------------------------");
            }

        } catch (SQLException e) {
            e.printStackTrace();
        } /*catch (ClassNotFoundException e) {
			e.printStackTrace();
		} */
        try {
            con.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        try {
            stmt.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }

}
