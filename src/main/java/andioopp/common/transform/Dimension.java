package andioopp.common.transform;

public class Dimension {

    private final Vector3f dimension;

    public Dimension(Dimension dimension) {
        this.dimension = dimension.toVector();
    }

    public Dimension(Vector3f dimension) {
        this.dimension = dimension;
    }

    public Dimension(float width, float height) {
        this(new Vector3f(width, height));
    }

    public Vector3f centerWithin(Vector3f position, Dimension other) {
        Vector3f center = getCenter(position);
        Vector3f offset = other.toVector().scale(-0.5f);
        return center.add(offset);
    }

    public Vector3f getCenter(Vector3f position) {
        return position.add(toVector().scale(0.5f));
    }

    public Dimension scaleByWidth(float width) {
        float ratio = getWidth() / getHeight();
        return new Dimension(width, width / ratio);
    }

    public Dimension scaleByHeight(float height) {
        float ratio = getWidth() / getHeight();
        return new Dimension(height * ratio, height);
    }

    public float getWidth() {
        return toVector().getX();
    }

    public float getHeight() {
        return toVector().getY();
    }

    public Vector3f toVector() {
        return dimension;
    }
}