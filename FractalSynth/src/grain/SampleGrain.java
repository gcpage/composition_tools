package grain;

public class SampleGrain extends Grain {

	public SampleGrain(float strt, float dur, float amp, float att, float dec) {
		super(Instrument.sample.id, strt, dur, amp, att, dec);
		// TODO Auto-generated constructor stub
	}
	
	public String statement() {
		return "i" + iID + " " + strt + " " + dur + " " + amp + " " + att + " " + dec;
	}

}