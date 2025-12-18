import java.util.concurrent.TimeUnit;
public class ckt {
    public static void main(String[] args) {
        System.out.println("Simulating Circuit Switching:");
        try {
            // 1. Setup Phase: Establish a dedicated circuit
            System.out.println("Establishing circuit between Sender and Receiver...");
            TimeUnit.SECONDS.sleep(2); // Simulate connection setup time
            System.out.println("Circuit established. Dedicated path reserved.");
            // 2. Data Transfer Phase: Send data over the dedicated circuit
            System.out.println("Sending data: 'Hello, Circuit Switching!'");
            TimeUnit.SECONDS.sleep(3); // Simulate data transfer time
            System.out.println("Data sent successfully.");
            // 3. Teardown Phase: Release the circuit
            System.out.println("Releasing the dedicated circuit...");
            TimeUnit.SECONDS.sleep(1); // Simulate connection teardown time
            System.out.println("Circuit released. Path now available for others.");
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
            System.err.println("Simulation interrupted.");
        }
    }
}
