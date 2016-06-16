package cos.test;

public class Question {
    private int id;
    private String question;
    private String image;
    private String userName;
    private String dateCreateQuestion;
    private int countAnswers;
    private String linkQuestion;
    private String linkProfile;

    public Question(int id, String question, String image, String userName, String dateCreateQuestion,
                    int countAnswers, String linkQuestion, String linkProfile) {
        this.id = id;
        this.question = question;
        this.image = image;
        this.userName = userName;
        this.dateCreateQuestion = dateCreateQuestion;
        this.countAnswers = countAnswers;
        this.linkQuestion = linkQuestion;
        this.linkProfile = linkProfile;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getQuestion() {
        return question;
    }

    public void setQuestion(String question) {
        this.question = question;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getDateCreateQuestion() {
        return dateCreateQuestion;
    }

    public void setDateCreateQuestion(String dateCreateQuestion) {
        this.dateCreateQuestion = dateCreateQuestion;
    }

    public int getCountAnswers() {
        return countAnswers;
    }

    public void setCountAnswers(int countAnswers) {
        this.countAnswers = countAnswers;
    }

    public String getLinkQuestion() {
        return linkQuestion;
    }

    public void setLinkQuestion(String linkQuestion) {
        this.linkQuestion = linkQuestion;
    }

    public String getLinkProfile() {
        return linkProfile;
    }

    public void setLinkProfile(String linkProfile) {
        this.linkProfile = linkProfile;
    }
}