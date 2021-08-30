
import net.minecraft.server.v1_8_R3.IChatBaseComponent
import net.minecraft.server.v1_8_R3.PacketPlayOutTitle
import org.bukkit.craftbukkit.v1_8_R3.entity.CraftPlayer
import org.bukkit.entity.Player
import org.bukkit.inventory.ItemStack
import org.bukkit.potion.PotionEffect
import org.bukkit.potion.PotionEffectType

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

        fun give(player: Player, item: ItemStack, slot: Int) {
            val inv = player.inventory

            if (inv.getItem(slot) != null) {
                // Find empty slot
                for (i in 0..35) {
                    if (inv.getItem(i) != null) continue

                    // Move it from requested slot to empty slot
                    inv.setItem(i, inv.getItem(slot))
                    inv.setItem(slot, item)

                    return
                }

                // Inventory is full
                val oldItem = inv.getItem(slot)
                player.world.dropItem(player.location, oldItem)
            }

            inv.setItem(slot, item)
        }

        /**
         * Returns a PotionEffect
         * @param type The type of the potion effect
         * @param duration The duration of the potion effect in seconds.
         * @param amplifier The level of the effect.
         */
        fun effect(type: PotionEffectType, duration: Int = 10000000, amplifier: Int = 0): PotionEffect {
            return PotionEffect(type, duration * 20, amplifier, false, false)
        }
    }
}
