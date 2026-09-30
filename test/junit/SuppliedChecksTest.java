import java.util.stream.Stream;
import org.junit.jupiter.api.DynamicTest;
import org.junit.jupiter.api.TestFactory;

public final class SuppliedChecksTest {
    @TestFactory
    Stream<DynamicTest> publicRequirements() {
        return PublicChecks.cases().stream().map(c -> DynamicTest.dynamicTest(c.name(), () -> c.action().run()));
    }
}
