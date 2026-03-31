class TicketNode {
    String ticketId, customerName, movieName, seatNumber, bookingTime;
    TicketNode next;

    public TicketNode(String id, String cName, String mName, String seat, String time) {
        ticketId = id;
        customerName = cName;
        movieName = mName;
        seatNumber = seat;
        bookingTime = time;
        next = null;
    }
}

class TicketSystem {
    private TicketNode tail; // circular list
    private int totalTickets = 0;

    // Book ticket
    public void bookTicket(String id, String cName, String mName, String seat, String time) {
        TicketNode newTicket = new TicketNode(id, cName, mName, seat, time);

        if (tail == null) {
            tail = newTicket;
            tail.next = tail;
        } else {
            newTicket.next = tail.next; // head
            tail.next = newTicket;
            tail = newTicket;
        }
        totalTickets++;
        System.out.println("Ticket booked successfully");
    }

    // Display all tickets
    public void displayTickets() {
        if (tail == null) {
            System.out.println("No tickets booked");
            return;
        }

        TicketNode current = tail.next; // head
        do {
            System.out.println("ID: " + current.ticketId +
                    ", Name: " + current.customerName +
                    ", Movie: " + current.movieName +
                    ", Seat: " + current.seatNumber +
                    ", Time: " + current.bookingTime);
            current = current.next;
        } while (current != tail.next);
    }

    // Cancel ticket by ID
    public void cancelTicket(String id) {
        if (tail == null) {
            System.out.println("No tickets to cancel");
            return;
        }

        TicketNode current = tail.next; // head
        TicketNode prev = tail;

        do {
            if (current.ticketId.equals(id)) {

                // Only one node
                if (current == tail && current.next == tail) {
                    tail = null;
                }
                // Deleting tail
                else if (current == tail) {
                    prev.next = current.next;
                    tail = prev;
                }
                // Deleting head
                else if (current == tail.next) {
                    tail.next = current.next;
                }
                // Middle node
                else {
                    prev.next = current.next;
                }

                totalTickets--;
                System.out.println("Ticket cancelled");
                return;
            }

            prev = current;
            current = current.next;

        } while (current != tail.next);

        System.out.println("Ticket not found");
    }

    // Count tickets
    public int getTotalTickets() {
        return totalTickets;
    }
}

public class Main {
    public static void main(String[] args) {

        TicketSystem ts = new TicketSystem();

        // Booking tickets
        ts.bookTicket("T1", "Sanyam", "Inception", "A1", "10:00 AM");
        ts.bookTicket("T2", "Rahul", "Interstellar", "B2", "1:00 PM");
        ts.bookTicket("T3", "Amit", "Avatar", "C3", "4:00 PM");

        System.out.println("\nAll Tickets:");
        ts.displayTickets();

        // Cancel a ticket
        System.out.println("\nCancelling T2:");
        ts.cancelTicket("T2");

        System.out.println("\nAfter Cancellation:");
        ts.displayTickets();

        // Total tickets
        System.out.println("\nTotal Tickets: " + ts.getTotalTickets());
    }
}