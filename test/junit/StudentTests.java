// Add two focused @Test methods here. Use fresh objects for each test.
// Suggested goals: preserve receipt order after two submissions; verify clear()
// on a returned snapshot is rejected and the recorder still contains its job.
// Useful imports: org.junit.jupiter.api.Test and static org.junit.jupiter.api.Assertions.*

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThrows;
import java.util.List;
import org.junit.Test;

public final class StudentTests {

    @Test
    void submits() {
        RecordingReceiptSink sink = new RecordingReceiptSink();
        PrintDesk desk = new PrintDesk(sink);
        PrintJob job1 = new PrintJob("Q", 1);
        PrintJob job2 = new PrintJob("P", 2);
        desk.submit(job1);
        desk.submit(job2);
        assertEquals(List.of(job1, job2), sink.snapshot());
    }

    @Test
    void test2() {
        RecordingReceiptSink sink = new RecordingReceiptSink();
        PrintDesk desk = new PrintDesk(sink);
        PrintJob job = new PrintJob(("Q1"), 1);
        desk.submit(job);
        List<PrintJob> reciptdsa = sink.snapshot();
        assertThrows(UnsupportedOperationException.class, () -> reciptdsa.clear());
        assertEquals(List.of(job), sink.snapshot());

    }
}


/*public final class Main {
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
} */