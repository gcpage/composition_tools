package grain.mod;

import grain.Grain;
import grain.Layer;

public class Normalize implements Modifier {
	
	double mod;

	public Normalize(double mod) {
		super();
		this.mod = mod;
	}

	@Override
	public int applyTo(Layer layer) {
			float maxAmp = 0;
			for (Grain g1 : layer.sequence) {
				float amp = g1.amp;
				for (Grain g2 : layer.sequence) {
					if (g1 != g2 && g1.overlaps(g2)) {
						amp += g2.amp;
					}
				}
				if (amp > maxAmp)
					maxAmp = amp;
			}
			for (Grain g : layer.sequence) {
				g.amp /= maxAmp;
				g.amp *= mod;
			}
			return (int) (100 / maxAmp); // Percent modifier
	}

}
