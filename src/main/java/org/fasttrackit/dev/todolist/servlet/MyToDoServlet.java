package org.fasttrackit.dev.todolist.servlet;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Created by condor on 04/04/15.
 * FastTrackIT, 2015
 */
public class MyToDoServlet extends HttpServlet {

    public void service(HttpServletRequest request, HttpServletResponse response) {


        System.out.println("service called...");
        String myValue  = request.getParameter("myValue");
        System.out.println("myvalue venita din ui este:"+myValue);

    }
}
