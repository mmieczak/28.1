package pl.mmieczak.exercises.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pl.mmieczak.exercises.model.Task;

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
    public void delete(Long id) {
        Task t = entityManager.find(Task.class, id);
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

    public List<Task> findOngoing(Enum name) {
        TypedQuery<Task> selectActualTasks = entityManager.createQuery("SELECT t FROM Task t WHERE t.status=?1 ORDER BY t.taskEndDate ASC", Task.class);
        return selectActualTasks.setParameter(1, name).getResultList();
    }

    public List<Task> findArchived(Enum status) {
        TypedQuery<Task> selectActualTasks = entityManager.createQuery("SELECT t FROM Task t WHERE t.status=:status ORDER BY t.taskEndDate ASC", Task.class);
        return selectActualTasks.setParameter("status", status).getResultList();
    }
}
