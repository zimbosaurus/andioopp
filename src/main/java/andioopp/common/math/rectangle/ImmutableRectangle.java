package andioopp.common.math.rectangle;

import andioopp.common.math.Rectangle;
import andioopp.common.math.Vector3f;
import andioopp.common.math.Dimension;

import java.util.Objects;

public class ImmutableRectangle implements Rectangle {

    private final Vector3f position;
    private final Dimension size;

    public ImmutableRectangle(Vector3f position, Dimension size) {
        this.position = position;
        this.size = size;
    }

    public ImmutableRectangle(float x, float y, float w, float h) {
        this(new Vector3f(x, y), new Dimension(w, h));
    }

    @Override
    public Vector3f getPosition() {
        return position;
    }

    @Override
    public Dimension getSize() {
        return size;
    }

    @Override
    public Rectangle setPosition(Vector3f position) {
        return new ImmutableRectangle(position, size);
    }

    @Override
    public Rectangle setSize(Dimension size) {
        return new ImmutableRectangle(position, size);
    }

    @Override
    public String toString() {
        return "Rectangle{" +
                "position=" + position +
                ", size=" + size +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Rectangle rectangle = (Rectangle) o;
        return getPosition().equals(rectangle.getPosition()) && getSize().equals(rectangle.getSize());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getPosition(), getSize());
    }
}
