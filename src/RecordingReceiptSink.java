import java.util.ArrayList;
import java.util.List;

public final class RecordingReceiptSink implements ReceiptSink {
    private final List<PrintJob> receipts = new ArrayList<>();
    
    @Override public void accept(PrintJob job) {
        if (job == null) throw new IllegalArgumentException("Job is required");
        receipts.add(job);
    }
    public List<PrintJob> snapshot() {
        // TODO: return an unmodifiable, detached snapshot in insertion order.
        final List<PrintJob> newreceit = List.copyOf(receipts);
        return newreceit;
    }
}
