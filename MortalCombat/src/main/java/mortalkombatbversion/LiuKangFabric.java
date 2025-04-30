/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package mortalkombatbversion;

/**
 * Класс создающий Liu Kang
 *
 * @see LiuKang
 */
public class LiuKangFabric implements EnemyFabricInterface {

    /**
     * Функция создания Liu Kang
     *
     * @return возвращает созданного Liu Kang
     * @see LiuKang
     */
    @Override
    public Fighter create() {
        Fighter enemy = new LiuKang(1, 70, 20, 1);
        return enemy;

    }
}
