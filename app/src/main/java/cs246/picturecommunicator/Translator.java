package cs246.picturecommunicator;

/**
 * Created by landc on 4/7/2018.
 * <h1>Translator</h1>
 * This interface uses the strategy pattern to allow the user to choose which language they would like to see.
 */

public interface Translator {
    String translate(PictureHolder pictureHolder);
}
