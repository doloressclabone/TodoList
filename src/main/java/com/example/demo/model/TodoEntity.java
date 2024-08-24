package com.example.demo.model;

import com.example.demo.dto.TodoDTO;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.UuidGenerator;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Data
@Entity
@Table(name = "Todo")
public class TodoEntity {
    @Id
    @GeneratedValue(generator = "system-uuid")
    @GenericGenerator(name = "system-uuid", strategy = "uuid")
    private String id; // 이 오브젝트의 아이디
    private String userId; // 오브젝트를 생성한 유저의 아이디
    private String title; // 타이틀 예) 운동하기
    private boolean done; // true - todo를 완료한 경우(checked)

    @Builder(builderClassName = "of", builderMethodName = "of")
    public TodoDTO toDto(TodoEntity todoEntity){
        return TodoDTO.builder().id(todoEntity.getId())
                .title(todoEntity.getTitle())
                .done(todoEntity.isDone())
                .build();
    }
}
