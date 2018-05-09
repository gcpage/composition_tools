package main;

import java.io.File;
import java.util.List;

import grain.FTable;
import grain.Generator;
import grain.GrainManager;
import grain.Modifier;
import grain.PulsarMatrix;
import table.Filter;
import table.FractalTable;
import table.TableManager;

public class FractalSynth {

	private GrainManager grainManager;
	private TableManager tableManager;
	private String mainDir;
	private String mainProjectDir;
	private String mainTableDir;

	public FractalSynth() {
		File path = new File("");
		mainDir = path.getAbsolutePath().replace("\\", "/") + "/";
		mainProjectDir = mainDir + "projects/";
		mainTableDir = mainDir + "tables/";
		openDir(mainProjectDir);
		openDir(mainTableDir);
		grainManager = null;
		tableManager = new TableManager(mainTableDir);
	}

	public void openProject(String project) {
		grainManager = new GrainManager(mainProjectDir, project);
	}
	
	public boolean projectLoaded() {
		return grainManager != null;
	}

	public String getProject() {
		return grainManager.getProject();
	}

	public List<String> getTableNames() {
		return tableManager.getTableList();
	}

	public FractalTable getTable(String name) {
		return tableManager.getTable(name);
	}
	
	public boolean hasTable(String name) {
		return tableManager.getTableList().contains(name);
	}

	public void deleteTable(String name) {
		tableManager.deleteTable(name);
	}

	public void filterTable(String name, Filter filter) {
		tableManager.filter(name, filter);
	}

	public void addTable(String name, int tRes, int fRes, double zoomVel, int zoomMax, int kMax, double posX,
			double posY) {
		tableManager.generateFractalTable(name, tRes, fRes, zoomVel, zoomMax, kMax, posX, posY);
	}

	public void addTable(String name, String otherName) {
		FractalTable other = tableManager.getTable(otherName);
		int tRes = other.tRes;
		int fRes = other.fRes;
		double zoomVel = other.zoomVel;
		int zoomMax = other.zoomMax;
		int kMax = other.kMax;
		double posX = other.posX;
		double posY = other.posY;
		tableManager.generateFractalTable(name, tRes, fRes, zoomVel, zoomMax, kMax, posX, posY);
	}

	public String getTableProperties(String name) {
		return tableManager.getTable(name).toString();
	}

	public int genPulsarMatrix(int fMinP, int fMaxP, int fResP, int fMinD, int fMaxD, int minResD, int maxResD,
			double zoomVel, int zoomMax, String tablePName, String tableDName) {
		FractalTable tableP = tableManager.getTable(tablePName);
		FractalTable tableD = tableManager.getTable(tableDName);
		return grainManager.generateGrains(new PulsarMatrix(fMinP, fMaxP, fResP, fMinD, fMaxD, minResD, maxResD, zoomVel, zoomMax,
				tableP, tableD));
	}
	
	public int generateGrains(Generator gen) {
		return grainManager.generateGrains(gen);
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

	public int applyMod(Modifier mod) {
		return grainManager.applyMod(mod);
	}

	public void copyFrom(String other) {
		grainManager.copyFrom(other);
	}

	public boolean renameLayer(String name) {
		return grainManager.renameLayer(name);
	}

	public boolean changeActiveLayer(String name) {
		return grainManager.setActiveLayer(name);

	}

	public boolean newLayer(String name, double duration) {
		return grainManager.newLayer(name, duration);
	}

	public List<String> getLayerNames() {
		return grainManager.layerNames;
	}
	
	public boolean hasLayer(String name) {
		return grainManager.layerNames.contains(name);
	}

	public void clear() {
		grainManager.removeAll();
	}

	public void addFTable(FTable ft) {
		grainManager.active.fTables.add(ft);
	}
}
