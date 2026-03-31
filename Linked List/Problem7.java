class FriendNode {
    int friendId;
    FriendNode next;
    public FriendNode(int id) { friendId = id; }
}

class UserNode {
    int userId, age;
    String name;
    UserNode next;
    FriendNode friendsHead; // Nested Linked List for friends

    public UserNode(int id, String n, int a) {
        userId = id; name = n; age = a;
    }
}

class SocialNetwork {
    private UserNode head;

    public void addConnection(int userId1, int userId2) {
        UserNode u1 = findUser(userId1);
        UserNode u2 = findUser(userId2);
        
        if (u1 != null && u2 != null) {
            // Add u2 to u1's friend list
            FriendNode f1 = new FriendNode(userId2);
            f1.next = u1.friendsHead;
            u1.friendsHead = f1;

            // Add u1 to u2's friend list
            FriendNode f2 = new FriendNode(userId1);
            f2.next = u2.friendsHead;
            u2.friendsHead = f2;
        }
    }

    private UserNode findUser(int id) {
        UserNode current = head;
        while (current != null) {
            if (current.userId == id) return current;
            current = current.next;
        }
        return null;
    }
}