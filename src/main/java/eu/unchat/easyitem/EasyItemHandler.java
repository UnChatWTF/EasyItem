package eu.unchat.easyitem;

import cn.nukkit.Server;
import cn.nukkit.item.Item;
import cn.nukkit.plugin.PluginBase;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Optional;
import java.util.UUID;

public final class EasyItemHandler {
    private static EasyItemHandler instance;

    private final PluginBase plugin;

    private final Collection<EasyItem> items;

    public EasyItemHandler(final PluginBase plugin) {
        if (instance != null) {
            throw new IllegalStateException("Instance already exists");
        }

        instance = this;

        this.plugin = plugin;

        this.items = new ArrayList<>();

        Server.getInstance().getPluginManager().registerEvents(new EasyItemListener(this), plugin);
    }

    public static void init(final PluginBase plugin) {
        new EasyItemHandler(plugin);
    }

    public static EasyItemHandler get() {
        return instance;
    }

    public void cleanUp() {
        items.clear();
    }

    public void registerItem(final EasyItem item) {
        if (items.contains(item)) {
            throw new IllegalStateException("Item already registered");
        }

        items.add(item);
    }

    public Optional<EasyItem> findItem(final Item item) {
        return items.stream()
                .filter(easyItem -> easyItem.getUuid().equals(item.getNamedTag().getString("easy_item")))
                .findFirst();
    }

    private Optional<UUID> getItemUniqueId(final Item item) {
        return Optional.ofNullable(item.getNamedTag().getString("easy_item"))
                .map(UUID::fromString);
    }

}
