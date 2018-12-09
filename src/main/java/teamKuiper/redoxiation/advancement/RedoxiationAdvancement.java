package teamKuiper.redoxiation.advancement;

import java.util.HashMap;

import net.minecraft.advancements.Advancement;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraftforge.common.AchievementPage;
import teamKuiper.redoxiation.blocks.RedoxiationBlocks;
import teamKuiper.redoxiation.items.RedoxiationGenericItems;

public class RedoxiationAdvancement {
	public static boolean isachivenable;
	private static AdvancementPage advancementsPage;
	private static HashMap<String, Advancement> advancementList = new HashMap<String, Advancement>();

	public static void addAchivement(String name, Advancement advancement) {
		if (!isachivenable) {
			return;
		}

		advancementList.put(name, advancement.registerStat());
	}

	public static Advancement getAdvancement(String name) {
		return advancementList.get(name);
	}

	public static void triggerAdvancement(EntityPlayer player, String name) {
		if (!isachivenable) {
			return;
		}

		Advancement ach = getAdvancement(name);

		if (ach != null) {
			player.triggerAdvancement(ach);
		}
	}

	public static void addDefaultAdvancements() {
		if (!isachivenable) {
			return;
		}
		// new Advancement(Name, Name, xCode(?), yCode(?), display item,
		// required advancement)
		addAdvancement("redoxiation.start", new Advancement("redoxiation.start", "redoxiation.start", 0, 0, RedoxiationGenericItems.ingotCopper, null));
		addAdvancement("redoxiation.blast", new Advancement("redoxiation.blast", "redoxiation.blast", 1, 2, RedoxiationBlocks.blastFurnaceBlock, getAdvencement("redoxiation.start")));
	}

	public static void registerAdvancementPane() {
		if (!isachivenable) {
			return;
		}

		Advancement[] advancements = new Advancement[advancementsList.size()];

		advancements = advancementsList.values().toArray(advancements);
		advancementPage = new AdvancementPage("Redoxiation", advancements);
		AdvancementPage.registerAdvancementPage(advancementsPage);
	}

}
