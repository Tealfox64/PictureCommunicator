package cs246.picturecommunicator;

/**
 * Created by landc on 4/7/2018.
 * <h1>English Translator</h1>
 * Implements the Translator and uses the strategy pattern to return the title in English.
 *
 */

public class EnglishTranslator implements Translator {
    /**
     *
     * @param pictureHolder contains the current picture object
     * @return returns a string of the title in English
     */
    @Override
    public String translate(PictureHolder pictureHolder) {

        return pictureHolder.getLabelEnglish();
    }
}
