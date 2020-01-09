package ua.com.company.record.analyzer.extension;

@SuppressWarnings("serial")
public class WrongDataTypeExtension extends RuntimeException {

    public WrongDataTypeExtension(String errorMessage) {
        super(errorMessage);
    }
}