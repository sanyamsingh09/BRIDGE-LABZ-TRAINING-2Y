class BookNode {
    String bookId, title, author, genre;
    boolean isAvailable;
    BookNode prev, next;

    public BookNode(String id, String t, String a, String g) {
        bookId = id; title = t; author = a; genre = g; isAvailable = true;
    }
}

class LibraryManager {
    private BookNode head, tail;
    private int totalBooks = 0;

    public void addBook(String id, String t, String a, String g) {
        BookNode newBook = new BookNode(id, t, a, g);
        if (head == null) head = tail = newBook;
        else {
            tail.next = newBook;
            newBook.prev = tail;
            tail = newBook;
        }
        totalBooks++;
    }

    public void toggleAvailability(String id) {
        BookNode current = head;
        while (current != null) {
            if (current.bookId.equals(id)) {
                current.isAvailable = !current.isAvailable;
                return;
            }
            current = current.next;
        }
    }

    public int getCount() { return totalBooks; }
}