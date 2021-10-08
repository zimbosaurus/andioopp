package andioopp.control;

import andioopp.common.storage.ListFactory;
import andioopp.common.transform.Rectangle;
import andioopp.model.Model;
import andioopp.model.tower.Tower;
import andioopp.model.world.World;
import andioopp.view.gui.TowerCard;
import andioopp.service.infrastructure.input.DragAndDropService;
import andioopp.view.View;
import andioopp.view.gui.TowerCardsView;

import javax.smartcardio.Card;
import java.util.Collection;
import java.util.List;

public class PlaceTowerController {

    private final Model model;
    private final View<?> view;
    private final DragAndDropService<TowerDragEvent> dragAndDropService;
    private final Collection<CellDroppableController> droppables;

    public PlaceTowerController(DragAndDropService<TowerDragEvent> dragAndDropService, Model model, View<?> view, ListFactory listFactory) {
        this.model = model;
        this.dragAndDropService = dragAndDropService;
        this.view = view;
        droppables = listFactory.create();
    }

    private void registerDroppableCells() {
        World world = model.getWorld();
        for (int row = 0; row < world.getNumberOfLanes(); row++) {
            for (int col = 0; col < world.getNumberOfCellsInLanes(); col++) {
                Rectangle rectangle = view.getCellRectangle(world, row, col);
                CellDroppableController droppable = new CellDroppableController(rectangle, model, row, col);
                dragAndDropService.getDroppableObservable().addObserver(droppable);
                droppables.add(droppable);
            }
        }
    }

    private void registerDraggableCards() {
        TowerCardsView<?> towerCardsView = view.getTowerCardsView();
        List<? extends TowerCard<?>> cards = towerCardsView.getCards();

        for (int i = 0; i < cards.size(); i++) {
            TowerCard<?> card = cards.get(i);
            Rectangle rectangle = towerCardsView.getTowerCardRectangle(i);
            TowerCardDraggableController draggable = new TowerCardDraggableController(rectangle, card.getTowerSupplier());
            dragAndDropService.getDraggableObservable().addObserver(draggable);

        }
    }

    public void register() {
        dragAndDropService.register();
        registerDroppableCells();
        registerDraggableCards();
    }

    private void unregisterDroppableCells() {
        droppables.forEach(d -> dragAndDropService.getDroppableObservable().removeObserver(d));
    }

    public void unregister() {
        dragAndDropService.unregister();
        unregisterDroppableCells();
    }
}
