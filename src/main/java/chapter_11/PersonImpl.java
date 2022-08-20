package chapter_11;

public class PersonImpl implements Person {
    private String name;
    private String gender;
    private int rating;
    private int ratingCount = 0;

    public PersonImpl(String name, String gender) {
        this.name = name;
        this.gender = gender;
        rating = 0;
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public String getGender() {
        return gender;
    }

    public int getGeekRating() {
        if (ratingCount == 0) return 0;
        return (rating / ratingCount);
    }

    @Override
    public void setName(String name) {
        this.name = name;
    }

    @Override
    public void setGender(String gender) {
        this.gender = gender;
    }

    @Override
    public void setGeekRating(int rating) {
        this.rating += rating;
        ratingCount++;
    }
}
