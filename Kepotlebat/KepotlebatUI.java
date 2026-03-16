package Kepotlebat;

import javax.swing.*;
import java.awt.*;
import java.util.Random;

 ///Class Pokemon (เก็บข้อมูลตัวละคร)
class Pokemon {
    String name;
    int hp;
    int maxHp;
    Skill[] skills; // เปลี่ยนเป็น Array แล้ว

    public Pokemon(String name, int hp, Skill[] skills) {
        this.name = name;
        this.hp = hp;
        this.maxHp = hp;
        this.skills = skills;
    }

    public boolean isAlive() {
        return hp > 0;
    }

    public int getHpPercent() {
        return (int)((hp * 100.0) / maxHp);
    }
}

// Class หลักของหน้าจอ UI
public class KepotlebatUI extends JFrame {

    JLabel playerName, enemyName;
    JTextArea statusBox;
    JButton skill1, skill2, skill3, skill4;

    Pokemon player;
    Pokemon enemy;
    Pokemon[] pokemonList;

    Random rand = new Random();
    static final int MISS_CHANCE = 15;
    static final int CRIT_CHANCE = 15;

    public KepotlebatUI(){
        setTitle("KEPOTLEBAT");
        setSize(800, 450);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(null);
        mainPanel.setBackground(new Color(255, 235, 59));
        add(mainPanel);

        JLabel title = new JLabel("KEPOTLEBAT");
        title.setFont(new Font("Arial", Font.BOLD, 36));
        title.setBounds(270, 20, 400, 50);
        mainPanel.add(title);

        playerName = new JLabel("Choose Pokemon");
        playerName.setBounds(80, 120, 250, 50);
        playerName.setFont(new Font("Arial", Font.BOLD, 20));
        playerName.setOpaque(true);
        playerName.setBackground(new Color(240, 220, 180));
        playerName.setHorizontalAlignment(SwingConstants.CENTER);
        mainPanel.add(playerName);

        enemyName = new JLabel("Enemy");
        enemyName.setBounds(80, 200, 250, 50);
        enemyName.setFont(new Font("Arial", Font.BOLD, 20));
        enemyName.setOpaque(true);
        enemyName.setBackground(new Color(240, 220, 180));
        enemyName.setHorizontalAlignment(SwingConstants.CENTER);
        mainPanel.add(enemyName);

        statusBox = new JTextArea();
        statusBox.setFont(new Font("Arial", Font.PLAIN, 16));
        statusBox.setEditable(false);
        JScrollPane scroll = new JScrollPane(statusBox);
        scroll.setBounds(470, 120, 250, 130);
        mainPanel.add(scroll);

        skill1 = new JButton(); skill1.setBounds(180, 320, 120, 50);
        skill2 = new JButton(); skill2.setBounds(320, 320, 120, 50);
        skill3 = new JButton(); skill3.setBounds(460, 320, 120, 50);
        skill4 = new JButton(); skill4.setBounds(600, 320, 120, 50);

        mainPanel.add(skill1); mainPanel.add(skill2);
        mainPanel.add(skill3); mainPanel.add(skill4);

        startGame();
        setChooseButtons();
    }

    void startGame(){
        Pokemon peat = new Pokemon("Peat", 100, new Skill[]{
                new Skill("Fire Punch", 20),
                new Skill("Flame Kick", 25),
                new Skill("Heat Wave", 18),
                new Skill("Blaze Storm", 22)
        });

        Pokemon tan = new Pokemon("Tan", 110, new Skill[]{
                new Skill("Water Gun", 18),
                new Skill("Aqua Tail", 23),
                new Skill("Hydro Pump", 28),
                new Skill("Bubble Beam", 20)
        });

        Pokemon cheer = new Pokemon("Cheer", 95, new Skill[]{
                new Skill("Leaf Blade", 22),
                new Skill("Solar Beam", 30),
                new Skill("Razor Leaf", 18),
                new Skill("Vine Whip", 15)
        });

        Pokemon max = new Pokemon("Max", 120, new Skill[]{
                new Skill("Thunderbolt", 25),
                new Skill("Volt Tackle", 28),
                new Skill("Spark", 18),
                new Skill("Quick Attack", 15)
        });

        pokemonList = new Pokemon[]{peat, tan, cheer, max};
        statusBox.setText(statusBox.getText() + "choose your Kepo \n");
    }

