package utils;

public class GenerateStringForPhoto {

    public static String getString(String ownerId, Integer postId) {
        return String.format("photo%s_%d", ownerId, postId);
    }

}
