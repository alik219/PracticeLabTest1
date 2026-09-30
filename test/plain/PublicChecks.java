import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/** Every public case implements a requirement stated in README.md. */
public final class PublicChecks {
    private PublicChecks() {}
    @FunctionalInterface public interface Action { void run() throws Exception; }
    public record Case(String name, Action action) {}
    public static void equal(Object expected, Object actual) {
        if (!Objects.equals(expected, actual))
            throw new AssertionError("Expected " + expected + "; got " + actual);
    }
    public static void rejects(Class<? extends Throwable> type, Action action) throws Exception {
        try { action.run(); }
        catch (Throwable ex) {
            if (type.isInstance(ex)) return;
            throw new AssertionError("Expected " + type.getSimpleName() + "; got " + ex, ex);
        }
        throw new AssertionError("Expected " + type.getSimpleName() + "; returned normally");
    }
    public static List<Case> cases() {
        List<Case> cases = new ArrayList<>();
        cases.add(new Case("valid page endpoints and exact identifiers", () -> {
            PrintJob a = new PrintJob("P1", 1), b = new PrintJob("P10", 10);
            equal("P1", a.id()); equal(1, a.pages()); equal("P10", b.id()); equal(10, b.pages());
        }));
        cases.add(new Case("invalid page counts rejected", () -> {
            for (int n : new int[] {-1, 0, 11})
                rejects(IllegalArgumentException.class, () -> new PrintJob("P1", n));
        }));
        cases.add(new Case("null and blank job identifiers rejected", () -> {
            for (String id : new String[] {null, "", "  ", "\t"})
                rejects(IllegalArgumentException.class, () -> new PrintJob(id, 3));
        }));
        cases.add(new Case("null collaborator rejected", () ->
            rejects(IllegalArgumentException.class, () -> new PrintDesk(null))));
        cases.add(new Case("supplied collaborator receives exactly one job", () -> {
            RecordingReceiptSink sink = new RecordingReceiptSink();
            PrintJob job = new PrintJob("P7", 3);
            new PrintDesk(sink).submit(job);
            equal(List.of(job), sink.snapshot());
        }));
        cases.add(new Case("null submission rejected without recording", () -> {
            RecordingReceiptSink sink = new RecordingReceiptSink();
            PrintDesk desk = new PrintDesk(sink);
            rejects(IllegalArgumentException.class, () -> desk.submit(null));
            equal(List.of(), sink.snapshot());
        }));
        cases.add(new Case("snapshot cannot be changed by caller", () -> {
            RecordingReceiptSink sink = new RecordingReceiptSink();
            PrintJob first = new PrintJob("P1", 1);
            sink.accept(first);
            List<PrintJob> snapshot = sink.snapshot();
            rejects(UnsupportedOperationException.class, () -> snapshot.add(new PrintJob("P2", 2)));
            equal(List.of(first), sink.snapshot());
        }));
        cases.add(new Case("old snapshot is detached from later records", () -> {
            RecordingReceiptSink sink = new RecordingReceiptSink();
            PrintJob a = new PrintJob("P1", 1), b = new PrintJob("P2", 2);
            sink.accept(a);
            List<PrintJob> old = sink.snapshot();
            sink.accept(b);
            equal(List.of(a), old); equal(List.of(a, b), sink.snapshot());
        }));
        cases.add(new Case("separate sinks retain separate state", () -> {
            RecordingReceiptSink a = new RecordingReceiptSink(), b = new RecordingReceiptSink();
            PrintJob job = new PrintJob("P3", 3);
            new PrintDesk(a).submit(job);
            equal(List.of(job), a.snapshot()); equal(List.of(), b.snapshot());
        }));
        return cases;
    }
    public static void main(String[] args) {
        int failed = 0;
        for (Case c : cases()) {
            try { c.action().run(); System.out.println("PASS " + c.name()); }
            catch (Throwable ex) { failed++; System.out.println("FAIL " + c.name() + ": " + ex); }
        }
        System.out.println("CHECKS " + cases().size() + "; FAILED " + failed);
        if (failed != 0) System.exit(1);
    }
}
