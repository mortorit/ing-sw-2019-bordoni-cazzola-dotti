package it.polimi.ingsw.view.gui;

import javafx.scene.image.Image;

import java.util.HashMap;
import java.util.Map;

/**
 * This class allows image cashing.
 */
public class ImageCache {
    private static Map<String, Image> cachedImages = new HashMap<>();

    /**
     * Gets a cached image of a giving uri.
     *
     * @param uri is the uri of the image
     * @return the image cached
     */
    static Image getImage(String uri) {
        if (!cachedImages.containsKey(uri))
            cachedImages.put(uri, new Image(ImageCache.class.getResource(uri).toExternalForm()));
        return cachedImages.get(uri);
    }
}
