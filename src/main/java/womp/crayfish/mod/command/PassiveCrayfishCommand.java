package womp.crayfish.mod.command;

import com.mojang.brigadier.CommandDispatcher;
import com.mojang.brigadier.arguments.BoolArgumentType;
import com.mojang.brigadier.context.CommandContext;
import net.minecraft.command.CommandRegistryAccess;
import net.minecraft.server.command.CommandManager;
import net.minecraft.server.command.ServerCommandSource;
import net.minecraft.text.Text;
import womp.crayfish.mod.CrayfishMod;

public class PassiveCrayfishCommand {
    
    public static void register(CommandDispatcher<ServerCommandSource> dispatcher, CommandRegistryAccess commandRegistryAccess, CommandManager.RegistrationEnvironment registrationEnvironment) {

        dispatcher.register(CommandManager.literal("passivecrayfish").then(CommandManager.argument("value", BoolArgumentType.bool())
        .executes(context -> setBoolean(context, BoolArgumentType.getBool(context, "value")))));
    }

    private static int setBoolean(CommandContext<ServerCommandSource> context, boolean value) {
        CrayfishMod.passivecrayfish = value;

        context.getSource().sendFeedback(() -> Text.literal(CrayfishMod.passivecrayfish ? "Crayfish are now passive towards non-player mobs" : "Crayfish can now be hostile towards other mobs"), true);

        return 1;
    }
}
