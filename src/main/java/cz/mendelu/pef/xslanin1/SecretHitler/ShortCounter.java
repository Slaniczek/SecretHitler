package cz.mendelu.pef.xslanin1.SecretHitler;

public class ShortCounter {
    private short value = 0;

    public void inc(){
        value++;
    }

    public void dec(){
        value--;
    }

    public short getValue() {
        return value;
    }

    public void setValue(short value) {
        this.value = value;
    }

    public void reset(){
        value = 0;
    }
}
