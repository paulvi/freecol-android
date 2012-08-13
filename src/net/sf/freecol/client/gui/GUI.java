/**
 *  Copyright (C) 2002-2012   The FreeCol Team
 *
 *  This file is part of FreeCol.
 *
 *  FreeCol is free software: you can redistribute it and/or modify
 *  it under the terms of the GNU General Public License as published by
 *  the Free Software Foundation, either version 2 of the License, or
 *  (at your option) any later version.
 *
 *  FreeCol is distributed in the hope that it will be useful,
 *  but WITHOUT ANY WARRANTY; without even the implied warranty of
 *  MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 *  GNU General Public License for more details.
 *
 *  You should have received a copy of the GNU General Public License
 *  along with FreeCol.  If not, see <http://www.gnu.org/licenses/>.
 */


package net.sf.freecol.client.gui;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.logging.Level;
import java.util.logging.Logger;

import net.sf.freecol.client.ClientOptions;
import net.sf.freecol.client.FreeColClient;
import net.sf.freecol.client.gui.Canvas.BoycottAction;
import net.sf.freecol.client.gui.Canvas.BuyAction;
import net.sf.freecol.client.gui.Canvas.ClaimAction;
import net.sf.freecol.client.gui.Canvas.EventType;
import net.sf.freecol.client.gui.Canvas.MissionaryAction;
import net.sf.freecol.client.gui.Canvas.ScoutColonyAction;
import net.sf.freecol.client.gui.Canvas.ScoutIndianSettlementAction;
import net.sf.freecol.client.gui.Canvas.SellAction;
import net.sf.freecol.client.gui.Canvas.TradeAction;
import net.sf.freecol.client.gui.i18n.Messages;
import net.sf.freecol.client.gui.menu.FreeColMenuBar;
import net.sf.freecol.client.gui.menu.MapEditorMenuBar;
import net.sf.freecol.client.gui.panel.ChoiceItem;
import net.sf.freecol.client.gui.panel.EuropePanel;
import net.sf.freecol.client.gui.panel.FreeColDialog;
import net.sf.freecol.client.gui.panel.LabourData.UnitData;
import net.sf.freecol.client.gui.panel.LoadingSavegameDialog;
import net.sf.freecol.client.gui.panel.MainPanel;
import net.sf.freecol.client.gui.panel.MapEditorTransformPanel;
import net.sf.freecol.client.gui.panel.StartGamePanel;
import net.sf.freecol.client.gui.sound.SoundPlayer;
import net.sf.freecol.common.ServerInfo;
import net.sf.freecol.common.model.Colony;
import net.sf.freecol.common.model.DiplomaticTrade;
import net.sf.freecol.common.model.Europe;
import net.sf.freecol.common.model.FoundingFather;
import net.sf.freecol.common.model.FreeColGameObject;
import net.sf.freecol.common.model.FreeColObject;
import net.sf.freecol.common.model.Game;
import net.sf.freecol.common.model.Goods;
import net.sf.freecol.common.model.GoodsType;
import net.sf.freecol.common.model.IndianSettlement;
import net.sf.freecol.common.model.Location;
import net.sf.freecol.common.model.Map.Direction;
import net.sf.freecol.common.model.ModelMessage;
import net.sf.freecol.common.model.Monarch.MonarchAction;
import net.sf.freecol.common.model.PathNode;
import net.sf.freecol.common.model.Player;
import net.sf.freecol.common.model.Settlement;
import net.sf.freecol.common.model.Specification;
import net.sf.freecol.common.model.StringTemplate;
import net.sf.freecol.common.model.Tile;
import net.sf.freecol.common.model.TradeRoute;
import net.sf.freecol.common.model.Unit;
import net.sf.freecol.common.option.AudioMixerOption;
import net.sf.freecol.common.option.BooleanOption;
import net.sf.freecol.common.option.LanguageOption;
import net.sf.freecol.common.option.LanguageOption.Language;
import net.sf.freecol.common.option.OptionGroup;
import net.sf.freecol.common.option.PercentageOption;
import net.sf.freecol.common.resources.ResourceManager;

import org.freecolandroid.R;
import org.freecolandroid.fragments.ColonyFragment;
import org.freecolandroid.fragments.GameFragment;
import org.freecolandroid.fragments.MainMenuFragment;
import org.freecolandroid.fragments.NewGameFragment;
import org.freecolandroid.fragments.SplashScreenFragment;
import org.freecolandroid.fragments.StartGameFragment;
import org.freecolandroid.fragments.dialogs.ConfirmDialogFragment;
import org.freecolandroid.fragments.dialogs.EndTurnDialogFragment;
import org.freecolandroid.fragments.dialogs.FreeColDialogFragment.DialogListener;
import org.freecolandroid.fragments.dialogs.InfoDialogFragment;
import org.freecolandroid.fragments.dialogs.InputDialogFragment;
import org.freecolandroid.fragments.dialogs.MessageDialogFragment;
import org.freecolandroid.repackaged.java.awt.Component;
import org.freecolandroid.repackaged.java.awt.Dimension;
import org.freecolandroid.repackaged.java.awt.GraphicsDevice;
import org.freecolandroid.repackaged.java.awt.GraphicsEnvironment;
import org.freecolandroid.repackaged.java.awt.Point;
import org.freecolandroid.repackaged.java.awt.Rectangle;
import org.freecolandroid.repackaged.javax.swing.ImageIcon;
import org.freecolandroid.repackaged.javax.swing.JFrame;
import org.freecolandroid.repackaged.javax.swing.JInternalFrame;
import org.freecolandroid.repackaged.javax.swing.filechooser.FileFilter;

