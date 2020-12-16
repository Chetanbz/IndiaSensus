package indiaCensus;

public class CensusBuilderException extends Exception {
    public ExceptionType type;
    enum ExceptionType {
        CENSUS_FILE_PROBLEM,UNABLE_TO_PARSE,IllegalStateException,INVALID_FILE_TYPE,DATA_IMPROPER, No_DATA;
    }
    public CensusBuilderException(String message, ExceptionType type) {
        super(message);
        this.type = type;
    }

    public CensusBuilderException(String message, ExceptionType type, Throwable cause) {
        super(message, cause);
        this.type = type;
    }
}
