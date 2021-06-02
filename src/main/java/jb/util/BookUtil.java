package jb.util;

import jb.model.BiographyBook;
import jb.model.Book;
import jb.model.ScientificBook;

public final class BookUtil {

    private BookUtil() {
    }

    public static String type(Book book) {
        return "General";
    }

    public static String type(BiographyBook book) {
        return "Biography";
    }

    public static String type(ScientificBook book) {
        return "Scientific";
    }

}
