package teamKuiper.redoxiation;

import codechicken.lib.packet.PacketCustom;
import codechicken.lib.packet.PacketCustom.IServerPacketHandler;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.network.play.INetHandlerPlayServer;
import teamKuiper.redoxiation.multipart.MultiPartEventHandler;

public class PacketHandler implements IServerPacketHandler {
    public static Object channel = Redoxiation.MODID;

    @Override
    public void handlePacket(PacketCustom packet, EntityPlayerMP sender, INetHandlerPlayServer nethandler) {
        switch (packet.getType()) {
            case 1:
                MultiPartEventHandler.place(sender, sender.world);
                break;
        }
    }
}
