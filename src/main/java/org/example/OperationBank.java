package org.example;

import java.sql.*;
import java.util.Scanner;

import static org.example.BankApplication.connection;

public class OperationBank {
    public static Statement statement;
    public static ResultSet resultSet;
    public static void BankInformation1() throws SQLException {

             System.out.println("*********Bank Management System***********\n");
             System.out.println("Click Bottom 1. Insert Data.");
             System.out.println("Click Bottom 2. Fetch Data.");
             System.out.println("Click Bottom 3. Update Data.");
             System.out.println("Click Bottom 4. Delete Data.\n");

             Scanner scanner = new Scanner(System.in);


             System.out.println("Enter Any Choice : ");
             int choice = scanner.nextInt();

             switch (choice)
             {
                 case 1:
                     System.out.println("****** Insert Data ******");
                     System.out.println("Enter the Id :");
                     int id = scanner.nextInt();
                     scanner.nextLine(); // Add this line

                     System.out.println("Enter the AccountHolder Name : ");
                     String accountName = scanner.nextLine();

                     System.out.println("Enter the Account Type [saving or Current Account] :");
                     String accountType = scanner.nextLine();

                     System.out.println("Enter the Account Number : ");
                     String accountNo = scanner.nextLine();

                     System.out.println("Enter the Account Balance : ");
                     double accountBal = scanner.nextDouble();

                     System.out.println("Enter the Phone Number :");
                     String phoneNum = scanner.next();

                     System.out.println("Enter the Age :");
                     int age = scanner.nextInt();

                     System.out.println("Enter the Email Id :");
                     String email = scanner.next();
                     scanner.close();


                     statement = connection.createStatement();
                     int rowCount1 = statement.executeUpdate("insert into accountable values("+id+",'"+accountName+"','"+accountType+"','"+accountNo+"','"+accountBal+"','"+phoneNum+"',"+age+",'"+email+"')");
                     if(rowCount1 >0)
                     {
                         System.out.println("Data Inserted Successfully...");
                     }
                     else
                     {
                         System.out.println("Data Inserted Failed...");
                     }
                     break;

                 case 2:
                     System.out.println("******** AccountHolder Fetch Data ********");
                     resultSet = connection.createStatement().executeQuery("select * from  accountable");
                     while (resultSet.next())
                     {
                         System.out.println("----------------------------------------------------");
                         System.out.println("AccountHolder Id : "+resultSet.getInt("id"));
                         System.out.println("AccountHolder Name : "+resultSet.getString("accountName"));
                         System.out.println("Account Type : "+resultSet.getString("accountType"));
                         System.out.println("Account Number : "+resultSet.getString("accountNo"));
                         System.out.println("Account Balance : "+resultSet.getString("accountBal"));
                         System.out.println("AccountHolder's Phone Number : "+resultSet.getString("phoneNum"));
                         System.out.println("AccountHolder's Age : "+resultSet.getInt("age"));
                         System.out.println("AccountHolder's Email Id : "+resultSet.getString("email"));
                         System.out.println("------------------------------------------------\n");
                     }
                     break;

                 case 3:
                     System.out.println("****** Update Data ******");
                     System.out.println("Enter the AccountHolder Id ");
                     int id3 = scanner.nextInt();

                     PreparedStatement preparedStatement = connection.prepareStatement("UPDATE accountable SET accountName=?, accountType=?, " +
                             "accountNo=?, accountBal=?, phoneNum=?, age=?, email=? WHERE id=?");


                     scanner.nextLine();

                     System.out.println("Enter the AccountHolder Name : ");
                     String accountName1 = scanner.nextLine();
                     preparedStatement.setString(1,accountName1);

                     System.out.println("Enter the Account Type [saving or Current Account] :");
                     String accountType1 = scanner.nextLine();
                     preparedStatement.setString(2,accountType1);

                     System.out.println("Enter the Account Number : ");
                     String accountNo1 = scanner.nextLine();
                     preparedStatement.setString(3,accountNo1);

                     System.out.println("Enter the Account Balance : ");
                     double accountBal1 = scanner.nextDouble();
                     preparedStatement.setString(4, String.valueOf(accountBal1));

                     System.out.println("Enter the Phone Number :");
                     String phoneNum1 = scanner.next();
                     preparedStatement.setString(5,phoneNum1);

                     System.out.println("Enter the Age :");
                     int age1 = scanner.nextInt();
                     preparedStatement.setInt(6,age1 );

                     System.out.println("Enter the Email Id :");
                     String email1 = scanner.next();
                     preparedStatement.setString(7,email1);

                     preparedStatement.setInt(8, id3);

                     int update = preparedStatement.executeUpdate();
                     if (update > 0)
                     {
                         System.out.println("Account Details Updates Successfully...");
                     }
                     else
                     {
                         System.out.println("Account Details Failed...");
                     }


                     break;

                 case 4:
                     System.out.println("****** Delete AccountHolder Id ******");

                     System.out.println("Enter the AccountHolder Id ");
                     int id2 = scanner.nextInt();

                     PreparedStatement preparedStatement1 = connection.prepareStatement("DELETE FROM accountable WHERE id = ?");
                     preparedStatement1.setInt(1, id2);

                     int rowCount2 = preparedStatement1.executeUpdate();

                     if (rowCount2 > 0)
                     {
                         System.out.println("Delete Account Details Successfully for ID : " + id2);
                     }
                     else
                     {
                         System.out.println("No account found with ID : " + id2);
                     }

                     // Don't forget to close resources like preparedStatement and connection
                     preparedStatement1.close();


                     break;

                 default:
                     System.out.println("Invalid choice");
                     break;

             }

    }

     public static void createDataBaseBank() throws SQLException {
         String createTable = "CREATE TABLE IF NOT EXISTS accountable (id INT AUTO_INCREMENT PRIMARY KEY," +
                 "accountName VARCHAR(100)," +
                "accountType VARCHAR(100),"+
                 "accountNo  varchar(20) ,"+
                 "accountBal varchar(10),"+
                 "phoneNum varchar(10),"+
                 "age INT," +
                "email VARCHAR(30)" +
                 ")";

         PreparedStatement preparedStatement = connection.prepareStatement(createTable);
         preparedStatement.execute();
         System.out.println("DataBase Created Successfully...");
     }
}





