package xyz.tuxinal.girlbossed;

import static net.minecraft.server.command.CommandManager.literal;

import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.command.v1.CommandRegistrationCallback;
import net.minecraft.text.LiteralText;
import net.minecraft.util.Formatting;
import xyz.tuxinal.girlbossed.utils.ConfigParser;

public class Init implements ModInitializer {
    @Override
    public void onInitialize() {
        ConfigParser.init();
        CommandRegistrationCallback.EVENT.register((dispatcher, dedicated) -> {
            dispatcher.register(
                    literal("girlbossed-reload").requires(source -> source.hasPermissionLevel(4)).executes(context -> {
                        ConfigParser.init();
                        context.getSource().sendFeedback(
                                new LiteralText("Reloaded girlbossed config!").formatted(Formatting.YELLOW), true);
                        return 1;
                    }));
        });
    }

}
