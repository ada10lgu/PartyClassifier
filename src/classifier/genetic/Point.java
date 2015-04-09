package classifier.genetic;

import java.util.Random;

public class Point implements Comparable<Point> {

	private static final double A_MIN = 0.001;
	private static final double A_MAX = 1;

	private static final double B_MIN = 150;
	private static final double B_MAX = 300;

	private double a, b;
	private double ans;

	public Point() {
		Random r = new Random();

		double aRange = A_MAX - A_MIN;
		a = r.nextDouble() * aRange + A_MIN;
		double bRange = B_MAX - B_MIN;
		b = r.nextDouble() * bRange + B_MIN;

	}

	public Point(double a, double b) {
		this.a = a;
		this.b = b;
	}

	public double getA() {
		return a;
	}

	public double getB() {
		return b;
	}

	public void setAns(double ans) {
		this.ans = ans;
	}

	@Override
	public int compareTo(Point arg0) {
		double x = ans - arg0.ans;
		if (x < 0)
			return 1;
		if (x > 0)
			return -1;
		return 0;
	}

	@Override
	public boolean equals(Object obj) {
		if (obj instanceof Point)
			return compareTo((Point) obj) == 0;
		return false;
	}

	public double getAns() {
		return ans;
	}

	@Override
	public String toString() {
		return "(" + a + "," + b + ") = " + ans;
	}

	public void mutateA() {
		Random r = new Random();

		double aRange = A_MAX - A_MIN;
		a = r.nextDouble() * aRange + A_MIN;
		System.out.println("I'm mutating a leg!!!");
	}

	public void mutateB() {
		Random r = new Random();

		double bRange = B_MAX - B_MIN;
		b = r.nextDouble() * bRange + B_MIN;
		System.out.println("I'm mutating b-fore I'm done!!!");
	}
}
