package grain.mod;

import java.util.ArrayList;
import java.util.List;

import grain.Grain;
import grain.GrainType;
import grain.Layer;
import grain.OscGrain;
import grain.SampleGrain;
import sco.SoundfileFTable;

public class OverlaySample implements Modifier {

	private float fMod; // Frequency (pitch) modifier
	private int band; // Bandpass bandwidth in Hz
	private String filename; // Sample sound file name (within materials/)

	public OverlaySample(String filename, float fMod, int band) {
		this.filename = filename;
		this.fMod = fMod;
		this.band = band;
	}
	
	public OverlaySample(String filename) {
		this.filename = filename;
		this.fMod = 2;
		this.band = 20000;
	}

	@Override
	public int applyTo(Layer layer) {
		List<Grain> matrix = new ArrayList<Grain>();
		int count = 0;
		int fID = layer.addFTable(new SoundfileFTable(filename));
		for (Grain g : layer.sequence) {
			if (g.gType == GrainType.osc) {
				OscGrain og = (OscGrain) g;
				SampleGrain sg = new SampleGrain(og.strt, og.dur, og.amp, og.att, og.dec, fMod, og.strt, og.freq, band,
						fID, og.xNorm, og.yNorm);
				matrix.add(sg);
				count++;
			} else {
				matrix.add(g);
			}
		}
		layer.sequence.clear();
		layer.addGrains(matrix);
		return count;
	}

}
