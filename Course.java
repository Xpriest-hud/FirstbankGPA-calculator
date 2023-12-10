package GPAcalculator;


class Course {
    private String name;
    private String code;
    private int units;
    private String grade;

    public void setName(String name) {
        this.name = name;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public void setUnits(int units) {
        this.units = units;
    }

    public void setGrade(String grade) {
        this.grade = grade;
    }

    public String getName() {
        return name;
    }

    public String getCode() {
        return code;
    }

    public int getUnits() {
        return units;
    }

    public String getGrade() {
        return grade;
    }

    public double getScore() {
        return calculateWeightedScore(grade);
    }

    public int calculateGradeUnits() {
        switch (grade) {
            case "A":
                return 5;
            case "B":
                return 4;
            case "C":
                return 3;
            case "D":
                return 2;
            case "E":
                return 1;
            case "Fail":
                return 0;
            default:
                return -1;
        }
    }

    private double calculateWeightedScore(String grade) {
        switch (grade) {
            case "A":
                return 5.0;
            case "B":
                return 4.0;
            case "C":
                return 3.0;
            case "D":
                return 2.0;
            case "E":
                return 1.0;
            case "Fail":
                return 0.0;
            default:
                return -1.0;
        }
    }
}
