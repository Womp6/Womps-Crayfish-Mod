package womp.crayfish.mod.items;

import net.minecraft.item.Item;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.registry.tag.TagKey;
import net.minecraft.util.Identifier;

public class CrayfishTags {
    
    public static class Items {

        public static final TagKey<Item> CRAYFISH_FOOD = createTag("crayfish_food");

        private static TagKey<Item> createTag(String name) {
            return TagKey.of(RegistryKeys.ITEM, new Identifier("crayfish", name));
        }

    }
}
