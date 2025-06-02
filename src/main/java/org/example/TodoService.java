package org.example;
import java.util.*;
public class TodoService {
    private final Map<String, List<TodoItem>> fileTodos = new HashMap<>();

    public void addTodo(String file, String text, int offset) {
        fileTodos.computeIfAbsent(file, k -> new ArrayList<>()).add(new TodoItem(text, offset));
    }

    public List<TodoItem> getTodos(String file) {
        return fileTodos.getOrDefault(file, Collections.emptyList());
    }

    public void clearTodos(String file) {
        List<TodoItem> list = fileTodos.get(file);
        if (list != null) {
            list.clear();
        }
    }

    private static final TodoService INSTANCE = new TodoService();

    public static TodoService getInstance() {
        return INSTANCE;
    }

}
