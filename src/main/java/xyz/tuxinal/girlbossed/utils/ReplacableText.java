package xyz.tuxinal.girlbossed.utils;

import com.google.common.collect.ImmutableList;
import com.google.common.collect.ImmutableList.Builder;

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
        String string = this.getKey();
        try {
            Builder<StringVisitable> builder = ImmutableList.builder();
            this.forEachPart(string, builder::add);
            this.translations = builder.build();
        } catch (TranslationException var4) {
            this.translations = ImmutableList.of(StringVisitable.plain(string));
        }
    }
}
