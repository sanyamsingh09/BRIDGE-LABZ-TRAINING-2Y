class ProcessNode {
    int processId, burstTime, remainingTime, priority;
    ProcessNode next;

    public ProcessNode(int id, int bt, int p) {
        processId = id; burstTime = bt; remainingTime = bt; priority = p;
    }
}

class RoundRobinSimulator {
    private ProcessNode head, tail;

    public void addProcess(int id, int bt, int p) {
        ProcessNode newNode = new ProcessNode(id, bt, p);
        if (head == null) {
            head = tail = newNode;
            newNode.next = head;
        } else {
            tail.next = newNode;
            tail = newNode;
            tail.next = head;
        }
    }

    public void simulate(int timeQuantum) {
        if (head == null) return;
        ProcessNode current = head;
        ProcessNode prev = tail;

        while (head != null) {
            if (current.remainingTime > 0) {
                System.out.println("Executing Process " + current.processId);
                current.remainingTime -= timeQuantum;
                
                if (current.remainingTime <= 0) {
                    System.out.println("Process " + current.processId + " Finished.");
                    // Remove process from circular list
                    if (current == current.next) {
                        head = null; // Last process finished
                        break;
                    } else {
                        prev.next = current.next;
                        if (current == head) head = current.next;
                        if (current == tail) tail = prev;
                    }
                }
            }
            prev = current;
            current = current.next;
        }
    }
}