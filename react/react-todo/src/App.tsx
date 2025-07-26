import "./App.css";
import { useState } from "react";
import Lists from "./components/Lists";
import Form from "./components/Form";

const storedData = localStorage.getItem('todoData');
const initialData = storedData ? JSON.parse(storedData) : [];

export default function App() {
    const [todoData, setTodoData] = useState(initialData);

    const [value, setValue] = useState("");



    return (
        <div className="App">
            <h1>Todo List</h1>

            <Lists
                todoData={todoData}
                setTodoData={setTodoData}
            />

            <Form
                value={value}
                setValue={setValue}
                todoData={todoData}
                setTodoData={setTodoData}
            />
        </div>
    );
};
