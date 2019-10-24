package core;

public class Register {

    private Integer contents;

    public Register() {
        this.contents = null;
    }

    public Integer get() {
        return contents;
    }

    public void set(Integer contents) {
        this.contents = contents;
    }

    public void increment() {
        this.contents++;
    }

    public void decrement() {
        this.contents--;
    }
}
