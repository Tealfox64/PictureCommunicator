/**
 * Created by mlbru on 3/21/2018.
 */

public class Picture {
    protected String filename;
    protected String label;
    protected String category;

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
