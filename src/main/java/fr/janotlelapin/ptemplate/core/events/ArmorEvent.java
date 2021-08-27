package fr.janotlelapin.ptemplate.core.events;

import org.bukkit.entity.Player;
import org.bukkit.event.*;
import org.bukkit.event.player.PlayerEvent;
import org.bukkit.inventory.ItemStack;

/**
 * @author Arnah
 * @since Jul 30, 2015
 */
public class ArmorEvent extends PlayerEvent implements Cancellable {
    private boolean cancel = false;
    private final EquipMethod method;
    private final ArmorType type;
    private final ItemStack oldArmorPiece, newArmorPiece;

    public ArmorEvent(
        final Player who,
        final EquipMethod method,
        final ArmorType type,
        final ItemStack oldArmorPiece,
        final ItemStack newArmorPiece
    ) {
        super(who);
        this.method = method;
        this.type = type;
        this.oldArmorPiece = oldArmorPiece;
        this.newArmorPiece = newArmorPiece;
    }

    public static HandlerList HANDLERS = new HandlerList();

    @Override
    public HandlerList getHandlers() {
        return HANDLERS;
    }

    public static HandlerList getHandlerList() {
        return HANDLERS;
    }

    @Override
    public boolean isCancelled() {
        return cancel;
    }

    @Override
    public void setCancelled(boolean cancel) {
        this.cancel = cancel;
    }

    public EquipMethod getMethod() {
        return method;
    }

    public ArmorType getType() {
        return type;
    }

    public ItemStack getOldArmorPiece() {
        return oldArmorPiece;
    }

    public ItemStack getNewArmorPiece() {
        return newArmorPiece;
    }

    public enum EquipMethod {
        SHIFT_CLICK,
        DRAG,
        PICK_DROP,
        HOTBAR,
        HOTBAR_SWAP,
        BROKE,
        DEATH
    }
}