import android.app.Fragment;
import android.app.FragmentTransaction;
import android.widget.Toast;


public class GUI {


    private static final Logger logger = Logger.getLogger(GUI.class.getName());

    public static final String COPYRIGHT = "Copyright (C) 2002-2012   The FreeCol Team";

    public static final String LICENSE = "http://www.gnu.org/licenses/gpl.html";

    public static final String REVISION = "$Revision: 9599 $";


    /**
     * The space not being used in windowed mode.
     */
    private static final int DEFAULT_WINDOW_SPACE = 100;


    private FreeColClient freeColClient;


    // GUI:
    private GraphicsDevice gd;

    private JFrame frame;

    private Canvas canvas;

    private MapViewer mapViewer;

    /**
     * This is the MapViewer instance used to paint the colony tiles in the
     * ColonyPanel and other panels. It should not be scaled along
     * with the default MapViewer.
     */
    private MapViewer colonyTileGUI;

    private ImageLibrary imageLibrary;

    private SoundPlayer soundPlayer;

    private boolean windowed;

    private Rectangle windowBounds;
    
    private MessageDialogFragment statusPanel;

    public GUI(FreeColClient freeColClient) {
        this.freeColClient = freeColClient;
        this.imageLibrary = new ImageLibrary();
    }

    public void activateGotoPath() {
        Unit unit = getActiveUnit();

        // Action should be disabled if there is no active unit, but make sure
        if (unit == null)
            return;

        // Enter "goto mode" if not already activated; otherwise cancel it
        if (mapViewer.isGotoStarted()) {
            mapViewer.stopGoto();
        } else {
            mapViewer.startGoto();

            // Draw the path to the current mouse position, if the
            // mouse is over the screen; see also
            // CanvaseMouseMotionListener
            Point pt = canvas.getMousePosition();
            if (pt != null) {
                Tile tile = mapViewer.convertToMapTile(pt.x, pt.y);
                if (tile != null && unit.getTile() != tile) {
                    PathNode dragPath = unit.findPath(tile);
                    mapViewer.setGotoPath(dragPath);
                }
            }
        }

    }

    /**
     * Verifies if the client can play sounds.
     * @return boolean <b>true</b> if and only if client sound player has an instance
     */
    public boolean canPlaySound() {
        return soundPlayer != null;
    }

    public void centerActiveUnit() {
        mapViewer.centerActiveUnit();
    }

    public void closeMainPanel() {
        canvas.closeMainPanel();
    }

    public void closeMenus() {
    	closeStatusPanel();
    }

    /**
     * Closes the <code>StatusPanel</code>.
     *
     * @see #showStatusPanel
     */
    public void closeStatusPanel() {
    	if (statusPanel != null) {
    		statusPanel.dismiss();
    		statusPanel = null;
    	}
    }

    public boolean containsInGameComponents() {
        return canvas.containsInGameComponents();
    }

    public Dimension determineWindowSize() {

        Rectangle bounds = GraphicsEnvironment
            .getLocalGraphicsEnvironment().getMaximumWindowBounds();
        Dimension size = new Dimension(bounds.width - DEFAULT_WINDOW_SPACE,
                             bounds.height - DEFAULT_WINDOW_SPACE);
        logger.info("Window size is " + size.getWidth()
            + " x " + size.getHeight());
        return size;
    }

    public void displayChat(String senderNme, String message, boolean privateChat) {
        canvas.displayChat(senderNme, message, privateChat);

    }


    /**
     * Tells the map controls that a chat message was received.
     *
     * @param sender The player who sent the chat message to the server.
     * @param message The chat message.
     * @param privateChat 'true' if the message is a private one, 'false'
     *            otherwise.
     * @see GUIMessage
     */
    public void displayChatMessage(String message, boolean privateChat) {
        mapViewer.addMessage(new GUIMessage(freeColClient.getMyPlayer().getName() + ": " + message,
                                      imageLibrary.getColor(freeColClient.getMyPlayer())));

        canvas.repaint(0, 0, canvas.getWidth(), canvas.getHeight());
    }

    public void displaySpashScreen(final String splashFilename) {
		FragmentTransaction ft = freeColClient.getActivity()
				.getFragmentManager().beginTransaction();
        Fragment splash = new SplashScreenFragment();
        ft.replace(R.id.content, splash);
        ft.commit();
    }

    /**
     * Displays an error message.
     *
     * @param messageID The i18n-keyname of the error message to display.
     */
    public void errorMessage(String messageId) {
    	errorMessage(messageId, null);
    }

    /**
     * Displays an error message.
     *
     * @param messageID The i18n-keyname of the error message to display.
     * @param fallbackMessage An alternative message to display if the resource
     * 		  specified by <code>messageID</code> is unavailable.
     */
    public void errorMessage(final String messageID, final String fallbackMessage) {
    	
        freeColClient.getActivity().runOnUiThread(new Runnable() {
			
			@Override
			public void run() {
				String display = null;
				if (messageID != null) {
					display = Messages.message(messageID);
				} else {
					display = fallbackMessage;
				}
				Toast.makeText(freeColClient.getActivity(), display, Toast.LENGTH_SHORT).show();
			}
		});
    }

    public void executeWithUnitOutForAnimation(final Unit unit,
            final Tile sourceTile,
            final OutForAnimationCallback r) {
        mapViewer.executeWithUnitOutForAnimation(unit, sourceTile, r);
    }

