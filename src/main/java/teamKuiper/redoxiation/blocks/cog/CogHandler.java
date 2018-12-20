package teamKuiper.redoxiation.blocks.cog;

public class CogHandler {
	
	CogType type;
	
	public CogHandler() {
		
	}
	
	/**
	 * If cog is empty and newType is not empty, changes cog type and returns true.
	 * If cog is not empty and newType is empty, remove cog.
	 * @param newType
	 * @return
	 */
	public boolean setCog(CogType newType) {
		if(newType == CogType.NONE ^ newType == CogType.NONE) {
			type = newType;
			return true;
		}
		return false;
	}
	
	public CogType getCogType() {
		return type;
	}
}
