
import React, { useState } from "react";

interface Props {
  addTodo: (title: string, description: string) => void; // Function type for adding todo
}

const AddTodo: React.FC<Props> = ({ addTodo }) => {
  const [todoTitle, setTodoTitle] = useState("");
  const [todoDescription, setTodoDescription] = useState("");

  const handleSubmit = (e: React.FormEvent<HTMLFormElement>) => {
    e.preventDefault();
    addTodo(todoTitle, todoDescription);
    setTodoTitle("");
    setTodoDescription("");
  };

  return (
    <form className="form" onSubmit={handleSubmit}>
      <input
        className="input"
        type="text"
        placeholder="Todo Title"
        value={todoTitle}
        onChange={(e) => setTodoTitle(e.target.value)}
        required
      />
      <input
        className="input"
        type="text"
        placeholder="Todo Description"
        value={todoDescription}
        onChange={(e) => setTodoDescription(e.target.value)}
      />
      <button type="submit">Enter</button>
    </form>
  );
};

export default AddTodo;
