import { useState } from "react";

export default function List(
    props: {
        id: number,
        title: string,
        completed: boolean,
        todoData: Array<{ id: number; title: string; completed: boolean }>,
        setTodoData: React.Dispatch<React.SetStateAction<Array<{ id: number; title: string; completed: boolean }>>>
    }
) {
    const { id, title, completed, todoData, setTodoData } = props;

    const [isEditing, setIsEditing] = useState(false);
    const [editValue, setEditValue] = useState(title);

    const btnStyle = {
        color: '#fff',
        border: 'none',
        padding: '5px 10px',
        borderRadius: '50%',
        cursor: 'pointer',
        cssFloat: 'right',
    };

    const getStyle = (completed: boolean) => {
        return {
            padding: '10px',
            borderBottom: '1px #ccc dotted',
            textDecoration: completed ? 'line-through' : 'none',
        };
    };
    
    const handleClick = (id: number) => {
        const newTodo = todoData.filter(todo => todo.id !== id);
        setTodoData(newTodo);
        localStorage.setItem('todoData', JSON.stringify(newTodo));
    };

    const handleToggle = (id: number) => {
        setTodoData(todoData.map(todo =>
            todo.id === id ? { ...todo, completed: !todo.completed } : todo
        ));
    };

    const handleSaveClick = () => {
        if (editValue.trim() === "") return;
        const newLocal = todoData.map(todo => todo.id === id ? { ...todo, title: editValue } : todo
        );
        setTodoData(newLocal);
        setIsEditing(false);
        localStorage.setItem('todoData', JSON.stringify(newLocal));
    };


    if (isEditing) {
        return (
            <form style={getStyle(completed)} onSubmit={handleSaveClick}>
                <input value={editValue} onChange={(e) => setEditValue(e.target.value)} autoFocus />
                <button
                    type="button"
                    style={btnStyle}
                    onClick={() => setIsEditing(false)}
                >
                    X
                </button>
                <button
                    type="submit"
                    style={btnStyle}
                >
                    save
                </button>
            </form>
        )
    }

    return <div key={id} style={getStyle(completed)}>
        <input
            type="checkbox"
            checked={completed}
            onChange={() => handleToggle(id)}
        />
        {title}

        <button
            style={btnStyle}
            onClick={() => handleClick(id)}
        >
            X
        </button>
        <button style={btnStyle} onClick={() => setIsEditing(true)}>
            edit
        </button>
    </div >;
}