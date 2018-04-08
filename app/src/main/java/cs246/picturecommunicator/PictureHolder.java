package cs246.picturecommunicator;

/**
 * <h1>Picture Holder</h1>
 * A class to store individual data for each image file. Includes:
 * <ul>
 *     <li>int filename: holds the data for the R resource of the image file.</li>
 *     <li>String label - English: the name of the image, in English, can be displayed with the picture.</li>
 *     <li>String label - Spanish: the name of the image, in Spanish, can be displayed with the picture.</li>
 *     <li>String category: the category the image falls under.</li>
 * </ul>
 */
public class PictureHolder {

    protected int filename;
    protected String labelEnglish;
    protected String labelSpanish;
    protected String category;

    /**
     * <h2>Picture (default constructor) sets all variables to ""</h2>
     */
    public PictureHolder() {
        this.filename = 0;
        this.labelEnglish = "";
        this.labelSpanish = "";
    }

    /**
     * <h2>Picture (non-default constructor) accepts 3 parameters</h2>
     * @param filename holds the data for the R resource of the image file
     * @param labelEnglish is the English name of the image, can be displayed with the picture
     * @param labelSpanish is the Spanish name of the image, can be display with the picture
     * @param category is the category the image falls under
     */
    public PictureHolder(int filename, String labelEnglish, String labelSpanish, String category) {
        this.filename = filename;
        this.labelEnglish = labelEnglish;
        this.labelSpanish = labelSpanish;
        this.category = category;
    }

    /**
     * <h2>getFilename</h2>
     * @return the image file R resource ID
     */
    public int getFilename() {
        return filename;
    }

    /**
     * <h2>setFilename</h2>
     * @param filename the image file R resource ID
     */
    public void setFilename(int filename) {
        this.filename = filename;
    }

    /**
     * <h2>getLabelEnglish</h2>
     * @return the actual name in English of the object in the image
     */
    public String getLabelEnglish() {
        return labelEnglish;
    }

    /**
     * <h2>getLabelSpanish</h2>
     * @return the actual name in Spanish of the object in the image in
     */
    public String getLabelSpanish() {
        return labelSpanish;
    }

    /**
     * <h2>setLabelEnglish</h2>
     * @param labelEnglish the actual name in English of the object in the image
     */
    public void setLabelEnglish(String labelEnglish) {
        this.labelEnglish = labelEnglish;
    }

    /**
     * <h2>setLabelSpanish</h2>
     * @param labelSpanish the actual name in Spanish of the object in the image
     */
    public void setLabelSpanish(String labelSpanish) {
        this.labelSpanish = labelSpanish;
    }

    /**
     * <h2>getCategory</h2>
     * @return the category the image falls under
     */
    public String getCategory() {
        return category;
    }

    /**
     * <h2>setCategory</h2>
     * @param category the category the image falls under
     */
    public void setCategory(String category) {
        this.category = category;
    }
}
