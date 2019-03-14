package Module;


public class Reader extends Person {

    private String rId;

    private String mobile_number;


    private String emailR;


    public Reader(String rId) {

        this.rId = rId;
    }

    public Reader(String rId, String name, String mobile_number, String emailR) {
        super(name);
        this.rId = rId;
        this.mobile_number = mobile_number;
        this.emailR = emailR;
    }

    public String getrId() {
        return rId;
    }


    public String getMobile_number() {
        return mobile_number;
    }


    public String getEmailR() {
        return emailR;
    }


    @Override
    public String toString() {
        return "Reader{" +
                "rId='" + rId + '\'' +
                ", name='" + super.getName() + '\'' +
                ", mobile_number='" + mobile_number + '\'' +
                ", emailR='" + emailR + '\'' +
                '}';
    }

}