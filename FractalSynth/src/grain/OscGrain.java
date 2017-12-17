package grain;

public class OscGrain extends Grain {

	private static final long serialVersionUID = -6085574005442843342L;

	public static final int DEFAULT_FREQ = 60;

	public int freq;

	public OscGrain(float strt, float dur, float amp, int freq, float att, float dec) {
		super(Instrument.osc.id, strt, dur, amp, att, dec);
		this.freq = freq;
	}

	public String statement() {
		return "i" + iID + " " + strt + " " + dur + " " + amp + " " + freq + " " + att + " " + dec;
	}
}