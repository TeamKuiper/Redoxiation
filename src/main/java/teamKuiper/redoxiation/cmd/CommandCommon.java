package teamKuiper.redoxiation.cmd;

import java.util.ArrayList;
import java.util.List;

import net.minecraft.command.CommandException;
import net.minecraft.command.ICommand;
import net.minecraft.command.ICommandSender;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.server.MinecraftServer;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.text.TextComponentTranslation;

public class CommandCommon implements ICommand {
	private List<String> aliases;
	public CommandCommon() {
		this.aliases = new ArrayList<String>();
		this.aliases.add("redox");
		this.aliases.add("redoxiation");
	}

	@Override
	public int compareTo(ICommand arg0) {
		return 0;
	}


	@Override
	public String getName() {
		return "redoxiation";
	}


	@Override
	public String getUsage(ICommandSender sender) {
		return "redoxiation <text>";
	}


	@Override
	public List<String> getAliases() {
		return this.aliases;
	}


	@Override
	public void execute(MinecraftServer server, ICommandSender sender, String[] args) throws CommandException {
		if (sender instanceof EntityPlayer) {
			EntityPlayer player = (EntityPlayer) sender;
			if (args.length == 0) {
				player.sendMessage(new TextComponentTranslation( "[Redoxiation] Hello,  " + player.getGameProfile().getName()));
			}
		}
	}


	@Override
	public boolean checkPermission(MinecraftServer server, ICommandSender sender) {
		return true;
	}


	@Override
	public List<String> getTabCompletions(MinecraftServer server, ICommandSender sender, String[] args,
			BlockPos targetPos) {
		return null;
	}

	@Override
	public boolean isUsernameIndex(String[] p_82358_1_, int p_82358_2_) {
		return false;
	}
}
