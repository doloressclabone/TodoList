package com.example.demo.service;

import com.example.demo.model.TodoEntity;
import com.example.demo.persistence.TodoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TodoService {
    @Autowired
    private TodoRepository repository;

    public String testService(){
        //TodoEntity 생성
        TodoEntity entity = TodoEntity.builder().title("My first todo item").build();
        //TodoEntity 저장
        repository.save(entity);
        //TodoEntity 검색
        TodoEntity savedEntity = repository.findById(entity.getId()).get();
        return savedEntity.getTitle();
    }
    public String testService2(){
        return "test Service";
    }

    public TodoEntity createTodoItem(TodoEntity todoEntity){
        return repository.save(todoEntity);
    }

    public List<TodoEntity> getAllTodoList(){
        List<TodoEntity> todoEntityList = repository.findAll();
        return todoEntityList;
    }
}
