package ogamebot;

public interface IdBatiment {
	
	public final String mineMetal = "1";
	public final String mineCrystal = "2";
	public final String mineDeut = "3";
	public final String mineEnergy = "4";
	public final String usineRobots = "14";
	public final String hangarMetal = "22";
	public final String hangarCrystal = "23";
	public final String hangarDeut = "24";
	public final String laboratoire = "31";
	public final String chantier = "21";
	public final String satellite = "212";
	public int construireBatiment();
	public int construireInstallations();
}
