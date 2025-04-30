/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package mortalkombatbversion;

import java.util.Arrays;
import javax.swing.*;

/**
 * Клас реализации действий, связанных с персонажами
 */
public class CharacterAction {

    private final int experience_for_next_level[] = {40, 90, 180, 260, 410, 1000};

    private final int kind_fight[][] = {{1, 0}, {1, 1, 0}, {0, 1, 0}, {1, 1, 1}, {2}, {3}, {0, 0, 0}, {0, 1}};
    /**
     * Массив противников
     */
    private Fighter enemyes[] = new Fighter[5];

    protected EnemyFabric fabric = new EnemyFabric();

    private Fighter enemyy = null;
    /**
     * {@link Fight#quantityMovesKindPlayer}
     */
    private int[] quantityMovesKindPlayer;

    CharacterAction() {
        setEnemyes();
    }

    /**
     * Создание массива со всеми типами противников
     */
    public void setEnemyes() {
        enemyes[0] = fabric.create(0);
        enemyes[1] = fabric.create(1);
        enemyes[2] = fabric.create(2);
        enemyes[3] = fabric.create(3);
        enemyes[4] = fabric.create(4);
    }

    /**
     * Функция получения значения поля {@link CharacterAction#enemyes}
     *
     * @return возвращает противников
     */
    public Fighter[] getEnemyes() {
        return this.enemyes;
    }

    /**
     * Функция возвращения случайного противника
     *
     * @param enemyPictureLabel JLabel в котором отображается изображение
     * противника
     * @param enemyNameLabel JLabel в котором отображается имя противника
     * @param enemyQuantityDamageLabel JLabel в котором отображается количество
     * урона, которое может нанести противник
     * @param enemyQuantityHealthLabel JLabel в котором отображается количество
     * здоровья противника
     * @return возвращает случайного противника
     */
    public Fighter ChooseEnemy(JLabel enemyPictureLabel, JLabel enemyNameLabel,
            JLabel enemyQuantityDamageLabel, JLabel enemyQuantityHealthLabel) {
        int enemyNumber = (int) (Math.random() * 4);
        ImageIcon enemyPicture = null;
        switch (enemyNumber) {
            case 0:
                enemyy = enemyes[0];
                enemyPicture = new ImageIcon(getClass().getResource("/Pictures/Baraka.png"));
                enemyNameLabel.setText("Baraka (танк)");
                break;
            case 1:
                enemyy = enemyes[1];
                enemyPicture = new ImageIcon(getClass().getResource("/Pictures/Sub-Zero.png"));
                enemyNameLabel.setText("Sub-Zero (маг)");
                break;
            case 2:
                enemyy = enemyes[2];
                enemyPicture = new ImageIcon(getClass().getResource("/Pictures/Liu_Kang.png"));
                enemyNameLabel.setText("Liu Kang (боец)");
                break;
            case 3:
                enemyy = enemyes[3];
                enemyPicture = new ImageIcon(getClass().getResource("/Pictures/Соня.png"));
                enemyNameLabel.setText("Sonya Blade (солдат)");
                break;
        }
        enemyPictureLabel.setIcon(enemyPicture);
        enemyQuantityDamageLabel.setText(Integer.toString(enemyy.getDamage()));
        enemyQuantityHealthLabel.setText(Integer.toString(enemyy.getHealth()) + "/" + Integer.toString(enemyy.getMaxHealth()));
        return enemyy;
    }

    /**
     * Функция возвращения босса
     *
     * @param enemyPictureLabel JLabel в котором отображается изображение
     * противника
     * @param enemyNameLabel JLabel в котором отображается имя противника
     * @param enemyQuantityDamageLabel JLabel в котором отображается количество
     * урона, которое может нанести противник
     * @param enemyQuantityHealthLabel JLabel в котором отображается количество
     * здоровья противника
     * @return возвращает босса
     */
    public Fighter ChooseBoss(JLabel enemyPictureLabel, JLabel enemyNameLabel,
            JLabel enemyQuantityDamageLabel, JLabel enemyQuantityHealthLabel) {
        ImageIcon icon1 = new ImageIcon(getClass().getResource("/Pictures/General_Shao.png"));
        enemyNameLabel.setText("Shao Kahn (босс)");
        enemyy = enemyes[4];
        enemyPictureLabel.setIcon(icon1);
        enemyQuantityDamageLabel.setText(Integer.toString(enemyy.getDamage()));
        enemyQuantityHealthLabel.setText(Integer.toString(enemyy.getHealth()) + "/" + Integer.toString(enemyy.getMaxHealth()));
        return enemyy;
    }

    /**
     * Функция определения поведения противника
     *
     * @param k1
     * @param k2
     * @param k3
     * @param k4
     * @param canCurse определяет может ли персонаж проклинать
     * @param canRegenerate определяет может ли персонаж регенерировать
     * @return
     */
    public int[] EnemyBehavior(int k1, int k2, int k3, int k4, boolean canCurse, boolean canRegenerate) {
        int arr[];
        int allMoves = Arrays.stream(quantityMovesKindPlayer).sum();
        double i = Math.random();
        if (canCurse && i > 0.7) {
            return kind_fight[4];
        } else if (canRegenerate && i > 0.8) {
            return kind_fight[5];
        } else if (allMoves > 4) {
            if (0.6 < quantityMovesKindPlayer[2] / allMoves) {
                return kind_fight[3];
            } else if (0.6 < quantityMovesKindPlayer[1] / allMoves) {
                return kind_fight[6];
            } else if ((0.6 < (quantityMovesKindPlayer[0] / allMoves)) && ((quantityMovesKindPlayer[3] % 2) == 0)) {
                return kind_fight[7];
            }
        }
        if (i < k1 * 0.01) {
            arr = kind_fight[0];
        } else if (i < (k1 + k2) * 0.01) {
            arr = kind_fight[1];
        } else if (i < (k1 + k2 + k3) * 0.01) {
            arr = kind_fight[2];
        } else {
            arr = kind_fight[3];
        }
        return arr;
    }

