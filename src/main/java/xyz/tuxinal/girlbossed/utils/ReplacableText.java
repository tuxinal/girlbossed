package xyz.tuxinal.girlbossed.utils;

import net.minecraft.text.StringVisitable;
import net.minecraft.text.TranslatableText;
import net.minecraft.text.TranslationException;

public class ReplacableText extends TranslatableText {

    public ReplacableText(String string, Object[] args) {
        super(string, args);
    }

    public ReplacableText(String string) {
        super(string);
    }

    @Override
    protected void updateTranslations() {
        this.translations.clear();
        try {
            // we are using the key as our string
            // so getKey doesn't make much sense
            this.setTranslation(this.getKey());
        } catch (TranslationException var4) {
            this.translations.clear();
            this.translations.add(StringVisitable.plain(this.getKey()));
        }
    }
}
