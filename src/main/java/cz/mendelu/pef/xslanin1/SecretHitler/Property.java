package cz.mendelu.pef.xslanin1.SecretHitler;

public class Property<T> {
    private T value;

    public void setValue(T value) {
        this.value = value;
    }

    public T getValue() {
        return value;
    }
}
