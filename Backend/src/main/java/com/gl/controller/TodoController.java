package com.gl.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.gl.dto.TodoDto;
import com.gl.serviceimpl.TodoServiceImplementation;

import lombok.AllArgsConstructor;

@CrossOrigin("*")
@RestController
@RequestMapping("/api/todos")
public class TodoController {

	@Autowired
	private TodoServiceImplementation todoService;
	
	@PostMapping
	public ResponseEntity<TodoDto> createTodo(@RequestBody TodoDto todoDto){
		TodoDto saveTodo = todoService.createTodo(todoDto);
		return new ResponseEntity<>(saveTodo, HttpStatus.CREATED);
	}
	
	@GetMapping("{id}")
	public ResponseEntity<TodoDto> getTodoById(@PathVariable("id") Long todoId){
		TodoDto todoDto=todoService.getTodoById(todoId);
		return ResponseEntity.ok(todoDto);
	}
		
	@GetMapping
	public ResponseEntity<List<TodoDto>> getAllTodos(){
		List<TodoDto> todo=todoService.getAllTodos();
		return ResponseEntity.ok(todo);
	}
	
	@PutMapping("{id}")
	public ResponseEntity<TodoDto> updtaeTodo(@PathVariable("id") Long todoId,@RequestBody TodoDto updatedTodo){
		TodoDto todoDto = todoService.updateTodo(todoId, updatedTodo);
		return ResponseEntity.ok(todoDto);
	}
	
	@DeleteMapping("{id}")
	public ResponseEntity<String> deleteTodo(@PathVariable("id") Long todoId){
	    todoService.deleteTodo(todoId);
	    return ResponseEntity.ok("Todo Activity Deleted Successfully..!");
	}
}
