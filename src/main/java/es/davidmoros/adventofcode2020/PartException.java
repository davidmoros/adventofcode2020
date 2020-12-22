package es.davidmoros.adventofcode2020;

public class PartException extends RuntimeException {
    private static final long serialVersionUID = 4694324537587887672L;

    public PartException(String errorMessage) {
        super(errorMessage);
    }

    public PartException(String errorMessage, Throwable err) {
        super(errorMessage, err);
    }
}