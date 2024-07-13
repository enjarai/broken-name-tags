package bluefirephoenix.brokennametags.mixin;

import bluefirephoenix.brokennametags.BrokenNameTagComponent;
import bluefirephoenix.brokennametags.BrokenNameTags;
import net.minecraft.component.DataComponentTypes;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.Tameable;
import net.minecraft.entity.damage.DamageSource;
import net.minecraft.item.ItemStack;
import net.minecraft.text.Text;
import net.minecraft.world.World;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(LivingEntity.class)
public abstract class LivingEntityMixin extends Entity {

    @Shadow protected boolean dead;

    public LivingEntityMixin(EntityType<?> type, World world) {
        super(type, world);
    }

    @Inject(method = "onDeath", at = @At("HEAD"))
    public void onDeath(DamageSource source, CallbackInfo ci) {
        if (!this.isRemoved() && !this.dead) {

            // check if the entity has a custom name
            if (hasCustomName() || (this instanceof Tameable tameable && tameable.getOwner() != null)) { // the order of this if statement matters
                ItemStack tag = new ItemStack(BrokenNameTags.BROKEN_NAMETAG, 1);

                Text name = getDefaultName();

                if (hasCustomName()) {
                    name = getCustomName();
                }

                Text deathMessage = source.getDeathMessage((LivingEntity) (Object) this);
                if (deathMessage != null) {
                    tag.set(BrokenNameTags.BROKEN_NAMETAG_COMPONENT, new BrokenNameTagComponent(deathMessage.getString()));
                }

                tag.set(DataComponentTypes.CUSTOM_NAME, name); // todo this is causing the crash
                dropStack(tag);
            }
        }
    }
}