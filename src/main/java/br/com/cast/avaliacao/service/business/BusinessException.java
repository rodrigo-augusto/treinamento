package br.com.cast.avaliacao.service.business;

public class BusinessException extends RuntimeException {

    private Object[] args;

    public BusinessException(final String message) {
        super(message);
    }

    public BusinessException(final String message, final Object[] args) {
        this(message);
        this.args = args;
    }

    public Object[] getArgs() {
        return args;
    }
}
