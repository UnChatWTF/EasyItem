package eu.unchat.easyitem;

import cn.nukkit.Player;
import cn.nukkit.item.Item;
import cn.nukkit.nbt.tag.CompoundTag;

import java.util.UUID;

public final class EasyItem {

    private final EasyItemProvider provider;

    private final UUID uuid;

    private boolean droppable, breakable;

    public EasyItem(EasyItemProvider provider) {
        this.provider = provider;
        this.uuid = UUID.randomUUID();
        EasyItemHandler.get().registerItem(this);
    }

    public EasyItem droppable(final boolean droppable) {
        this.droppable = droppable;
        return this;
    }

    public EasyItem breakable(final boolean breakable) {
        this.breakable = breakable;
        return this;
    }

    public Item get(final Player player) {
        final Item item = provider.getBaseItem().clone();
        provider.init(player, item);
        handleNBTChange(item);
        return item;
    }

    private void handleNBTChange(final Item item) {
        CompoundTag namedTag = item.getNamedTag();
        namedTag.putString("easy_item", uuid.toString());
        item.setNamedTag(namedTag);
    }

    public EasyItemProvider getProvider() {
        return provider;
    }

    public UUID getUuid() {
        return uuid;
    }

    public boolean isDroppable() {
        return droppable;
    }

    public boolean isBreakable() {
        return breakable;
    }
}
