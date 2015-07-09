package com.tw.core;

import org.hibernate.Session;

import java.sql.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

/**
 * Created by Vivi on 7/8/15.
 */
public class UserDaoSQL {

    // JDBC driver name and database URL
    static final String DB_URL = "jdbc:mysql://localhost:3306/hello";

    //  Database credentials
    static final String USER = "root";
    static final String PASS = "";

    public User getUserById(int userId){
        Connection conn = null;
        Statement stmt = null;

        User user = new User();

        try{
            //STEP 2: Register JDBC driver
            Class.forName("com.mysql.jdbc.Driver");

            //STEP 3: Open a connection
            System.out.println("Connecting to database...");
            conn = DriverManager.getConnection(DB_URL, USER, PASS);

            //STEP 4: Execute a query
            System.out.println("Creating statement...");
            stmt = conn.createStatement();

            //check the tables
            String sql = "SELECT * FROM people where id="+userId;
            ResultSet rs = stmt.executeQuery(sql);

            //STEP 5: Extract data from result set
            while(rs.next()){
                user.setId(rs.getInt("id"));
                user.setName(rs.getString("name"));
                user.setSex(rs.getString("sex"));
                user.setEmail(rs.getString("email"));
                user.setAge(rs.getInt("age"));
            }

            //STEP 6: Clean-up environment
            rs.close();
            stmt.close();
            conn.close();
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
        return user;
    }

    public List<User> getUsers(){
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

            //STEP 5: Extract data from result set
            while(rs.next()){
                User sqlUser = new User();

                sqlUser.setId(rs.getInt("id"));
                sqlUser.setName(rs.getString("name"));
                sqlUser.setSex(rs.getString("sex"));
                sqlUser.setEmail(rs.getString("email"));
                sqlUser.setAge(rs.getInt("age"));
                userList.add(sqlUser);
            }

            //STEP 6: Clean-up environment
            rs.close();
            stmt.close();
            conn.close();
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
        return userList;
    }

    public void deleteUser(int userId){
        Connection conn = null;
        Statement stmt = null;

        try {
            //STEP 2: Register JDBC driver
            Class.forName("com.mysql.jdbc.Driver");

            //STEP 3: Open a connection
            System.out.println("Connecting to database...");
            conn = DriverManager.getConnection(DB_URL, USER, PASS);

            //STEP 4: Execute a query
            System.out.println("Creating statement...");
            stmt = conn.createStatement();

            //delete from the table
            String sql = "delete from people where id="+userId;
            stmt.executeUpdate(sql);
            System.out.println("Delete records from the table...");
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
    }

    public void updateUser(User user){
        Connection conn = null;
        Statement stmt = null;

        try {
            //STEP 2: Register JDBC driver
            Class.forName("com.mysql.jdbc.Driver");

            //STEP 3: Open a connection
            System.out.println("Connecting to database...");
            conn = DriverManager.getConnection(DB_URL, USER, PASS);

            //STEP 4: Execute a query
            System.out.println("Creating statement...");
            stmt = conn.createStatement();

            //delete from the table
//            String sql = "update people "+
//                    "set name="+user.getName()+
//                    ", sex="+user.getSex()+
//                    ", email="+user.getEmail()+
//                    ", age="+user.getAge()+" where id="+user.getId();
            PreparedStatement st = conn.prepareStatement("update people set name=?, sex=?, email=?, age=? where id=?");
            st.setString(1,user.getName());
            st.setString(2,user.getSex());
            st.setString(3,user.getEmail());
            st.setInt(4,user.getAge());
            st.setInt(5,user.getId());
            st.executeUpdate();

//            System.out.println(sql);
//            stmt.executeUpdate(sql);
            System.out.println("Update records from the table...");
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
    }

//    public void addUserSQL(User user){
//        Connection conn = null;
//        Statement stmt = null;
//
//        try {
//            //STEP 2: Register JDBC driver
//            Class.forName("com.mysql.jdbc.Driver");
//
//            //STEP 3: Open a connection
//            System.out.println("Connecting to database...");
//            conn = DriverManager.getConnection(DB_URL, USER, PASS);
//
//            //STEP 4: Execute a query
//            System.out.println("Creating statement...");
//            stmt = conn.createStatement();
//
//            //delete from the table
////            String sql = "update people "+
////                    "set name="+user.getName()+
////                    ", sex="+user.getSex()+
////                    ", email="+user.getEmail()+
////                    ", age="+user.getAge()+" where id="+user.getId();
//            PreparedStatement st = conn.prepareStatement("insert into people values(NULL,?,?,?,? )");
//            st.setString(1,user.getName());
//            st.setString(2,user.getSex());
//            st.setString(3, user.getEmail());
//            st.setInt(4,user.getAge());
//            //st.setInt(5,user.getId());
//            st.executeUpdate();
//
////            System.out.println(sql);
////            stmt.executeUpdate(sql);
//            System.out.println("Update records from the table...");
//        }catch(SQLException se){
//            //Handle errors for JDBC
//            se.printStackTrace();
//        }catch(Exception e){
//            //Handle errors for Class.forName
//            e.printStackTrace();
//        }finally{
//            //finally block used to close resources
//            try{
//                if(stmt!=null)
//                    stmt.close();
//            }catch(SQLException se2){
//            }// nothing we can do
//            try{
//                if(conn!=null)
//                    conn.close();
//            }catch(SQLException se){
//                se.printStackTrace();
//            }//end finally try
//        }//end try
//    }

    public void addUser(User user) {
        Configuration cfg = new Configuration().configure();
        SessionFactory factory = cfg.buildSessionFactory();

        Session session = factory.openSession();
        session.beginTransaction();

        session.save(user);

        session.getTransaction().commit();

        factory.close();
    }
}
