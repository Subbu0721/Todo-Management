package com.gl.service;

import java.util.List;

import com.gl.dto.TodoDto;

public interface TodoService {
	
	public TodoDto createTodo(TodoDto todoDto);
	
	public TodoDto getTodoById(Long todoId);
	
	public List<TodoDto> getAllTodos();
	
	public TodoDto updateTodo(Long todoId, TodoDto updateTodoDetails);
	
	public void deleteTodo(Long todoId);
	
	

}
