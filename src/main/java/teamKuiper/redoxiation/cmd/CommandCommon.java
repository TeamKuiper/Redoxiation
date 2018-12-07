package teamKuiper.redoxiation.cmd;

import java.util.ArrayList;
import java.util.List;

import net.minecraft.command.ICommand;
import net.minecraft.command.ICommandSender;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.ChatComponentTranslation;

public class CommandCommon implements ICommand {
	private List aliases;
	public CommandCommon() {
		this.aliases = new ArrayList();
		this.aliases.add("redox");
		this.aliases.add("redoxiation");
	}

	@Override
	public int compareTo(Object arg0) {
		return 0;
	}

	@Override
	public String getCommandName() {
		return "redoxiation";
	}

	@Override
	public String getCommandUsage(ICommandSender p_71518_1_) {
		return "redoxiation <text>";
	}

	@Override
	public List getCommandAliases() {
		return this.aliases;
	}

	@Override
	public void processCommand(ICommandSender icommandsender, String[] argString) {
		if (icommandsender instanceof EntityPlayer) {
			EntityPlayer player = (EntityPlayer) icommandsender;
			if (argString.length == 0) {
				player.addChatMessage(new ChatComponentTranslation( "[Redoxiation] Hello,  " + player.getGameProfile().getName()));
			}
		}

	}

	@Override
	public boolean canCommandSenderUseCommand(ICommandSender p_71519_1_) {
		return true;
	}

	@Override
	public List addTabCompletionOptions(ICommandSender p_71516_1_, String[] p_71516_2_) {
		return null;
	}

	@Override
	public boolean isUsernameIndex(String[] p_82358_1_, int p_82358_2_) {
		return false;
	}
}
