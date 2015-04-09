package classifier.genetic;

import java.util.Arrays;
import java.util.Random;

public class Population {
	private Point[] points;

	public Population(int size) {
		points = new Point[size];
		populate(size);
	}

	private void populate(int size) {
		for (int i = 0; i < size; i++)
			points[i] = new Point();

	}

	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();

		sb.append(points[0]);
		for (int i = 1; i < points.length; i++) {
			sb.append("\n");
			sb.append(points[i]);
		}
		return sb.toString();
	}

	public void sort() {
		Arrays.sort(points);
	}

	public void breed(int breeders) {
		Random r = new Random();
		int last = points.length - 1;

		for (int i = 0; i < points.length / 2 && i < breeders; i += 2) {
			Point mom = points[i];
			Point dad = points[i + 1];

			double child0a = mom.getA();
			double child1a = dad.getA();
			double child0b = mom.getB();
			double child1b = dad.getB();

			if (r.nextBoolean()) {
				child0a = dad.getA();
				child1a = mom.getA();
			}
			if (r.nextBoolean()) {
				child0b = dad.getB();
				child1b = mom.getB();
			}

			points[last] = new Point(child0a, child0b);
			points[last - 1] = new Point(child1a, child1b);

			last -= 2;

		}

	}

	public void mutate(double risk, int free) {
		Random r = new Random();
		for (int i = free; i < points.length; i++) {
			if (r.nextDouble() < risk) {
				points[i].mutateA();
			}
			if (r.nextDouble() < risk) {
				points[i].mutateB();
			}
		}
	}

	public Point[] getPoints() {
		return points;
	}

}
