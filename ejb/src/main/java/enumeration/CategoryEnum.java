package enumeration;

public enum CategoryEnum {
    SELFSTICKING("AUTOCOLLANT"),
    MAGNETIC("MAGNETIQUE");

    private String categoryText;

    CategoryEnum(String categoryText) {
        this.categoryText = categoryText;
    }

    public String getCategoryText() {
        return categoryText;
    }

    public void setCategoryText(String categoryText) {
        this.categoryText = categoryText;
    }


}
