/** Receives a validated print job. Implementations decide how to record it. */
public interface ReceiptSink {
    void accept(PrintJob job);
}
