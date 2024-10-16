
import React, { useEffect, useState } from "react";
import axios from "axios";
import "./styles.css";
import AddTodo from "./AddTodo";

interface Todo {
  todoId: number;
  todoTitle: string;
  todoDescription: string;
  todoIsCompleted: boolean;
}

const AxiosExample: React.FC = () => {
  const [posts, setPosts] = useState<Todo[]>([]);
  const [showAddForm, setShowAddForm] = useState(false); // State for form visibility
  const [editMode, setEditMode] = useState(false); // State for edit mode
  const [editedTodo, setEditedTodo] = useState<Todo | null>(null); // State for the todo being edited


  const fetchData = async () => {
    const response = await axios.get<Todo[]>("http://localhost:8080/api/todos");
    setPosts(response.data);
  };

  useEffect(() => {
    fetchData();
  }, []);

  const complete = async (id: number, title: string, description: string) => {
    await axios.put(`http://localhost:8080/api/todos/${id}`, {
      todoIsCompleted: true,
      todoTitle: title,
      todoDescription: description,
    });
    fetchData();
  };

  const inComplete = async (id: number, title: string, description: string) => {
    await axios.put(`http://localhost:8080/api/todos/${id}`, {
      todoIsCompleted: false,
      todoTitle: title,
      todoDescription: description,
    });
    fetchData();
  };

  const addTodo = async (title: string, description: string) => {
    const response = await axios.post<Todo>("http://localhost:8080/api/todos", {
      todoTitle: title,
      todoDescription: description,
      todoIsCompleted: false,
    });

    const newTodo: Todo = response.data;
    setPosts([...posts, newTodo]);
    setShowAddForm(false);
  };

  const startEditing = (todo: Todo) => {
    setEditMode(true);
    setEditedTodo(todo);
  };

  const cancelEditing = () => {
    setEditMode(false);
    setEditedTodo(null);
  };

  const saveChanges = async () => {
    if (editedTodo) {
      await axios.put(`http://localhost:8080/api/todos/${editedTodo.todoId}`, {
        todoTitle: editedTodo.todoTitle,
        todoDescription: editedTodo.todoDescription,
        todoIsCompleted: editedTodo.todoIsCompleted,
      });
      fetchData();
      setEditMode(false);
      setEditedTodo(null);
    }
  };

  const deleteTodo = async (id: number) => {
    await axios.delete(`http://localhost:8080/api/todos/${id}`);
    fetchData();
  };
  

  return (
    <div className="table-container">
      <h1 className="header">Todo Management Application</h1>
      <br/><br/>
      <h1 className="heading">List of Todos</h1>
      <br /><br /><br />
     
      <button className="add-button" type="button" onClick={() => setShowAddForm(true)}>Add Todo</button>

      {showAddForm && <AddTodo addTodo={addTodo}></AddTodo>}
      
      <table>
        <thead>
          <tr>
            <th>Todo Title</th>
            <th>Todo Description</th>
            <th>Todo Completed</th>
            <th>Actions</th>
          </tr>
        </thead>
        <tbody>
          {posts.map((temp) => (
            <tr key={temp.todoId}>
              <td>{editMode && editedTodo?.todoId === temp.todoId ? (
                <input
                  type="text"
                  value={editedTodo.todoTitle}
                  onChange={(e) => setEditedTodo({ ...editedTodo, todoTitle: e.target.value })}
                />
              ) : temp.todoTitle}</td>
              <td>{editMode && editedTodo?.todoId === temp.todoId ? (
                <input
                  type="text"
                  value={editedTodo.todoDescription}
                  onChange={(e) => setEditedTodo({ ...editedTodo, todoDescription: e.target.value })}
                />
              ) : temp.todoDescription}</td>
              <td>{temp.todoIsCompleted ? "YES" : "NO"}</td>
              <td>
                {editMode && editedTodo?.todoId === temp.todoId ? (
                  <>
                    <button className="save-button" onClick={saveChanges}>Save</button>
                    <button className="cancel-button" onClick={cancelEditing}>Cancel</button>
                  </>
                ) : (
                  <>
                    <button className="update-button" onClick={() => startEditing(temp)}>Update</button>
                    <button className="delete-button" onClick={() => deleteTodo(temp.todoId)}>Delete</button>
                    <button className="complete-button" onClick={() => complete(temp.todoId, temp.todoTitle, temp.todoDescription)}>Complete</button>
                    <button className="incomplete-button" onClick={() => inComplete(temp.todoId, temp.todoTitle, temp.todoDescription)}>In Complete</button>
                  </>
                )}
              </td>
            </tr>
          ))}
        </tbody>
      </table>
      <footer>
        <p>©Copyrights Reserved</p>
      </footer>
    </div>
  );
};

export default AxiosExample;

