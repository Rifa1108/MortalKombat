/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package mortalkombatbversion;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Comparator;
import javax.swing.JLabel;
import javax.swing.JProgressBar;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;
import org.apache.poi.openxml4j.exceptions.InvalidOperationException;
import org.apache.poi.xssf.usermodel.*;

/**
 *
 * @author Мария
 */
public class Game {

    CharacterAction action = new CharacterAction();
    TextChanger textChanger = new TextChanger();
    Fight fight = new Fight();
    private ArrayList<Result> results = new ArrayList<>();
    
    public Fighter NewEnemy(JLabel enemyPictureLabel, JLabel enemyNameLabel,
                           JLabel enemyQuantityDamageLabel, JLabel enemyQuantityHealthLabel, 
                           JProgressBar enemyHealthProgressBar) {
        action.setEnemyes();
        Fighter enemy = action.ChooseEnemy(enemyPictureLabel, enemyNameLabel, enemyQuantityDamageLabel, enemyQuantityHealthLabel);
        action.setHealthProgressBar(enemy, enemyHealthProgressBar);
        enemyHealthProgressBar.setMaximum(enemy.getMaxHealth());
        return enemy;
    }

    public Player newPlayer(JProgressBar playerHealthProgressBar) {
        Player human = new Player(0, 80, 16, 1);
        action.setHealthProgressBar(human, playerHealthProgressBar);
        playerHealthProgressBar.setMaximum(human.getMaxHealth());
        return human;
    }

    public void EndGameTop(Player player, JTextField nameForRecordTableTextField, JTable recordsTable) throws IOException {
        results.add(new Result(nameForRecordTableTextField.getText(), player.getPoints()));
        results.sort(Comparator.comparing(Result::getPoints).reversed());
        WriteToTable(recordsTable);
        WriteToExcel();
    }

    public void WriteToExcel() throws IOException {
        XSSFWorkbook book = new XSSFWorkbook();
        XSSFSheet sheet = book.createSheet("Результаты ТОП 10");
        XSSFRow r = sheet.createRow(0);
        r.createCell(0).setCellValue("№");
        r.createCell(1).setCellValue("Имя");
        r.createCell(2).setCellValue("Количество баллов");
        for (int i = 0; i < results.size(); i++) {
            if (i < 10) {
                XSSFRow r2 = sheet.createRow(i + 1);
                r2.createCell(0).setCellValue(i + 1);
                r2.createCell(1).setCellValue(results.get(i).getName());
                r2.createCell(2).setCellValue(results.get(i).getPoints());
            }
        }
        File f = new File(System.getProperty("user.dir") + "\\Results.xlsx");
        book.write(new FileOutputStream(f));
        book.close();
    }

    public ArrayList<Result> getResults() {
        return this.results;
    }

    public void ReadFromExcel() {
        try {
            XSSFWorkbook book = new XSSFWorkbook(System.getProperty("user.dir") + "\\Results.xlsx");
            XSSFSheet sh = book.getSheetAt(0);
            for (int i = 1; i <= sh.getLastRowNum(); i++) {
                results.add(new Result(sh.getRow(i).getCell(1).getStringCellValue(), (int) sh.getRow(i).getCell(2).getNumericCellValue()));
            }
        } catch (InvalidOperationException | IOException e) {

        }
    }

    public void WriteToTable(JTable table) {
        DefaultTableModel model = (DefaultTableModel) table.getModel();
        for (int i = 0; i < results.size(); i++) {
            if (i < 10) {
                model.setValueAt(results.get(i).getName(), i, 0);
                model.setValueAt(results.get(i).getPoints(), i, 1);
            }
        }
    }
}
