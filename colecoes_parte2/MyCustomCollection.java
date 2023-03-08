import java.util.ArrayList;
import java.util.List;

public class MyCustomCollection {
    private List<Register> man = new ArrayList<>();
    private List<Register> woman = new ArrayList<>();
    private Integer size = 0;

    // Size
    public int size() {
        return this.size;
    }

    // Add new Register into Collection
    public boolean add(Register register) {
        switch (register.second) {
            case MAN -> this.man.add(register);
            case WOMAN -> this.woman.add(register);
        }
        this.size++;
        return true;
    }

    public List<Register> getMan() {
        return man;
    }

    public List<Register> getWoman() {
        return woman;
    }
}
