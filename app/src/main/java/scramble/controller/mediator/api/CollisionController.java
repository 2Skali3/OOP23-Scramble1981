package scramble.controller.mediator.api;

import scramble.model.common.api.GameElement;

public interface CollisionController {

    boolean isColliding(GameElement e1, GameElement e2);
}
