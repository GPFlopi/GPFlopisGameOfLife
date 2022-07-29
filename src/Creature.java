public class Creature {

    Boolean alive;
    int id;

    public Creature(Boolean l,int id) {
        this.alive=l;
        this.id=id;
    }

    public Boolean getAlive() {
        return alive;
    }

    public void setAlive(Boolean alive) {
        this.alive = alive;
    }

    public String isCreatureAlive(){
        if(alive) {
            return "1";
        } else return "0";
    }
}
