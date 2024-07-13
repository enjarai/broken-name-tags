package bluefirephoenix.brokennametags;

import net.minecraft.item.*;
import net.minecraft.item.tooltip.TooltipType;
import net.minecraft.text.Text;
import net.minecraft.util.Formatting;

import java.util.List;

public class BrokenNameTagItem extends Item {

    public BrokenNameTagItem(Settings settings) {
        super(settings);
    }

    @Override
    public void appendTooltip(ItemStack stack, TooltipContext context, List<Text> tooltip, TooltipType type) {
        var component = stack.get(BrokenNameTags.BROKEN_NAMETAG_COMPONENT);
        if (component != null) {
            tooltip.add(Text.of(Formatting.GRAY+component.deathMessage()));
        }
    }
}
