
public class Box {
    private boolean isOpen = false;
    private int value = 0;
    private boolean isMine = false;



    public Box() {
    }

    public boolean isOpen() {
        return isOpen;
    }

    public boolean setOpen(boolean open) {
        isOpen = open;
        return isMine();
    }

    public boolean isMine() {
        return isMine;
    }

    public void setMine(boolean mine) {
        //todo change value
        isMine = mine;
    }


    public int getValue() {
        return value;
    }

    private void setValue(int value) {
        this.value = value;
    }

    public void increaseValue(){
        this.value++;
    }

    @Override
    public String toString() {
        return isOpen ? String.valueOf(getValue()) : "X";
    }


}
