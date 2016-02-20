package org.fasttrackit.dev.todolist.db;
import org.fasttrackit.dev.todolist.ToDoBean;
import org.fasttrackit.dev.todolist.TweetBean;

import java.sql.*;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;


/**
 * Created by condor on 26/02/15.
 * FastTrackIT, 2015
 * <p/>
 * DEMO ONLY PURPOSES, IT MIGHT CONTAINS INTENTIONALLY ERRORS OR ESPECIALLY BAD PRACTICES
 *
 * make sure you refactor it and remove lots of bad practices like loading the driver multiple times or
 * repeating the same common code multiple times
 *
 * BTW, exercise 1: how we reorg this/refactor in small pieces
 */
public class DBOperations {


    public static void main(String[] a) {

//        try {
//            List<TweetBean> l = DBOperations.readTweetOfAUser(3);
//
////            Iterator i = l.iterator();
////            while(i.hasNext()) {
////                TweetBean tb =(TweetBean)i.next();
////                System.out.println(tb.getValue());
////            }
//
//
//            for (TweetBean tweet : l) {
//                System.out.println(tweet.getUser() + "   ---  " + tweet.getValue());
//            }
//
//        } catch (ClassNotFoundException e) {
//            e.printStackTrace();
//        } catch (SQLException e) {
//            e.printStackTrace();
//        }


        try {
            List<TweetBean> L = DBOperations.readTweetFeed(1);

            for(TweetBean tweet1 : L){
                System.out.println(tweet1.getUser()+ "-----" + tweet1.getValue());
            }


        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }


    }


    public static void addFollower( int u, int r) throws ClassNotFoundException, SQLException {

        // 1. load driver
        Class.forName("org.postgresql.Driver");

        // 2. define connection params to db
        final String URL = "jdbc:postgresql://54.93.65.5:5432/Social_Raul";
        final String USERNAME = "fasttrackit_dev";
        final String PASSWORD = "fasttrackit_dev";

        // 3. obtain a connection
        Connection conn = DriverManager.getConnection(URL, USERNAME, PASSWORD);

        // 4. create a query statement
        PreparedStatement pSt = conn.prepareStatement("INSERT INTO relations(FKU,FKR) VALUES (?,?)");
        pSt.setInt(1,u);
        pSt.setInt(2,r);



        // 5. execute a prepared statement
        int rowsInserted = pSt.executeUpdate();

        // 6. close the objects
        pSt.close();
        conn.close();
    }

    public static List readTweetOfAUser(int userid) throws ClassNotFoundException, SQLException {
        // 1. load driver

        Class.forName("org.postgresql.Driver");

        // 2. define connection params to db
        final String URL = "jdbc:postgresql://54.93.65.5:5432/Social_Raul";
        final String USERNAME = "fasttrackit_dev";
        final String PASSWORD = "fasttrackit_dev";

        // 3. obtain a connection
        Connection conn = DriverManager.getConnection(URL, USERNAME, PASSWORD);

        // 4. create a query statement
        Statement st = conn.createStatement();

        // 5. execute a query
        ResultSet rs = st.executeQuery("SELECT * FROM \"user\", tweet WHERE \"user\".k = tweet.fku AND \"user\".k="+userid );
        // 6. iterate the result set and print the values

        List returnValues = new LinkedList();
        while (rs.next()) {

            TweetBean tb = new TweetBean();
            tb.setValue(rs.getString("value"));
            tb.setData(rs.getDate("tweet_date"));
            tb.setPk(rs.getInt("k"));
            tb.setUser(rs.getString("username"));

            returnValues.add(tb);

        }

        // 7. close the objects
        rs.close();
        st.close();
        conn.close();

        return returnValues;
    }


