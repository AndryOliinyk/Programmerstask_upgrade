package ua.com.company.record.analyzer.data;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

import ua.com.company.record.analyzer.extension.WrongDataTypeExtension;

public class Query extends CustomerResponse {
    private static final char QUERY_TYPE = 'D';
    private static final String TIME_LINE = "-";
    private static final String ANY_PARAMETER_SYMBOL = "*";

    private Date queryStartDate;
    private Date queryFinalDate;

    public Date getQueryStartDate() {
        return queryStartDate;
    }
    public void setQueryStartDate(Date queryStartDate) {
        this.queryStartDate = queryStartDate;
    }
    public Date getQueryFinalDate() {
        return queryFinalDate;
    }
    public void setQueryFinalDate(Date queryFinalDate) {
        this.queryFinalDate = queryFinalDate;
    }
    public static char getQueryType() {
        return QUERY_TYPE;
    }
    public class AdditionalImport {
        public void importFromSentence(String sentence) {
            try {
                String[] queryParameters = sentence.split(MAIN_PARAMETERS_SEPARATOR);
                int parametersCount = 0;
                for (String singleParameter : queryParameters) {
                    parametersCount++;
                    if (singleParameter.equals(ANY_PARAMETER_SYMBOL)) {
                        continue;
                    }
                    if (parametersCount == 2 && !checkIfDate(singleParameter)) {
                        importServices(singleParameter);
                    }
                    if (parametersCount == 3 && !checkIfDate(singleParameter)) {
                        importQuestion(singleParameter);
                    }
                    if (checkIfDate(singleParameter)) {
                        importTimeLine(singleParameter);
                    }
                    if (singleParameter.equals(FIRST_ANSWER) || singleParameter.equals(NEXT_ANSWER)) {
                        setResponseType(singleParameter);
                    }

                }
            } catch (ParseException | NullPointerException err) {
                throw new WrongDataTypeExtension(DATE_CONVERT_ERROR + sentence);
            }
        }

        private void importServices(String parameter) {
            String[] subParameter = parameter.split(SUB_PARAMETERS_SEPATOR);
            setService(subParameter[0]);
            if (subParameter.length > 1) {
                setServiceVariation(subParameter[1]);
            }
        }

        private void importQuestion(String parameter) {
            String[] subParameter = parameter.split(SUB_PARAMETERS_SEPATOR);
            setQuestion(subParameter[0]);
            if (subParameter.length > 1) {
                setQuestionCategori(subParameter[1]);
            }
            if (subParameter.length > 2) {
                setQuestionSubCategori(subParameter[2]);
            }
        }

        private void importTimeLine(String timeLimits) throws ParseException {
            SimpleDateFormat timelineFormat = new SimpleDateFormat(REPORT_DATE_FORMAT);
            timelineFormat.setLenient(false);
            String[] timeBorders = timeLimits.split(TIME_LINE);
            setQueryStartDate(timelineFormat.parse(timeBorders[0]));
            setQueryFinalDate(timelineFormat.parse(timeBorders[timeBorders.length - 1]));

        }

        private boolean checkIfDate(String parameter) {
            for (String element : parameter.split(SUB_PARAMETERS_SEPATOR)) {
                if (element.length() >= 4) {
                    return true;
                }
            }
            return false;
        }
    }
}