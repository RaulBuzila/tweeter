package org.fasttrackit.dev.todolist;

import java.sql.Date;

/**
 * Created by raulbuzila on 2/20/2016.
 */
public class TweetBean {

    int pk;
    String value;
    Date data;

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    String user;


    public int getPk() {
        return pk;
    }

    public void setPk(int pk) {
        this.pk = pk;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public Date getData() {
        return data;
    }

    public void setData(Date data) {
        this.data = data;
    }


}