    public Unit getActiveUnit() {
        if (mapViewer == null)
            return null;
        return mapViewer.getActiveUnit();
    }

    public Canvas getCanvas() {
        return canvas;
    }

    public MapViewer getColonyTileGUI() {
        return colonyTileGUI;
    }

    public int getCurrentViewMode() {
        return mapViewer.getViewMode().getView();
    }

    public Tile getFocus() {
        if (mapViewer == null)
            return null;
        return mapViewer.getFocus();
    }


    public ImageIcon getImageIcon(Object display, boolean small) {
        return imageLibrary.getImageIcon(display, small);
    }

    public ImageLibrary getImageLibrary() {
        return imageLibrary;
    }

    public LoadingSavegameDialog getLoadingSavegameDialog() {
        return canvas.getLoadingSavegameDialog();
    }


    public float getMapScale() {
        return mapViewer.getMapScale();
    }

    public MapViewer getMapViewer() {
        return mapViewer;
    }

    public Tile getSelectedTile() {
        return mapViewer.getSelectedTile();
    }

    public SoundPlayer getSoundPlayer() {
        return soundPlayer;
    }

    public Rectangle getTileBounds(Tile tile) {
        return mapViewer.getTileBounds(tile);
    }

    public Point getTilePosition(Tile tile) {
        return mapViewer.getTilePosition(tile);
    }

    public Rectangle getWindowBounds() {
        return windowBounds;
    }

    public boolean isClientOptionsDialogShowing() {
        return canvas != null && !canvas.isClientOptionsDialogShowing();
    }


    public boolean isMapboardActionsEnabled() {
        return canvas != null && canvas.isMapboardActionsEnabled();
    }

    public boolean isShowingSubPanel() {
        return canvas.isShowingSubPanel();
    }

    public boolean isWindowed() {
        return windowed;
    }

    public void moveTileCursor(Direction direction) {
        mapViewer.moveTileCursor(direction);
    }

    public boolean onScreen(Tile tileToCheck) {
        return mapViewer.onScreen(tileToCheck);
    }

    public void paintImmediatelyCanvasIn(Rectangle rectangle) {
        canvas.paintImmediately(rectangle);
    }

    public void paintImmediatelyCanvasInItsBounds() {
        canvas.paintImmediately(canvas.getBounds());
    }


    /**
     * Plays some sound. Parameter == null stops playing a sound.
     *
     * @param sound The sound resource to play or <b>null</b>
     */
    public void playSound(String sound) {
        if (canPlaySound()) {
            if (sound == null) {
               soundPlayer.stop();
            } else {
               File file = ResourceManager.getAudio(sound);
               if (file != null) {
                   soundPlayer.playOnce(file);
               }
               logger.finest(((file == null) ? "Could not load" : "Playing")
                             + " sound: " + sound);
            }
        }
    }


    public void quit() {
        if (!isWindowed()) {
            try {
                gd.setFullScreenWindow(null);
            } catch(Exception e) {
                // this can fail, but who cares?
                // we are quitting anyway
                System.exit(1);
            }
        }
    }

    public void refresh() {
        mapViewer.forceReposition();
        canvas.refresh();
    }

    public void refreshPlayersTable() {
        canvas.refreshPlayersTable();
    }

    /**
     * Refreshes the screen at the specified Tile.
     *
     * @param t The tile to refresh.
     */
    public void refreshTile(Tile t) {
        if (t.getX() >= 0 && t.getY() >= 0) {
            canvas.repaint(mapViewer.getTileBounds(t));
        }
    }

    public void removeFromCanvas(Component component) {
        canvas.remove(component);
    }

    public void removeInGameComponents() {
        canvas.removeInGameComponents();
    }

    public void requestFocusForSubPanel() {
        canvas.getShowingSubPanel().requestFocus();
    }

    public boolean requestFocusInWindow() {
        return canvas.requestFocusInWindow();
    }

    public void returnToTitle() {
        canvas.returnToTitle();
    }

    public void scaleMap(float delta) {
        mapViewer.scaleMap(delta);
        refresh();
    }

    public void setActiveUnit(Unit unitToActivate) {
        mapViewer.setActiveUnit(unitToActivate);
    }

    public void setFocus(Tile tileToFocus) {
        mapViewer.setFocus(tileToFocus);
    }

    public void setFocusImmediately(Tile tileToFocus) {
        mapViewer.setFocusImmediately(tileToFocus);
    }

    public boolean setSelectedTile(Tile newTileToSelect, boolean clearGoToOrders) {
        return mapViewer.setSelectedTile(newTileToSelect, clearGoToOrders);
    }

    public void showAboutPanel() {
        canvas.showAboutPanel();
    }

    public ScoutIndianSettlementAction showArmedUnitIndianSettlementDialog(IndianSettlement settlement) {
        return canvas.showArmedUnitIndianSettlementDialog(settlement);
    }

    public BoycottAction showBoycottedGoodsDialog(Goods goods, Europe europe) {
        return canvas.showBoycottedGoodsDialog(goods, europe);
    }

    public void showBuildQueuePanel(Colony colony) {
        canvas.showBuildQueuePanel(colony);
    }

    public void showBuildQueuePanel(Colony colony, Runnable callBack) {
        canvas.showBuildQueuePanel(colony, callBack);
    }

    public BuyAction showBuyDialog(Unit unit, Settlement settlement,
            Goods goods, int gold, boolean canBuy) {
        return canvas.showBuyDialog(unit, settlement, goods, gold, canBuy);
    }

