package scramble.model.command.impl;

import scramble.model.command.api.Command;
import scramble.view.compact.RocketPanel;

public class RocketCommand implements Command{

    private final RocketPanel rocketPanel;

    public RocketCommand(final RocketPanel panel){
        this.rocketPanel = panel;
    }
    @Override
    public void execute() {
        rocketPanel.moveRocket();
    }

}
