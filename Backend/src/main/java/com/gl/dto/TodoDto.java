package com.gl.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class TodoDto {
	
	private Long todoId;
	
	private String todoTitle;
	
	private String todoDescription;
	
	private boolean todoIsCompleted;

}