    /**
     * Функция установления поведения противника
     *
     * @param enemy противник
     * @param quantityMovesKindPlayer {@link Fight#quantityMovesKindPlayer}
     * @return
     */
    public int[] ChooseBehavior(Fighter enemy, int[] quantityMovesKindPlayer) {
        int arr[] = null;
        this.quantityMovesKindPlayer = quantityMovesKindPlayer;
        if (enemy instanceof Baraka) {
            arr = EnemyBehavior(15, 15, 60, 10, false, false);
        }
        if (enemy instanceof SubZero) {
            arr = EnemyBehavior(25, 25, 0, 50, true, false);
        }
        if (enemy instanceof LiuKang) {
            arr = EnemyBehavior(13, 13, 10, 64, false, false);
        }
        if (enemy instanceof SonyaBlade) {
            arr = EnemyBehavior(25, 25, 50, 0, false, false);
        }
        if (enemy instanceof ShaoKahn) {
            arr = EnemyBehavior(10, 45, 0, 45, false, true);
        }
        return arr;
    }

    /**
     * Функция визуализации количества здоровья
     *
     * @param character персонаж
     * @param healthProgressBar JProgressBar в котором отображается здоровье
     * персонажа
     */
    public void setHealthProgressBar(Fighter character, JProgressBar healthProgressBar) {
        if (character.getHealth() >= 0) {
            healthProgressBar.setValue(character.getHealth());
        } else {
            healthProgressBar.setValue(0);
        }
    }

    /**
     * Функция добавления игроку очков, опыта и, при достижении необходимого
     * количества опыта, перехода на новый уровень
     *
     * @param player игрок
     * @param enemyes противнники
     */
    public void AddPoints(Player player, Fighter[] enemyes) {
        switch (player.getLevel()) {
            case 0:
                player.addExperience(20);
                player.addPoints(25 + player.getHealth() / 4);
                break;
            case 1:
                player.addExperience(25);
                player.addPoints(30 + player.getHealth() / 4);
                break;
            case 2:
                player.addExperience(30);
                player.addPoints(35 + player.getHealth() / 4);
                break;
            case 3:
                player.addExperience(40);
                player.addPoints(45 + player.getHealth() / 4);
                break;
            case 4:
                player.addExperience(50);
                player.addPoints(55 + player.getHealth() / 4);
                break;
        }
        for (int i = 0; i < 5; i++) {
            if (experience_for_next_level[i] == player.getExperience()) {
                player.levelUp();
                player.setNextExperianceGoal(experience_for_next_level[i + 1]);
                for (int j = 0; j < 5; j++) {
                    addHealthAndDamgeEnemy(enemyes[j], player);
                }
            }
        }
    }

    /**
     * Функция добавления игроку предметов
     *
     * @param k1
     * @param k2
     * @param k3
     * @param items {@link JFrames#items}
     */
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

    /**
     * Функция добавления игроку здоровья при переходе на новый уровень
     *
     * @param player игрок
     * @see Fighter#maxhealth
     * @see Fighter#level
     */
    public void addHealthToPlayer(Player player) {
        int hp;
        hp = switch (player.getLevel()) {
            case 1 ->
                40;
            case 2 ->
                50;
            case 3 ->
                65;
            case 4 ->
                80;
            default ->
                0;
        };
        player.addMaxHealth(hp);
    }

    /**
     * Функция добавления игроку урона при переходе на новый уровень
     *
     * @param player игрок
     * @see Fighter#damage
     * @see Fighter#level
     */
    public void addDamageToPlayer(Player player) {
        int damage;
        damage = switch (player.getLevel()) {
            case 1 ->
                5;
            case 2 ->
                6;
            case 3 ->
                8;
            case 4 ->
                11;
            default ->
                0;
        };
        player.addDamage(damage);
    }

    /**
     * Функция добавления противнику урона и здоровья при переходе игрока на
     * новый уровень
     *
     * @param enemy противник
     * @param player игрок
     * @see Fighter#damage
     * @see Fighter#maxhealth
     * @see Fighter#level
     */
    public void addHealthAndDamgeEnemy(Fighter enemy, Player player) {
        int hp = 0;
        int damage = 0;
        switch (player.getLevel()) {
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

    /**
     * Функция реализации логики при использовании игроком предмета
     *
     * @param player игрок
     * @param items {@link JFrames#items}
     * @param nameElixirButton название предмета, которое игрок хочет
     * использовать
     * @param elixirRestrictionDialog JDialog, который всплывает, если игрок
     * выбрал предмет, который не может использовать
     * @param bagDialog JDialog в котором отображается инвентарь
     */
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
