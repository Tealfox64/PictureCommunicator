package cs246.picturecommunicator;

/**
 * Created by landc on 4/7/2018.
 * <h1>Spanish Translator</h1>
 * Implements the Translator and uses the strategy pattern to return the title in Spanish.
 *
 */

public class SpanishTranslator implements Translator {
    /**
     * <h2>public String translate(Picture Holder)</h2>
     * @param pictureHolder contains the current picture class
     * @return string of title in Spanish
     */
    @Override
    public String translate(PictureHolder pictureHolder) {

        return pictureHolder.getLabelSpanish();
    }
}
