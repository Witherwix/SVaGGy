package web.Model;

/**
 * Class used to get input data from user for generated path data
 */
public class GeneratedPathData {

    private int fromOffset;
    private int toOffset;

    public GeneratedPathData() {

    }

    public int getFromOffset() {
        return fromOffset;
    }

    public void setFromOffset(int fromOffset) {
        this.fromOffset = fromOffset;
    }

    public int getToOffset() {
        return toOffset;
    }

    public void setToOffset(int toOffset) {
        this.toOffset = toOffset;
    }
}
