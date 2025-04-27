/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package mortalkombatbversion;

import javax.swing.*;

/**
 *
 * @author Мария
 */
public class CharacterAction {

    private final int experience_for_next_level[] = {40, 90, 180, 260, 410, 1000};

    private final int kind_fight[][] = {{1, 0}, {1, 1, 0}, {0, 1, 0}, {1, 1, 1, 1}};

    private Fighter enemyes[] = new Fighter[6];

    EnemyFabric fabric = new EnemyFabric();

    private Fighter enemyy = null;
    CharacterAction(){
        setEnemyes();
    }

    public void setEnemyes() {
        enemyes[0] = fabric.create(0, 0);
        enemyes[1] = fabric.create(1, 0);
        enemyes[2] = fabric.create(2, 0);
        enemyes[3] = fabric.create(3, 0);
        enemyes[4] = fabric.create(4, 0);
        enemyes[5] = fabric.create(4, 0);
    }

    public Fighter[] getEnemyes() {
        return this.enemyes;
    }

    public Fighter ChooseEnemy(JLabel enemyPictureLabel, JLabel enemyNameLabel,
            JLabel enemyQuantityDamageLabel, JLabel enemyQuantityHealthLabel) {
        int enemyNumber = (int) (Math.random() * 4);
        ImageIcon enemyPicture = null;
        switch (enemyNumber) {
            case 0:
                enemyy = enemyes[0];
                enemyPicture = new ImageIcon("src\\main\\resources\\Pictures\\Baraka.png");
                enemyNameLabel.setText("Baraka (танк)");
                break;
            case 1:
                enemyy = enemyes[1];
                enemyPicture = new ImageIcon("src\\main\\resources\\Pictures\\Sub-Zero.png");
                enemyNameLabel.setText("Sub-Zero (маг)");
                break;
            case 2:
                enemyy = enemyes[2];
                enemyPicture = new ImageIcon("src\\main\\resources\\Pictures\\Liu_Kang.png");
                enemyNameLabel.setText("Liu Kang (боец)");
                break;
            case 3:
                enemyy = enemyes[3];
                enemyPicture = new ImageIcon("src\\main\\resources\\Pictures\\Соня.png");
                enemyNameLabel.setText("Sonya Blade (солдат)");
                break;
        }
        enemyPictureLabel.setIcon(enemyPicture);
        enemyQuantityDamageLabel.setText(Integer.toString(enemyy.getDamage()));
        enemyQuantityHealthLabel.setText(Integer.toString(enemyy.getHealth()) + "/" + Integer.toString(enemyy.getMaxHealth()));
        return enemyy;
    }

    public Fighter ChooseBoss(JLabel enemyPictureLabel, JLabel enemyNameLabel,
            JLabel enemyQuantityDamageLabel, JLabel enemyQuantityHealthLabel,
            int playerLevel) {
        ImageIcon icon1 = null;
        icon1 = new ImageIcon("src\\main\\resources\\Pictures\\General_Shao.png");
        enemyNameLabel.setText("Shao Kahn (босс)");
        switch (playerLevel) {
            case 2:
                enemyy = enemyes[4];
                break;
            case 4:
                enemyy = enemyes[5];
                break;
        }
        enemyPictureLabel.setIcon(icon1);
        enemyQuantityDamageLabel.setText(Integer.toString(enemyy.getDamage()));
        enemyQuantityHealthLabel.setText(Integer.toString(enemyy.getHealth()) + "/" + Integer.toString(enemyy.getMaxHealth()));
        return enemyy;
    }

    public int[] EnemyBehavior(int k1, int k2, int k3, int k4) {
        int arr[];
        double i = Math.random();
        if (i < k1 * 0.01) {
            arr = kind_fight[0];
        } else if (i < (k1 + k2) * 0.01) {
            arr = kind_fight[0];
        } else if (i < (k1 + k2 + k3) * 0.01) {
            arr = kind_fight[0];
        } else {
            arr = kind_fight[0];
        }
        return arr;
    }

    public int[] ChooseBehavior(Fighter enemy) {
        int arr[] = null;
        if (enemy instanceof Baraka) {
            arr = EnemyBehavior(15, 15, 60, 10);
        }
        if (enemy instanceof SubZero) {
            arr = EnemyBehavior(25, 25, 0, 50);
        }
        if (enemy instanceof LiuKang) {
            arr = EnemyBehavior(13, 13, 10, 64);
        }
        if (enemy instanceof SonyaBlade) {
            arr = EnemyBehavior(25, 25, 50, 0);
        }
        if (enemy instanceof ShaoKahn) {
            arr = EnemyBehavior(10, 45, 0, 45);
        }
        return arr;
    }

