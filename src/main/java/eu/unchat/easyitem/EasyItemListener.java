package eu.unchat.easyitem;

import cn.nukkit.event.Cancellable;
import cn.nukkit.event.Event;
import cn.nukkit.event.EventHandler;
import cn.nukkit.event.Listener;
import cn.nukkit.event.block.BlockBreakEvent;
import cn.nukkit.event.block.BlockPlaceEvent;
import cn.nukkit.event.player.PlayerDropItemEvent;
import cn.nukkit.event.player.PlayerInteractEvent;
import cn.nukkit.item.Item;

import java.util.function.BiConsumer;

public final class EasyItemListener implements Listener {

    private final EasyItemHandler handler;

    public EasyItemListener(EasyItemHandler handler) {
        this.handler = handler;
    }

    @EventHandler
    public void onDrop(final PlayerDropItemEvent event) {
        Item item = event.getItem();
        if (item == null) {
            return;
        }

        handleEvent(event, item, (t, e) -> t.onDrop(event.getPlayer(), e));
    }

    @EventHandler
    public void onInteract(final PlayerInteractEvent event) {
        Item item = event.getItem();
        if (item == null) {
            return;
        }

        handleEvent(event, item, (t, e) -> t.onInteract(event.getPlayer(), e));
    }

    @EventHandler
    public void onPlace(final BlockPlaceEvent event) {
        Item item = event.getItem();
        if (item == null) {
            return;
        }

        handleEvent(event, item, (t, e) -> t.onPlace(event.getPlayer(), e));
    }

    @EventHandler
    public void onBreak(final BlockBreakEvent event) {
        Item item = event.getItem();
        if (item == null) {
            return;
        }

        handleEvent(event, item, (t, e) -> t.onBreak(event.getPlayer(), e));
    }

    private <T extends Event> void handleEvent(final T event, final Item baseItem, final BiConsumer<EasyItemProvider, T> action) {
        handler.findItem(baseItem).ifPresent(item -> {
            if (event instanceof Cancellable cancellable) {
                if (event instanceof PlayerDropItemEvent && !item.isDroppable()) {
                    cancellable.setCancelled(true);
                }

                if (event instanceof BlockBreakEvent && !item.isBreakable()) {
                    cancellable.setCancelled(true);
                }
            }

            action.accept(item.getProvider(), event);
        });
    }
}
