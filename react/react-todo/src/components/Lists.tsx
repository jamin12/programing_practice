import List from "./List";

export default function Lists(props: {
    todoData: Array<{ id: number; title: string; completed: boolean }>,
    setTodoData: React.Dispatch<React.SetStateAction<Array<{ id: number; title: string; completed: boolean }>>>
}) {
    const { todoData, setTodoData } = props;

    return <div>
        {
            todoData.map(todo => (
                <List id={todo.id} key={todo.id} title={todo.title} completed={todo.completed} todoData={todoData} setTodoData={setTodoData} />
            ))
        }
    </div>
}