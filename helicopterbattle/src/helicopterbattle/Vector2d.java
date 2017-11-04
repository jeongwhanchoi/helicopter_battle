package helicopterbattle;

public class Vector2d {
	public final double x;
	public final double y;
	
	public Vector2d(double x, double y) {
		this.x = x;
		this.y = y;
	}
	
	public Vector2d add(Vector2d v) {
		return new Vector2d(x + v.x, y + v.y);
	}
	
	public Vector2d subtract(Vector2d v) {
		return new Vector2d(x + v.x, y + v.y);
	}
	
	public Vector2d inverse(Vector2d v) {
		return new Vector2d(-x, -y);
	}
	
	public Vector2d multiply(double a) {
		return new Vector2d(x * a, y * a);
	}
	
	public double dotProduct(Vector2d v) {
		return x*v.x + y*v.y;
	}
	
	public double length() {
		return Math.sqrt(x*x + y*y);
	}
}
