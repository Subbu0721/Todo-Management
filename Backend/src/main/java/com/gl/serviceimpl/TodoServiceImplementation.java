package com.gl.serviceimpl;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gl.dto.TodoDto;
import com.gl.entity.Todo;
import com.gl.exception.ResourceNotFoundException;
import com.gl.mapper.TodoMapper;
import com.gl.repository.TodoRepository;
import com.gl.service.TodoService;

@Service
public class TodoServiceImplementation implements TodoService{
	
	@Autowired
	private TodoRepository todoRepository;
	
	@Override
	public TodoDto createTodo(TodoDto todoDto) {
		todoDto.setTodoIsCompleted(false);
		Todo todo = TodoMapper.mapToTodo(todoDto);
		Todo savedTodo=todoRepository.save(todo);
		return TodoMapper.mapToTodoDto(savedTodo);
	}
	
	@Override
	public TodoDto getTodoById(Long todoId) {
		Todo todo=todoRepository.findById(todoId).orElseThrow(()->new ResourceNotFoundException("Todo activity not fountd with Id:"+todoId));
		return TodoMapper.mapToTodoDto(todo);
	}
	
	@Override
	public List<TodoDto> getAllTodos(){
		List<Todo> todos =todoRepository.findAll();
		return todos.stream().map((Todo)->TodoMapper.mapToTodoDto(Todo)).collect(Collectors.toList());
	}
	
	@Override
	public TodoDto updateTodo(Long todoId, TodoDto updateTodoDetails) {
		Todo todo= todoRepository.findById(todoId).orElseThrow(()->new ResourceNotFoundException("Todo activity not found with Id:"+todoId));
		
		todo.setTodoTitle(updateTodoDetails.getTodoTitle());
		todo.setTodoDescription(updateTodoDetails.getTodoDescription());
		todo.setTodoIsCompleted(updateTodoDetails.isTodoIsCompleted());
		
		Todo updatedTodoObj = todoRepository.save(todo);
		return TodoMapper.mapToTodoDto(updatedTodoObj);
	}
	
	@Override
	public void deleteTodo(Long todoId) {
		Todo todo=todoRepository.findById(todoId).orElseThrow(()->new ResourceNotFoundException("Todo activity not found with Id:"+todoId));
		todoRepository.delete(todo);
	}
}