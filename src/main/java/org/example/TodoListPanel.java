package org.example;
import com.intellij.openapi.fileEditor.FileEditorManager;
import com.intellij.openapi.fileEditor.OpenFileDescriptor;
import com.intellij.openapi.project.Project;
import com.intellij.openapi.vfs.VirtualFile;
import com.intellij.ui.components.JBScrollPane;

import javax.swing.*;
import java.awt.*;
import java.util.List;

public class TodoListPanel extends JPanel {
    private final Project project;
    private final DefaultListModel<String> listModel = new DefaultListModel<>();
    private final JList<String> list = new JList<>(listModel);

    public TodoListPanel(Project project) {
        super(new BorderLayout());
        this.project = project;

        add(new JBScrollPane(list), BorderLayout.CENTER);
        refreshTodoList();

        list.addListSelectionListener(e -> {
            String selectedValue = list.getSelectedValue();
            if (selectedValue == null || !selectedValue.contains(" @offset:")) return;

            String[] parts = selectedValue.split(" @offset:");
            if (parts.length != 2) return;

            try {
                int offset = Integer.parseInt(parts[1]);
                VirtualFile file = FileEditorManager.getInstance(project).getSelectedEditor().getFile();
                if (file == null) return;

                OpenFileDescriptor descriptor = new OpenFileDescriptor(project, file, offset);
                FileEditorManager.getInstance(project).openTextEditor(descriptor, true);
            } catch (NumberFormatException ex) {
                ex.printStackTrace();
            }
        });
    }

    public void refreshTodoList() {
        listModel.clear();
        VirtualFile file = FileEditorManager.getInstance(project).getSelectedEditor().getFile();
        if (file == null) return;

        List<TodoItem> todos = TodoService.getInstance().getTodos(file.getPath());
        for (TodoItem todo : todos) {
            listModel.addElement(todo.getText() + " @offset:" + todo.getOffset());
        }
    }

}
