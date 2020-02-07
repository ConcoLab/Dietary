package models;

import java.util.Date;

public class Meal {
    private long id;
    private Date date;

    public Meal(long id, Date date){
        this.id = id;
        this.date = date;
    }

    public long getId(){
        return id;
    }

    public void setId(long id){
        this.id = id;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }
}
