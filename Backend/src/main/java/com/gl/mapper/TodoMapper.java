package com.gl.mapper;

import com.gl.dto.TodoDto;
import com.gl.entity.Todo;

public class TodoMapper {
	
	public static TodoDto mapToTodoDto(Todo todo) {
		return new TodoDto(
				todo.getTodoId(),
				todo.getTodoTitle(),
				todo.getTodoDescription(),
				todo.isTodoIsCompleted()
		);
				
	}
	
	public static Todo mapToTodo(TodoDto todoDto) {
		Todo todo = new Todo();
		todo.setTodoId(todoDto.getTodoId());
		todo.setTodoTitle(todoDto.getTodoTitle());
		todo.setTodoDescription(todoDto.getTodoDescription());
		todo.setTodoIsCompleted(todoDto.isTodoIsCompleted());
		
		return todo;
	}
}
