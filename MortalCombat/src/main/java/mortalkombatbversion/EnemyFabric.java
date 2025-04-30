/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package mortalkombatbversion;

/**
 * Фабрика противников
 */
public class EnemyFabric {

    /**
     * Функция создания противника по выбранному номеру
     *
     * @param enemyNumber номер противника, которого нужно создать
     * <ul>
     * <li>0 - Baraka</li>
     * <li>1- Sub Zero</li>
     * <li>2 - Liu Kang</li>
     * <li>3 - Sonya Blade</li>
     * <li>4 - Shao Kahn</li>
     * </ul>
     * @return возвращает созданного противника
     * @see Baraka
     * @see SubZero
     * @see LiuKang
     * @see SonyaBlade
     * @see ShaoKahn
     */
    public Fighter create(int enemyNumber) {
        EnemyFabricInterface fabric = switch (enemyNumber) {
            case 0 ->
                new BarakaFabric();
            case 1 ->
                new SubZeroFabric();
            case 2 ->
                new LiuKangFabric();
            case 3 ->
                new SonyaBladeFabric();
            case 4 ->
                new ShaoKahnFabric();
            default ->
                new BarakaFabric();
        };
        Fighter enemy = fabric.create();
        return enemy;
    }
}
