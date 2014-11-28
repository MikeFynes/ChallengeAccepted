package fi.metropolia.challengedemo;


/**
 * Created by mike on 18/11/2014.
 */
class Challenges {
    private int id;

    private String name;

    private String description;

    private int points;


    public Challenges(int id, int points, String name, String description){
    setId(id);
    setPoints(points);
    setName(name);
    setDescription(description);

    }

    public int getPoints() {
        return points;
    }

    public void setPoints(int points) {
        this.points = points;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
