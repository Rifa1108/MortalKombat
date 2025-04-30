/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package mortalkombatbversion;

/**
 * Класс игрока
 */
public class Player extends Fighter {

    /**
     * Набранное количество очков
     */
    private int points;
    /**
     * Набранное количество опыта
     */
    private int experience;
    /**
     * Количество побед
     */
    private int win;
    /**
     * Необходимое количество опыта для перехода на следующий уровень
     *
     * @see Player#experience
     * @see Fighter#level
     */
    private int nextexperience;

    /**
     * Конструктор - создание нового игрока с определёнными значениями
     *
     * @param level
     * @param health
     * @param damage
     * @param attack
     * @see Fighter#Fighter(int, int, int, int)
     * @see Player
     */
    public Player(int level, int health, int damage, int attack) {
        super(level, health, damage, attack);
        this.points = 0;
        this.experience = 0;
        this.nextexperience = 40;
        this.win = 0;
    }

    /**
     * Функция получения значения поля {@link Player#points}
     *
     * @return возвращает набранное количество очков
     */
    public int getPoints() {
        return this.points;
    }

    /**
     * Функция получения значения поля {@link Player#experience}
     *
     * @return возвращает набранное количество опыта
     */
    public int getExperience() {
        return this.experience;
    }

    /**
     * Функция получения значения поля {@link Player#nextexperience}
     *
     * @return возвращает необходимое количество опыта для перехода на следующий
     * уровень
     */
    public int getNextExperience() {
        return this.nextexperience;
    }

    /**
     * Функция получения значения поля {@link Player#win}
     *
     * @return возвращает количество побед
     */
    public int getWin() {
        return this.win;
    }

    /**
     * Добавление к текущему количеству набранных очков переданного значения
     *
     * @param newPoints количество очков, которое нужно добавить
     * @see Player#points
     */
    public void addPoints(int newPoints) {
        this.points += newPoints;
    }

    /**
     * Добавление к текущему количеству набранного опыта переданного значения
     *
     * @param expirienceQuantity количество опыта, которое нужно добавить
     * @see Player#experience
     */
    public void addExperience(int expirienceQuantity) {
        this.experience += expirienceQuantity;
    }

    /**
     * Установление игроку новое количество очков, требуемых для перехода на
     * новый уровень
     *
     * @param experianceGoal новое количество очков
     * @see Player#nextexperience
     */
    public void setNextExperianceGoal(int experianceGoal) {
        this.nextexperience = experianceGoal;
    }

    /**
     * Увеличение количества побед на единицу
     *
     * @see Player#win
     */
    public void addWin() {
        this.win++;
    }

    @Override
    public String getName() {
        return "You";
    }

}