    public List<Goods> showCaptureGoodsDialog(Unit winner, List<Goods> loot) {
        return canvas.showCaptureGoodsDialog(winner, loot);
    }

    public void showChatPanel() {
        canvas.showChatPanel();
    }

    public <T> T showChoiceDialog(Tile tile, String text, String cancelText,
            List<ChoiceItem<T>> choices) {
        return canvas.showChoiceDialog(tile, text, cancelText, choices);
    }

    public MonarchAction showChoiceMonarchActionDialog(String monarchTitle, List<ChoiceItem<MonarchAction>> actions) {
        return canvas.showChoiceMonarchActionDialog(monarchTitle, actions);
    }

    public FoundingFather showChooseFoundingFatherDialog(List<ChoiceItem<FoundingFather>> fathers, String fatherTitle) {
        return canvas.showChooseFoundingFatherDialog(fathers, fatherTitle);
    }

    public FoundingFather showChooseFoundingFatherDialog(List<FoundingFather> ffs) {
        return canvas.showChooseFoundingFatherDialog(ffs);
    }

    public ClaimAction showClaimDialog(Tile tile, Player player, int price,
            Player owner, boolean canAccept) {
        return canvas.showClaimDialog(tile, player, price, owner, canAccept);
    }

    public OptionGroup showClientOptionsDialog() {
        return canvas.showClientOptionsDialog();
    }

    /**
     * Displays the colony panel of the given <code>Colony</code>.
     * Defends against duplicates as this can duplicate messages
     * generated by multiple property change listeners registered
     * against the same colony.
     *
     * @param colony The colony whose panel needs to be displayed.
     */
	public void showColonyPanel(Colony colony) {
		ColonyFragment f = ColonyFragment.newInstance(colony);
		f.setClient(freeColClient);
		FragmentTransaction ft = freeColClient.getActivity()
				.getFragmentManager().beginTransaction();
		ft.replace(R.id.content, f);
		ft.addToBackStack("");
		ft.commit();
	}

    public void showColonyPanel(Colony colony, Runnable callback) {
        canvas.showColonyPanel(colony, callback);
    }

    public void showColopediaPanel(String nodeId) {
        canvas.showColopediaPanel(nodeId);
    }

    public void showCompactLabourReport() {
        canvas.showCompactLabourReport();
    }

    public void showCompactLabourReport(UnitData unitData) {
        canvas.showCompactLabourReport(unitData);
    }

    public List<String> showConfirmDeclarationDialog() {
        return canvas.showConfirmDeclarationDialog();
    }

    /**
     * Displays a dialog with a text and a ok/cancel option.
     *
     * @param text The text that explains the choice for the user.
     * @param okText The text displayed on the "ok"-button.
     * @param cancelText The text displayed on the "cancel"-button.
     * @return <i>true</i> if the user clicked the "ok"-button and <i>false</i>
     *         otherwise.
     */
    public boolean showConfirmDialog(String text, String okText, String cancelText) {
    	return ConfirmDialogFragment.showDialogAndWaitForResult(freeColClient
				.getActivity().getFragmentManager(), Messages.message(text),
				Messages.message(okText), Messages.message(cancelText));
    }

    /**
     * Displays a dialog with a text and a ok/cancel option.
     *
     * @param tile An optional <code>Tile</code> to make visible (not
     *        under the dialog!)
     * @param messages The messages that explains the choice for the user.
     * @param okText The text displayed on the "ok"-button.
     * @param cancelText The text displayed on the "cancel"-button.
     * @return <i>true</i> if the user clicked the "ok"-button and <i>false</i>
     *         otherwise.
     */
    public boolean showConfirmDialog(Tile tile, ModelMessage[] messages,
            String okText, String cancelText) {
    	
    	//TODO: Fix - Broken for now, need to show the pictures!
//    	String[] texts = new String[messages.length];
//        ImageIcon[] images = new ImageIcon[messages.length];
//        for (int i = 0; i < messages.length; i++) {
//            texts[i] = Messages.message(messages[i]);
//            images[i] = gui.getImageIcon(freeColClient.getGame().getMessageDisplay(messages[i]), false);
//        }
    	
    	StringBuilder builder = new StringBuilder();
    	for (int i = 0; i < messages.length; i++) {
    		builder.append(Messages.message(messages[i]) + "\n");
    	}
    	
    	return ConfirmDialogFragment.showDialogAndWaitForResult(freeColClient
				.getActivity().getFragmentManager(), builder.toString(),
				Messages.message(okText), Messages.message(cancelText));
    }

    /**
     * Displays a dialog with a text and a ok/cancel option.
     *
     * @param tile A <code>Tile</code> to make visible (not under the dialog!)
     * @param text The text that explains the choice for the user.
     * @param okText The text displayed on the "ok"-button.
     * @param cancelText The text displayed on the "cancel"-button.
     *
     * @return <i>true</i> if the user clicked the "ok"-button and <i>false</i>
     *         otherwise.
     * @see FreeColDialog
     */
    public boolean showConfirmDialog(Tile tile, StringTemplate text,
            String okText, String cancelText) {
    	return ConfirmDialogFragment.showDialogAndWaitForResult(freeColClient
				.getActivity().getFragmentManager(), Messages.message(text),
				Messages.message(okText), Messages.message(cancelText));
    }

    public void showDeclarationDialog() {
        canvas.showDeclarationDialog();
    }

    public void showDifficultyDialog() {
        canvas.showDifficultyDialog();
    }

