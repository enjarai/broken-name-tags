package bluefirephoenix.brokennametags;

import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.item.v1.FabricItemSettings;
import net.fabricmc.fabric.api.itemgroup.v1.ItemGroupEvents;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemGroups;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;

public class BrokenNameTags implements ModInitializer {

    public static final Item BROKEN_NAMETAG = new BrokenNameTagItem(new FabricItemSettings().maxCount(1));

    @Override
    public void onInitialize() {
        Registry.register(Registries.ITEM, new Identifier("brokennametags", "broken_nametag"),  BROKEN_NAMETAG);

        ItemGroupEvents.modifyEntriesEvent(ItemGroups.TOOLS).register(entries -> {
            entries.add(BROKEN_NAMETAG);
        });
    }
}