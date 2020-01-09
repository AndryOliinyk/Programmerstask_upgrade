package ua.com.company.record.analyzer.data;

import org.junit.Assert;
import org.junit.jupiter.api.Test;
import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.apache.commons.lang3.builder.EqualsBuilder;

import java.text.ParseException;
import java.text.SimpleDateFormat;

import ua.com.company.record.analyzer.extension.WrongDataTypeExtension;

class RecordTest {
    protected static final String DATE_CONVERT_ERROR = "Input date cannot be processed. "
            + "Please check date or contact with specialist";
    
    private Record record = new Record();

    @Test
    void shouldGetFullObjectWhenInputParametersFull() throws ParseException {
        String testSentence = "C 1.1 8.15.1 P 15.10.2012 83";
        Record expectedRecord = new Record();
        expectedRecord.new AdditionalImport().importFromSentence(testSentence);
        Record actualRecord = new Record();
        actualRecord.setService("1");
        actualRecord.setServiceVariation("1");
        actualRecord.setQuestion("8");
        actualRecord.setQuestionCategori("15");
        actualRecord.setQuestionSubCategori("1");
        actualRecord.setResponseType("P");
        actualRecord.setRecordDate(new SimpleDateFormat("dd.MM.yyyy").parse("15.10.2012"));
        actualRecord.setResonseResult(83);
        Assert.assertTrue("Test for input object from sentence",
                EqualsBuilder.reflectionEquals(expectedRecord, actualRecord));
    }
    
    @Test
    void shouldGetBasicObjectWhenInputParametersBasic() throws ParseException {
        String testSentence = "C 1 8 P 15.10.2012 83";
        Record expectedRecord = new Record();
        expectedRecord.new AdditionalImport().importFromSentence(testSentence);
        Record actualRecord = new Record();
        actualRecord.setService("1");
        actualRecord.setQuestion("8");
        actualRecord.setResponseType("P");
        actualRecord.setRecordDate(new SimpleDateFormat("dd.MM.yyyy").parse("15.10.2012"));
        actualRecord.setResonseResult(83);
        Assert.assertTrue("Test for input object from sentence",
                EqualsBuilder.reflectionEquals(expectedRecord, actualRecord));
    }
    
    @Test
    void shouldGetExaptationsWhenInputDateIncorrect() throws ParseException {
        String testSentence = "C 1 8 P 31.02.2012 83";
        WrongDataTypeExtension thrown = assertThrows(WrongDataTypeExtension.class,
                () -> record.new AdditionalImport().importFromSentence(testSentence),
                "Expected WrongDataTypeExtension message to throw, but it didn't");
        assertTrue(thrown.getMessage().contains(DATE_CONVERT_ERROR));
    }
    
    @Test
    void shouldGetExaptationsWhenInputParametersLessBasic() throws ParseException {
        String testSentence = "C 1   P 15.10.2012 83";
        WrongDataTypeExtension thrown = assertThrows(WrongDataTypeExtension.class,
                () -> record.new AdditionalImport().importFromSentence(testSentence),
                "Expected WrongDataTypeExtension message to throw, but it didn't");
        assertTrue(thrown.getMessage().contains(DATE_CONVERT_ERROR));
    }
    
    @Test
    void shouldGetExaptationsWhenInputNull() throws ParseException {
        String testSentence = null;
        WrongDataTypeExtension thrown = assertThrows(WrongDataTypeExtension.class,
                () -> record.new AdditionalImport().importFromSentence(testSentence),
                "Expected WrongDataTypeExtension message to throw, but it didn't");
        assertTrue(thrown.getMessage().contains(DATE_CONVERT_ERROR));
    }

}