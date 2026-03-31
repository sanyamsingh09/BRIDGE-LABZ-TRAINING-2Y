class ItemNode {
    int itemId, quantity;
    String itemName;
    double price;
    ItemNode next;

    public ItemNode(int id, String name, int q, double p) {
        itemId = id; itemName = name; quantity = q; price = p;
    }
}

class InventoryManager {
    private ItemNode head;

    // ... Standard Add/Remove methods omitted for brevity ...

    public double calculateTotalValue() {
        double total = 0;
        ItemNode current = head;
        while (current != null) {
            total += (current.price * current.quantity);
            current = current.next;
        }
        return total;
    }

    // Simple Bubble Sort by Price (Descending) for Singly Linked List
    public void sortByPrice() {
        if (head == null || head.next == null) return;
        boolean swapped;
        do {
            swapped = false;
            ItemNode current = head;
            while (current.next != null) {
                if (current.price < current.next.price) {
                    // Swap values (in a real scenario, swapping nodes is better but more complex)
                    double tempPrice = current.price; current.price = current.next.price; current.next.price = tempPrice;
                    int tempId = current.itemId; current.itemId = current.next.itemId; current.next.itemId = tempId;
                    swapped = true;
                }
                current = current.next;
            }
        } while (swapped);
    }
}