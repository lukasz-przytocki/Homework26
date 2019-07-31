package com.lukasz.homework;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import java.util.List;

@Controller
public class TaskController {

    @Autowired
    private EntityManager entityManager;

    @GetMapping("/")
    public String toDoTasks(Model model){
        TypedQuery<Task> query= entityManager.createQuery("select m from Task m where m.is_done='no'", Task.class);
        List<Task> tasksList = query.getResultList();
        model.addAttribute("tasks",tasksList);
        return "home";
    }


}
