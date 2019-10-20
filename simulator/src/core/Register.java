package core;

public class Register {

    private Object contents;

    public Register() {
        this.contents = null;
    }

    public Object get() {
        return contents;
    }

    public void set(Object contents) {
        this.contents = contents;
    }
}
