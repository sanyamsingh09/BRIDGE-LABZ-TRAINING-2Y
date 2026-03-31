class TextStateNode {
    String content;
    TextStateNode prev, next;

    public TextStateNode(String c) { content = c; }
}

class TextEditor {
    private TextStateNode head, tail, currentState;
    private int historySize = 0;
    private final int MAX_HISTORY = 10;

    public void type(String text) {
        TextStateNode newState = new TextStateNode(text);
        
        // If we typed after undoing, discard the redo history
        if (currentState != null) currentState.next = null; 
        
        if (head == null) {
            head = tail = currentState = newState;
        } else {
            currentState.next = newState;
            newState.prev = currentState;
            currentState = tail = newState;
        }
        
        historySize++;
        if (historySize > MAX_HISTORY) {
            head = head.next;
            head.prev = null;
            historySize--;
        }
    }

    public void undo() {
        if (currentState != null && currentState.prev != null) {
            currentState = currentState.prev;
        }
    }

    public void redo() {
        if (currentState != null && currentState.next != null) {
            currentState = currentState.next;
        }
    }
}