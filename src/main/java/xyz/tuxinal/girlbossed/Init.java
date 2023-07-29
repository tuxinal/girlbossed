package xyz.tuxinal.girlbossed;

import static net.minecraft.commands.Commands.literal;

import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.command.v2.CommandRegistrationCallback;
import net.minecraft.ChatFormatting;
import net.minecraft.network.chat.Component;
import xyz.tuxinal.girlbossed.utils.ConfigParser;

public class Init implements ModInitializer {
    @Override
    public void onInitialize() {
        ConfigParser.init();
        CommandRegistrationCallback.EVENT.register((dispatcher, dedicated, environment) -> {
            dispatcher.register(
                    literal("girlbossed-reload").requires(source -> source.hasPermission(4)).executes(context -> {
                        ConfigParser.init();
                        context.getSource().sendSystemMessage(Component.literal("Reload success.").withStyle(ChatFormatting.GREEN));
                        return 1;
                    }));
        });
    }

}
