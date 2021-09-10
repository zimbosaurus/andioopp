package andioopp.common;

public class ConcreteTransform implements Transform {

    private final Vector3f position, rotation, scale;

    public ConcreteTransform() {
        this(Vector3f.zero(), Vector3f.zero(), Vector3f.zero());
    }

    public ConcreteTransform(Vector3f position, Vector3f rotation, Vector3f scale) {
        this.position = position;
        this.rotation = rotation;
        this.scale = scale;
    }

    @Override
    public Vector3f getPosition() {
        return position;
    }

    @Override
    public Vector3f getRotation() {
        return rotation;
    }

    @Override
    public Vector3f getScale() {
        return scale;
    }
}