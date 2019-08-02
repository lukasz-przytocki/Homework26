package com.lukasz.homework;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.TypedQuery;
import java.time.LocalDate;
import java.util.List;

@Controller
public class TaskController {

    @Autowired
    private EntityManagerFactory entityManagerFactory;

    @GetMapping("/")
    public String home(){
        return "home";
    }

    @GetMapping("/notDone")
    public String toDoTasks(Model model){
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        TypedQuery<Task> query= entityManager.createQuery("select t from Task t  where t.isDone=:isDone order by t.toDate asc", Task.class);
         query.setParameter("isDone", "no");
        List<Task> tasksList = query.getResultList();
        model.addAttribute("tasks",tasksList);
        return "notDone";
    }
    @GetMapping("/archive")
    public String archiveTasks(Model model){
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        TypedQuery<Task> query= entityManager.createQuery("select t from Task t  where t.isDone=:isDone order by t.toDate desc", Task.class);
        query.setParameter("isDone", "yes");
        List<Task> tasksList = query.getResultList();
        model.addAttribute("tasks",tasksList);
        return "archive";
    }

    @PostMapping("/add")
    public String add(Model model, @RequestParam String task, @DateTimeFormat(iso=DateTimeFormat.ISO.DATE)  @ModelAttribute LocalDate toDate, TaskCategory category){
        Task task1 = new Task(task,toDate,category, "no");
        EntityManager entityManager= entityManagerFactory.createEntityManager();
        entityManager.getTransaction().begin();
        entityManager.persist(task1);
        entityManager.getTransaction().commit();
        return "home";
    }

}
