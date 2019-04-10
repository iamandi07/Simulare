package Service;

import Domain.*;
import Repository.IRepository;

import java.util.ArrayList;
import java.util.List;

public class AgentService {

    private IRepository<Agent> repository;

    public AgentService(IRepository<Agent> repository) {

        this.repository = repository;
    }

    public Agent AddAndUpdate(String id, String name, String date, int minute, String starth) {
        Agent agent = repository.findById(id);
        if (agent != null) {
            if (id.isEmpty()) {
                id = agent.getId();
            }
            if (name.isEmpty()) {
                name = agent.getName();
            }
            if (date.isEmpty()) {
                date = agent.getDate();
            }
            if (minute == 0 ) {
                minute = agent.getMinute();
            }
            if (starth.isEmpty()) {
                starth = agent.getStarth();
            }
        }

        Agent agent2 = new Agent(id, name, date, minute, starth);
        repository.AddAndUpdate(agent2);
        return agent;
    }

    public void Remove(String id) {
        repository.Remove(id);
    }


    public ArrayList<Agent> getAll() {
        ArrayList<Agent> temp = repository.getAll();
        return temp;
    }



}
