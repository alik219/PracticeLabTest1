// Add two focused @Test methods here. Use fresh objects for each test.
// Suggested goals: preserve receipt order after two submissions; verify clear()
// on a returned snapshot is rejected and the recorder still contains its job.
// Useful imports: org.junit.jupiter.api.Test and static org.junit.jupiter.api.Assertions.*

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertThrows;
import java.util.List;
import org.junit.jupiter.api.Test;

public final class StudentTests {

    @Test
    void test1() {
        RecordingReceiptSink sink = new RecordingReceiptSink();
        PrintDesk desk = new PrintDesk(sink);
        PrintJob job1 = new PrintJob("b1",1);
        PrintJob job2 = new PrintJob("b2",3);
        desk.submit(job1);
        desk.submit(job2);

        assertEquals(List.of(job1, job2), sink.snapshot());
    }

    @Test
    void test2and4() {
        RecordingReceiptSink sink = new RecordingReceiptSink();
        PrintDesk desk = new PrintDesk(sink);
        PrintJob job1 = new PrintJob("b1",1);
        desk.submit(job1);
        assertThrows(UnsupportedOperationException.class,() -> sink.snapshot().clear());
        assertThrows(UnsupportedOperationException.class,() -> sink.snapshot().add(job1));
        assertEquals(List.of(job1), sink.snapshot());
    }

    @Test
    void test3() {
        assertThrows(IllegalArgumentException.class, () -> new PrintJob("", 4));
    }

    @Test
    void test5() {
        RecordingReceiptSink sink = new RecordingReceiptSink();
        PrintDesk desk = new PrintDesk(sink);

        RecordingReceiptSink sink2 = new RecordingReceiptSink();
        PrintDesk desk2 = new PrintDesk(sink2);

        PrintJob job1 = new PrintJob("b1",1);
        PrintJob job2 = new PrintJob("b2",3);

        desk.submit(job1);
        desk2.submit(job2);

        assertEquals(List.of(job1), sink.snapshot());
        assertEquals(List.of(job2), sink2.snapshot());    }

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