    public OptionGroup showDifficultyDialog(Specification specification) {
        return canvas.showDifficultyDialog(specification);
    }

    public List<Goods> showDumpCargoDialog(Unit unit) {
        return canvas.showDumpCargoDialog(unit);
    }


    public int showEmigrationPanel(boolean fountainOfYouth) {
        return canvas.showEmigrationPanel(fountainOfYouth);
    }

	public void showEndTurnDialog(List<Unit> units, DialogListener listener) {
		EndTurnDialogFragment f = EndTurnDialogFragment.newInstance(units,
				listener);
    	f.setClient(freeColClient);
    	f.show(freeColClient.getActivity().getFragmentManager(), "");
    }

    public int showEuropeDialog(EuropePanel.EuropeAction europeAction) {
        return canvas.showEuropeDialog(europeAction);
    }

    public void showEuropePanel() {
        canvas.showEuropePanel();
    }

    public void showEventPanel(EventType type) {
        canvas.showEventPanel(type);
    }

    public void showFindSettlementDialog() {
        canvas.showFindSettlementDialog();
    }

    public void showGameOptionsDialog(boolean editable, boolean loadCustomOptions) {
        canvas.showGameOptionsDialog(editable, loadCustomOptions);
    }

    public void showHighScoresPanel(String messageId) {
        canvas.showHighScoresPanel(messageId);
    }

    public void showIndianSettlementPanel(IndianSettlement indianSettlement) {
        canvas.showIndianSettlementPanel(indianSettlement);
    }

    public TradeAction showIndianSettlementTradeDialog(Settlement settlement,
            boolean canBuy,
            boolean canSell,
            boolean canGift) {
        return canvas.showIndianSettlementTradeDialog(settlement, canBuy, canSell, canGift);
    }

    /**
     * Shows a message with some information and an "OK"-button.
     * 
     * @param displayObject Optional object for displaying an icon.
     * @param messageId ID of the message to show.
     */
    public void showInformationMessage(FreeColObject displayObject, String messageId) {
    	ImageIcon icon = null;
        if (displayObject != null) {
            icon = getImageIcon(displayObject, false);
            Tile t = getTileForMessage(displayObject);
            if (t != null) {
            	mapViewer.setFocus(t);
            }
        }
    	showInformationMessage(Messages.message(messageId), icon);
    }

    /**
     * Shows a message with some information and an "OK"-button.
     * 
     * @param displayObject Optional object for displaying an icon.
     * @param template message template of the message to show.
     */
    public void showInformationMessage(FreeColObject displayObject, StringTemplate template) {
    	ImageIcon icon = null;
        if (displayObject != null) {
            icon = getImageIcon(displayObject, false);
            Tile t = getTileForMessage(displayObject);
            if (t != null) {
            	mapViewer.setFocus(t);
            }
        }
    	showInformationMessage(Messages.message(template), icon);
    }

    /**
     * Shows a message with some information and an "OK"-button.
     * 
     * @param message message to show.
     */
    public void showInformationMessage(ModelMessage message) {
		FreeColObject displayObject = freeColClient.getGame()
				.getMessageDisplay(message);
    	ImageIcon icon = null;
        if (displayObject != null) {
            icon = getImageIcon(displayObject, false);
            Tile t = getTileForMessage(displayObject);
            if (t != null) {
            	mapViewer.setFocus(t);
            }
        }
    	showInformationMessage(Messages.message(message), icon);
    }

    /**
     * Shows a message with some information and an "OK"-button.
     * 
     * @param messageId ID of the message to show.
     */
    public void showInformationMessage(String messageId) {
    	showInformationMessage(Messages.message(messageId), null);
    }

    /**
     * Shows a message with some information and an "OK"-button.
     * 
     * @param template message template of the message to show.
     */
    public void showInformationMessage(StringTemplate template) {
    	showInformationMessage(Messages.message(template), null);
    }
    
    private Tile getTileForMessage(FreeColObject displayObject) {
    	Tile tile = null;
        if (displayObject instanceof Tile) {
            tile = (Tile) displayObject;
        } else {
            try { // If the displayObject has a "getTile" method, invoke it.
                tile = (Tile) displayObject.getClass().getMethod("getTile")
                    .invoke(displayObject);
            } catch (Exception e) { /* Ignore failure */ }
        }
        return tile;
    }
    
    private void showInformationMessage(String message, ImageIcon icon) {
		InfoDialogFragment f = InfoDialogFragment.newInstance(message, icon
				.getImage().getBitmap());
    	f.show(freeColClient.getActivity().getFragmentManager(), "");
    }

    /**
     * Displays a dialog with a text field and a ok/cancel option.
     *
     * @param tile An optional tile to make visible (not under the dialog).
     * @param text The text that explains the action to the user.
     * @param defaultValue The default value appearing in the text field.
     * @param okText The text displayed on the "ok"-button.
     * @param cancelText The text displayed on the "cancel"-button. Use <i>null</i>
     *            to disable the cancel-option.
     * @param rejectEmptyString a <code>boolean</code> value
     * @return The text the user have entered or <i>null</i> if the user chose
     *         to cancel the action.
     */
    public String showInputDialog(Tile tile, StringTemplate text, String defaultValue,
            String okText, String cancelText,
            boolean rejectEmptyString) {
    	return "This version of GUI.showInputDialog is deprecated!";
//        return canvas.showInputDialog(tile, text, defaultValue, okText, cancelText, rejectEmptyString);
    }
    
