package ua.com.company.record.analyzer.data;

import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.text.ParseException;
import java.text.SimpleDateFormat;

import java.util.Date;

import org.junit.Assert;
import org.junit.jupiter.api.Test;


import ua.com.company.record.analyzer.extension.WrongDataTypeExtension;

import org.apache.commons.lang3.builder.EqualsBuilder;

class QueryTest {
    private static final String DATE_CONVERT_ERROR = "Input date cannot be processed. "
            + "Please check date or contact with specialist ";
    
    private SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd.MM.yyyy");

    @Test
    void shouldGetFullObjectWhenInputFull() throws ParseException {
        Query actualQuery = new Query();
        String testQuery = "D 1.1 8.2.3 P 01.01.2012-01.12.2012";
        actualQuery.new AdditionalImport().importFromSentence(testQuery);
        Query expectedQuery = new Query();
        expectedQuery.setService("1");
        expectedQuery.setServiceVariation("1");
        expectedQuery.setQuestion("8");
        expectedQuery.setQuestionCategori("2");
        expectedQuery.setQuestionSubCategori("3");
        expectedQuery.setResponseType("P");
        Date searchStartDate = simpleDateFormat.parse("01.01.2012");
        expectedQuery.setQueryStartDate(searchStartDate);
        Date searchFinalDate = simpleDateFormat.parse("01.12.2012");
        expectedQuery.setQueryFinalDate(searchFinalDate);

        Assert.assertTrue("Test for input object from sentence",
                EqualsBuilder.reflectionEquals(expectedQuery, actualQuery));
    }

    @Test
    void shouldGetBasicObjectWhenInputBasic() throws ParseException {
        Query actualQuery = new Query();
        String testQuery = "D 1 8 P 01.01.2012";
        actualQuery.new AdditionalImport().importFromSentence(testQuery);
        Query expectedQuery = new Query();
        expectedQuery.setService("1");
        expectedQuery.setQuestion("8");
        expectedQuery.setResponseType("P");
        Date searchStartDate = simpleDateFormat.parse("01.01.2012");
        expectedQuery.setQueryStartDate(searchStartDate);
        Date searchFinalDate = simpleDateFormat.parse("01.01.2012");
        expectedQuery.setQueryFinalDate(searchFinalDate);
        Assert.assertTrue("Test for input object from sentence",
                EqualsBuilder.reflectionEquals(expectedQuery, actualQuery));
    }

    @Test
    void shouldGetGeneralObjectWhenQueryHasOneParameterAny() throws ParseException {
        Query actualQuery = new Query();
        String testQuery = "D 1 * P 8.10.2012-20.11.2012";
        actualQuery.new AdditionalImport().importFromSentence(testQuery);
        Query expectedQuery = new Query();
        expectedQuery.setService("1");
        expectedQuery.setResponseType("P");
        Date searchStartDate = simpleDateFormat.parse("08.10.2012");
        expectedQuery.setQueryStartDate(searchStartDate);
        Date searchFinalDate = simpleDateFormat.parse("20.11.2012");
        expectedQuery.setQueryFinalDate(searchFinalDate);

        Assert.assertTrue("Test for input object from sentence",
                EqualsBuilder.reflectionEquals(expectedQuery, actualQuery));
    }
    
    @Test
    void shouldGetGeneralObjectWhenQueryHasSeveralParameterAny() throws ParseException {
        Query actualQuery = new Query();
        String testQuery = "D   *   20.11.2012";
        actualQuery.new AdditionalImport().importFromSentence(testQuery);
        Query expectedQuery = new Query();
        Date searchStartDate = simpleDateFormat.parse("20.11.2012");
        expectedQuery.setQueryStartDate(searchStartDate);
        Date searchFinalDate = simpleDateFormat.parse("20.11.2012");
        expectedQuery.setQueryFinalDate(searchFinalDate);
        Assert.assertTrue("Test for input object from sentence",
                EqualsBuilder.reflectionEquals(expectedQuery, actualQuery));
    }
    
    @Test
    void shouldGetExaptationsWhenInputDateIncorrect() throws ParseException {
        Query actualQuery = new Query();
        String testQuery = "D   *   31.02.2012";
        WrongDataTypeExtension thrown = assertThrows(WrongDataTypeExtension.class,
                () -> actualQuery.new AdditionalImport().importFromSentence(testQuery),
                "Expected WrongDataTypeExtension message to throw, but it didn't");
        assertTrue(thrown.getMessage().contains(DATE_CONVERT_ERROR));
    }
    
    @Test
    void shouldGetExaptationsWhenInputNull() throws ParseException {
        Query actualQuery = new Query();
        String testQuery = null;
        WrongDataTypeExtension thrown = assertThrows(WrongDataTypeExtension.class,
                () -> actualQuery.new AdditionalImport().importFromSentence(testQuery),
                "Expected WrongDataTypeExtension message to throw, but it didn't");
        assertTrue(thrown.getMessage().contains(DATE_CONVERT_ERROR));
    }
}