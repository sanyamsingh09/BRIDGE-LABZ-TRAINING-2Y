class BookNode {
    String bookId, title, author, genre;
    boolean isAvailable;
    BookNode prev, next;

    public BookNode(String id, String t, String a, String g) {
        bookId = id;
        title = t;
        author = a;
        genre = g;
        isAvailable = true;
        prev = next = null;
    }
}

class LibraryManager {
    private BookNode head, tail;
    private int totalBooks = 0;

    // Add book
    public void addBook(String id, String t, String a, String g) {
        BookNode newBook = new BookNode(id, t, a, g);

        if (head == null) {
            head = tail = newBook;
        } else {
            tail.next = newBook;
            newBook.prev = tail;
            tail = newBook;
        }

        totalBooks++;
        System.out.println("Book added");
    }

    // Toggle availability
    public void toggleAvailability(String id) {
        BookNode current = head;

        while (current != null) {
            if (current.bookId.equalsIgnoreCase(id)) {
                current.isAvailable = !current.isAvailable;

                if (current.isAvailable)
                    System.out.println("Book is now AVAILABLE");
                else
                    System.out.println("Book is now ISSUED");

                return;
            }
            current = current.next;
        }

        System.out.println("Book not found");
    }

    // Remove book
    public void removeBook(String id) {
        if (head == null) {
            System.out.println("Library empty");
            return;
        }

        BookNode current = head;

        while (current != null) {
            if (current.bookId.equalsIgnoreCase(id)) {

                // Remove head
                if (current == head) {
                    head = head.next;
                    if (head != null) head.prev = null;
                    else tail = null;
                }

                // Remove tail
                else if (current == tail) {
                    tail = tail.prev;
                    tail.next = null;
                }

                // Middle node
                else {
                    current.prev.next = current.next;
                    current.next.prev = current.prev;
                }

                totalBooks--;
                System.out.println("Book removed");
                return;
            }
            current = current.next;
        }

        System.out.println("Book not found");
    }

    // Display all books
    public void displayBooks() {
        if (head == null) {
            System.out.println("No books in library");
            return;
        }

        BookNode current = head;

        while (current != null) {
            System.out.println("ID: " + current.bookId +
                    ", Title: " + current.title +
                    ", Author: " + current.author +
                    ", Genre: " + current.genre +
                    ", Status: " + (current.isAvailable ? "Available" : "Issued"));
            current = current.next;
        }
    }

    // Count books
    public int getCount() {
        return totalBooks;
    }
}

public class Main {
    public static void main(String[] args) {

        LibraryManager lm = new LibraryManager();

        // Add books
        lm.addBook("B1", "Java Basics", "James", "Programming");
        lm.addBook("B2", "DSA", "Mark", "Education");
        lm.addBook("B3", "AI Guide", "John", "Technology");

        System.out.println("\nAll Books:");
        lm.displayBooks();

        // Toggle availability
        System.out.println("\nToggling B2:");
        lm.toggleAvailability("B2");

        // Remove book
        System.out.println("\nRemoving B1:");
        lm.removeBook("B1");

        System.out.println("\nFinal Library:");
        lm.displayBooks();

        System.out.println("\nTotal Books: " + lm.getCount());
    }
}