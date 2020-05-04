package br.com.kerubin.api.notificador.contas;

public class NotificatorException extends RuntimeException {

	private static final long serialVersionUID = 1L;
	
	public NotificatorException() {
        super();
    }

    public NotificatorException(String message) {
        super(message);
    }

    public NotificatorException(String message, Throwable cause) {
        super(message, cause);
    }

}
