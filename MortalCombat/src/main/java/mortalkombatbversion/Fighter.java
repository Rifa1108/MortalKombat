/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package mortalkombatbversion;

/**
 * Класс персонажа
 * @see Player, Baraka, LiuKang, ShaoKahn, SonyaBlade, SubZero
 */
public class Fighter {
/**
 * Текущий уровень персонажа
 */
    private int level;
/**
 * Текущее количетво здоровья персонажа
 */
    private int health;
/**
 * Максимально возможное на данный момент количество здоровья персонажа
 */
    private int maxhealth;
/**
 * Количество урона, которое может нанести персонаж
 */
    private int damage;
/**
 * Тип действия, которое в данный момент совершает персонаж
 * <ul> 
 * <li>0 - защита </li>
 * <li>1 - атака</li>
 * <li>2 - проклясть</li>
 * <li>3 - регенерировать</li>
 * </ul>
 */
    private int attack;
/**
 * Количество ходов во время которых персонаж будет проклят. Если персонаж не проклят, равно 0.
 */
    private int remainCursedTime;
/**
 * Конструктор - создание нового персонажа с определёнными значениями
 * @param level
 * @param health
 * @param damage
 * @param attack
 * @see Fighter#level
 * @see Fighter#health
 * @see Fighter#damage
 * @see Fighter#attack
 */
    public Fighter(int level, int health, int damage, int attack) {
        this.level = level;
        this.health = health;
        this.damage = damage;
        this.attack = attack;
        this.maxhealth = health;
        remainCursedTime = 0;
    }
/**
 * Увеличение уровня персонажа
 * @see Fighter#level
 */
    public void levelUp() {
        this.level++;
    }
/**
 * Добавление к текущему количеству здоровья персонажа переданного значения
 * @param addedHealth количество здоровья, которое 
 * персонаж получил (положительное значение) или потерял (отрицательное значение)
 * @see Fighter#health
 */
    public void addHealth(int addedHealth) {
        if (remainCursedTime > 0 && addedHealth < 0) {
            addedHealth *= 1.25;
        }
        this.health += addedHealth;
    }
/**
 * Изменение текущего количества здоровья на переданное значение
 * @param health количество здоровья, которое нужно уствновить персонажу
 * @see Fighter#health
 */
    public void setHealth(int health) {
        this.health = health;
    }
/**
 * Добавление к текущему количеству урона персонажа переданного значения
 * @param addedDamage количество урона, которое нужно добавить
 * /**
 * @see Fighter#damage
 */
    public void addDamage(int addedDamage) {
        this.damage += addedDamage;
    }
/**
 * Установление персонажу переданное действие
 * @param attack вид дествия, которое совершит персонаж
 * @see Fighter#attack
 */
    public void setAttack(int attack) {
        this.attack = attack;
    }
/**
 * Добавление к текущему максимально возможному количеству здоровья персонажа переданное значения
 * @param addedMaxHealth количество здоровья, которое нужно добавить
 * @see Fighter#maxhealth
 */
    public void addMaxHealth(int addedMaxHealth) {
        this.maxhealth += addedMaxHealth;
    }
    
/**
 * Функция получения знаения поля {@link Fighter#level}
 * @return возвращает текущий уровень персонажа
 */
    public int getLevel() {
        return this.level;
    }
/**
 * Функция получения знаения поля {@link Fighter#health}
 * @return возвращает текущее количество здоровья персонажа
 */
    public int getHealth() {
        return this.health;
    }
/**
 * Функция получения знаения поля {@link Fighter#damage}
 * @return возвращает текущий уровень урона, который может нанести персонаж
 */
    public int getDamage() {
        if (remainCursedTime > 0) {
            return (int) (damage * 0.5);
        } else {
            return damage;
        }
    }
/**
 * Функция получения знаения поля {@link Fighter#attack}
 * @return возвращает вид атаки, кторое че персонаж
 */
    public int getAttack() {
        return this.attack;
    }

    public int getMaxHealth() {
        return this.maxhealth;
    }

    public String getName() {
        return "";
    }
/**
 * Добавление к текущему количеству шагов во время которых персонаж будет проклят на переданное значение
 * @param steps количество проклятых шагов, которое 
 * персонаж получил (положительное значение) или уже пережил (отрицательное значение)
 * @see Fighter#remainCursedTime
 */
    public void changeCurseTime(int steps) {
        remainCursedTime = remainCursedTime + steps + 1;
    }

    public int getCurseTime() {
        return remainCursedTime;
    }

}
