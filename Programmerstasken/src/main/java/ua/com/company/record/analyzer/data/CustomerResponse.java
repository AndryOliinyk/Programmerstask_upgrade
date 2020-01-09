package ua.com.company.record.analyzer.data;

public abstract class CustomerResponse {
    protected static final String MAIN_PARAMETERS_SEPARATOR = "\\s+";
    protected static final String SUB_PARAMETERS_SEPATOR = "\\.";
    protected static final String DATE_CONVERT_ERROR = "Input date cannot be processed. "
            + "Please check date or contact with specialist ";
    protected static final String FIRST_ANSWER = "P";
    protected static final String NEXT_ANSWER = "N";
    protected static final String REPORT_DATE_FORMAT = "dd.MM.yyyy";


    private String service;
    private String serviceVariation;
    private String question;
    private String questionCategori;
    private String questionSubCategori;
    private String responseType;
    
    public final String getService() {
        return service;
    }
    public final void setService(String separateParameter) {
        this.service = separateParameter;
    }
    public final String getServiceVariation() {
        return serviceVariation;
    }
    public final void setServiceVariation(String serviceVariation) {
        this.serviceVariation = serviceVariation;
    }
    public final String getQuestion() {
        return question;
    }
    public final void setQuestion(String question) {
        this.question = question;
    }
    public final String getQuestionCategori() {
        return questionCategori;
    }
    public final void setQuestionCategori(String questionCategori) {
        this.questionCategori = questionCategori;
    }
    public final String getQuestionSubCategori() {
        return questionSubCategori;
    }
    public final void setQuestionSubCategori(String questionSubCategori) {
        this.questionSubCategori = questionSubCategori;
    }
    public final String getResponseType() {
        return responseType;
    }
    public final void setResponseType(String responseType) {
        this.responseType = responseType;
    }
    public abstract class AdditionalImport{
        public abstract void importFromSentence(String sentence);
    }
    
}