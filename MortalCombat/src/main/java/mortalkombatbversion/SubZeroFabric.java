/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package mortalkombatbversion;

/**
 * Класс создающий Sub Zero
 *
 * @see SubZero
 */
public class SubZeroFabric implements EnemyFabricInterface {

    /**
     * Функция создания Sub Zero
     *
     * @return возвращает созданного Sub Zero
     * @see SubZero
     */
    @Override
    public Fighter create() {
        Fighter enemy = new SubZero(1, 60, 16, 1);
        return enemy;
    }

}
