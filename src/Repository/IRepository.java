package Repository;

import Domain.*;
import Domain.Entity;

import java.util.*;

public interface IRepository<T extends Entity> {

    T findById(String id);
    void AddAndUpdate(T agent);
    void Remove(String id);
    ArrayList<T> getAll();

}