package wirtualnySwiat;

public class Wspolrzedne {

    public int x,y;

    public Wspolrzedne(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public Wspolrzedne(Wspolrzedne inne) {
        this.x = inne.x;
        this.y = inne.y;
    }

    @Override
    public boolean equals(Object obj) {
        if(obj instanceof Wspolrzedne) {
            Wspolrzedne other = (Wspolrzedne)obj;
            return (x == other.x && y == other.y);
        }
        return false;
    }
}