    public static List readTweetFeed(int userid) throws ClassNotFoundException, SQLException {
        // 1. load driver

        Class.forName("org.postgresql.Driver");

        // 2. define connection params to db
        final String URL = "jdbc:postgresql://54.93.65.5:5432/Social_Raul";
        final String USERNAME = "fasttrackit_dev";
        final String PASSWORD = "fasttrackit_dev";

        // 3. obtain a connection
        Connection conn = DriverManager.getConnection(URL, USERNAME, PASSWORD);

        // 4. create a query statement
        Statement st = conn.createStatement();

        // 5. execute a query
        ResultSet rs = st.executeQuery("SELECT * FROM  tweet,\"user\" WHERE \"user\".k=tweet.fku and fku IN(SELECT \"FKR\" FROM relations WHERE \"FKU\"= "+ userid + ") ORDER BY tweet_date ASC ");
        // 6. iterate the result set and print the values

        List returnValues = new LinkedList();
        while (rs.next()) {

            TweetBean tb = new TweetBean();
            tb.setValue(rs.getString("value"));
            tb.setData(rs.getDate("tweet_date"));
            tb.setPk(rs.getInt("k"));
            tb.setUser(rs.getString("username"));

            returnValues.add(tb);

        }

        // 7. close the objects
        rs.close();
        st.close();
        conn.close();

        return returnValues;
    }

    public static void updateItem(int id, int userid) throws ClassNotFoundException, SQLException {

        // 1. load driver
        Class.forName("org.postgresql.Driver");

        // 2. define connection params to db
        final String URL = "jdbc:postgresql://54.93.65.5:5432/Agenda_Adrian";
        final String USERNAME = "fasttrackit_dev";
        final String PASSWORD = "fasttrackit_dev";

        // 3. obtain a connection
        Connection conn = DriverManager.getConnection(URL, USERNAME, PASSWORD);

        // 4. create a query statement
        PreparedStatement pSt = conn.prepareStatement("UPDATE ToDoo SET isDone=true WHERE id=? and userid=? ");
        pSt.setInt(1, id);
        pSt.setInt(2, userid);



        // 5. execute a prepared statement
        int rowsUpdated = pSt.executeUpdate();

        // 6. close the objects
        pSt.close();
        conn.close();
    }

    public static int login(String user , String password) throws ClassNotFoundException, SQLException {
        // 1. load driver
        Class.forName("org.postgresql.Driver");

        // 2. define connection params to db
        final String URL = "jdbc:postgresql://54.93.65.5:5432/Agenda_Adrian";
        final String USERNAME = "fasttrackit_dev";
        final String PASSWORD = "fasttrackit_dev";

        // 3. obtain a connection
        Connection conn = DriverManager.getConnection(URL, USERNAME, PASSWORD);

        // 4. create a query statement
        Statement st = conn.createStatement();

        // 5. execute a que0
        ResultSet rs = st.executeQuery("SELECT * FROM users where username='"+user+ "' AND password='"+password+ "'");

        // 6. iterate the result set and print the value

     int found=-1;
        while (rs.next()) {

            found=rs.getInt("id");
        }

        // 7. close the objects
        rs.close();
        st.close();
        conn.close();

        return found;
    }

//    private static void demoDelete() throws ClassNotFoundException, SQLException {
//
//        // 1. load driver
//        Class.forName("org.postgresql.Driver");
//
//        // 2. define connection params to db
//        final String URL = "jdbc:postgresql://IP:5432/fast1";
//        final String USERNAME = "fasttrackit_dev";
//        final String PASSWORD = "fasttrackit_dev";
//
//        // 3. obtain a connection
//        Connection conn = DriverManager.getConnection(URL, USERNAME, PASSWORD);
//
//        // 4. create a query statement
//        // PreparedStatement pSt = conn.prepareStatement("DELETE FROM USERS WHERE PK_USER=?");
//        //pSt.setLong(1, 1);
//
//        // 5. execute a prepared statement
//        //int rowsDeleted = pSt.executeUpdate();
//
//        String cititTastatura="'ionel'";
//        String delete = "DELETE FROM USERS WHERE name="+cititTastatura;
//        Statement s = conn.createStatement();
//        s.execute(delete);
//
//        System.out.println(rowsDeleted + " rows were deleted.");
//        // 6. close the objects
//        pSt.close();
//        conn.close();
//    }
}

