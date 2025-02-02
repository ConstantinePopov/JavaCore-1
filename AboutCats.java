import com.fasterxml.jackson.annotation.JsonProperty;

public class AboutCats {
    private  String id;
    private  String text;
    private  String type;
    private  String user;
    private  int upvotes;

    public AboutCats() {}

    public AboutCats(@JsonProperty("id") String id,
                     @JsonProperty("text") String text,
                     @JsonProperty("type") String type,
                     @JsonProperty("user") String user,
                     @JsonProperty("upvotes") int upvotes) {
        this.id = id;
        this.text = text;
        this.type = type;
        this.user = user;
        this.upvotes = upvotes;
    }

    @Override
    public String toString() {
        return "AboutCats{" +
                "id='" + id + '\'' +
                ", text='" + text + '\'' +
                ", type='" + type + '\'' +
                ", user='" + user + '\'' +
                ", upvotes=" + upvotes +
                '}';
    }

    public int getUpvotes() {
        return upvotes;
    }
}
