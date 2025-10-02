package LABORATORY;
import java.util.PriorityQueue;

public class EmergencyRoomTriage {

    
    static class Patient implements Comparable<Patient> {
        String name;
        int priority;
        String condition;
        String arrivalTime;

        public Patient(String name, int priority, String condition, String arrivalTime) {
            this.name = name;
            this.priority = priority;
            this.condition = condition;
            this.arrivalTime = arrivalTime;
        }

        @Override
        public int compareTo(Patient other) {
            if (this.priority != other.priority) {
                return Integer.compare(this.priority, other.priority);
            }
            return this.arrivalTime.compareTo(other.arrivalTime); 
        }

        @Override
        public String toString() {
            return String.format("[P%d] %s - %s (%s)", priority, name, condition, arrivalTime);
        }
    }

    
    static class ERQueue {
        private PriorityQueue<Patient> queue = new PriorityQueue<>();

        public void arrive(String name, int priority, String condition, String time) {
            queue.add(new Patient(name, priority, condition, time));
        }

        public void treatNext() {
            if (queue.isEmpty()) {
                System.out.println(">>> No patients to treat.");
                return;
            }
            Patient next = queue.poll();
            System.out.println("\n>>> Treating patient now...");
            System.out.println("Treated: " + next);
        }

        public void displayQueue() {
            System.out.println("\n=== UPDATED QUEUE ===");
            System.out.println("Patients Waiting: " + queue.size());

            PriorityQueue<Patient> tempQueue = new PriorityQueue<>(queue);
            int count = 1;
            while (!tempQueue.isEmpty()) {
                System.out.println(count++ + ". " + tempQueue.poll());
            }
        }
    }

    
    public static void main(String[] args) {
        ERQueue er = new ERQueue();

        er.arrive("Pedro Cruz", 1, "Head injury - NOW UNCONSCIOUS ⚠️", "23:52");
        er.arrive("Carlos Mendoza", 2, "Compound fracture - leg", "23:50");
        er.arrive("Lisa Wang", 2, "Severe asthma attack", "23:56");
        er.arrive("Maria Santos", 3, "Deep laceration on arm", "23:48");
        er.arrive("Ana Lim", 4, "Sprained ankle", "23:49");

        er.displayQueue();
        er.treatNext();
        er.displayQueue();
    }
}
