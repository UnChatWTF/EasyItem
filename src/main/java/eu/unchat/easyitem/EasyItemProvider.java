package eu.unchat.easyitem;

import cn.nukkit.Player;
import cn.nukkit.event.block.BlockBreakEvent;
import cn.nukkit.event.block.BlockPlaceEvent;
import cn.nukkit.event.inventory.InventoryClickEvent;
import cn.nukkit.event.player.PlayerDropItemEvent;
import cn.nukkit.event.player.PlayerInteractEvent;
import cn.nukkit.item.Item;

public interface EasyItemProvider {

    /**
     * Get the base item for this EasyItem
     *
     * @return The base item as {@link Item}
     * Note: This item will be used for every instance of EasyItem, that means every Player will have the same item
     */
    Item getBaseItem();

    /**
     * This method initializes the EasyItem for a player
     *
     * @param player The player
     * @param item   The item
     */
    default void init(final Player player, final Item item) {
    }

    /**
     * This method is called when the player drops the item
     *
     * @param player The player
     * @param event  The event
     */
    default void onDrop(final Player player, final PlayerDropItemEvent event) {
    }

    /**
     * This method is called when the player interacts with the item
     *
     * @param player The player
     * @param event  The event
     */
    default void onInteract(final Player player, final PlayerInteractEvent event) {
    }

    /**
     * This method is called when the player places a block
     *
     * @param player The player
     * @param event  The event
     */
    default void onPlace(final Player player, final BlockPlaceEvent event) {
    }

    /**
     * This method is called when the player breaks a block
     *
     * @param player The player
     * @param event  The event
     */
    default void onBreak(final Player player, final BlockBreakEvent event) {
    }

    /**
     * This method is called when the player clicks on the item in the inventory
     *
     * @param player The player
     * @param event  The event
     */
    default void onClick(final Player player, final InventoryClickEvent event) {
    }

}
