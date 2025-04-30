/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package mortalkombatbversion;

/**
 * Класс персонажа Baraka (танк)
 *
 * @see Fighter
 * @see BarakaFabric
 */
public class Baraka extends Fighter {

    /**
     * Конструктор - создание нового Baraka с определёнными значениями
     *
     * @param level
     * @param health
     * @param damage
     * @param attack
     * @see Fighter#Fighter(int, int, int, int)
     * @see Baraka
     */
    public Baraka(int level, int health, int damage, int attack) {
        super(level, health, damage, attack);
    }

    @Override
    public String getName() {
        return "Baraka";
    }

}
