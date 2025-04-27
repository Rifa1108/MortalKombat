/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package mortalkombatbversion;

/**
 *
 * @author Мария
 */
public class EnemyFabric {

    public Fighter create(int enemyNumber) {
        EnemyFabricInterface fabric = switch (enemyNumber) {
            case 0 -> new BarakaFabric();
            case 1 -> new SubZeroFabric();
            case 2 -> new LiuKangFabric();
            case 3 -> new SonyaBladeFabric();
            case 4 -> new ShaoKahnFabric();
            default -> new BarakaFabric();
        };
        Fighter enemy = fabric.create();
        return enemy;
    }
}
