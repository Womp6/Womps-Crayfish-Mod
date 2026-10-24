package womp.crayfish.mod.registry;

import net.fabricmc.fabric.api.object.builder.v1.entity.FabricEntityTypeBuilder;
import net.minecraft.entity.EntityDimensions;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.SpawnGroup;
import net.minecraft.entity.SpawnRestriction.Location;
import net.minecraft.util.Identifier;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.world.Heightmap.Type;
import womp.crayfish.mod.entity.CrayfishEntity;

@SuppressWarnings("ALL")
public class CrayfishEntityInit {
    
    public static final EntityType<CrayfishEntity> CRAYFISH = FabricEntityTypeBuilder
            .createMob().spawnGroup(SpawnGroup.WATER_AMBIENT).entityFactory(CrayfishEntity::new).spawnRestriction(Location.IN_WATER, Type.MOTION_BLOCKING_NO_LEAVES, CrayfishEntity::canSpawn)
            .dimensions(EntityDimensions.fixed(0.75f, 0.25f)).build();

    public static void registerEntities() {
        Registry.register(Registries.ENTITY_TYPE, new Identifier("crayfish", "crayfish"), CRAYFISH);
    }

}