    public void setHealthProgressBar(Fighter player, JProgressBar healthProgressBar) {

        if (player.getHealth() >= 0) {
            healthProgressBar.setValue(player.getHealth());
        } else {
            healthProgressBar.setValue(0);
        }
    }

    public void AddPoints(Player human, Fighter[] enemyes) {
        switch (human.getLevel()) {
            case 0:
                human.addExperience(20);
                human.setPoints(25 + human.getHealth() / 4);
                break;
            case 1:
                human.addExperience(25);
                human.setPoints(30 + human.getHealth() / 4);
                break;
            case 2:
                human.addExperience(30);
                human.setPoints(35 + human.getHealth() / 4);
                break;
            case 3:
                human.addExperience(40);
                human.setPoints(45 + human.getHealth() / 4);
                break;
            case 4:
                human.addExperience(50);
                human.setPoints(55 + human.getHealth() / 4);
                break;
        }
        for (int i = 0; i < 5; i++) {
            if (experience_for_next_level[i] == human.getExperience()) {
                human.levelUp();
                human.setNextExperianceGoal(experience_for_next_level[i + 1]);
                addHealthAndDamgeHuman(human);
                for (int j = 0; j < 4; j++) {
                    addHealthAndDamgeEnemy(enemyes[j], human);
                }
            }
        }
    }

    public void AddPointsBoss(Player human, Fighter[] enemyes) {
        switch (human.getLevel()) {
            case 2:
                human.addExperience(30);
                human.setPoints(45 + human.getHealth() / 2);
                break;
            case 4:
                human.addExperience(50);
                human.setPoints(65 + human.getHealth() / 2);
                break;
        }
        for (int i = 0; i < 5; i++) {
            if (experience_for_next_level[i] == human.getExperience()) {
                human.levelUp();
                human.setNextExperianceGoal(experience_for_next_level[i + 1]);
                addHealthAndDamgeHuman(human);
                for (int j = 0; j < 4; j++) {
                    addHealthAndDamgeEnemy(enemyes[j], human);
                }
            }
        }
    }

    public void AddItems(int k1, int k2, int k3, Items[] items) {
        double i = Math.random();
        if (i < k1 * 0.01) {
            items[0].addElixir(1);
        }
        if (i >= k1 * 0.01 & i < (k1 + k2) * 0.01) {
            items[1].addElixir(1);
        }
        if (i >= (k1 + k2) * 0.01 & i < (k1 + k2 + k3) * 0.01) {
            items[2].addElixir(1);
        }
    }

    public void addHealthAndDamgeHuman(Player human) {
        int hp = 0;
        int damage = 0;
        switch (human.getLevel()) {
            case 1:
                hp = 25;
                damage = 3;
                break;
            case 2:
                hp = 30;
                damage = 3;
                break;
            case 3:
                hp = 30;
                damage = 4;
                break;
            case 4:
                hp = 40;
                damage = 6;
                break;
        }
        human.addMaxHealth(hp);
        human.addDamage(damage);
    }

    public void addHealthAndDamgeEnemy(Fighter enemy, Player human) {
        int hp = 0;
        int damage = 0;
        switch (human.getLevel()) {
            case 1:
                hp = 32;
                damage = 25;
                break;
            case 2:
                hp = 30;
                damage = 20;
                break;
            case 3:
                hp = 23;
                damage = 24;
                break;
            case 4:
                hp = 25;
                damage = 26;
                break;
        }
        enemy.addMaxHealth((int) enemy.getMaxHealth() * hp / 100);
        enemy.addDamage((int) enemy.getDamage() * damage / 100);
        enemy.levelUp();
    }

    public void UseItem(Fighter player, Items[] items, String nameElixirButton,
            JDialog elixirRestrictionDialog, JDialog bagDialog) {
        switch (nameElixirButton) {
            case "smallHealingElixir":
                if (items[0].getCount() > 0) {
                    player.addHealth((int) (player.getMaxHealth() * 0.25));
                    items[0].addElixir(-1);
                } else {
                    elixirRestrictionDialog.setVisible(true);
                    elixirRestrictionDialog.setBounds(300, 200, 400, 300);
                }
                break;
            case "bigHealingElixir":
                if (items[1].getCount() > 0) {
                    player.addHealth((int) (player.getMaxHealth() * 0.5));
                    items[1].addElixir(-1);
                } else {
                    elixirRestrictionDialog.setVisible(true);
                    elixirRestrictionDialog.setBounds(300, 200, 400, 300);
                }
                break;
            case "rebirthElixir":
                elixirRestrictionDialog.setVisible(true);
                elixirRestrictionDialog.setBounds(300, 200, 400, 300);
                break;
        }

        if (elixirRestrictionDialog.isVisible() == false) {
            bagDialog.dispose();
        }
    }
}
