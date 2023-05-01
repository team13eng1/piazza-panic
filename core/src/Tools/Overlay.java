package Tools;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.badlogic.gdx.utils.SnapshotArray;

import java.awt.*;

public class Overlay {

    private Table table;

    public void setUpTable(Stage stage){
        table = new Table();
        table.setFillParent(false);
//        table.debug();
        table.setPosition(20,0);
        table.sizeBy(100,100);
        stage.addActor(table);
        Texture  texture = new Texture(Gdx.files.internal("pixil-frame-0.png"));
        TextureRegion upRegion = new TextureRegion(texture, 20, 20, 50, 50);

        TextButton.TextButtonStyle style = new TextButton.TextButtonStyle();
        style.up = new TextureRegionDrawable(upRegion);
        style.font = new BitmapFont(Gdx.files.internal("bitmapfont/Amble-Regular-26.fnt"));

            TextButton button1 = new TextButton("Button 1", style);
            button1.addListener(new ClickListener(){
                @Override
                public void clicked(InputEvent event, float x, float y){
                    System.out.println("Button action");
                }
            });
//        bgPixmap.setColor(88);
//        textureRegionDrawableBg.
          table.add(button1);
//        table.background(textureRegionDrawableBg);
    }

    public void setPos(float x, float y){
        table.setPosition(x,y);
    }

    public void setTableBackgroundColor(float r, float g, float b, float transparency){

//        Texture  texture = new Texture(Gdx.files.internal("Tiles/kitchen_fridge.png"));
//        TextureRegion upRegion = new TextureRegion(texture, r, g, b, transparency);
//
//        TextButton.TextButtonStyle style = new TextButton.TextButtonStyle();
//        style.up = new TextureRegionDrawable(upRegion);
//        style.font = new BitmapFont(Gdx.files.internal("bitmapfont/Amble-Regular-26.fnt"));;
//
//        TextButton button1 = new TextButton("Button 1", style);
        Pixmap bgPixmap = new Pixmap(1,1, Pixmap.Format.RGBA8888);
//        bgPixmap.setColor(88);
        bgPixmap.setColor(r,g,b,transparency);
        bgPixmap.fill();
        TextureRegionDrawable textureRegionDrawableBg = new TextureRegionDrawable(new TextureRegion(new Texture(bgPixmap)));
        table.background(textureRegionDrawableBg);
    }

    public void setSize(float x, float y){
        table.setSize(x,y);
    }

    public void addText(String textToAdd){
        Skin skin = new Skin(Gdx.files.internal("skins/skin.json"));
        skin.getFont("default").getData().setScale(0.4f,0.4f);
        skin.setScale(0.1f);
        table.setSkin(skin);
        Label textLabel = new Label(textToAdd);

        table.row();
        table.add(textToAdd);
    }

    public Table getTable() {
        return table;
    }

    public void removeRow(int row, int COLUMN_NUMBER){
        SnapshotArray<Actor> children = table.getChildren();
        children.ordered = false;

        for (int i = row*COLUMN_NUMBER; i < children.size - COLUMN_NUMBER; i++) {
            children.swap(i, i + COLUMN_NUMBER);
        }
        // Remove last row
        for(int i = 0 ; i < COLUMN_NUMBER; i++) {
            table.removeActor(children.get(children.size - 1));
        }
    }
}
