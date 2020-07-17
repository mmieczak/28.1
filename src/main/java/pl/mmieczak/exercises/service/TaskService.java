package pl.mmieczak.exercises.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pl.mmieczak.exercises.model.Task;
import pl.mmieczak.exercises.model.TaskType;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import java.util.List;
import java.util.Optional;

@Service
public class TaskService {

    @PersistenceContext
    private EntityManager entityManager;

    @Transactional
    public void save(Task task) {
        entityManager.persist(task);
    }

    @Transactional
    public void update(Task task) {
        entityManager.merge(task);
    }

    @Transactional
    public void delete(Task task) {
        Task t = entityManager.find(Task.class, task.getId());
        entityManager.remove(t);
    }

    public List<Task> findAll() {
        TypedQuery<Task> selectAllQuery = entityManager.createQuery("SELECT t FROM Task t ORDER BY t.taskEndDate ASC ", Task.class);
        return selectAllQuery.getResultList();
    }

    public Optional<Task> findOneById(Long id) {
        Task task = entityManager.find(Task.class, id);
        return Optional.ofNullable(task);
    }

    public List<Task> findOngoing() {
        TypedQuery<Task> selectActualTasks = entityManager.createQuery("SELECT t FROM Task t WHERE t.status='" + TaskType.ONGOING + "' ORDER BY t.taskEndDate ASC", Task.class);
        return selectActualTasks.getResultList();
    }

    public List<Task> findArchived() {
        TypedQuery<Task> selectActualTasks = entityManager.createQuery("SELECT t FROM Task t WHERE t.status='" + TaskType.FINISHED + "' ORDER BY t.taskEndDate ASC", Task.class);
        return selectActualTasks.getResultList();
    }
}
