package ua.com.company.record.analyzer.evaluate;

import java.util.ArrayList;
import java.util.List;

import ua.com.company.record.analyzer.data.*;

public class EvaluateData {
    private List<Record> clientsRecord = new ArrayList<>();

    public Result[] provideEvaluateResult(String[] clientResponse) {
        List<Result> resultEvaluate = new ArrayList<>();
        for (String response : clientResponse) {
            if (response.charAt(0) == 'C') {
                Record record = new Record();
                record.new AdditionalImport().importFromSentence(response);
                clientsRecord.add(record);
            }
            if (response.charAt(0) == 'D') {
                Compare compare = new Compare();
                Query query = new Query();
                Result result = new Result();
                List<Integer> appropriateResult = new ArrayList<>();
                query.new AdditionalImport().importFromSentence(response);
                compare.setQuery(query);
                for (Record record : clientsRecord.toArray(new Record[clientsRecord.size()])) {
                    compare.setRecord(record);
                    if (compare.compareRecord()) {
                        appropriateResult.add(record.getResonseResult());
                    }
                }
                result.setResultID(response);
                result.setValidRepresent(appropriateResult.stream().mapToInt(i -> i).toArray());
                result.generateAvarage();
                resultEvaluate.add(result);
            }
        }
        return resultEvaluate.toArray(new Result[0]);
    }

    private class Compare {
        private Record record;
        private Query query;

        public void setQuery(Query query) {
            this.query = query;
        }

        public void setRecord(Record record) {
            this.record = record;
        }

        boolean compareRecord() {
            return (compareService() && compareQuestion() && compareResponseType()
                    && (record.getRecordDate().after(query.getQueryStartDate())
                            && record.getRecordDate().before(query.getQueryFinalDate())));
        }

        private boolean compareService() {
            return ((isNullOrEmpty(query.getService()) || query.getService().equals(record.getService()))
                    && (isNullOrEmpty(query.getServiceVariation())
                            || query.getServiceVariation().equals(record.getServiceVariation())));
        }

        private boolean compareQuestion() {
            return ((isNullOrEmpty(query.getQuestion()) || query.getQuestion().equals(record.getQuestion()))
                    && (isNullOrEmpty(query.getQuestionCategori())
                            || query.getQuestionCategori().equals(record.getQuestionCategori()))
                    && (isNullOrEmpty(query.getQuestionSubCategori())
                            || query.getQuestionSubCategori().equals(record.getQuestionSubCategori())));
        }
        private boolean compareResponseType() {
            return (isNullOrEmpty(query.getResponseType())) || query.getResponseType().equals(record.getResponseType());
        }

        private boolean isNullOrEmpty(String str) {
            return (str == null || str.isEmpty());
        }
    }
}