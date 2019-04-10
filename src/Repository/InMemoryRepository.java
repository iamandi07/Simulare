package Repository;
import Domain.*;
import java.util.*;

public class InMemoryRepository<T extends Entity> implements IRepository<T> {

    private Map<String, T> storage = new HashMap<>();
    private IValidator<T> validator;

    public InMemoryRepository(IValidator<T> validator) {this.validator = validator;}

    public T findById(String id) {return storage.get(id);}

    public void AddAndUpdate(T entity) {
        validator.validate(entity);
        storage.put(entity.getId(), entity);
    }

    public void Remove(String id) {
        if (!storage.containsKey(id)) {
//            throw new RuntimeException("There are no entities with the given Id!");
            throw new RuntimeException();
        }
        storage.remove(id);
    }

    public ArrayList<T> getAll() {
        return new ArrayList<>(storage.values());
    }
}