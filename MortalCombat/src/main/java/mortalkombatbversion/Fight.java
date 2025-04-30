/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package mortalkombatbversion;

import java.util.ArrayList;
import java.util.function.BiConsumer;
import java.util.function.Consumer;
import javax.swing.*;

/**
 * Класс реализующий логику сражения
 */
public class Fight {

    /**
     * Экземпляр класса, который будет создавать надписи в текщей игре
     */
    protected TextChanger textChanger = new TextChanger();
    /**
     * Модель поведения, которой будет придерживаться противник
     *
     * @see Fighter#attack
     */
    protected int kind_attack[] = {0};
    /**
     * Количество шагов в текущем раунде
     */
    protected int moveNumber = 1;
    /**
     * Счётчик ходов, в котором противник реализовывал текущую модель поведения
     * {@link Fight#kind_attack}
     */
    protected int k = -1;
    /**
     * Флаг, определяющий оглушён ли боец чья очередь ходить
     * <ul>
     * <li>0 - не оглушён</li>
     * <li>1 - оглушён</li>
     * </ul>
     */
    protected int stun = 0;
    /**
     * Случайное число для производства случайных событий
     */
    protected double v = 0.0;
    /**
     * Счётчик шагов игрока за игру
     * <ul>
     * <li>1 элемент - количество выбора защиты</li>
     * <li>2 элемент - количество выбора атаки</li>
     * <li>3 элемент - количество выбора проклятия</li>
     * </ul>
     */
    protected int[] quantityMovesKindPlayer = {0, 0, 0,0};

