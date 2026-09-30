public final class Main {
    private Main() {}
    public static void main(String[] args) {
        RecordingReceiptSink sink = new RecordingReceiptSink();
        PrintDesk desk = new PrintDesk(sink);
        PrintJob job = new PrintJob("P7", 3);
        desk.submit(job);
        System.out.println("Job: " + job.id() + "; pages: " + job.pages());
        System.out.println("Recorded receipts: " + sink.snapshot().size());
        System.out.println("This is an orientation example; run check and test for evidence.");
    }
}
