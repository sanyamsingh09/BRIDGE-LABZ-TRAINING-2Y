class FriendNode {
    int friendId;
    FriendNode next;

    public FriendNode(int id) {
        friendId = id;
        next = null;
    }
}

class UserNode {
    int userId, age;
    String name;
    UserNode next;
    FriendNode friendsHead;

    public UserNode(int id, String n, int a) {
        userId = id;
        name = n;
        age = a;
        next = null;
        friendsHead = null;
    }
}

class SocialNetwork {
    private UserNode head;

    // Add user
    public void addUser(int id, String name, int age) {
        UserNode newUser = new UserNode(id, name, age);

        if (head == null) {
            head = newUser;
            return;
        }

        UserNode current = head;
        while (current.next != null) {
            current = current.next;
        }
        current.next = newUser;
    }

    // Find user
    private UserNode findUser(int id) {
        UserNode current = head;
        while (current != null) {
            if (current.userId == id)
                return current;
            current = current.next;
        }
        return null;
    }

    // Check if already friends
    private boolean isAlreadyFriend(UserNode user, int friendId) {
        FriendNode temp = user.friendsHead;
        while (temp != null) {
            if (temp.friendId == friendId)
                return true;
            temp = temp.next;
        }
        return false;
    }

    // Add connection 
    public void addConnection(int userId1, int userId2) {
        UserNode u1 = findUser(userId1);
        UserNode u2 = findUser(userId2);

        if (u1 == null || u2 == null) {
            System.out.println("One or both users not found");
            return;
        }

        if (isAlreadyFriend(u1, userId2)) {
            System.out.println("Already friends");
            return;
        }

        // Add u2 to u1
        FriendNode f1 = new FriendNode(userId2);
        f1.next = u1.friendsHead;
        u1.friendsHead = f1;

        // Add u1 to u2
        FriendNode f2 = new FriendNode(userId1);
        f2.next = u2.friendsHead;
        u2.friendsHead = f2;

        System.out.println("Connection added");
    }

    
    public void displayNetwork() {
        if (head == null) {
            System.out.println("No users in network");
            return;
        }

        UserNode current = head;
        while (current != null) {
            System.out.print("User " + current.userId + " (" + current.name + ") -> Friends: ");

            FriendNode temp = current.friendsHead;
            if (temp == null) {
                System.out.print("None");
            } else {
                while (temp != null) {
                    System.out.print(temp.friendId + " ");
                    temp = temp.next;
                }
            }

            System.out.println();
            current = current.next;
        }
    }
}

public class Main {
    public static void main(String[] args) {

        SocialNetwork sn = new SocialNetwork();

        
        sn.addUser(1, "Sanyam", 20);
        sn.addUser(2, "Rahul", 21);
        sn.addUser(3, "Amit", 22);

        
        sn.addConnection(1, 2);
        sn.addConnection(1, 3);

        
        System.out.println("\nSocial Network:");
        sn.displayNetwork();
    }
}