class MovieNode {
    String title, director;
    int year;
    double rating;
    MovieNode prev, next;

    public MovieNode(String t, String d, int y, double r) {
        title = t;
        director = d;
        year = y;
        rating = r;
        prev = next = null;
    }
}

class MovieManager {
    private MovieNode head, tail;

    // Add movie at end
    public void addMovie(String t, String d, int y, double r) {
        MovieNode newNode = new MovieNode(t, d, y, r);

        if (head == null) {
            head = tail = newNode;
        } else {
            tail.next = newNode;
            newNode.prev = tail;
            tail = newNode;
        }
    }

    // Remove movie by title
    public void removeMovie(String title) {
        if (head == null) {
            System.out.println("List is empty");
            return;
        }

        MovieNode current = head;

        while (current != null) {
            if (current.title.equalsIgnoreCase(title)) {

                // Removing head
                if (current == head) {
                    head = head.next;
                    if (head != null) head.prev = null;
                    else tail = null; // list became empty
                }

                // Removing tail
                else if (current == tail) {
                    tail = tail.prev;
                    tail.next = null;
                }

                // Removing middle node
                else {
                    current.prev.next = current.next;
                    current.next.prev = current.prev;
                }

                System.out.println("Movie removed");
                return;
            }
            current = current.next;
        }

        System.out.println("Movie not found");
    }

    // Display forward
    public void displayForward() {
        if (head == null) {
            System.out.println("No movies available");
            return;
        }

        MovieNode current = head;
        while (current != null) {
            System.out.println(current.title + " (" + current.year + ") - Rating: " + current.rating);
            current = current.next;
        }
    }

    // Display reverse
    public void displayReverse() {
        if (tail == null) {
            System.out.println("No movies available");
            return;
        }

        MovieNode current = tail;
        while (current != null) {
            System.out.println(current.title + " (" + current.year + ") - Rating: " + current.rating);
            current = current.prev;
        }
    }
}

public class Main {
    public static void main(String[] args) {

        MovieManager mm = new MovieManager();

        // Add movies
        mm.addMovie("Inception", "Christopher Nolan", 2010, 8.8);
        mm.addMovie("Interstellar", "Christopher Nolan", 2014, 8.6);
        mm.addMovie("Avatar", "James Cameron", 2009, 7.8);

        System.out.println("Movies (Forward):");
        mm.displayForward();

        System.out.println("\nMovies (Reverse):");
        mm.displayReverse();

        // Remove movie
        System.out.println("\nRemoving 'Interstellar':");
        mm.removeMovie("Interstellar");

        System.out.println("\nAfter Deletion:");
        mm.displayForward();
    }
}