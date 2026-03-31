class MovieNode {
    String title, director;
    int year;
    double rating;
    MovieNode prev, next;

    public MovieNode(String t, String d, int y, double r) {
        title = t; director = d; year = y; rating = r;
    }
}

class MovieManager {
    private MovieNode head, tail;

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

    public void removeMovie(String title) {
        MovieNode current = head;
        while (current != null) {
            if (current.title.equals(title)) {
                if (current.prev != null) current.prev.next = current.next;
                else head = current.next; // Removing head

                if (current.next != null) current.next.prev = current.prev;
                else tail = current.prev; // Removing tail
                return;
            }
            current = current.next;
        }
    }

    public void displayReverse() {
        MovieNode current = tail;
        while (current != null) {
            System.out.println(current.title + " - " + current.rating);
            current = current.prev;
        }
    }
}