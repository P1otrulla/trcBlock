package net.thundercode.block.cache;

import com.google.common.collect.Maps;
import net.thundercode.block.object.ItemChange;

import java.util.Map;

public class ItemChangeCache {

    private final Map<String, ItemChange> itemChangeCache;

    public ItemChangeCache(){
        this.itemChangeCache = Maps.newConcurrentMap();
    }

    public void addItem(final String id, final ItemChange itemChange){
        this.itemChangeCache.put(id, itemChange);
    }

    public Map<String, ItemChange> getCache() {
        return itemChangeCache;
    }
}
