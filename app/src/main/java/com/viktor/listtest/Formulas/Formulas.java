package com.viktor.listtest.Formulas;

import java.lang.*;

public class Formulas {
    // ----------------------------FORMULA FOR KILOGRAMS/GRAMS----------------------------
    public static double calculateFormula(String idealWeight, String activityLevel, String age, String massunit) {
        double idealWeightD = Double.valueOf(idealWeight);
        double result = 0.0;
        double resFinal = 0.0;


        if (activityLevel.equals("Low")) {
            if (age.equals("2 - 4 months old")) {
                result = 0.08;
            } else if (age.equals("4 - 6 months old")) {
                result = 0.06;
            } else if (age.equals("6 - 8 months old")) {
                result = 0.04;
            } else if (age.equals("8 - 18 months old")) {
                result = 0.03;
            } else if (age.equals("Adult")) {
                result = 0.02;
            } else if (age.equals("Senior")) {
                result = 0.02;
            }
        } else if (activityLevel.equals("Medium")) {
            if (age.equals("2 - 4 months old")) {
                result = 0.09;
            } else if (age.equals("4 - 6 months old")) {
                result = 0.07;
            } else if (age.equals("6 - 8 months old")) {
                result = 0.05;
            } else if (age.equals("8 - 18 months old")) {
                result = 0.035;
            } else if (age.equals("Adult")) {
                result = 0.025;
            } else if (age.equals("Senior")) {
                result = 0.02;
            }
        } else if (activityLevel.equals("High")) {
            if (age.equals("2 - 4 months old")) {
                result = 0.1;
            } else if (age.equals("4 - 6 months old")) {
                result = 0.08;
            } else if (age.equals("6 - 8 months old")) {
                result = 0.06;
            } else if (age.equals("8 - 18 months old")) {
                result = 0.04;
            } else if (age.equals("Adult")) {
                result = 0.03;
            } else if (age.equals("Senior")) {
                result = 0.025;
            }
        }

        if (massunit.equals("Kilograms")) {
            resFinal = idealWeightD * result * 1000;
        } else if (massunit.equals("Pounds")) {
            resFinal = idealWeightD * result * 16;
        }

        return resFinal;
    }
}
