package Module;



import java.util.List;

public class DVD extends LibraryItem   {


 private List<Language> available_languages;

    private List<Language> available_subtitles;

    private String producer;

    private String actors;


    public DVD() {


    }

    public DVD(int iSBN, String theTitle, String sector, DateModule publicationDateModule, String type,
               List<Language> available_languages, List<Language> available_subtitles, String producer, String actors) {
        super(iSBN, theTitle, sector, publicationDateModule, type);
        this.available_languages = available_languages;
        this.available_subtitles = available_subtitles;


        this.producer = producer;
        this.actors = actors;
    }

    public DVD(int iSBN, String theTitle, String sector, DateModule publicationDateModule, DateModule borrowed,
               Reader currentReader, String type, List<Language> available_languages, List<Language> available_subtitles
            , String producer, String actors) {
        super(iSBN, theTitle, sector, publicationDateModule, borrowed, currentReader, type);
        this.available_languages = available_languages;
        this.available_subtitles = available_subtitles;
        this.producer = producer;
        this.actors = actors;

    }

    public List<Language> getAvailable_languages() {
        return available_languages;
    }

    public void setAvailable_languages(List<Language> available_languages) {
        this.available_languages = available_languages;
    }

    public List<Language> getAvailable_subtitles() {
        return available_subtitles;
    }

    public void setAvailable_subtitles(List<Language> available_subtitles) {
        this.available_subtitles = available_subtitles;
    }

    public String getProducer() {
        return producer;
    }

    public void setProducer(String producer) {
        this.producer = producer;
    }

    public String getActors() {
        return actors;
    }

    public void setActors(String actors) {
        this.actors = actors;
    }
}
