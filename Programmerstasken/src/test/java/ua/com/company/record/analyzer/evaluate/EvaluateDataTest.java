package ua.com.company.record.analyzer.evaluate;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.Test;

import ua.com.company.record.analyzer.data.Result;

class EvaluateDataTest {

    @Test
    void shoudGetStandardWhenInputDefault() {
        List<String> imitationInput = new ArrayList<>();
        imitationInput.add("C 1.1 8.15.1 P 15.10.2012 83");
        imitationInput.add("C 1 10.1 P 01.12.2012 65");
        imitationInput.add("C 1.1 5.5.1 P 01.11.2012 117");
        imitationInput.add("D 1.1 8 P 01.01.2012-01.12.2012");
        imitationInput.add("C 3 10.2 N 02.10.2012 100");
        imitationInput.add("D 1 * P 8.10.2012-20.11.2012");
        imitationInput.add("D 3 10 P 01.12.2012");
        EvaluateData evaluateData = new EvaluateData();
        Result[] actualResult = evaluateData.provideEvaluateResult(imitationInput.toArray(new String[0]));
        String[] test = Arrays.stream(actualResult).map(Result::getAverageWaitingTime).toArray(String[]::new);
        String[] expectedResult = {"83", "100", "-"};
        assertArrayEquals(expectedResult, test);
    }
}
