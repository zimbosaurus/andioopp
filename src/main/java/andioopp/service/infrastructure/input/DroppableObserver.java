package andioopp.service.infrastructure.input;

import andioopp.common.input.MouseData;
import andioopp.common.observer.Observer;
import andioopp.common.transform.Rectangle;

public interface DroppableObserver extends Observer<MouseData> {

    Rectangle getRectangle();

}