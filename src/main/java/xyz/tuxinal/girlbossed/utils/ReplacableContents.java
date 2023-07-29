package xyz.tuxinal.girlbossed.utils;

import com.google.common.collect.ImmutableList;
import com.google.common.collect.ImmutableList.Builder;

import net.minecraft.network.chat.FormattedText;
import net.minecraft.network.chat.contents.TranslatableContents;
import net.minecraft.network.chat.contents.TranslatableFormatException;

public class ReplacableContents extends TranslatableContents {

    public ReplacableContents(String string, Object[] args) {
        super(string, null, args);
    }

    @Override
    protected void decompose() {
        String string = this.getKey();
        try {
            Builder<FormattedText> builder = ImmutableList.builder();
            this.decomposeTemplate(string, builder::add);
            this.decomposedParts = builder.build();
        } catch (TranslatableFormatException e) {
            this.decomposedParts = ImmutableList.of(FormattedText.of(string));
        }
    }
}
