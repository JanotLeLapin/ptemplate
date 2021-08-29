
import net.minecraft.server.v1_8_R3.IChatBaseComponent
import net.minecraft.server.v1_8_R3.PacketPlayOutTitle
import org.bukkit.craftbukkit.v1_8_R3.entity.CraftPlayer
import org.bukkit.entity.Player

class Util {
    companion object {
        fun sendTitle(player: Player, title: String, subtitle: String = "", stay: Int = 80, fadeIn: Int = 1, fadeOut: Int = 1) {
            if (player !is CraftPlayer) return

            val c = player.handle.playerConnection

            val text = IChatBaseComponent.ChatSerializer.a("{\"text\": \"$title\"}")
            val packet = PacketPlayOutTitle(
                PacketPlayOutTitle.EnumTitleAction.TITLE,
                text,
                fadeIn,
                stay,
                fadeOut
            )

            val subText = IChatBaseComponent.ChatSerializer.a("{\"text\": \"$subtitle\"}")
            val subPacket = PacketPlayOutTitle(
                PacketPlayOutTitle.EnumTitleAction.SUBTITLE,
                subText,
                fadeIn,
                stay,
                fadeOut
            )

            c.sendPacket(subPacket)
            c.sendPacket(packet)
        }
    }
}
