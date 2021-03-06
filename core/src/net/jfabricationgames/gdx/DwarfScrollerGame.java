package net.jfabricationgames.gdx;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputMultiplexer;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.Screen;

import net.jfabricationgames.gdx.assets.AssetGroupManager;
import net.jfabricationgames.gdx.input.InputContext;
import net.jfabricationgames.gdx.input.InputProfile;
import net.jfabricationgames.gdx.screens.MainMenuScreen;
import net.jfabricationgames.gdx.sound.SoundManager;
import net.jfabricationgames.gdx.text.FontManager;

public class DwarfScrollerGame extends Game {
	
	public static final String ASSET_GROUP_MANAGER_CONFIG_PATH = "config/assets/asset_groups.json";
	public static final String INPUT_PROFILE_CONFIG_PATH = "config/input/profile.xml";
	public static final String SOUND_CONFIG_PATH = "sound/config.json";
	public static final String FONT_CONFIG_PATH = "font/config.json";
	
	private static DwarfScrollerGame instance;
	
	private InputMultiplexer multiplexer;
	
	private InputProfile gameInputProfile;
	
	public static synchronized DwarfScrollerGame getInstance() {
		if (instance == null) {
			instance = new DwarfScrollerGame();
		}
		return instance;
	}
	
	private DwarfScrollerGame() {
		
	}
	
	@Override
	public void create() {
		multiplexer = new InputMultiplexer();
		Gdx.input.setInputProcessor(multiplexer);
		
		gameInputProfile = new InputProfile(Gdx.files.internal(INPUT_PROFILE_CONFIG_PATH), multiplexer);
		
		AssetGroupManager.initialize(ASSET_GROUP_MANAGER_CONFIG_PATH);
		SoundManager.getInstance().loadConfig(SOUND_CONFIG_PATH);
		FontManager.getInstance().load(FONT_CONFIG_PATH);
		
		setScreen(new MainMenuScreen());
	}
	
	public void setScreen(Screen screen) {
		super.setScreen(screen);
	}
	
	public void addInputProcessor(InputProcessor processor) {
		multiplexer.addProcessor(processor);
	}
	public void removeInputProcessor(InputProcessor processor) {
		multiplexer.removeProcessor(processor);
	}
	
	public InputContext changeInputContext(String contextName) {
		gameInputProfile.setContext(contextName);
		return gameInputProfile.getContext();
	}
	public InputContext getInputContext() {
		return gameInputProfile.getContext();
	}
	
	@Override
	public void dispose() {
		super.dispose();
		AssetGroupManager.getInstance().dispose();
		SoundManager.getInstance().dispose();
		FontManager.getInstance().dispose();
	}
}
