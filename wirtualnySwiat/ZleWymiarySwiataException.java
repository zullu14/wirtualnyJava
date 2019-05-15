package wirtualnySwiat;

public class ZleWymiarySwiataException extends Exception {

    ZleWymiarySwiataException() {
        super();
    }

    /** Konstruktor przyjmujący jako parametr tekst opisujący błąd */
    public ZleWymiarySwiataException(String message) {
        super(message);
    }

    /** Konstruktor przyjmujący jako parametr referencje do innego * wyjątku, który spowodował błąd */
    public ZleWymiarySwiataException(Throwable cause) {
        super(cause);
    }

    /** Konstruktor przyjmujący jako parametr tekst opisujący błąd oraz wyjątek, który spowodował błąd. */
    public ZleWymiarySwiataException(String message, Throwable cause) {
        super(message, cause);
    }
}
