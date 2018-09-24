package pl.sporttown.domain.model;

public enum Category {


    BIEGANIE("BIEGANIE"), KOLARSTWO("KOLARSTWO"), PIŁKA_NOŻNA("PIŁKA NOŻNA"), WSPINACZKA("WSPINACZKA"), SPORTY_WODNE("SPORTY WODNE"),
    SPORTY_ZIMOWE("SPORTY ZIMOWE"),  ROLKI("ROLKI"), BOKS("BOKS"), WĘDKARSTWO("WĘDKARSTWO");

    private String name;

    Category(String name) {
        this.name = name;
    }
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

}