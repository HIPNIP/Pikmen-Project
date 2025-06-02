
import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.PerspectiveCamera;
import com.badlogic.gdx.graphics.g3d.Environment;
import com.badlogic.gdx.graphics.g3d.Model;
import com.badlogic.gdx.graphics.g3d.ModelBatch;
import com.badlogic.gdx.graphics.g3d.ModelInstance;
import com.badlogic.gdx.graphics.g3d.attributes.ColorAttribute;
import com.badlogic.gdx.graphics.g3d.environment.DirectionalLight;
import com.badlogic.gdx.graphics.g3d.utils.ModelBuilder;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.math.Vector3;
import java.util.ArrayList;
import java.util.List;

public class Pikmen extends ApplicationAdapter {
    public PerspectiveCamera cam;
    public ModelBatch modelBatch;
    public Environment environment;

    // Player and Pikmin
    public Player player;
    public List<Pikmin> pikminSquad;

    // Models (placeholders for now)
    public Model playerModel;
    public Model pikminModel;
    public Model groundModel;

    @Override
    public void create() {
        // Initialize ModelBatch for rendering 3D models
        modelBatch = new ModelBatch();

        // Setup the 3D camera
        cam = new PerspectiveCamera(67, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
        cam.position.set(10f, 10f, 10f); // Initial camera position
        cam.lookAt(0, 0, 0); // Where the camera is looking
        cam.near = 0.1f; // Near clipping plane
        cam.far = 300f; // Far clipping plane
        cam.update(); // Update camera settings

        // Setup basic lighting for the 3D scene
        environment = new Environment();
        environment.set(new ColorAttribute(ColorAttribute.AmbientLight, 0.4f, 0.4f, 0.4f, 1f));
        environment.add(new DirectionalLight().set(0.8f, 0.8f, 0.8f, -1f, -0.8f, -0.2f)); // Main light source

        // Create placeholder models using ModelBuilder
        ModelBuilder modelBuilder = new ModelBuilder();
        playerModel = modelBuilder.createBox(1f, 2f, 1f, // Width, Height, Depth
                new com.badlogic.gdx.graphics.g3d.Material(ColorAttribute.createDiffuse(Color.BLUE)), // Material color
                com.badlogic.gdx.graphics.GL20.GL_TRIANGLES); // Primitive type

        pikminModel = modelBuilder.createSphere(0.5f, 0.5f, 0.5f, 20, 20, // Radius, width/height segments
                new com.badlogic.gdx.graphics.g3d.Material(ColorAttribute.createDiffuse(Color.RED)),
                com.badlogic.gdx.graphics.GL20.GL_TRIANGLES);

        groundModel = modelBuilder.createBox(50f, 0.1f, 50f, // Large flat box for ground
                new com.badlogic.gdx.graphics.g3d.Material(ColorAttribute.createDiffuse(Color.GREEN)),
                com.badlogic.gdx.graphics.GL20.GL_TRIANGLES);


        // Initialize player and Pikmin
        player = new Player(playerModel);
        player.setPosition(0, 1.0f, 0); // Place player slightly above ground

        pikminSquad = new ArrayList<>();
        // Add a few placeholder Pikmin
        for (int i = 0; i < 3; i++) {
            Pikmin newPikmin = new Pikmin(pikminModel);
            newPikmin.setPosition(2f + i, 0.25f, 2f); // Place Pikmin near the player
            pikminSquad.add(newPikmin);
        }
    }

    @Override
    public void render() {
        // Clear the screen with a background color
        Gdx.gl.glViewport(0, 0, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT | GL20.GL_DEPTH_BUFFER_BIT); // Clear color and depth buffer

        // Update player based on input
        player.update(Gdx.graphics.getDeltaTime());

        // Update camera to follow player (simple follow camera)
        // You'll want more sophisticated camera control later
        Vector3 playerPos = player.getPosition();
        cam.position.set(playerPos.x + 5f, playerPos.y + 5f, playerPos.z + 5f);
        cam.lookAt(playerPos);
        cam.update();

        // Start rendering the 3D scene
        modelBatch.begin(cam);

        // Render ground
        modelBatch.render(new ModelInstance(groundModel), environment);

        // Render player
        modelBatch.render(player.getModelInstance(), environment);

        // Render Pikmin
        for (Pikmin p : pikminSquad) {
            modelBatch.render(p.getModelInstance(), environment);
        }

        // End rendering
        modelBatch.end();
    }

    @Override
    public void resize(int width, int height) {
        // Update camera's viewport when window is resized
        cam.viewportWidth = width;
        cam.viewportHeight = height;
        cam.update();
    }

    @Override
    public void dispose() {
        // Dispose of all resources to prevent memory leaks
        modelBatch.dispose();
        playerModel.dispose();
        pikminModel.dispose();
        groundModel.dispose();
    }
}