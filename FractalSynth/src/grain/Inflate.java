package grain;

import table.Table;

public class Inflate implements Modulator {
	
	private double dMin;
	private double dMax;
	private double zoomVel;
	private Table table;
	
	public Inflate(double dMin, double dMax, double zoomVel, Table table) {
		this.dMin = dMin;
		this.dMax = dMax;
		this.zoomVel = zoomVel;
		this.table = table;
	}

	@Override
	public int applyTo(Layer layer) {
		int fMin = layer.getFMin();
		int fMax = layer.getFMax();
		for (Grain g : layer.sequence) {
			double m = table.get(g.strt, g.freq, fMin, fMax, zoomVel) / table.kMax;
			g.dur = (float) ((dMax - dMin) * m + dMin);
			g.att = g.dur / 2;
			g.dec = g.dur / 2;
		}
		return layer.sequence.size();
	}

}