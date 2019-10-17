package core;

public class Register {

    private Integer id;
    private Integer contents;

    public Register(Integer id) {
        this.id = id;
        this.contents = null;
    }

    public Integer getId() {
        return id;
    }

    public Integer get() {
        return contents;
    }

    public void set(Integer contents) {
        this.contents = contents;
    }
}
