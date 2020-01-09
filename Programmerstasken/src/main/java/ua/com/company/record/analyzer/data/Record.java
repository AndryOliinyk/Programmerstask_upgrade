package ua.com.company.record.analyzer.data;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import ua.com.company.record.analyzer.extension.WrongDataTypeExtension;

public class Record extends CustomerResponse {
    private static final char RECORD_TYPE = 'C';
    private Date recordDate;
    private int resonseResult;

    public Date getRecordDate() {
        return recordDate;
    }
    public void setRecordDate(Date recordDate) {
        this.recordDate = recordDate;
    }
    public int getResonseResult() {
        return resonseResult;
    }
    public void setResonseResult(int resonseResult) {
        this.resonseResult = resonseResult;
    }
    public char getRecordType() {
        return RECORD_TYPE;
    }
    public class AdditionalImport {
        public void importFromSentence(String sentence) {
            try {
                String[] recordParameters = sentence.split(MAIN_PARAMETERS_SEPARATOR);
                if (recordParameters.length != 6) {
                    throw new WrongDataTypeExtension(DATE_CONVERT_ERROR + sentence);
                }
                int countParameters = 0;
                String[] serviceParameters = recordParameters[++countParameters].split(SUB_PARAMETERS_SEPATOR);
                setService(serviceParameters[0]);
                if (serviceParameters.length > 1) {
                    setServiceVariation(serviceParameters[1]);
                }
                String[] questionParameters = recordParameters[++countParameters].split(SUB_PARAMETERS_SEPATOR);
                setQuestion(questionParameters[0]);
                if (questionParameters.length > 1) {
                    setQuestionCategori(questionParameters[1]);
                }
                if (questionParameters.length > 2) {
                    setQuestionSubCategori(questionParameters[2]);
                }
                setResponseType(recordParameters[++countParameters]);
                SimpleDateFormat simpleDateFormat = new SimpleDateFormat(REPORT_DATE_FORMAT);
                simpleDateFormat.setLenient(false);
                setRecordDate(simpleDateFormat.parse(recordParameters[++countParameters]));
                setResonseResult(Integer.parseInt(recordParameters[++countParameters]));
            } catch (ParseException | NullPointerException err) {
                throw new WrongDataTypeExtension(DATE_CONVERT_ERROR + sentence);
            }
        }
    }
}