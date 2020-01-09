package ua.com.company.record.analyzer.output;

import ua.com.company.record.analyzer.data.Result;

public class ConsoleInsert implements Insert {

    @Override
    public void commit(Result[] processingResult) {
        for (Result singleResult : processingResult) {
            System.out.println(singleResult.getAverageWaitingTime());
        }

    }

}
