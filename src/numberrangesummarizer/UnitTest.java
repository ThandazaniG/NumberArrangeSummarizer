package numberrangesummarizer;
import java.util.Arrays;
import java.util.Collection;
import java.util.stream.Stream;


import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.params.provider.Arguments;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class UnitTest {

    @Test
    public void testCollect(){
        ArrangeNumbers obj = new ArrangeNumbers();
        String input = "1,3,6,7,8,12,13,14,15,21,22,23,24,31";
        Collection<Integer> expectedAns = obj.collect(input);
        assertEquals(Arrays.asList(1,3,6,7,8,12,13,14,15,21,22,23,24,31), expectedAns);
    }

    @Test
    public void testSummarizeCollection() {
        ArrangeNumbers obj = new ArrangeNumbers();
        String input = "1,3,6,7,8,12,13,14,15,21,22,23,24,31";
        String expectedAns = obj.summarizeCollection(obj.collect(input));
        assertEquals("1,3,6-8,12-15,21-24,31", expectedAns);
    }
    @ParameterizedTest
    @MethodSource("getTestcasesCollect")
    @DisplayName("Test different cases")
    void  differentErrorInputsCollect(String input, String expectedOutput){
        ArrangeNumbers obj = new ArrangeNumbers();
        Assertions.assertEquals(expectedOutput, Assertions.assertThrows(RuntimeException.class,()->obj.collect(input)).getMessage());

    }

    @ParameterizedTest
    @MethodSource("getTestcasesSummary")
    @DisplayName("Test different cases")
    void  differentErrorInputsSummary( Collection<Integer> input, String expectedOutput){
        ArrangeNumbers obj = new ArrangeNumbers();
        Assertions.assertEquals(expectedOutput, Assertions.assertThrows(RuntimeException.class,()->obj.summarizeCollection(input)).getMessage());
    }

    public Stream<Arguments> getTestcasesCollect(){
        return  Stream.of(
                Arguments.of(null, "We can not collect and summarize null or empty input"),

                Arguments.of(" ", "We can not collect and summarize null or empty input"),

                Arguments.of("1,2,3,t", "We can not collect and summarize non-integer input")

        );
    }   public Stream<Arguments> getTestcasesSummary(){
        return  Stream.of(
                Arguments.of(null, "We can not summarize null or empty collection")

        );
    }


}