    /**
     * Функция, реализующая последствия действий текущего раунда
     *
     * @param fighter1 персонаж чья очередь ходить
     * @param fighter2 противник персонажа чья очередь ходить
     * @param specialCommentAboutFightLabel JLabel в котором отображается
     * комментарий о том, что какой-то персонаж был оглушён
     * @param commentAboutFightLabel JLabel в котором отображаются все остальные
     * комментарии о ходе битвы
     */
    public void Move(Fighter fighter1, Fighter fighter2, JLabel specialCommentAboutFightLabel, JLabel commentAboutFightLabel) {
        if (stun == 1) {
            fighter1.setAttack(-1);
        }
        Consumer<Fighter> successfulRegeneration = regenerator -> {
            regenerator.addHealth((regenerator.getMaxHealth() - regenerator.getHealth()) / 2);
            commentAboutFightLabel.setText(regenerator.getName() + " recovered");
        };
        BiConsumer<Fighter, Fighter> unSuccessfulRegeneration = (regenerator, attacker) -> {
            regenerator.addHealth(-attacker.getDamage() * 2);
            commentAboutFightLabel.setText(regenerator.getName() + " was attacked");
        };
        switch (Integer.toString(fighter1.getAttack()) + Integer.toString(fighter2.getAttack())) {
            case "10":
                v = Math.random();
                if (fighter1 instanceof ShaoKahn & v < 0.15) {
                    fighter2.addHealth(-(int) (fighter1.getDamage() * 0.5));
                    commentAboutFightLabel.setText("Your block is broken");

                } else {
                    fighter1.addHealth(-(int) (fighter2.getDamage() * 0.5));
                    commentAboutFightLabel.setText(fighter2.getName() + " counterattacked");
                }
                break;
            case "11":
                fighter2.addHealth(-fighter1.getDamage());
                commentAboutFightLabel.setText(fighter1.getName() + " successfully attacked");
                break;
            case "00":
                v = Math.random();
                if (v <= 0.5) {
                    stun = 1;
                }
                commentAboutFightLabel.setText("Both defended themselves");
                break;
            case "01":
                commentAboutFightLabel.setText(fighter1.getName() + " didn't attack");
                break;
            case "-10":
                specialCommentAboutFightLabel.setText(fighter1.getName() + " was stunned");
                stun = 0;
                commentAboutFightLabel.setText(fighter2.getName() + " didn't attack");
                break;
            case "-11":
                fighter1.addHealth(-fighter2.getDamage());
                specialCommentAboutFightLabel.setText(fighter1.getName() + " was stunned");
                stun = 0;
                commentAboutFightLabel.setText(fighter2.getName() + " attacked");
                break;
            case "20":
                v = Math.random();
                if (v <= 0.75) {
                    fighter2.changeCurseTime(fighter1.getLevel());
                    commentAboutFightLabel.setText(fighter2.getName() + " was cursed");
                } else {
                    commentAboutFightLabel.setText(fighter1.getName() + " tried to curse");
                }
                break;
            case "21":
                fighter1.addHealth((int) (-fighter2.getDamage() * 1.15));
                commentAboutFightLabel.setText(fighter2.getName() + " attacked");
                break;
            case "22":
                commentAboutFightLabel.setText("Both tried to curse each other");
                break;
            case "2-1":
                fighter2.changeCurseTime(fighter1.getLevel());
                commentAboutFightLabel.setText(fighter2.getName() + " was cursed");
                specialCommentAboutFightLabel.setText(fighter2.getName() + " was stunned");
                stun = 0;
                break;
            case "-12":
                fighter1.changeCurseTime(fighter2.getLevel());
                commentAboutFightLabel.setText(fighter1.getName() + " was cursed");
                specialCommentAboutFightLabel.setText(fighter1.getName() + " was stunned");
                stun = 0;
                break;
            case "02":
                v = Math.random();
                if (v <= 0.75) {
                    fighter1.changeCurseTime(fighter2.getLevel());
                    commentAboutFightLabel.setText(fighter1.getName() + " was cursed");
                } else {
                    commentAboutFightLabel.setText(fighter2.getName() + " tried to curse");
                }
                break;
            case "12":
                fighter2.addHealth((int) (-fighter1.getDamage() * 1.15));
                commentAboutFightLabel.setText(fighter1.getName() + " attacked");
                break;
            case "30", "3-1":
                successfulRegeneration.accept(fighter1);
                break;
            case "03", "-13":
                successfulRegeneration.accept(fighter2);
                break;
            case "31", "32":
                unSuccessfulRegeneration.accept(fighter1, fighter2);
                break;
            case "13", "23":
                unSuccessfulRegeneration.accept(fighter2, fighter1);
                break;

        }
        Consumer<Fighter> checkCurse = fighter -> {
            if (fighter.getCurseTime() > 0) {
                fighter.changeCurseTime(-2);
            }
        };
        checkCurse.accept(fighter1);
        checkCurse.accept(fighter2);
    }