    void setChooseButtons(){
        skill1.setText("Peat");
        skill2.setText("Tan");
        skill3.setText("Cheer");
        skill4.setText("Max");

        for(var a : skill1.getActionListeners()) skill1.removeActionListener(a);
        for(var a : skill2.getActionListeners()) skill2.removeActionListener(a);
        for(var a : skill3.getActionListeners()) skill3.removeActionListener(a);
        for(var a : skill4.getActionListeners()) skill4.removeActionListener(a);

        skill1.addActionListener(_ -> choosePokemon(0));
        skill2.addActionListener(_ -> choosePokemon(1));
        skill3.addActionListener(_ -> choosePokemon(2));
        skill4.addActionListener(_ -> choosePokemon(3));
    }

    void choosePokemon(int index){
        player = pokemonList[index];
        do {
            enemy = pokemonList[rand.nextInt(pokemonList.length)];
        } while(enemy == player);

        updateHp();
        statusBox.append("You chose " + player.name + "\n");
        statusBox.append("Enemy is " + enemy.name + "\n");
        statusBox.append("Battle Start! \n");
        setBattleButtons();
    }

    void setBattleButtons(){
        skill1.setText(player.skills[0].name);
        skill2.setText(player.skills[1].name);
        skill3.setText(player.skills[2].name);
        skill4.setText(player.skills[3].name);

        for(var a : skill1.getActionListeners()) skill1.removeActionListener(a);
        for(var a : skill2.getActionListeners()) skill2.removeActionListener(a);
        for(var a : skill3.getActionListeners()) skill3.removeActionListener(a);
        for(var a : skill4.getActionListeners()) skill4.removeActionListener(a);

        skill1.addActionListener(_ -> attack(0));
        skill2.addActionListener(_ -> attack(1));
        skill3.addActionListener(_ -> attack(2));
        skill4.addActionListener(_-> attack(3));
    }

    void attack(int index){
        Skill skill = player.skills[index];
        int damage = calculateDamage(skill.damage);

        enemy.hp -= damage;
        if(enemy.hp < 0) enemy.hp = 0;

        statusBox.append(player.name + " used " + skill.name + " DMG " + damage + "\n");
        updateHp();

        if(!enemy.isAlive()){
            statusBox.setText("YOU WIN! \n");
            disableButtons();
            return;
        }
        enemyTurn();
    }

    void enemyTurn(){
        Skill skill = enemy.skills[rand.nextInt(4)];
        int damage = calculateDamage(skill.damage);

        player.hp -= damage;
        if(player.hp < 0) player.hp = 0;

        statusBox.append(enemy.name + " used " + skill.name + " DMG " + damage + "\n");
        updateHp();

        if(!player.isAlive()){
            statusBox.setText("YOU LOSE! \n");
            disableButtons();
        }
    }

    int calculateDamage(int base){
        int roll = rand.nextInt(100);
        if(roll < MISS_CHANCE) {
            statusBox.append("miss ahaha!\n");
            return 0;
        }
        if(roll < MISS_CHANCE + CRIT_CHANCE) {
            statusBox.append("Critical Hit! BOOOM !\n");
            return base * 2;
        }
        return base;
    }

    void updateHp(){
        playerName.setText(player.name + " HP " + player.getHpPercent() + "%");
        enemyName.setText(enemy.name + " HP " + enemy.getHpPercent() + "%");
    }

    void disableButtons(){
        skill1.setEnabled(false);
        skill2.setEnabled(false);
        skill3.setEnabled(false);
        skill4.setEnabled(false);
    }
}