package org.example;

import java.sql.*;
import java.util.Scanner;

/**
 * Hello world!
 *
 */
public class BankApplication
{
    public   static final String url = "jdbc:mysql://localhost:3306/bank";
    public   static final String username = "root";
    public static final String password = "G7596@golu#";

    public static Connection connection;

    public static void  main( String[] args ){
        try {
            connection = DriverManager.getConnection(url, username, password);
            OperationBank.createDataBaseBank();
            OperationBank.BankInformation1();
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }

    }

}
