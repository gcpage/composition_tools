package main;

import java.io.File;
import java.util.List;

import grain.GrainManager;
import grain.Modulator;
import table.Filter;
import table.Table;
import table.TableManager;

public class FractalSynth {

	private GrainManager grainManager;
	private TableManager tableManager;
	private String mainDir;
	private String mainProjectDir;
	private String mainTableDir;

	public FractalSynth(String project) {
		File path = new File("");
		mainDir = path.getAbsolutePath().replace("\\", "/") + "/";
		mainProjectDir = mainDir + "projects/";
		mainTableDir = mainDir + "tables/";
		openDir(mainProjectDir);
		openDir(mainTableDir);
		grainManager = new GrainManager(mainProjectDir, project);
		tableManager = new TableManager(mainTableDir);
	}

	public String getProject() {
		return grainManager.getProject();
	}

	public List<String> getTableNames() {
		return tableManager.getTableList();
	}
	
	public Table getTable(String name) {
		return tableManager.getTable(name);
	}

	public void deleteTable(String name) {
		tableManager.deleteTable(name);
	}

	public void filterTable(String name, Filter filter) {
		tableManager.filter(name, filter);
	}

	public void addTable(String name, int tRes, int fRes, double zoomVel, int zoomMax, int kMax, double posX,
			double posY) {
		tableManager.generateTable(name, tRes, fRes, zoomVel, zoomMax, kMax, posX, posY);
	}
	
	public void addTable(String name, String otherName) {
		Table other = tableManager.getTable(otherName);
		int tRes = other.tRes;
		int fRes = other.fRes;
		double zoomVel = other.zoomVel;
		int zoomMax = other.zoomMax;
		int kMax = other.kMax;
		double posX = other.posX;
		double posY = other.posY;
		tableManager.generateTable(name, tRes, fRes, zoomVel, zoomMax, kMax, posX, posY);
	}

	public String getTableProperties(String name) {
		return tableManager.getTable(name).toString();
	}

	public void genPulsarMatrix(int fMinP, int fMaxP, int fResP, int fMinD, int fMaxD, int minResD, int maxResD, double zoomVel,
			int zoomMax, String tablePName, String tableDName) {
		Table tableP = tableManager.getTable(tablePName);
		Table tableD = tableManager.getTable(tableDName);
		int grains = grainManager.genPulsarMatrix(fMinP, fMaxP, fResP, fMinD, fMaxD, minResD, maxResD, zoomVel, zoomMax, tableP,
				tableD);
		System.out.println(grains + "grains created");
	}

	public static boolean openDir(String path) {
		File dir = new File(path);
		if (!dir.exists()) {
			dir.mkdir();
			return true;
		}
		return false;
	}
	
	public static void deleteFile(String path) {
		File dir = new File(path);
		dir.delete();
	}

	public String getActiveLayerName() {
		return grainManager.active.name;
	}

	public void saveLayers() {
		grainManager.save();
	}

	public void visualizeLayers() {
		grainManager.visualizeLayers();
	}
	
	public void renderAll(String title) {
		grainManager.renderAll(title);
	}

	public void clearLayer() {
		grainManager.clear();
	}

	public int applyMod(Modulator mod) {
		return grainManager.applyMod(mod);
	}

	public int getActiveLayerFMax() {
		return grainManager.active.getFMax();
	}

	public int getActiveLayerFMin() {
		return grainManager.active.getFMin();
	}

	public boolean renameLayer(String name) {
		return grainManager.renameLayer(name);
	}

	public boolean changeActiveLayer(String name) {
		return grainManager.setActiveLayer(name);
		
	}

	public boolean newLayer(String name) {
		return grainManager.newLayer(name);
	}

	public List<String> getLayerNames() {
		return grainManager.layerNames;
	}
	
	public void clear() {
		grainManager.removeAll();
	}
	
	public void executeScript(String path) {
		ScriptReader scriptReader = new ScriptReader(path, this);
		scriptReader.execute();
	}
}
