package telran.controller.titanicStats;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class TitanicStatsAppl {
    public static void main(String[] args) {
        try {
            BufferedReader br = new BufferedReader(new FileReader("train.csv"));
            String str = br.readLine();
            String[] cells;
            //PassengerId [0], Survived[1], Pclass[2], Last name[3], First name[4], Sex[5],
            //Age[6], SibSp[7], Parch[8], Ticket[9], Fare[10], Cabin[11], Embarked[12]
            double totalFares = 0, averageFare1 = 0, averageFare2 = 0, averageFare3 = 0;
            long amountPess = 0,survivedPass = 0, notSurvivedPass = 0, survivedMen = 0,
                    notSurvivedMen = 0, survivedWomen = 0, notSurvivedWomen = 0,
                    survivedChildren = 0, notSurvivedChildren = 0;
            str = br.readLine();
            while (str != null) {
                cells = str.split(",");
                amountPess++;

                totalFares += Double.parseDouble(cells[10]);
                averageFare1 += Integer.parseInt(cells[2]) == 1 ? Double.parseDouble(cells[10]) : 0;
                averageFare2 += Integer.parseInt(cells[2]) == 2 ? Double.parseDouble(cells[10]) : 0;
                averageFare3 += Integer.parseInt(cells[2]) == 3 ? Double.parseDouble(cells[10]) : 0;

                survivedPass += Integer.parseInt(cells[1]) == 1 ? 1 : 0;
                survivedMen += Integer.parseInt(cells[1]) == 1 &&
                        cells[5].equalsIgnoreCase("male") ? 1 : 0;
                survivedWomen += Integer.parseInt(cells[1]) == 1 &&
                        cells[5].equalsIgnoreCase("female") ? 1 : 0;
                survivedChildren += Integer.parseInt(cells[1]) == 1 &&
                        !cells[6].equalsIgnoreCase("") &&
                        Double.parseDouble(cells[6]) < 18 ? 1 : 0;
                str = br.readLine();

                notSurvivedChildren+= Integer.parseInt(cells[1]) == 0 &&
                        !cells[6].equalsIgnoreCase("") &&
                        Double.parseDouble(cells[6]) < 18 ? 1 : 0;
            }

            notSurvivedPass = amountPess - survivedPass;
            notSurvivedMen = notSurvivedPass - survivedMen;
            notSurvivedWomen = notSurvivedPass - survivedWomen;

            System.out.println("total fares: " + String.format("%.2f", totalFares));

            System.out.println("average fare for 1 class : " + String.format("%.2f", averageFare1));
            System.out.println("average fare for 2 class : " + String.format("%.2f", averageFare2));
            System.out.println("average fare for 3 class : " + String.format("%.2f", averageFare3));

            System.out.println("total quantity of survived : " + survivedPass);
            System.out.println("total quantity of non survived : " + (notSurvivedPass));
            System.out.println("quantity of survived men : " + survivedMen);
            System.out.println("quantity of non survived men : " + notSurvivedMen);
            System.out.println("quantity of survived women : " + survivedWomen);
            System.out.println("quantity of non survived women : " + notSurvivedWomen);
            System.out.println("quantity of survived children : " + survivedChildren);
            System.out.println("quantity of not survived children : " + notSurvivedChildren);

        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }
}
