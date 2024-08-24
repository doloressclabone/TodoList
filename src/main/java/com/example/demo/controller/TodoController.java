package com.example.demo.controller;

import com.example.demo.dto.ResponseDTO;
import com.example.demo.dto.TodoDTO;
import com.example.demo.model.TodoEntity;
import com.example.demo.service.TodoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/todo")
public class TodoController {

    @Autowired
    private TodoService service;

    @GetMapping("/test")
    public ResponseEntity<?> testTodo(){
        String str = service.testService(); // 테스트 서비스 사용
        List<String> list = new ArrayList<>();
        list.add(str);
        ResponseDTO<String> response = ResponseDTO.<String>builder().data(list).build();
        return ResponseEntity.ok().body(response);
    }

    @GetMapping("/list")
    public ResponseEntity<?> getTodoList(){
        List<TodoEntity> todoEntityList = service.getAllTodoList();
        List<TodoDTO> todoDTOList = todoEntityList.stream()
                .map(
                entity -> TodoDTO
                        .builder()
                        .id(entity.getId()).title(entity.getTitle()).done(entity.isDone())
                        .build()
                )
                        .collect(Collectors.toList());
        return ResponseEntity.ok().body(todoDTOList);
    }

    @PostMapping("/todoItem")
    public String createTodoItem(
            @RequestBody String title
    ){
        //제목만 설정함. 제목은 스트링 타입. 이걸 쿼리스트링으로 받아도 된다고 생각함. 별로 보안이랑 관련 없음.
        service.createTodoItem(TodoEntity.builder().title(title).build());
        return "redirect:/todo/add";
    }

    @GetMapping("/add")
    public String add(){
        return "redirect:/todo/list";
    }
}
