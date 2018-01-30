package grain;

public class SoundfileFTable extends FTable {
	
	private static final long serialVersionUID = -2596051195054624160L;
	
	public String Sfilnam;
	public float iskip;
	public int iformat;
	public int ichn;

	public SoundfileFTable(String filename) {
		super(IFN_DEFAULT, Instrument.sample.genRoutine);
		this.Sfilnam = "materials/" + filename;
		this.iskip = 0; // Start at beginning
		this.iformat = 0;
		this.ichn = 0;
	}

	@Override
	public String statement() {
		return super.statement() + " " + "\"" + Sfilnam + "\" "
				+ iskip + " " + iformat + " " + ichn;
	}

}