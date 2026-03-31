class TicketNode {
    String ticketId, customerName, movieName, seatNumber, bookingTime;
    TicketNode next;

    public TicketNode(String id, String cName, String mName, String seat, String time) {
        ticketId = id; customerName = cName; movieName = mName;
        seatNumber = seat; bookingTime = time;
    }
}

class TicketSystem {
    private TicketNode tail; // Using tail pointer is highly efficient for circular lists
    private int totalTickets = 0;

    public void bookTicket(String id, String cName, String mName, String seat, String time) {
        TicketNode newTicket = new TicketNode(id, cName, mName, seat, time);
        if (tail == null) {
            tail = newTicket;
            tail.next = tail;
        } else {
            newTicket.next = tail.next; // Point to head
            tail.next = newTicket;
            tail = newTicket;
        }
        totalTickets++;
    }

    public void displayTickets() {
        if (tail == null) return;
        TicketNode current = tail.next; // Start at head
        do {
            System.out.println("Ticket: " + current.ticketId + " | Customer: " + current.customerName);
            current = current.next;
        } while (current != tail.next);
    }
}