/** Coordinates the work using the supplied collaborator. */
public final class PrintDesk {
    private final ReceiptSink sink;
    public PrintDesk(ReceiptSink sink) {
        // TODO: reject a null collaborator.
        if (sink == null) {
            throw new IllegalArgumentException("You cannot have a null as a sink");
        }
        this.sink = sink;
    }
    public void submit(PrintJob job) {
        if (job == null) {
            throw new IllegalArgumentException("You cannot have a null as a job");
        }
        this.sink.accept(job);
        // TODO: reject null, then send the job to this.sink exactly once.
    }
}
