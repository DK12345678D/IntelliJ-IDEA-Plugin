package org.example;

public class TodoItem {
    private final String text;
    private final int offset;
    public TodoItem(String text, int offset) {
        this.text = text;
        this.offset = offset;
    }

    public String getText() {
        return text;
    }

    public int getOffset() {
        return offset;
    }

}
