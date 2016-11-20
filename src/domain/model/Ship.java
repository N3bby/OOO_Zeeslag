package domain.model;

public class Ship {

	private String name;
	private int length;
	private int x;
	private int y;
	private Orientation orientation;
	
	public Ship(String name, int length, int x, int y, Orientation orientation) {
		setName(name);
		setLength(length);
		setX(x);
		setY(y);
		setOrientation(orientation);
	}
	
	public String getName() {
		return name;
	}

	public void setName(String name) {
		if(name.equals(null) || name.trim().isEmpty()) throw new IllegalArgumentException();
		this.name = name;
	}

	public int getLength() {
		return length;
	}

	public void setLength(int length) {
		if(length < 1) throw new IllegalArgumentException();
		this.length = length;
	}

	public int getX() {
		return x;
	}

	public void setX(int x) {
		if(x < 0 || x > 10) throw new IllegalArgumentException();
		this.x = x;
	}

	public int getY() {
		return y;
	}

	public void setY(int y) {
		if(y < 0 || y > 10) throw new IllegalArgumentException();
		this.y = y;
	}

	public Orientation getOrientation() {
		return orientation;
	}

	public void setOrientation(Orientation orientation) {
		if(orientation == null) throw new IllegalArgumentException();
		this.orientation = orientation;
	}

    @Override
    public String toString() {
        return "Ship{" +
                "length=" + length +
                ", x=" + x +
                ", y=" + y +
                '}';
    }
}
