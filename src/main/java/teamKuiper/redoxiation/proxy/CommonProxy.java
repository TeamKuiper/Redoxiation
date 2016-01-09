package teamKuiper.redoxiation.proxy;

import teamKuiper.redoxiation.Redoxiation;

public class CommonProxy {
	public void registerTileEntitySpecialRenderer() {
		method();
	}

	private void method() {
		if (Redoxiation.dummybool)
			Redoxiation.logger.info("Tileentity proxy complete. Starting Recipe & Smelting ");
		else
			Redoxiation.logger.info("Tileentity proxy complete. Starting World Generator");
	}
}
