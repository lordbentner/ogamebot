package ogamebot;

public class main {
	
	static vue_ensemble vue;
	public static int launch() {
		try {
			vue = new vue_ensemble();
			vue.connexion();
			while(true)
			{
				vue.vueEnsemble();
			}
		}
		catch(Exception e)
		{
			System.out.println("izi:"+e.getMessage());
			vue.getDriver().quit();
			return launch();
		}
	}

	public static void main(String[] args) {
			launch();
	}

}
