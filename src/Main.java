import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Random;
import javax.swing.*;

public abstract class Main implements ActionListener {

    public static ArrayList<Creature> population = new ArrayList<>();

    public static void main(String[] args) {

        Random rand = new Random();
        for (int i = 0; i < 100; i++) {
            int seed = rand.nextInt(2);
            if(seed==0){
                population.add(new Creature(false,i));
            }else population.add(new Creature(true,i));
        }

        JFrame frame = new JFrame();
        frame.setVisible(true);
        frame.setSize(500, 500);
        frame.setBackground(Color.blue);
        GridLayout grid = new GridLayout(10, 10, 3, 3);
        frame.setLayout(grid);

        update(frame);

        Timer runtime = new Timer(250, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                frame.getContentPane().removeAll();
                frame.repaint();

                update(frame);

                cycle();
            }
        });

        runtime.start();
    }

    public static void cycle() {


        for (int i = 0; i < 100; i++) {
            int neighbours = countNeighbours(i);
            Random rand = new Random();
            if(population.get(i).getAlive() && (neighbours==2 || neighbours==3)){
                //int suicide=rand.nextInt(100);
                //if(suicide==0){
                    //System.out.println("Someone commited suicide"+ suicide);
                    //population.get(i).setAlive(false);
                //}else
                    population.get(i).setAlive(true);
            }else if(!population.get(i).getAlive() && neighbours==3){
                population.get(i).setAlive(true);
            }else if(population.get(i).getAlive()){
                population.get(i).setAlive(false);
            }else if(population.get(i).getAlive() & neighbours>3){
                population.get(i).setAlive(false);
            }


        }

    }

    public static int countNeighbours(int id) {
        int ret = 0;
        //top-left corner
        if(id==0){
            if(population.get(id+1).getAlive()) ret++;
            if(population.get(id+10).getAlive()) ret++;
            if(population.get(id+11).getAlive()) ret++;
        }
        //top-right corner
        else if(id==9){
            if(population.get(id-1).getAlive()) ret++;
            if(population.get(id+10).getAlive()) ret++;
            if(population.get(id+9).getAlive()) ret++;
        }
        //bottom-right corner
        else if(id==99){
            if(population.get(id-1).getAlive()) ret++;
            if(population.get(id-10).getAlive()) ret++;
            if(population.get(id-11).getAlive()) ret++;
        }
        //bottom-left corner
        else if(id==90){
            if(population.get(id-10).getAlive()) ret++;
            if(population.get(id+1).getAlive()) ret++;
            if(population.get(id-9).getAlive()) ret++;
        }
        //top side
        else if (1 <= id && id <= 8) {
            if(population.get(id+1).getAlive()) ret++;
            if(population.get(id-1).getAlive()) ret++;
            if(population.get(id+10).getAlive()) ret++;
            if(population.get(id+9).getAlive()) ret++;
            if(population.get(id+11).getAlive()) ret++;

        }
        //bottom side
        else if (91 <= id && id <= 98) {
            if(population.get(id-10).getAlive()) ret++;
            if(population.get(id+1).getAlive()) ret++;
            if(population.get(id-1).getAlive()) ret++;
            if(population.get(id-9).getAlive()) ret++;
            if(population.get(id-11).getAlive()) ret++;
        }
        //right side
        else if (id==19 || id==29 || id==39 || id==49
                || id==59 || id==69 || id==79 || id==89) {
            if(population.get(id-10).getAlive()) ret++;
            if(population.get(id-1).getAlive()) ret++;
            if(population.get(id+10).getAlive()) ret++;
            if(population.get(id-11).getAlive()) ret++;
            if(population.get(id+9).getAlive()) ret++;
        }
        //left side
        else if (id % 10 == 0) {
            if(population.get(id-10).getAlive()) ret++;
            if(population.get(id+1).getAlive()) ret++;
            if(population.get(id+10).getAlive()) ret++;
            if(population.get(id-9).getAlive()) ret++;
            if(population.get(id+11).getAlive()) ret++;
        }
        else {
            if(population.get(id-9).getAlive()) ret++;
            if(population.get(id-10).getAlive()) ret++;
            if(population.get(id-11).getAlive()) ret++;
            if(population.get(id+9).getAlive()) ret++;
            if(population.get(id+10).getAlive()) ret++;
            if(population.get(id+11).getAlive()) ret++;
            if(population.get(id-1).getAlive()) ret++;
            if(population.get(id+1).getAlive()) ret++;
        }

        return ret;

    }

    public static void update(JFrame frame){
        for (int i = 0; i < 100; i++) {
            JButton button = new JButton();
            button.setEnabled(false);
            if (population.get(i).getAlive()) {
                button.setBackground(Color.black);
            } else button.setBackground(Color.white);

            frame.add(button);
        }
        frame.setVisible(true);
    }
}




