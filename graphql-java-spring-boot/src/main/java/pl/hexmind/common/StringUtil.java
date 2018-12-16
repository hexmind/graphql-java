package pl.hexmind.common;

import com.google.common.base.CaseFormat;
import com.google.common.base.Splitter;
import com.google.common.collect.Iterables;

public final class StringUtil {

    private StringUtil() {
    }

    public static String toUnderscore(String text) {
        return CaseFormat.LOWER_CAMEL
                .to(CaseFormat.LOWER_UNDERSCORE, text);
    }

    public static String left(String text, int limit) {
        return Iterables.getFirst(
                Splitter.fixedLength(limit).split(text),
                ""
        );
    }
}
