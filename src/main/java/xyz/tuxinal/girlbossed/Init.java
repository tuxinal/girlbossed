package xyz.tuxinal.girlbossed;

import net.fabricmc.api.ModInitializer;
import xyz.tuxinal.girlbossed.utils.ConfigParser;

public class Init implements ModInitializer {

    @Override
    public void onInitialize() {
        ConfigParser.init();
    }

}
