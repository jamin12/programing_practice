export default function Form(props: {
    value: string,
    setValue: React.Dispatch<React.SetStateAction<string>>,
    todoData: Array<{ id: number; title: string; completed: boolean }>,
    setTodoData: React.Dispatch<React.SetStateAction<Array<{ id: number; title: string; completed: boolean }>>>
}) {
    const { value, setValue, todoData, setTodoData } = props;

    const handleChange = (e: React.ChangeEvent<HTMLInputElement>) => {
        setValue(e.target.value);
    }

    const handleSubmit = (e: React.FormEvent<HTMLFormElement>) => {
        e.preventDefault();
        if (!value) return;
        const newLocal = [...todoData, { id: Date.now(), title: value, completed: false }];
        setTodoData(newLocal);
        setValue("");
        localStorage.setItem('todoData', JSON.stringify(newLocal));
    }

    return <form style={{ display: 'flex' }} onSubmit={handleSubmit}>
        <input type="text"
            name="value"
            style={{ flex: 10, padding: '5px' }}
            placeholder="Add a new todo"
            value={value}
            onChange={handleChange}
        />
        <input type="submit"
            value='Add Todo'
            className="btn"
            style={{ flex: 1 }}
        />
    </form>
}