package dev.adventure.utils;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;


public class ConnectionUtil {

        public static Connection createConnection(){

            try {
                Connection connection = DriverManager.getConnection("jdbc:postgresql://database-1.cvxivljxw8r6.us-east-2.rds.amazonaws.com:5432/postgres?user=postgres&password=mame6536");
                return connection;
            } catch (SQLException sqlException) {
                sqlException.printStackTrace();
                return null;
            }
        }


        public static void main(String[] args) {
            System.out.println(createConnection());
        }
    }


