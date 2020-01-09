package ua.com.company.record.analyzer;

import ua.com.company.record.analyzer.data.*;
import ua.com.company.record.analyzer.evaluate.EvaluateData;
import ua.com.company.record.analyzer.output.*;

public class Main {

    public static void main(String[] args) {
        EvaluateData evaluateData = new EvaluateData();
        Result[] processingResult  = evaluateData.provideEvaluateResult(args);
        Insert insert = new ConsoleInsert();
        insert.commit(processingResult);
    }
}