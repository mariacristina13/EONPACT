package game;

import java.awt.Graphics2D;
import java.awt.event.MouseEvent;
import java.util.ArrayList;

import constants.Constants;

public class CharacterMenu {
    // Class variables.
    private ArrayList<CharacterButton> characters;
    private ArrayList<String> selectedCharacters;
    private MenuButton playBtn;
    private MenuButton backBtn;
    private int maxSelections = 2;
}