    /**
     * Displays a dialog with a text field and a ok/cancel option.
     *
     * @param tile An optional tile to make visible (not under the dialog).
     * @param text The text that explains the action to the user.
     * @param defaultValue The default value appearing in the text field.
     * @param okText The text displayed on the "ok"-button.
     * @param cancelText The text displayed on the "cancel"-button. Use <i>null</i>
     *            to disable the cancel-option.
     * @param rejectEmptyString a <code>boolean</code> value
     * @param listener listener to be called when the user has made a choice.
     */
	public void showInputDialog(Tile tile, StringTemplate text,
			String defaultValue, String okText, String cancelText,
			boolean rejectEmptyString,
			InputDialogFragment.InputDialogListener listener) {

		InputDialogFragment f = InputDialogFragment.newInstance(
				Messages.message(text), Messages.message(defaultValue),
				Messages.message(okText), Messages.message(cancelText),
				!rejectEmptyString, listener);
		f.show(freeColClient.getActivity().getFragmentManager(), "");
	}


    public File showLoadDialog(File directory) {
        return canvas.showLoadDialog(directory);
    }

    public File showLoadDialog(File directory, FileFilter[] fileFilters) {
        return canvas.showLoadDialog(directory, fileFilters);
    }

    public boolean showLoadingSavegameDialog(boolean publicServer, boolean singleplayer) {
        return canvas.showLoadingSavegameDialog(publicServer, singleplayer);
    }

    public void showLogFilePanel() {
        canvas.showLogFilePanel();
    }


    /**
     * Shows the <code>MainPanel</code>.
     *
     * @see MainPanel
     */
    public void showMainPanel() {
		FragmentTransaction ft = freeColClient.getActivity()
				.getFragmentManager().beginTransaction();
		MainMenuFragment f = new MainMenuFragment();
		f.setClient(freeColClient);
		ft.replace(R.id.content, f);
		ft.commit();
    }

    public OptionGroup showMapGeneratorOptionsDialog(OptionGroup mgo, boolean editable, boolean loadCustomOptions){
        return canvas.showMapGeneratorOptionsDialog(mgo, editable, loadCustomOptions);
    }

    public Dimension showMapSizeDialog() {
        return canvas.showMapSizeDialog();
    }

    public void showModelMessages(ModelMessage... modelMessages) {
        canvas.showModelMessages(modelMessages);
    }

    public boolean showMonarchPanelDialog(MonarchAction action, StringTemplate replace) {
        return canvas.showMonarchPanelDialog(action, replace);
    }

    public DiplomaticTrade showNegotiationDialog(Unit unit, Settlement settlement, DiplomaticTrade agreement) {
        return canvas.showNegotiationDialog(unit, settlement, agreement);
    }

    public void showNewPanel() {
        showNewPanel(freeColClient.getGame().getSpecification());
    }

	public void showNewPanel(Specification specification) {
		FragmentTransaction ft = freeColClient.getActivity()
				.getFragmentManager().beginTransaction();
		NewGameFragment f = new NewGameFragment();
		f.setClient(freeColClient);
		f.setSpecification(specification);
		ft.addToBackStack("");
		ft.replace(R.id.content, f);
		ft.commit();
	}

    public boolean showPreCombatDialog(FreeColGameObject attacker,
            FreeColGameObject defender,
            Tile tile) {
        return canvas.showPreCombatDialog(attacker, defender, tile);
    }

    public void showReportCargoPanel() {
        canvas.showReportCargoPanel();
    }

    public void showReportColonyPanel() {
        canvas.showReportColonyPanel();
    }

    public void showReportContinentalCongressPanel() {
        canvas.showReportContinentalCongressPanel();
    }

    public void showReportEducationPanel() {
        canvas.showReportEducationPanel();
    }

    public void showReportExplorationPanel() {
        canvas.showReportExplorationPanel();
    }

    public void showReportForeignAffairPanel() {
        canvas.showReportForeignAffairPanel();
    }

    public void showReportHistoryPanel() {
        canvas.showReportHistoryPanel();
    }

    public void showReportIndianPanel() {
        canvas.showReportIndianPanel();
    }

    public void showReportLabourPanel() {
        canvas.showReportLabourPanel();
    }

    public void showReportMilitaryPanel() {
        canvas.showReportMilitaryPanel();
    }


    public void showReportNavalPanel() {
        canvas.showReportNavalPanel();
    }

    public void showReportProductionPanel() {
        canvas.showReportProductionPanel();
    }

    public void showReportReligiousPanel() {
        canvas.showReportReligiousPanel();
    }

    public void showReportRequirementsPanel() {
        canvas.showReportRequirementsPanel();
    }

    public void showReportTradePanel() {
        canvas.showReportTradePanel();
    }

    public void showReportTurnPanel(ModelMessage... messages) {
        canvas.showReportTurnPanel(messages);
    }

    public File showSaveDialog(File directory, String defaultName) {
        return canvas.showSaveDialog(directory, defaultName);
    }

    public File showSaveDialog(File directory, String standardName, FileFilter[] fileFilters, String defaultName) {
        return canvas.showSaveDialog(directory, standardName, fileFilters, defaultName);
    }

    public ScoutColonyAction showScoutForeignColonyDialog(Colony colony,
            Unit unit,
            boolean canNegotiate) {
        return canvas.showScoutForeignColonyDialog(colony, unit, canNegotiate);
    }

    public ScoutIndianSettlementAction showScoutIndianSettlementDialog(IndianSettlement settlement, String number) {
        return canvas.showScoutIndianSettlementDialog(settlement, number);
    }

