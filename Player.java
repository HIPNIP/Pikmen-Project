
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.g3d.Model;
import com.badlogic.gdx.graphics.g3d.ModelInstance;
import com.badlogic.gdx.math.Vector3;

public class Player {
    private ModelInstance modelInstance;
    private Vector3 position;
    private float moveSpeed = 5.0f; // Units per second

    public Player(Model model) {
        this.modelInstance = new ModelInstance(model);
        this.position = new Vector3(); // Initialize at origin
        // Update the model instance's transform to reflect the initial position
        this.modelInstance.transform.setTranslation(position);
    }

    public void update(float delta) {
        // Handle input for movement
        Vector3 direction = new Vector3();

        if (Gdx.input.isKeyPressed(Input.Keys.W)) {
            direction.z -= 1; // Move forward (negative Z in LibGDX's default 3D space)
        }
        if (Gdx.input.isKeyPressed(Input.Keys.S)) {
            direction.z += 1; // Move backward
        }
        if (Gdx.input.isKeyPressed(Input.Keys.A)) {
            direction.x -= 1; // Move left
        }
        if (Gdx.input.isKeyPressed(Input.input.Keys.D)) {
            direction.x += 1; // Move right
        }

        // Normalize the direction vector to prevent faster movement when moving diagonally
        if (direction.len() > 0) {
            direction.nor().scl(moveSpeed * delta);
            position.add(direction);
            modelInstance.transform.setTranslation(position);
        }

        // You might want to add rotation based on mouse or other input here later.
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
}