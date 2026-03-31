class TextStateNode {
    String content;
    TextStateNode prev, next;

    public TextStateNode(String c) {
        content = c;
        prev = next = null;
    }
}

class TextEditor {
    private TextStateNode head, tail, currentState;
    private int historySize = 0;
    private final int MAX_HISTORY = 10;

    // Type new text
    public void type(String text) {
        TextStateNode newState = new TextStateNode(text);

        // Clear redo history
        if (currentState != null && currentState.next != null) {
            currentState.next = null;
            tail = currentState; // 
        }

        // First node
        if (head == null) {
            head = tail = currentState = newState;
        } else {
            currentState.next = newState;
            newState.prev = currentState;
            currentState = newState;
            tail = newState;
        }

        historySize++;

        // Maintain max history
        if (historySize > MAX_HISTORY) {
            head = head.next;
            if (head != null) head.prev = null;
            historySize--;
        }
    }

    // Undo
    public void undo() {
        if (currentState != null && currentState.prev != null) {
            currentState = currentState.prev;
        } else {
            System.out.println("Nothing to undo");
        }
    }

    // Redo
    public void redo() {
        if (currentState != null && currentState.next != null) {
            currentState = currentState.next;
        } else {
            System.out.println("Nothing to redo");
        }
    }

    // Show current text
    public void show() {
        if (currentState == null) {
            System.out.println("Empty editor");
        } else {
            System.out.println("Current Text: " + currentState.content);
        }
    }
}

public class Main {
    public static void main(String[] args) {

        TextEditor editor = new TextEditor();

        editor.type("Hello");
        editor.type("Hello World");
        editor.type("Hello World!");

        editor.show();

        System.out.println("\nUndo:");
        editor.undo();
        editor.show();

        System.out.println("\nUndo:");
        editor.undo();
        editor.show();

        System.out.println("\nRedo:");
        editor.redo();
        editor.show();

        System.out.println("\nTyping after undo (clears redo):");
        editor.type("New Text");
        editor.show();

        System.out.println("\nRedo attempt:");
        editor.redo(); // should fail
    }
}