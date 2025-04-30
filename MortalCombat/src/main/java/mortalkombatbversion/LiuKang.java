/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package mortalkombatbversion;

/**
 * Класс персонажа Liu Kang (боец)
 *
 * @see Fighter
 * @see LiuKangFabric
 */
public class LiuKang extends Fighter {

    /**
     * Конструктор - создание нового Liu Kang с определёнными значениями
     *
     * @param level
     * @param health
     * @param damage
     * @param attack
     * @see Fighter#Fighter(int, int, int, int)
     * @see LiuKang
     */
    public LiuKang(int level, int health, int damage, int attack) {
        super(level, health, damage, attack);
    }

    @Override
    public String getName() {
        return "Liu Kang";
    }
}
