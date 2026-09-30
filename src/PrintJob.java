/** A job with a nonblank identifier and a whole page count from 1 through 10. */
public final class PrintJob {
    private final String id;
    private final int pages;
    public PrintJob(String id, int pages) {
        // TODO: reject null/blank id and page counts outside 1..10.
        if ((id == null) || (id.isBlank())) {
            throw new IllegalArgumentException("id cannot be null or blank");
        }
        if((pages<1) || (pages>10)) {
            throw new IllegalArgumentException("id cannot be null or blank");
        }
        this.id = id;
        this.pages = pages;
    }
    public String id() { return id; }
    public int pages() { return pages; }
}
