package grain;

import java.io.Serializable;

public abstract class Grain implements Serializable {

	private static final long serialVersionUID = 7289961340282365917L;

	// Grain specific constants
	public static final float DEFAULT_DUR = 0.01f;
	public static final float DEFAULT_AMP = 0.01f;
	public static final float DEFAULT_ATT = 0.001f;
	public static final float DEFAULT_DEC = 0.001f;
	public static final int DEFAULT_FREQ = 60;

	// Rendering constants
	public static final int SAMPLE_RATE = 44100;
	public static final int SAMPLE_CONTROL_RATIO = 32;
	public static final int NUMBER_OF_CHANNELS = 2;
	public static final float AMP_SCALE = 1.0f;

	public Instrument gType;
	public float strt;
	public float dur;
	public float amp;
	public float att;
	public float dec;
	public double xNorm;
	public double yNorm;

	public Grain(Instrument gType, float strt, float dur, float amp, float att, float dec) {
		this.gType = gType;
		this.strt = strt;
		this.dur = dur;
		this.amp = amp;
		this.att = att;
		this.dec = dec;

	}

	public String statement() {
		return "i " + gType.id + " " + strt + " " + dur + " " + amp;
	}

	public boolean overlaps(Grain other) {
		// Overlapping if starts during, ends during, or completely covers
		return (this.strt <= other.strt && this.strt + this.dur >= other.strt)
				|| (this.strt <= other.strt + other.dur && this.strt + this.dur >= other.strt + other.dur)
				|| (this.strt > other.strt && this.strt + this.dur < other.strt + other.dur);
	}
}
