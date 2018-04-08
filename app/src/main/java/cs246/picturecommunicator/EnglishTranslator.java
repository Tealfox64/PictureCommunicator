package cs246.picturecommunicator;

/**
 * Created by landc on 4/7/2018.
 */

public class EnglishTranslator implements Translator {
    @Override
    public String translate(PictureHolder pictureHolder) {

        return pictureHolder.getLabelEnglish();
    }
}