    /**
     * Функция создающая всё окружение текущего раунда до его окончания
     *
     * @param player игрок
     * @param enemy противник
     * @param kindOfAttack вид атаки, которую произвёл игрок
     * {@link Fighter#attack}
     * @param enemyQuantityHealthLabel JLabel в котором отображается здоровье
     * противника в числах
     * @param playerQuantityHeathLabel JLabel в котором отображается здоровье
     * игрока в числах
     * @param infoAboutWinnerDialog JDialog, всплывающий после окончания раунда
     * (если это не финальный раунд), в котором отображается кто выиграл
     * @param winnerNameLabel JLabel в infoAboutWinnerDialog в котором написано
     * имя выигравшего персонажа
     * @param action {@link Game#action}
     * @param playerHealthProgressBar JProgressBar в котором отображается
     * здоровье игрока
     * @param enemyHealthProgressBar JProgressBar в котором отображается
     * здоровье противника
     * @param winWithRecordDialog JDialog, всплывающий после окончания
     * финального раунда, если игрок попал в топ-10
     * @param winWithoutRecordDialog JDialog, всплывающий после окончания
     * финального раунда, если игрок не попал в топ-10
     * @param fightFrame JFrame в котором отображается битва
     * @param results {@link Game#results}
     * @param winWithRecordLabel JLabel в winWithRecordDialog, отображающий
     * выиграл игрок или нет
     * @param winWithoutRecordLabel JLabel в winWithoutRecordDialog,
     * отображающий выиграл игрок или нет
     * @param turnInfoLabel JLabel, отображающий чья очередь ходить
     * @param specialCommentAboutFightLabel JLabel в котором отображается
     * комментарий о том, что какой-то персонаж был оглушён
     * @param commentAboutFightLabel JLabel в котором отображаются все остальные
     * комментарии о ходе битвы
     * @param items список предметов, которые есть у игрока
     * @param rebirthElixirRadioButton JRadioButton креста возрождения
     */
    public void Hit(Fighter player, Fighter enemy, int kindOfAttack, JLabel enemyQuantityHealthLabel,
            JLabel playerQuantityHeathLabel, JDialog infoAboutWinnerDialog, JLabel winnerNameLabel, CharacterAction action,
            JProgressBar playerHealthProgressBar, JProgressBar enemyHealthProgressBar, JDialog winWithRecordDialog,
            JDialog winWithoutRecordDialog, JFrame fightFrame, ArrayList<Result> results,
            JLabel winWithRecordLabel, JLabel winWithoutRecordLabel, JLabel turnInfoLabel, JLabel specialCommentAboutFightLabel,
            JLabel commentAboutFightLabel, Items[] items, JRadioButton rebirthElixirRadioButton) {
        specialCommentAboutFightLabel.setText("");
        player.setAttack(kindOfAttack);
        quantityMovesKindPlayer[3] = moveNumber;
        if (k < kind_attack.length - 1) {
            k++;
        } else {
            kind_attack = action.ChooseBehavior(enemy, quantityMovesKindPlayer);
            k = 0;
        }
        enemy.setAttack(kind_attack[k]);
        if (moveNumber % 2 == 1) {
            Move(player, enemy, specialCommentAboutFightLabel, commentAboutFightLabel);
        } else {
            Move(enemy, player, specialCommentAboutFightLabel, commentAboutFightLabel);
        }
        moveNumber++;
        quantityMovesKindPlayer[kindOfAttack] += 1;    
        textChanger.RoundTexts(player, enemy, enemyQuantityHealthLabel, playerQuantityHeathLabel, moveNumber, turnInfoLabel);
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
            fightFrame.dispose();
            specialCommentAboutFightLabel.setText("");
            commentAboutFightLabel.setText("");
            if (((Player) player).getWin() == 11) {
                EndFinalRound(((Player) player), action, results, winWithRecordDialog, winWithoutRecordDialog,
                        fightFrame, winWithRecordLabel, winWithoutRecordLabel);
            } else {
                EndRound(player, enemy, infoAboutWinnerDialog, winnerNameLabel, action, items);
            }

        }
    }

    /**
     * Функция реализующая логику окончания раунда, если он не финальный
     *
     * @param player игрок
     * @param enemy противник
     * @param infoAboutWinnerDialog JDialog, всплывающий после окончания раунда
     * (если это не финальный раунд), в котором отображается кто выиграл
     * @param winnerNameLabel JLabel в infoAboutWinnerDialog в котором написано
     * имя выигравшего персонажа
     * @param action {@link Game#action}
     * @param items список предметов, которые есть у игрока
     */
    public void EndRound(Fighter player, Fighter enemy, JDialog infoAboutWinnerDialog,
            JLabel winnerNameLabel, CharacterAction action, Items[] items) {
        if (player.getHealth() > 0) {
            int currentLevel = player.getLevel();
            winnerNameLabel.setText("You win");
            ((Player) player).addWin();

            if (enemy instanceof ShaoKahn) {
                action.AddItems(38, 23, 8, items);
            } else {
                action.AddItems(25, 15, 5, items);
            }
            action.AddPoints(((Player) player), action.getEnemyes());
            if (currentLevel == player.getLevel()) {
                infoAboutWinnerDialog.setVisible(true);
            }
        } else {
            winnerNameLabel.setText(enemy.getName() + " win");
            infoAboutWinnerDialog.setVisible(true);
        }

        moveNumber = 1;
        k = -1;
        kind_attack = ResetAttack();

    }

    /**
     * Функция реализующая логику окончания раунда, если он финальный
     *
     * @param player игрок
     * @param action {@link Game#action}
     * @param results {@link Game#results}
     * @param winWithRecordDialog JDialog, всплывающий после окончания
     * финального раунда, если игрок попал в топ-10
     * @param winWithoutRecordDialog JDialog, всплывающий после окончания
     * финального раунда, если игрок не попал в топ-10
     * @param fightFrame JFrame в котором отображается битва
     * @param winWithRecordLabel JLabel в winWithRecordDialog, отображающий
     * выиграл игрок или нет
     * @param winWithoutRecordLabel JLabel в winWithoutRecordDialog,
     * отображающий выиграл игрок или нет
     */
    public void EndFinalRound(Player player, CharacterAction action, ArrayList<Result> results,
            JDialog winWithRecordDialog, JDialog winWithoutRecordDialog,
            JFrame fightFrame, JLabel winWithRecordLabel, JLabel winWithoutRecordLabel) {
        String text = "Победа не на вашей стороне";
        if (player.getHealth() > 0) {
            player.addWin();
            action.AddPoints(player, action.getEnemyes());
            text = "Победа на вашей стороне";
        }
        boolean top = false;
        if (results == null) {
            top = true;
        } else {
            int i = 0;
            for (int j = 0; j < results.size(); j++) {
                if (player.getPoints() < results.get(j).getPoints()) {
                    i++;
                }
            }
            if (i < 10) {
                top = true;
            }
        }
        if (top) {
            winWithRecordDialog.setVisible(true);
            winWithRecordLabel.setText(text);
        } else {
            winWithoutRecordDialog.setVisible(true);
            winWithoutRecordLabel.setText(text);
        }
    }

    /**
     * Функция сбрасывающая текущую модель поведения у противника
     *
     * @return возвращает дефолтную модель
     */
    public int[] ResetAttack() {
        int a[] = {0};
        return a;
    }

    /**
     * Функция реализующая логику начала раунда
     *
     * @param player игрок
     * @param enemyPictureLabel JLabel в котором отображается изображение
     * противника
     * @param playerHealthProgressBar JProgressBar в котором отображается
     * здоровье игрока
     * @param enemyHealthProgressBar JProgressBar в котором отображается
     * здоровье противника
     * @param enemyNameLabel JLabel в котором отображается имя противника
     * @param enemyQuantityDamageLabel JLabel в котором отображается количество
     * урона, которое может нанести противник {@link Fighter#damage}
     * @param enemyQuantityHealthLabel JLabel в котором отображается количество
     * здоровья, которое есть у противника {@link Fighter#health}
     * @param action {@link Game#action}
     * @param remainQuantity {@link JFrames#reamainQuantity}
     * @return возвращает созданного противника текущего раунда
     */
    public Fighter NewRound(Fighter player, JLabel enemyPictureLabel, JProgressBar playerHealthProgressBar,
            JProgressBar enemyHealthProgressBar, JLabel enemyNameLabel,
            JLabel enemyQuantityDamageLabel, JLabel enemyQuantityHealthLabel,
            CharacterAction action, int remainQuantity) {
        Fighter enemy1 = null;
        if (remainQuantity == 1) {
            enemy1 = action.ChooseBoss(enemyPictureLabel, enemyNameLabel, enemyQuantityDamageLabel, enemyQuantityHealthLabel);
        } else {
            enemy1 = action.ChooseEnemy(enemyPictureLabel, enemyNameLabel, enemyQuantityDamageLabel, enemyQuantityHealthLabel);
        }
        playerHealthProgressBar.setMaximum(player.getMaxHealth());
        enemyHealthProgressBar.setMaximum(enemy1.getMaxHealth());
        player.setHealth(player.getMaxHealth());
        enemy1.setHealth(enemy1.getMaxHealth());
        action.setHealthProgressBar(player, playerHealthProgressBar);
        action.setHealthProgressBar(enemy1, enemyHealthProgressBar);
        return enemy1;
    }

}
