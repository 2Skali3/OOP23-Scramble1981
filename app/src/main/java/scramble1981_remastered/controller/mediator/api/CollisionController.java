package scramble1981_remastered.controller.mediator.api;

import scramble1981_remastered.model.common.api.GameElement;

public interface CollisionController {

    boolean isColliding(GameElement e1, GameElement e2);
}
