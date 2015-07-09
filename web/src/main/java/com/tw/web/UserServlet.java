package com.tw.web;

import java.io.*;
import java.util.List;
import javax.servlet.http.*;
import javax.servlet.*;

import com.tw.core.MySQL;
import com.tw.core.User;
import com.tw.core.UserDao;

/**
 * Created by Vivi on 7/8/15.
 */
public class UserServlet extends HttpServlet {

    public UserDao userDao = new UserDao();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
        String action = req.getParameter("action");
        String DELETE_ACTION = "deleteUser";
        String UPDATE_ACTION = "updateUser";

        if (DELETE_ACTION.equals(action)) {
            int userId = Integer.parseInt(req.getParameter("userId"));
            userDao.deleteUser(userId);
            req.setAttribute("userList", userDao.getUsers());
            req.getRequestDispatcher("/index.jsp").forward(req, res);
        }
        else if (UPDATE_ACTION.equals(action)){
            int userId = Integer.parseInt(req.getParameter("userId"));
            User user = userDao.getUserById(userId);
            req.setAttribute("user",user);
            req.getRequestDispatcher("/user.jsp").forward(req,res);
        }
        else {
            PrintWriter out = res.getWriter();
            List<User> userList = new MySQL().mysql();
            req.setAttribute("userList",userList);
            req.getRequestDispatcher("index.jsp").forward(req, res);
            out.println("hi");
            out.close();
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
        User user = new User();

        user.setName(req.getParameter("name"));
        user.setSex(req.getParameter("sex"));
        user.setEmail(req.getParameter("email"));
        user.setAge(Integer.parseInt(req.getParameter("age")));

        String userId = req.getParameter("id");

        System.out.println(userId);
        if (userId == null || userId.isEmpty()){
            userDao.addUser(user);
        }
        else{
            user.setId(Integer.parseInt(userId));
            userDao.updateUser(user);
        }

        req.setAttribute("userList", userDao.getUsers());
        req.getRequestDispatcher("/index.jsp").forward(req, res);
    }

}


