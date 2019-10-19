package com.javatest2;

public class Graduate extends Student{
    private String tutor;//研究生导师
    private String researchDirection;//研究生研究方向

    public Graduate() {
    }

    public Graduate(String name, String stuID, String sex, String birthday, String grade, String major, int stuCredit, String tutor, String researchDirection) {
        super(name, stuID, sex, birthday, grade, major, stuCredit);
        this.tutor = tutor;
        this.researchDirection = researchDirection;
    }

    public String getTutor() {
        return tutor;
    }

    public void setTutor(String tutor) {
        this.tutor = tutor;
    }

    public String getResearchDirection() {
        return researchDirection;
    }

    public void setResearchDirection(String researchDirection) {
        this.researchDirection = researchDirection;
    }
}