    public int showSelectAmountDialog(GoodsType goodsType, int available, int defaultAmount, boolean needToPay) {
        return canvas.showSelectAmountDialog(goodsType, available, defaultAmount, needToPay);
    }




    public Location showSelectDestinationDialog(Unit unit) {
        return canvas.showSelectDestinationDialog(unit);
    }

    public SellAction showSellDialog(Unit unit, Settlement settlement,
            Goods goods, int gold) {
        return canvas.showSellDialog(unit, settlement, goods, gold);
    }

    public void showServerListPanel(String username, ArrayList<ServerInfo> serverList) {
        canvas.showServerListPanel(username, serverList);

    }

    public <T> T showSimpleChoiceDialog(Tile tile,
            String text, String cancelText,
            List<T> objects) {
        return canvas.showSimpleChoiceDialog(tile, text, cancelText, objects);
    }

    /**
     * Displays the <code>StartGamePanel</code>.
     *
     * @param game The <code>Game</code> that is about to start.
     * @param player The <code>Player</code> using this client.
     * @param singlePlayerMode 'true' if the user wants to start a single player
     *            game, 'false' otherwise.
     * @see StartGamePanel
     */
    public void showStartGamePanel(Game game, Player player, boolean singlePlayerMode) {
        if (game != null && player != null) {
			FragmentTransaction ft = freeColClient.getActivity()
					.getFragmentManager().beginTransaction();
			StartGameFragment f = new StartGameFragment();
			f.setClient(freeColClient);
			ft.addToBackStack("");
			ft.replace(R.id.content, f);
			ft.commit();
        } else {
            logger.warning("Tried to open 'StartGamePanel' without having 'game' and/or 'player' set.");
        }
    }

    public void showStatisticsPanel() {
        canvas.showStatisticsPanel();
    }

    /**
     * Shows a status message that cannot be dismissed. The panel will be
     * removed when another component is added to this <code>Canvas</code>.
     * This includes all the <code>showXXX</code>-methods. In addition,
     * {@link #closeStatusPanel()} also removes this panel.
     *
     * @param message The text message to display on the status panel.
     * @param progressSpinner if true, show a progress spinner next to
     *        the message.
     */
    public void showStatusPanel(String message, boolean progressSpinner) {
    	if (statusPanel != null) {
    		statusPanel.dismiss();
    		statusPanel = null;
    	}
    	statusPanel = MessageDialogFragment.newInstance(message,
				progressSpinner, null);
    	statusPanel.show(freeColClient.getActivity().getFragmentManager(), "");
    }
    
    /**
     * Shows a status message that cannot be dismissed. The panel will be
     * removed when another component is added to this <code>Canvas</code>.
     * This includes all the <code>showXXX</code>-methods. In addition,
     * {@link #closeStatusPanel()} also removes this panel.
     *
     * @param message The text message to display on the status panel.
     * @param icon an icon to show next to the message.
     */
	public void showStatusPanel(String message, ImageIcon icon) {
		FragmentTransaction ft = freeColClient.getActivity()
				.getFragmentManager().beginTransaction();
		if (statusPanel != null) {
			ft.remove(statusPanel);
    		statusPanel = null;
    	}
    	statusPanel = MessageDialogFragment.newInstance(message,
				false, icon.getImage().getBitmap());
    	statusPanel.show(ft, "");
	}


    public void showTilePanel(Tile tile) {
        canvas.showTilePanel(tile);
    }

    public void showTilePopUpAtSelectedTile() {
        canvas.showTilePopup(getSelectedTile(),
                mapViewer.getCursor().getCanvasX(),
                mapViewer.getCursor().getCanvasY());
    }

    public TradeRoute showTradeRouteDialog(TradeRoute tradeRoute, Tile tile) {
        return canvas.showTradeRouteDialog(tradeRoute, tile);
    }

    public boolean showTradeRouteInputDialog(TradeRoute newRoute) {
        return canvas.showTradeRouteInputDialog(newRoute);
    }

    public MissionaryAction showUseMissionaryDialog(Unit unit,
            IndianSettlement settlement,
            boolean canEstablish,
            boolean canDenounce) {
        return canvas.showUseMissionaryDialog(unit, settlement, canEstablish, canDenounce);
    }

    public void showVictoryPanel() {
        canvas.showVictoryPanel();
    }

    public boolean showWarehouseDialog(Colony colony) {
        return canvas.showWarehouseDialog(colony);
    }

    public void showWorkProductionPanel(Unit unit) {
        canvas.showWorkProductionPanel(unit);
    }

