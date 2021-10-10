package andioopp.view;

import andioopp.common.graphics.Color;
import andioopp.common.graphics.Renderer;
import andioopp.common.graphics.Sprite;
import andioopp.model.Model;

public class ComposedView<S extends Sprite<?>> implements View<S> {

    private final View<S>[] views;

    @SafeVarargs
    public ComposedView(View<S>... views) {
        this.views = views;
    }

    @Override
    public void render(Renderer<S> renderer, Model model) {
        clearScreen(renderer);
        for (View<S> view : views) {
            view.render(renderer, model);
        }
    }

    private void clearScreen(Renderer<S> renderer) {
        renderer.clear(Color.WHITE);
    }
}
