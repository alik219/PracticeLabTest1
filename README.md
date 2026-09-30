# EECS 3311 B - LT1 ungraded demo practice
## PrintDesk: valid objects, a collaborator and a safe snapshot

**Prepare before September 30, 2026. Ungraded. Suggested time: 30 minutes.**
This is a new practice exercise, not the live Lab Test 1 paper or starter. It revisits
L02-L05 and Labs 01-02 taught by September 25. It introduces no later design-pattern
or named SOLID requirements. Practice access does not make a resource permitted
during the test; follow the final test instructions.

### 1. Open and predict (5 minutes)

Extract the whole ZIP. Open **EECS3311B_LT1_Ungraded_PrintDesk_Practice**, the folder
containing this README, run scripts and src/. Check `java -version` and `javac -version`;
both should identify JDK 21. The pinned JUnit 6.0.3 jar is already cached in lib/.
No download, account, network call or model is needed while running this exercise.

macOS/Linux or supported Git Bash:

```sh
bash run.sh compile
bash run.sh check
bash run.sh test
```

Supported Windows PowerShell:

```powershell
./run.ps1 compile
./run.ps1 check
./run.ps1 test
```

Follow the institution-supported setup if scripts are restricted. Do not change
managed security settings. A TA-provided jar can instead be selected with JUNIT_JAR;
it must match the pinned checksum. `setup-junit` is an optional repair tool, not a
required step when the included jar is present.

**Starting results:** compilation succeeds; the supplied suite has **9 cases:
1 passes and 8 fail intentionally**. An assertion failure identifies unfinished
behaviour. A missing compiler, missing jar or checksum error is a setup problem.
JUnit reports actual tests; `check` reports ordinary Java checks. Keep those distinct.
`run` is an orientation example, not the full suite. At first it records zero receipts.

Before editing, predict what `new PrintJob("P7", 3)` and one `submit` call should do.
Identify the field, constructor parameter, object receiving each call and state to protect.

### 2. Complete the behaviour (12 minutes)

A print desk accepts a validated print job and sends one receipt to a collaborator.
Data is synthetic; nothing is actually printed or transmitted.

| Public operation | Required behaviour |
|---|---|
| `new PrintJob(id, pages)` | Reject null/blank id and integer pages outside 1-10 with IllegalArgumentException. Store permitted input exactly; preserve private final fields and read methods. |
| `new PrintDesk(sink)` | Reject null with IllegalArgumentException; retain the supplied ReceiptSink. |
| `submit(job)` | Reject null with IllegalArgumentException before recording; for a valid job call the supplied sink exactly once. |
| `RecordingReceiptSink.snapshot()` | Return receipts in insertion order as an unmodifiable, detached list. A caller cannot change it; later records must not change an earlier snapshot. |

Edit only the TODOs in **PrintJob.java**, **PrintDesk.java** and
**RecordingReceiptSink.java** under src/. Preserve the public method signatures
and supplied checks. ReceiptSink, Main and the recorder's accept operation are provided.
You may use `List.copyOf(...)` as in protected-state practice. PrintJob has immutable
fields, so sharing references to these jobs in the snapshot is safe for this exercise.

### 3. Test and explain (8 minutes)

Run `check` and `test` again. All nine supplied cases should pass after completion.
Add **two focused Jupiter @Test methods** in test/junit/StudentTests.java using fresh
objects: test two submissions in order, and test that clearing a returned snapshot
cannot erase internal records. Use real assertions and state expected results first.
With these two tests, JUnit should discover **11 tests**; a wrapper alone is not your
independent test design.

Write a tiny class diagram plus text alternative in DESIGN.md: PrintDesk,
ReceiptSink, RecordingReceiptSink and PrintJob. Show interface realization and the
relevant associations/multiplicities. Explain why a supplied collaborator does not
imply exclusive lifecycle ownership. Sketch the message order for a valid submission.
You may draw by hand; no special UML tool is needed.

### 4. Compare and rehearse (5 minutes)

Try the task before opening the separately supplied **Self_Check_Answers.md**.
It contains the practice answers and explanation, not live-test answers.
Explain one rejection case and one snapshot case aloud without assistance.
Keep actual output in results.txt if useful. There is **no mark, attendance credit or
required submission** for this demo. An instructor may demonstrate it in class.
Allow up to 35 minutes if you want extra time for your diagram; this is a suggested
practice budget, not a measured lab-computer timing pilot.

The initial prediction/design should be your own reasoning. If you use assistance
afterwards while practising, record it in AI_USE.md. The supervised Lab Test 1
prohibits generative AI under its issued rules.

### Files and scope

Source, public checks and scripts are in this bundle. A reference implementation
is held separately by the teaching team. No private test, live assessment paper,
grading rules, credentials or student data is included. The test covers the announced
course scope; this practice is one rehearsal and does not predict its exact questions.

Prepared for Section B on September 29, 2026 using the existing Lab01/Lab02 starter
script conventions, Java 21 and pinned JUnit 6.0.3. Read the course preparation guide
for the full revision map and the final test instructions for permitted resources.
