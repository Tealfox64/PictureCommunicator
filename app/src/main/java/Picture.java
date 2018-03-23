/**
 * Created by mlbru on 3/21/2018.
 */

/**
 *
 */
public class Picture {
    /**
     * A class to store individual data for each image file. Includes:
     * <ul>
     *     <li>String filename: holds the actual filename of an image.</li>
     *     <li>String label: the name of the image, can be displayed with it.</li>
     *     <li>String category: the category the image falls under</li>
     * </ul>
     */
    protected String filename;
    protected String label;
    protected String category;

    /**
     * Picture (default constructor) sets all variables to ""
     */
    public Picture() {
        this.filename = "";
        this.label = "";
    }

    /**
     * Picture (non-default constructor) accepts 3
     * @param filename
     * @param label
     * @param category
     */
    public Picture(String filename, String label, String category) {
        this.filename = filename;
        this.label = label;
        this.category = category;
    }

    public String getFilename() {
        return filename;
    }

    public void setFilename(String filename) {
        this.filename = filename;
    }

    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }
}
