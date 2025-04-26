/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package mortalkombatbversion;

import javax.swing.JLabel;
import javax.swing.JRadioButton;

/**
 *
 * @author Мария
 */
public class TextChanger {

    public void NewRoundTexts(Fighter human, Fighter enemy, JLabel quantityPointsLabel,
            JLabel quantityExperienceLabel, JLabel playerLevelLabel,
            JLabel enemyLevelLabel, JLabel playerQuantityHeathLabel,
            JLabel enemyQuantityHealthLabel, JLabel playerQuantityDamageLabel,
            JLabel turnInfoLabel, JLabel commentAboutFightLabel, int moveNumber,
            Items[] items, JRadioButton smallHealingElixirRadioButton,
            JRadioButton bigHealingElixirRadioButton, JRadioButton rebirthElixirRadioButton) {
        quantityPointsLabel.setText(Integer.toString(((Player) human).getPoints()));
        quantityExperienceLabel.setText(Integer.toString(((Player) human).getExperience()) + "/" + ((Player) human).getNextExperience());
        playerLevelLabel.setText(Integer.toString(human.getLevel()) + " level");
        enemyLevelLabel.setText(Integer.toString(enemy.getLevel()) + " level");
        playerQuantityHeathLabel.setText(Integer.toString(human.getMaxHealth()) + "/" + Integer.toString(human.getMaxHealth()));
        enemyQuantityHealthLabel.setText(Integer.toString(enemy.getMaxHealth()) + "/" + Integer.toString(enemy.getMaxHealth()));
        playerQuantityDamageLabel.setText(Integer.toString(human.getDamage()));
        if (moveNumber % 2 == 1) {
            turnInfoLabel.setText("Your turn");
        } else {
            turnInfoLabel.setText(enemy.getName() + "'s turn");
        }
        BagText(items, smallHealingElixirRadioButton, bigHealingElixirRadioButton, rebirthElixirRadioButton);
        commentAboutFightLabel.setText("");
    }

    public void RoundTexts(Fighter human, Fighter enemy, JLabel enemyQuantityHealthLabel,
            JLabel playerQuantityHeathLabel, int moveNumber, JLabel turnInfoLabel) {
        if (enemy.getHealth() >= 0) {
            enemyQuantityHealthLabel.setText(Integer.toString(enemy.getHealth()) + "/" + Integer.toString(enemy.getMaxHealth()));
        } else {
            enemyQuantityHealthLabel.setText("0/" + Integer.toString(enemy.getMaxHealth()));
        }
        if (human.getHealth() >= 0) {
            playerQuantityHeathLabel.setText(Integer.toString(human.getHealth()) + "/" + Integer.toString(human.getMaxHealth()));
        } else {
            playerQuantityHeathLabel.setText("0/" + Integer.toString(human.getMaxHealth()));
        }
        if (moveNumber % 2 == 1) {
            turnInfoLabel.setText("Your turn");
        } else {
            turnInfoLabel.setText(enemy.getName() + "'s turn");
        }
    }

    public void EndGameText(Player human, JLabel label) {
        if (human.getWin() == 12) {
            label.setText("Победа на вашей стороне");
        } else {
            label.setText("Победа не на вашей стороне");
        }
    }

    public void BagText(Items[] items, JRadioButton smallHealingElixirRadioButton,
            JRadioButton bigHealingElixirRadioButton, JRadioButton rebirthElixirRadioButton) {

        smallHealingElixirRadioButton.setText(items[0].getName() + ", " + items[0].getCount() + " шт");
        bigHealingElixirRadioButton.setText(items[1].getName() + ", " + items[1].getCount() + " шт");
        rebirthElixirRadioButton.setText(items[2].getName() + ", " + items[2].getCount() + " шт");

    }

}
