package womp.crayfish.mod.registry;

import net.minecraft.registry.Registries;
import net.minecraft.sound.SoundEvent;
import net.minecraft.util.Identifier;
import net.minecraft.registry.Registry;

public class CrayfishSoundsInit {
    public static final Identifier CRAYFISH_HURT_ID = new Identifier("crayfish", "entity.crayfish.hurt");
    public static final SoundEvent CRAYFISH_HURT = SoundEvent.of(CRAYFISH_HURT_ID);

    public static final Identifier CRAYFISH_ATTACK_ID = new Identifier("crayfish", "entity.crayfish.attack");
    public static final SoundEvent CRAYFISH_ATTACK = SoundEvent.of(CRAYFISH_ATTACK_ID);

    public static final Identifier CRAYFISH_DEATH_ID = new Identifier("crayfish", "entity.crayfish.death");
    public static final SoundEvent CRAYFISH_DEATH = SoundEvent.of(CRAYFISH_DEATH_ID);

    public static final Identifier CRAYFISHES_HATCH_ID = new Identifier("crayfish", "entity.crayfish.hatch");
    public static final SoundEvent CRAYFISHES_HATCH = SoundEvent.of(CRAYFISHES_HATCH_ID);

    public static final Identifier CRAYFISH_LAYS_EGGS_ID = new Identifier("crayfish", "entity.crayfish.lay_eggs");
    public static final SoundEvent CRAYFISH_LAYS_EGGS = SoundEvent.of(CRAYFISH_LAYS_EGGS_ID);


    public static void registerSounds() {
        Registry.register(Registries.SOUND_EVENT, CRAYFISH_HURT_ID, CRAYFISH_HURT);
        Registry.register(Registries.SOUND_EVENT, CRAYFISH_ATTACK_ID, CRAYFISH_ATTACK);
        Registry.register(Registries.SOUND_EVENT, CRAYFISH_DEATH_ID, CRAYFISH_DEATH);
        Registry.register(Registries.SOUND_EVENT, CRAYFISHES_HATCH_ID, CRAYFISHES_HATCH);
        Registry.register(Registries.SOUND_EVENT, CRAYFISH_LAYS_EGGS_ID, CRAYFISH_LAYS_EGGS);
    }
}
