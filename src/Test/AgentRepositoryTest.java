package Test;

import Domain.Agent;
import Domain.AgentValidator;
import Repository.IRepository;
import Repository.InMemoryRepository;

import static org.junit.jupiter.api.Assertions.*;

public class AgentRepositoryTest {

    @org.junit.jupiter.api.Test
    void findByIdWithExistingIdShouldReturnCorrectCar() {

        IRepository<Agent> repo = new InMemoryRepository<>(new AgentValidator());
        Agent added = new Agent("1", "Szabi", "07.04.1992", 30, "10:30");
        repo.AddAndUpdate(added);
        Agent found = repo.findById("1");
        assertNotNull(found, "Returned null for existing id!");
        assertEquals("1", found.getId(), String.format("Returned id %s instead of correct id=1!", found.getId()));
        assertEquals("test", found.getId(), String.format("Returned name=%s instead of %s", found.getId(), added.getId()));
        // ... so on
    }

    @org.junit.jupiter.api.Test
    void AddAndUpdate() {
    }

    @org.junit.jupiter.api.Test
    void Remove() {
    }

    @org.junit.jupiter.api.Test
    void getAll() {
    }
}
