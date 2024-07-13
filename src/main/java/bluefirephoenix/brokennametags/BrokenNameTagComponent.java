package bluefirephoenix.brokennametags;

import com.mojang.serialization.Codec;

public record BrokenNameTagComponent(String deathMessage) {
    public static final Codec<BrokenNameTagComponent> CODEC = Codec.STRING.fieldOf("deathMessage")
            .xmap(BrokenNameTagComponent::new, BrokenNameTagComponent::deathMessage).codec();
}
