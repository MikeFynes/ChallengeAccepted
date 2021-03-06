/*
 * THIS IS A PLAIN OLD JAVA OBJECT WHICH ALLOWS A CHALLENGE TO BE EASILY
 * TRANSFERED FROM THE BACK END, THE INSTANCES OF THIS CLASS ARE STORED IN
 * AN ARRAYLIST IN MainActivity CLASS
 * 
*/


package fi.metropolia.challengedemo;


/**
 * Created by mike on 18/11/2014.
 */
class Challenges {
    private int id;

    private String name;

    private String description;
    
    private String category;

    private int points;


    public Challenges(int id, int points, String name, String description, String category){
    setId(id);
    setPoints(points);
    setName(name);
    setDescription(description);
    setCategory(category);

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

	public String getCategory() {
		return category;
	}

	public void setCategory(String category) {
		this.category = category;
	}
    
    
    
}
