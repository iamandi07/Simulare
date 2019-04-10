package Domain;

import java.text.SimpleDateFormat;
import java.text.ParseException;

public class AgentValidator implements IValidator<Agent>{

    public void validate(Agent agent) {


        int number = agent.getMinute();
        if (number <= 0) {
            throw new RuntimeException("The minutes can't be 0 or low!");
        }
        String date = agent.getDate();
        SimpleDateFormat format = new SimpleDateFormat("dd.MM.yyyy");
        try {
            format.parse(agent.getDate());
            if (date.charAt(0) < '0' || date.charAt(0) > '3'  ||
                    (date.charAt(1) < '0' || date.charAt(1) > '9' ) ||
                    (date.charAt(2) < '0' || date.charAt(2) > '1' ) ||
                    (date.charAt(3) < '0' || date.charAt(3) > '9' ) ||
                    (date.charAt(4) < '0' || date.charAt(4) > '9' ) ||
                    (date.charAt(5) < '0' || date.charAt(5) > '9' ) ||
                    (date.charAt(6) < '0' || date.charAt(6) > '9' ) ||
                    (date.charAt(7) < '0' || date.charAt(7) > '9' ) );
        } catch (ParseException pe) {
            throw new RuntimeException("The date is not correct!");
        }

        SimpleDateFormat format3 = new SimpleDateFormat("HH:MIN");
        try {
            format3.parse(agent.getStarth());
        } catch (ParseException pe) {
            throw new RuntimeException("The starting time is not in a correct format!");
        }
    }
}
