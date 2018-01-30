package grain;

import table.Table;

public class RandomShift implements Modifier {

	private double rMax;
	private Table table;

	public RandomShift(double rMax, Table table) {
		this.rMax = rMax;
		this.table = table;
	}

	@Override
	public int applyTo(Layer layer) {
		for (Grain g : layer.sequence) {
				double r = rMax * table.get(g.xNorm, g.yNorm) / table.kMax;
				double d = Math.random() * 2 * r - r;
				g.strt += d;
		}
		return layer.sequence.size();
	}

}
