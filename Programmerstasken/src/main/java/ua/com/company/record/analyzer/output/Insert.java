package ua.com.company.record.analyzer.output;

import ua.com.company.record.analyzer.data.Result;

public interface Insert {
    public void commit(Result[] result);
}
