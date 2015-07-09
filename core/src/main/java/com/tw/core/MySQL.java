package com.tw.core;

/**
 * Created by Vivi on 7/7/15.
 */
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
//import com.tw.core.User;

public class MySQL {
    // JDBC driver name and database URL
    static final String DB_URL = "jdbc:mysql://localhost:3306/hello";

    //  Database credentials
    static final String USER = "root";
    static final String PASS = "";

    public List<User> mysql() {
        Connection conn = null;
        Statement stmt = null;
        List<User> userList = new ArrayList<User>();

        //String result = "";

        try{
            //STEP 2: Register JDBC driver
            Class.forName("com.mysql.jdbc.Driver");

            //STEP 3: Open a connection
            System.out.println("Connecting to database...");
            conn = DriverManager.getConnection(DB_URL,USER,PASS);

            //STEP 4: Execute a query
            System.out.println("Creating statement...");
            stmt = conn.createStatement();

            //check the tables
            String sql = "SELECT id,name,sex,email,age FROM people";
            ResultSet rs = stmt.executeQuery(sql);

            //insert into the table
//            String sql = "insert into people "+"values('Jack','m','123@126.com',20)";
//            stmt.executeUpdate(sql);
//            System.out.println("Insert records into the table...");

            //delete from the table
//            String sql = "delete from people " +
//                    "where name = 'Jack'";
//            stmt.executeUpdate(sql);
//            System.out.println("Delete records from the table...");

            //update records in the table
//            String sql = "update people "+
//                    "set age=30 where name='vivi'";
//            stmt.executeUpdate(sql);
//            System.out.println("Update records in the table...");

            //STEP 5: Extract data from result set
            while(rs.next()){
                //Retrieve by column name
//                String name = rs.getString("name");
//                String sex = rs.getString("sex");
//                String email = rs.getString("email");
//                int age = rs.getInt("age");

                User sqlUser = new User();

                sqlUser.setId(rs.getInt("id"));
                sqlUser.setName(rs.getString("name"));
                sqlUser.setSex(rs.getString("sex"));
                sqlUser.setEmail(rs.getString("email"));
                sqlUser.setAge(rs.getInt("age"));
                userList.add(sqlUser);

                //Display values
                //System.out.print("NO: " + no);
                //System.out.print(", NAME: " + name);
                //System.out.println(", SEX: " + sex);
                //System.out.println(", Last: " + last);
                //result = name+"   "+sex+"   "+email+"   "+age;

            }

            //STEP 6: Clean-up environment
//            rs.close();
//            stmt.close();
//            conn.close();
        }catch(SQLException se){
            //Handle errors for JDBC
            se.printStackTrace();
        }catch(Exception e){
            //Handle errors for Class.forName
            e.printStackTrace();
        }finally{
            //finally block used to close resources
            try{
                if(stmt!=null)
                    stmt.close();
            }catch(SQLException se2){
            }// nothing we can do
            try{
                if(conn!=null)
                    conn.close();
            }catch(SQLException se){
                se.printStackTrace();
            }//end finally try
        }//end try
        //System.out.println("Goodbye!");
        //System.out.println(userList.size());
        return userList;
    }//end main
}//end FirstExample - by www.yiibai.com

