package br.com.biscoithor.screenmatch.model;

import org.springframework.cglib.core.Local;

import java.time.LocalDate;
import java.time.format.DateTimeParseException;

public class Episode {
    private Integer season;
    private String title;
    private Integer episodeNo;
    private double imdbRating;
    private LocalDate releaseDate;

    public Episode(Integer seasonNo, EpisodeData episodeData)
    {
        this.season = seasonNo;
        this.title = episodeData.title();
        this.episodeNo = episodeData.episodeNumber();
        try
        {
            this.imdbRating = Double.valueOf(episodeData.imdbRating());
        }
        catch(NumberFormatException e)
        {
            this.imdbRating = 0.0;
        }
        try
        {
            this.releaseDate = LocalDate.parse(episodeData.releaseDate());
        }
        catch(DateTimeParseException e)
        {
            this.releaseDate = null;
        }

    }

    public Integer getSeason() {
        return season;
    }

    public void setSeason(Integer season) {
        this.season = season;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Integer getEpisodeNo() {
        return episodeNo;
    }

    public void setEpisodeNo(Integer episodeNo) {
        this.episodeNo = episodeNo;
    }

    public double getImdbRating() {
        return imdbRating;
    }

    public void setImdbRating(double imdbRating) {
        this.imdbRating = imdbRating;
    }

    public LocalDate getReleaseDate() {
        return releaseDate;
    }

    public void setReleaseDate(LocalDate releaseDate) {
        this.releaseDate = releaseDate;
    }

    @Override
    public String toString() {
        return "{" +
                "season=" + season +
                ", title='" + title + '\'' +
                ", episodeNo=" + episodeNo +
                ", imdbRating=" + imdbRating +
                ", releaseDate=" + releaseDate +
                '}';
    }
}
