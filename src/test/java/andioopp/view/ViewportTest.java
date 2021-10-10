package andioopp.view;

import andioopp.common.transform.Dimension;
import andioopp.common.transform.Rectangle;
import andioopp.common.transform.Vector3f;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class ViewportTest {

    private static final float HALF = 1.0f / 2.0f;

    private static final Dimension BASE_RESOLUTION = new Dimension(128, 128);
    private static final Dimension SCREEN_RESOLUTION = new Dimension(1920, 1080);
    private static final Dimension MODEL_RESOLUTION = new Dimension(7, 5);

    private static final float TEST_NUMBER = 7;
    private static final Vector3f TEST_POSITION = new Vector3f(TEST_NUMBER, TEST_NUMBER);
    private static final Dimension TEST_SIZE = new Dimension(TEST_NUMBER, TEST_NUMBER);
    private static final Rectangle TEST_RECT = new Rectangle(TEST_POSITION, TEST_SIZE);

    @Test
    public void testTrivialPosition() {
        Viewport viewport = getTrivialViewport();
        Vector3f position = TEST_POSITION;
        assertEquals(position, viewport.toViewportPosition(position));
    }

    @Test
    public void testTrivialSize() {
        Viewport viewport = getTrivialViewport();
        Dimension size = TEST_SIZE;
        assertEquals(size, viewport.toViewportSize(size));
    }

    @Test
    public void testTrivialRect() {
        Viewport viewport = getTrivialViewport();
        Rectangle rectangle = TEST_RECT;
        assertEquals(rectangle, viewport.toViewportRect(rectangle));
    }

    @Test
    public void testHalfPosition() {
        Viewport viewport = getHalfViewport();
        Vector3f position = TEST_POSITION;
        Vector3f expected = position.scale(HALF);
        assertEquals(expected, viewport.toViewportPosition(position));
    }

    @Test
    public void testHalfSize() {
        Viewport viewport = getHalfViewport();
        Dimension size = TEST_SIZE;
        Dimension expected = new Dimension(size.toVector().scale(HALF));
        assertEquals(expected, viewport.toViewportSize(size));
    }

    @Test
    public void testHalfRect() {
        Viewport viewport = getHalfViewport();
        Rectangle rectangle = TEST_RECT;
        Rectangle expected = new Rectangle(rectangle.getPosition().scale(HALF), new Dimension(rectangle.getSize().toVector().scale(HALF)));
        assertEquals(expected, viewport.toViewportRect(rectangle));
    }

    @Test
    public void testScreenRes() {
        Viewport viewport = getScreenViewport();
        Dimension rect = new Dimension(0.5f, 0.5f);
        Vector3f centeredRectPos = viewport.getResolution().centerWithin(Vector3f.zero(), rect);
        Rectangle centeredRect = new Rectangle(centeredRectPos, rect);
        Rectangle viewportRect = viewport.toViewportRect(centeredRect);

        Dimension expectedViewportSize = new Dimension(viewportRect.getSize().toVector());
        Vector3f expectedViewportPos = viewport.getViewport().getSize().toVector().scale(HALF).sub(expectedViewportSize.toVector().scale(HALF));
        Rectangle expectedViewportRect = new Rectangle(expectedViewportPos, expectedViewportSize);

        assertEquals(expectedViewportRect.round(), viewportRect.round());
    }

    private Viewport getTrivialViewport() {
        Dimension resolution = BASE_RESOLUTION;
        Rectangle viewport = new Rectangle(Vector3f.zero(), resolution);
        return new Viewport(viewport, resolution);
    }

    private Viewport getHalfViewport() {
        Dimension resolution = BASE_RESOLUTION;
        Dimension resolutionHalf = new Dimension(resolution.toVector().scale(HALF));
        Rectangle viewport = new Rectangle(Vector3f.zero(), resolutionHalf);
        return new Viewport(viewport, resolution);
    }

    private Viewport getScreenViewport() {
        Rectangle viewport = new Rectangle(Vector3f.zero(), SCREEN_RESOLUTION);
        return new Viewport(viewport, MODEL_RESOLUTION);
    }
}