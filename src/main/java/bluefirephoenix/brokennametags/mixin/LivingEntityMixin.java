package bluefirephoenix.brokennametags.mixin;

import bluefirephoenix.brokennametags.BrokenNameTags;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.Tameable;
import net.minecraft.entity.damage.DamageSource;
import net.minecraft.entity.passive.TameableEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NbtCompound;
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
    LivingEntity entity;

    public LivingEntityMixin(EntityType<?> type, World world) {
        super(type, world);
    }

    @Inject(method = "onDeath", at = @At("HEAD"))
    public void onDeath(DamageSource source, CallbackInfo ci) {
        if (!this.isRemoved() && !this.dead) {

            // check if the entity has a custom name
            if (hasCustomName() || (this instanceof Tameable tameable && tameable.getOwner() != null)) { // the order of this if statement matters
                ItemStack tag = new ItemStack(BrokenNameTags.BROKEN_NAMETAG, 1);
                NbtCompound nbt = new NbtCompound();

                Text name = getDefaultName();

                if (hasCustomName()) {
                    name = getCustomName();
                }

                Text deathMessage = source.getDeathMessage((LivingEntity) (Object) this);
                if (deathMessage != null) {
                    nbt.putString("deathMessage", deathMessage.getString());
                }

                tag.setCustomName(name); // todo this is causing the crash
                tag.getOrCreateNbt().put("data", nbt);
                dropStack(tag);
            }
        }
    }
}