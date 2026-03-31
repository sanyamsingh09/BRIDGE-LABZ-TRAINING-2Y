class ItemNode {
    int itemId, quantity;
    String itemName;
    double price;
    ItemNode next;

    public ItemNode(int id, String name, int q, double p) {
        itemId = id;
        itemName = name;
        quantity = q;
        price = p;
        next = null;
    }
}

class InventoryManager {
    private ItemNode head;

    // Add item at end
    public void addItem(int id, String name, int q, double p) {
        ItemNode newNode = new ItemNode(id, name, q, p);

        if (head == null) {
            head = newNode;
            return;
        }

        ItemNode current = head;
        while (current.next != null) {
            current = current.next;
        }
        current.next = newNode;
    }

    // Remove item by ID
    public void removeItem(int id) {
        if (head == null) {
            System.out.println("Inventory empty");
            return;
        }

        if (head.itemId == id) {
            head = head.next;
            System.out.println("Item removed");
            return;
        }

        ItemNode current = head;
        while (current.next != null && current.next.itemId != id) {
            current = current.next;
        }

        if (current.next == null) {
            System.out.println("Item not found");
        } else {
            current.next = current.next.next;
            System.out.println("Item removed");
        }
    }

    // Display items
    public void displayItems() {
        if (head == null) {
            System.out.println("No items in inventory");
            return;
        }

        ItemNode current = head;
        while (current != null) {
            System.out.println("ID: " + current.itemId +
                    ", Name: " + current.itemName +
                    ", Qty: " + current.quantity +
                    ", Price: " + current.price);
            current = current.next;
        }
    }

    // Total inventory value
    public double calculateTotalValue() {
        double total = 0;
        ItemNode current = head;

        while (current != null) {
            total += (current.price * current.quantity);
            current = current.next;
        }

        return total;
    }

    
    public void sortByPrice() {
        if (head == null || head.next == null) return;

        boolean swapped;

        do {
            swapped = false;
            ItemNode current = head;

            while (current.next != null) {
                if (current.price < current.next.price) {

                    // Swap FULL DATA 
                    int tempId = current.itemId;
                    String tempName = current.itemName;
                    int tempQty = current.quantity;
                    double tempPrice = current.price;

                    current.itemId = current.next.itemId;
                    current.itemName = current.next.itemName;
                    current.quantity = current.next.quantity;
                    current.price = current.next.price;

                    current.next.itemId = tempId;
                    current.next.itemName = tempName;
                    current.next.quantity = tempQty;
                    current.next.price = tempPrice;

                    swapped = true;
                }
                current = current.next;
            }

        } while (swapped);
    }
}

public class Main {
    public static void main(String[] args) {

        InventoryManager im = new InventoryManager();

        // Add items
        im.addItem(1, "Laptop", 5, 50000);
        im.addItem(2, "Phone", 10, 20000);
        im.addItem(3, "Tablet", 7, 30000);

        System.out.println("Inventory:");
        im.displayItems();

        // Total value
        System.out.println("\nTotal Value: " + im.calculateTotalValue());

        // Sort by price
        System.out.println("\nSorted by Price (Descending):");
        im.sortByPrice();
        im.displayItems();

        // Remove item
        System.out.println("\nRemoving Item ID 2:");
        im.removeItem(2);

        System.out.println("\nFinal Inventory:");
        im.displayItems();
    }
}