    /**
     * Starts the GUI by creating and displaying the GUI-objects.
     */
    public void startGUI(Dimension innerWindowSize,
                          final boolean sound,
                          final boolean showOpeningVideo,
                          final boolean loadGame) {
        final ClientOptions opts = freeColClient.getClientOptions();
        // Prepare the sound system.
        if (sound) {
            final AudioMixerOption amo
                = (AudioMixerOption) opts.getOption(ClientOptions.AUDIO_MIXER);
            final PercentageOption volume
                = (PercentageOption) opts.getOption(ClientOptions.AUDIO_VOLUME);
            try {
                this.soundPlayer = new SoundPlayer(amo, volume);
            } catch (Exception e) {
                // #3168279 reports an undocumented NPE thrown by
                // AudioSystem.getMixer(null).  Workaround this and other
                // such failures by just disabling sound.
                this.soundPlayer = null;
                logger.log(Level.WARNING, "Sound disabled", e);
            }
        } else {
            this.soundPlayer = null;
        }

        if (GraphicsEnvironment.isHeadless()) {
            logger.info("It seems that the GraphicsEnvironment is headless!");
        }
        this.gd = GraphicsEnvironment.getLocalGraphicsEnvironment().getDefaultScreenDevice();
        if (!isWindowed()) {
            if (!gd.isFullScreenSupported()) {
                String fullscreenNotSupported =
                   "\nIt seems that full screen mode is not fully supported for this" +
                   "\nGraphicsDevice. Please try the \"--windowed\" option if you\nexperience" +
                   "any graphical problems while running FreeCol.";
                logger.info(fullscreenNotSupported);
                System.out.println(fullscreenNotSupported);
                /*
                 * We might want this behavior later: logger.warning("It seems
                 * that full screen mode is not supported for this
                 * GraphicsDevice! Using windowed mode instead."); windowed =
                 * true; setWindowed(true); frame = new
                 * WindowedFrame(size);
                 */
            }
            Rectangle bounds = gd.getDefaultConfiguration().getBounds();
            innerWindowSize = new Dimension(bounds.width - bounds.x, bounds.height - bounds.y);
        }

        // Work around a Java 2D bug that seems to be X11 specific.
        // According to:
        //   http://www.oracle.com/technetwork/java/javase/index-142560.html
        //
        //   ``The use of pixmaps typically results in better
        //     performance. However, in certain cases, the opposite is true.''
        //
        // The standard workaround is to use -Dsun.java2d.pmoffscreen=false,
        // but this is too hard for some users, so provide an option to
        // do it easily.  However respect the initial value if present.
        //
        // Remove this if Java 2D is ever fixed.  DHYB.
        //
        final String pmoffscreen = "sun.java2d.pmoffscreen";
        BooleanOption usePixmaps
            = (BooleanOption) opts.getOption(ClientOptions.USE_PIXMAPS);
        String pmoffscreenValue = System.getProperty(pmoffscreen);
        if (pmoffscreenValue == null) {
            System.setProperty(pmoffscreen, usePixmaps.getValue().toString());
            logger.info(pmoffscreen + " using client option: "
                + usePixmaps.getValue().toString());
        } else {
            usePixmaps.setValue(new Boolean(pmoffscreenValue));
            logger.info(pmoffscreen + " overrides client option: "
                + pmoffscreenValue);
        }
        usePixmaps.addPropertyChangeListener(new PropertyChangeListener() {
                public void propertyChange(PropertyChangeEvent e) {
                    String newValue = e.getNewValue().toString();
                    System.setProperty(pmoffscreen, newValue);
                    logger.info("Set " + pmoffscreen + " to: " + newValue);
                }
            });

        this.mapViewer = new MapViewer(freeColClient, this, innerWindowSize, imageLibrary);
        this.canvas = new Canvas(freeColClient, this, innerWindowSize, mapViewer);
        this.colonyTileGUI = new MapViewer(freeColClient, this, innerWindowSize, imageLibrary);

        // Now that there is a canvas, prepare for language changes.
        LanguageOption o = (LanguageOption) freeColClient.getClientOptions().getOption(ClientOptions.LANGUAGE);
        if (o != null) {
            o.addPropertyChangeListener(new PropertyChangeListener() {
                    public void propertyChange(PropertyChangeEvent e) {
                        if (((Language) e.getNewValue()).getKey().equals(LanguageOption.AUTO)) {
                            showInformationMessage("autodetectLanguageSelected");
                        } else {
                            Locale l = ((Language) e.getNewValue()).getLocale();
                            Messages.setMessageBundle(l);
                            showInformationMessage(StringTemplate.template("newLanguageSelected")
                                .addName("%language%", l.getDisplayName()));
                        }
                    }
                });
        }

        if (!loadGame) {
            showMainPanel();
        }
        playSound("sound.intro.general");
        mapViewer.startCursorBlinking();
    }

    public void startMapEditorGUI() {
        // We may need to reset the zoom value to the default value
        scaleMap(2f);

        setupMapEditorMenuBar();
        JInternalFrame f = canvas.addAsToolBox(new MapEditorTransformPanel(freeColClient, this));
        f.setLocation(f.getX(), 50);

        canvas.repaint();
        setupMouseListenerForMapEditor();
    }

    public void toggleViewMode() {
        mapViewer.getViewMode().toggleViewMode();
    }

    public void updateGameOptions() {
        canvas.updateGameOptions();
    }

    /**
     * Updates the label displaying the current amount of gold.
     */
    public void updateGoldLabel() {
        frame.getJMenuBar().repaint();
    }

    public void updateMapGeneratorOptions() {
        canvas.updateMapGeneratorOptions();
    }

    public void updateMenuBar() {
        if (frame != null && frame.getJMenuBar() != null) {
            ((FreeColMenuBar) frame.getJMenuBar()).update();
        }
    }

    private void setupMapEditorMenuBar() {
        frame.setJMenuBar(new MapEditorMenuBar(freeColClient, this));
    }

    private void setupMouseListenerForMapEditor() {
        CanvasMapEditorMouseListener listener = new CanvasMapEditorMouseListener(freeColClient, this, canvas);
        canvas.addMouseListener(listener);
        canvas.addMouseMotionListener(listener);
    }

	public void showGamePanel() {
		closeStatusPanel();
		FragmentTransaction ft = freeColClient.getActivity()
				.getFragmentManager().beginTransaction();
		GameFragment f = new GameFragment();
		f.setClient(freeColClient);
		ft.replace(R.id.content, f);
		ft.commit();
	}

}
