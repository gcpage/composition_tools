package main;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

import grain.Inflate;
import grain.RandomShift;
import grain.UniformShift;

public class ScriptReader {

	private FractalSynth fractalSynth;
	private Scanner in;

	public ScriptReader(String path, FractalSynth fractalSynth) {
		try {
			in = new Scanner(new File(path));
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		in.nextLine(); // Skip project name
		this.fractalSynth = fractalSynth;
	}

	public ScriptReader(String script) {
		try {
			in = new Scanner(new File("scripts/" + script));
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		this.fractalSynth = new FractalSynth(in.nextLine());
	}

	public void execute() {
		while (in.hasNext()) {
			String[] cmd = in.nextLine().split(" ");
			if (!cmd[0].equals("") && !cmd[0].startsWith("#")) {
				if (cmd[0].equals("inflate")) {
					System.out.println("Resizing grains...");
					fractalSynth.applyMod(new Inflate(Double.parseDouble(cmd[1]), Double.parseDouble(cmd[2]),
							Double.parseDouble(cmd[3]), fractalSynth.getTable(cmd[4])));
				} else if (cmd[0].equals("rshift")) {
					System.out.println("Shifting grains...");
					fractalSynth.applyMod(new RandomShift(Double.parseDouble(cmd[1]), Double.parseDouble(cmd[2]),
							fractalSynth.getTable(cmd[3])));
				} else if (cmd[0].equals("reverb")) {
					// TODO
				} else if (cmd[0].equals("spatial")) {
					// TODO
				} else if (cmd[0].equals("ushift")) {
					fractalSynth.applyMod(new UniformShift(Double.parseDouble(cmd[1])));
				} else if (cmd[0].equals("pulsar")) {
					System.out.println("Generating pulsar matrix...");
					fractalSynth.genPulsarMatrix(Integer.parseInt(cmd[1]), Integer.parseInt(cmd[2]),
							Integer.parseInt(cmd[3]), Integer.parseInt(cmd[4]), Integer.parseInt(cmd[5]),
							Integer.parseInt(cmd[6]), Integer.parseInt(cmd[7]), Double.parseDouble(cmd[8]),
							Integer.parseInt(cmd[9]), cmd[10], cmd[11]);
				} else if (cmd[0].equals("layer")) {
					if (!fractalSynth.changeActiveLayer(cmd[1])) {
						fractalSynth.newLayer(cmd[1]);
					}
				}
			}
		}
	}

	private void prepare() {
		fractalSynth.clear();
	}

	private void render(String title) {
		fractalSynth.renderAll(title);
	}

	public static void main(String[] args) {
		ScriptReader scriptReader = new ScriptReader(args[0]);
		scriptReader.prepare();
		scriptReader.execute();
		scriptReader.render(args[1]);
	}
}
