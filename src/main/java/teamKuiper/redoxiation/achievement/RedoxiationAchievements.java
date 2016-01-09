package teamKuiper.redoxiation.achievement;

import java.util.HashMap;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.stats.Achievement;
import net.minecraftforge.common.AchievementPage;
import teamKuiper.redoxiation.blocks.RedoxiationBlocks;
import teamKuiper.redoxiation.items.RedoxiationGenericItems;

public class RedoxiationAchievements {
	public static boolean isachivenable;
	private static AchievementPage achievementsPage;
	private static HashMap<String, Achievement> achievementsList = new HashMap<String, Achievement>();

	public static void addAchivement(String name, Achievement achievement) {
		if (!isachivenable) {
			return;
		}

		achievementsList.put(name, achievement.registerStat());
	}

	public static Achievement getAchievement(String name) {
		return achievementsList.get(name);
	}

	public static void triggerAchievement(EntityPlayer player, String name) {
		if (!isachivenable) {
			return;
		}

		Achievement ach = getAchievement(name);

		if (ach != null) {
			player.triggerAchievement(ach);
		}
	}

	public static void addDefaultAchievements() {
		if (!isachivenable) {
			return;
		}
		// new Achievement(Name, Name, xCode(?), yCode(?), display item,
		// required achievement)
		addAchivement("redoxiation.start", new Achievement("redoxiation.start", "redoxiation.start", 0, 0, RedoxiationGenericItems.ingotCopper, null));
		addAchivement("redoxiation.blast", new Achievement("redoxiation.blast", "redoxiation.blast", 1, 2, RedoxiationBlocks.BlastFurnaceBlock, getAchievement("redoxiation.start")));
	}

	public static void registerAchievementPane() {
		if (!isachivenable) {
			return;
		}

		Achievement[] achievements = new Achievement[achievementsList.size()];

		achievements = achievementsList.values().toArray(achievements);
		achievementsPage = new AchievementPage("Redoxiation", achievements);
		AchievementPage.registerAchievementPage(achievementsPage);
	}

}
