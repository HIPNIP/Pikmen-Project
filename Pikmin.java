import com.badlogic.gdx.Game;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.ScreenUtils;
import com.badlogic.gdx.graphics.Texture; 

public class Pikmin extends Game
{
    private SpriteBatch batch;
    private Texture texture;
    
    public void create() 
    {        
        batch = new SpriteBatch();
        texture = new Texture("assets/background.png");
    }

    public void render() 
    {
        ScreenUtils.clear(Color.BLUE);
        
        batch.begin();
        batch.draw( texture, 192, 112 );
        batch.end();
    }

    public void dispose() {
        batch.dispose();
    }
}



	
