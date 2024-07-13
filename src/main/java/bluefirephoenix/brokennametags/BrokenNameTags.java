package bluefirephoenix.brokennametags;

import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.itemgroup.v1.ItemGroupEvents;
import net.minecraft.component.ComponentType;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroups;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;

public class BrokenNameTags implements ModInitializer {
    public static final ComponentType<BrokenNameTagComponent> BROKEN_NAMETAG_COMPONENT = ComponentType.<BrokenNameTagComponent>builder()
            .codec(BrokenNameTagComponent.CODEC)
            .build();

    public static final Item BROKEN_NAMETAG = new BrokenNameTagItem(new Item.Settings().maxCount(1));

    @Override
    public void onInitialize() {
        Registry.register(Registries.DATA_COMPONENT_TYPE, Identifier.of("brokennametags", "broken_nametag"),  BROKEN_NAMETAG_COMPONENT);
        Registry.register(Registries.ITEM, Identifier.of("brokennametags", "broken_nametag"),  BROKEN_NAMETAG);

        ItemGroupEvents.modifyEntriesEvent(ItemGroups.TOOLS).register(entries -> {
            entries.add(BROKEN_NAMETAG);
        });
    }
}