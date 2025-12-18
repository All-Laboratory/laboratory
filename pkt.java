import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.concurrent.TimeUnit;
public class pkt {
    static class Packet {
        int id;
        String data;
        String destination;
        public Packet(int id, String data, String destination) {
            this.id = id;
            this.data = data;
            this.destination = destination;
        }
        @Override
        public String toString() {
            return "Packet{" + "id=" + id + ", data='" + data + "', destination='" + destination + '}';
        }
    }
    public static void main(String[] args) {
        System.out.println("Simulating Packet Switching:");
        String message = "This is a message for packet switching demonstration.";
        String destination = "ReceiverA";
        List<Packet> packets = new ArrayList<>();
        // 1. Packetization: Break message into packets
        int packetSize = 10; // Characters per packet
        for (int i = 0; i < message.length(); i += packetSize) {
            int endIndex = Math.min(i + packetSize, message.length());
            String packetData = message.substring(i, endIndex);
            packets.add(new Packet(i / packetSize, packetData, destination));
        }
        System.out.println("Message broken into packets: " + packets.size());
        // 2. Transmission and Routing (simulated)
        Random random = new Random();
        for (Packet packet : packets) {
            try {
                // Simulate variable routing paths and delays
                int delay = random.nextInt(3) + 1; // 1 to 3 seconds delay
                TimeUnit.SECONDS.sleep(delay);
                System.out.println("Packet " + packet.id + " arrived at intermediate node (simulated delay: " + delay + "s)");
                // In a real scenario, routing decisions would happen here
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
                System.err.println("Simulation interrupted.");
            }
        }
        // 3. Reassembly at Destination
        System.out.println("All packets arrived at destination. Reassembling message...");
        StringBuilder reassembledMessage = new StringBuilder();
        // In a real scenario, packets might arrive out of order and need reordering based on ID
        for (Packet packet : packets) { // Assuming in-order arrival for simplicity
            reassembledMessage.append(packet.data);
        }
        System.out.println("Reassembled message: '" + reassembledMessage.toString() + "'");
    }
}
