/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package mortalkombatbversion;

//ADD IMAGE!!!
import java.util.ArrayList;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JProgressBar;
import javax.swing.JRadioButton;

/**
 *
 * @author Мария
 */
public class Fight {

    ChangeTexts change = new ChangeTexts();
    int kind_attack[] = {0};
    int experiences[] = {40, 90, 180, 260, 410};
    EnemyFabric fabric = new EnemyFabric();
    int moveNumber = 1;
    int k = -1;
    int stun = 0;
    double v = 0.0;

    public void Move(Fighter p1, Fighter p2, JLabel l, JLabel l2) {
        if (stun == 1) {
            p1.setAttack(-1);
        }
        switch (Integer.toString(p1.getAttack()) + Integer.toString(p2.getAttack())) {
            case "10":
                v = Math.random();
                if (p1 instanceof ShaoKahn & v < 0.15) {
                    p2.addHealth(-(int) (p1.getDamage() * 0.5));
                    l2.setText("Your block is broken");

                } else {
                    p1.addHealth(-(int) (p2.getDamage() * 0.5));
                    l2.setText(p2.getName() + " counterattacked");
                }
                break;
            case "11":
                p2.addHealth(-p1.getDamage());
                l2.setText(p1.getName() + " attacked");
                break;
            case "00":
                v = Math.random();
                if (v <= 0.5) {
                    stun = 1;
                }
                l2.setText("Both defended themselves");
                break;
            case "01":
                l2.setText(p1.getName() + " didn't attacked");
                break;
            case "-10":
                l.setText(p1.getName() + " was stunned");
                stun = 0;
                l2.setText(p2.getName() + " didn't attacked");
                break;
            case "-11":
                p1.addHealth(-p2.getDamage());
                l.setText(p1.getName() + " was stunned");
                stun = 0;
                l2.setText(p2.getName() + " attacked");
                break;
        }
    }

    public void Hit(Fighter player, Fighter enemy, int attack, JLabel enemyQuantityHealthLabel,
            JLabel playerQuantityHeathLabel, JDialog dialog, JLabel label3, CharacterAction action,
            JProgressBar playerHealthProgressBar, JProgressBar enemyHealthProgressBar, JDialog dialog1,
            JDialog dialog2, JFrame frame, ArrayList<Result> results,
            JLabel label4, JLabel label5, JLabel turnInfoLabel, JLabel specialCommentAboutFightLabel,
            JLabel commentAboutFightLabel, Items[] items, JRadioButton rebirthElixirRadioButton) {
        specialCommentAboutFightLabel.setText("");
        player.setAttack(attack);

        if (k < kind_attack.length - 1) {
            k++;
        } else {
            kind_attack = action.ChooseBehavior(enemy);
            k = 0;
        }
        enemy.setAttack(kind_attack[k]);
        if (moveNumber % 2 == 1) {
            Move(player, enemy, specialCommentAboutFightLabel, commentAboutFightLabel);
        } else {
            Move(enemy, player, specialCommentAboutFightLabel, commentAboutFightLabel);
        }
        moveNumber++;
        change.RoundTexts(player, enemy, enemyQuantityHealthLabel, playerQuantityHeathLabel, moveNumber, turnInfoLabel);
        action.setHealthProgressBar(player, playerHealthProgressBar);
        action.setHealthProgressBar(enemy, enemyHealthProgressBar);
        if (player.getHealth() <= 0 & items[2].getCount() > 0) {
            player.setHealth((int) (player.getMaxHealth() * 0.05));
            items[2].addElixir(-1);
            action.setHealthProgressBar(player, playerHealthProgressBar);
            playerQuantityHeathLabel.setText(player.getHealth() + "/" + player.getMaxHealth());
            rebirthElixirRadioButton.setText(items[2].getName() + ", " + items[2].getCount() + " шт");
            specialCommentAboutFightLabel.setText("Вы воскресли");
        }
        if (player.getHealth() <= 0 | enemy.getHealth() <= 0) {
            if (((Player) player).getWin() == 11) {
                EndFinalRound(((Player) player), action, results, dialog1, dialog2,
                        frame, label4, label5);
            } else {
                EndRound(player, enemy, dialog, label3, action, items);
            }
        }
    }

    public void EndRound(Fighter human, Fighter enemy, JDialog dialog, JLabel label,
            CharacterAction action, Items[] items) {

        dialog.setVisible(true);
        dialog.setBounds(300, 150, 700, 600);
        if (human.getHealth() > 0) {
            label.setText("You win");
            ((Player) human).setWin();

            if (enemy instanceof ShaoKahn) {
                action.AddItems(38, 23, 8, items);
                action.AddPointsBoss(((Player) human), action.getEnemyes());
            } else {
                action.AddItems(25, 15, 5, items);
                action.AddPoints(((Player) human), action.getEnemyes());
            }
        } else {
            label.setText(enemy.getName() + " win");
        }

        moveNumber = 1;
        k = -1;
        kind_attack = ResetAttack();

    }

    public void EndFinalRound(Player human, CharacterAction action,
            ArrayList<Result> results, JDialog dialog1, JDialog dialog2, JFrame frame,
            JLabel label1, JLabel label2) {
        String text = "Победа не на вашей стороне";
        if (human.getHealth() > 0) {
            human.setWin();
            action.AddPoints(human, action.getEnemyes());
            text = "Победа на вашей стороне";
        }
        boolean top = false;
        if (results == null) {
            top = true;
        } else {
            int i = 0;
            for (int j = 0; j < results.size(); j++) {
                if (human.getPoints() < results.get(j).getPoints()) {
                    i++;
                }
            }
            if (i < 10) {
                top = true;
            }
        }
        if (top) {
            dialog1.setVisible(true);
            dialog1.setBounds(150, 150, 600, 500);
            label1.setText(text);
        } else {
            dialog2.setVisible(true);
            dialog2.setBounds(150, 150, 470, 360);
            label2.setText(text);
        }
        frame.dispose();
    }

    public int[] ResetAttack() {
        int a[] = {0};
        return a;
    }

    public Fighter NewRound(Fighter human, JLabel label, JProgressBar pr1,
            JProgressBar pr2, JLabel label2, JLabel text, JLabel label3, CharacterAction action) {

        Fighter enemy1 = null;
        if (((Player) human).getWin() == 6 | ((Player) human).getWin() == 11) {
            enemy1 = action.ChooseBoss(label, label2, text, label3, human.getLevel());
        } else {
            enemy1 = action.ChooseEnemy(label, label2, text, label3);
        }
        pr1.setMaximum(human.getMaxHealth());
        pr2.setMaximum(enemy1.getMaxHealth());
        human.setHealth(human.getMaxHealth());
        enemy1.setHealth(enemy1.getMaxHealth());
        action.setHealthProgressBar(human, pr1);
        action.setHealthProgressBar(enemy1, pr2);
        return enemy1;
    }

}
