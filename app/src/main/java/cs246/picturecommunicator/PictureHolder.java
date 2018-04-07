package cs246.picturecommunicator;

/**
 * <h1>Picture Holder</h1>
 * A class to store individual data for each image file. Includes:
 * <ul>
 *     <li>int filename: holds the data for the R resource of the image file.</li>
 *     <li>String label: the name of the image, can be displayed with it.</li>
 *     <li>String category: the category the image falls under.</li>
 * </ul>
 */
public class PictureHolder {

    protected int filename;
    protected String label;
    protected String category;

    /**
     * <h2>Picture (default constructor) sets all variables to ""</h2>
     */
    public PictureHolder() {
        this.filename = 0;
        this.label = "";
    }

    /**
     * <h2>Picture (non-default constructor) accepts 3 parameters</h2>
     * @param filename holds the data for the R resource of the image file
     * @param label is the name of the image, can be displayed with it
     * @param category is the category the image falls under
     */
    public PictureHolder(int filename, String label, String category) {
        this.filename = filename;
        this.label = label;
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
     * <h2>getLabel</h2>
     * @return the actual name of the object in the image
     */
    public String getLabel() {
        return label;
    }

    /**
     * <h2>setLabel</h2>
     * @param label the actual name of the object in the image
     */
    public void setLabel(String label) {
        this.label = label;
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
