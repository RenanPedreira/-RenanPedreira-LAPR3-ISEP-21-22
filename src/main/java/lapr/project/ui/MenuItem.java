package lapr.project.ui;

import org.apache.commons.lang3.StringUtils;
import java.util.Objects;


/**
 * @author Luís Araújo
 */
public class MenuItem {

    private final String description;
    private final Runnable ui;

    public MenuItem(String description, Runnable ui) {
        if (StringUtils.isBlank(description))
            throw new IllegalArgumentException("MenuItem description cannot be null or empty.");
        if (Objects.isNull(ui))
            throw new IllegalArgumentException("MenuItem does not support a null UI.");

        this.description = description;
        this.ui = ui;
    }

    public void run() {
        this.ui.run();
    }

    public Runnable getUi() {
        return ui;
    }

    public boolean hasDescription(String description) {
        return this.description.equals(description);
    }

    public String toString() {
        return this.description;
    }

}
