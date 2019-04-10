package Domain;

import java.util.*;

public class Agent extends Entity {

    private String id, name, date, starth;                               //starth, starting hour
    private int minute;

    public Agent (String id, String name, String date, int minute, String starth) {

        super(id);
        this.id = id;
        this.name = name;
        this.date = date;
        this.minute = minute;
        this.starth = starth;

    }

        public String toString() {
            return "Car{" +
                    "id='" + id + '\'' +
                    ", name='" + name + '\'' +
                    ", date='" + date+
                    ", minute=" + minute +
                    ", starth" + starth +
                    '}';
        }

        public int hashCode() {
            return Objects.hash(id, name, date, minute, starth);
        }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public int getMinute() {
        return minute;
    }

    public void setMinute(int minute) {
        this.minute = minute;
    }

    public String getStarth() {
        return starth;
    }

    public void setStarth(String starth) {
        this.starth = starth;
    }

}
