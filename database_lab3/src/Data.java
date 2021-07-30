import java.util.Objects;

public class Data {
    private int A;  // 4字节int
    private String B;  // 12位String

    public Data(int a, String b) {
        A = a;
        B = b;
    }

    public int getA() {
        return A;
    }

    public String getB() {
        return B;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Data data = (Data) o;
        return A == data.A &&
                Objects.equals(B, data.B);
    }

    @Override
    public int hashCode() {
        return Objects.hash(A, B);
    }
}
