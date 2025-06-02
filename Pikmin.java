
import com.badlogic.gdx.graphics.g3d.Model;
import com.badlogic.gdx.graphics.g3d.ModelInstance;
import com.badlogic.gdx.math.Vector3;

public class Pikmin {
    private ModelInstance modelInstance;
    private Vector3 position;

    public Pikmin(Model model) {
        this.modelInstance = new ModelInstance(model);
        this.position = new Vector3(); // Initialize at origin
        this.modelInstance.transform.setTranslation(position);
    }

    public void setPosition(float x, float y, float z) {
        position.set(x, y, z);
        modelInstance.transform.setTranslation(position);
    }

    public Vector3 getPosition() {
        return position;
    }

    public ModelInstance getModelInstance() {
        return modelInstance;
    }

    // In a real game, this would have an update method for AI, movement, etc.
    // public void update(float delta) { /* ... Pikmin AI and movement ... */ }
}