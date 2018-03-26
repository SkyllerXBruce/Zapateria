package Persistencia;

@SuppressWarnings("serial")
public class DatabaseException extends RuntimeException {

	Exception m_realException;

	DatabaseException(String msg, Exception ex) {
		super(msg);
		m_realException = ex;
	}

	public Exception getRealException() {
		return m_realException;
	}

}
