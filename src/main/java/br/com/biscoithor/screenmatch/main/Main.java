package br.com.biscoithor.screenmatch.main;

import br.com.biscoithor.screenmatch.model.Episode;
import br.com.biscoithor.screenmatch.model.EpisodeData;
import br.com.biscoithor.screenmatch.model.SeasonData;
import br.com.biscoithor.screenmatch.model.SeriesData;
import br.com.biscoithor.screenmatch.service.ConsumeApi;
import br.com.biscoithor.screenmatch.service.DataConverter;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.*;
import java.util.stream.Collectors;

public class Main {

    private final String  ADDRESS="https://www.omdbapi.com/?t=";
    private final String APIKEY ="&apikey=f6243ad3";
    private Scanner reader = new Scanner(System.in);
    private DataConverter converter = new DataConverter();
    private ConsumeApi seriesLoader = new ConsumeApi();;

    public void showMenu()
    {
        System.out.println("Type the title you want to search");
        var title = reader.nextLine();
        var json = seriesLoader.obterDados(ADDRESS + title.replace(" ", "+") + APIKEY);
        SeriesData seriesData = converter.getDados(json,SeriesData.class);
        System.out.println(seriesData);

        List<SeasonData> seasons = new ArrayList<>();

		for (int i = 1; i <= seriesData.numberOfSeasons() ; i++)
		{
			json = seriesLoader.obterDados(ADDRESS + title.replace(" ", "+")+"&Season=" + i + APIKEY);
			SeasonData seasonData = converter.getDados(json, SeasonData.class);
			seasons.add(seasonData);
		}
		seasons.forEach(System.out::println);

       seasons.forEach(t -> t.episodes().forEach(e-> System.out.println(e.title())));
       List<EpisodeData> episodeDatas = seasons.stream()
                            .flatMap(s->s.episodes().stream())
                            .collect(Collectors.toList());
        System.out.println("Top 5 episodes");
       episodeDatas.stream()
               .filter(e->!e.imdbRating().equalsIgnoreCase("N/A"))
               .sorted(Comparator.comparing(EpisodeData::imdbRating).reversed())
               .limit(5)
               .forEach(System.out::println);

        List<Episode> episodes = seasons.stream()
                .flatMap(s->s.episodes().stream()
                        .map(ed-> new Episode(s.seasonNumber(),ed))
                ).collect(Collectors.toList());
        episodes.forEach(System.out::println);

//        System.out.println("Filter episodes starting from what year?");
//        var year = reader.nextInt();
//        reader.nextLine();
//
//        LocalDate searchDate = LocalDate.of(year,1,1);
//        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
//
//        episodes.stream()
//                .filter(e->e.getReleaseDate()!=null && e.getReleaseDate().isAfter(searchDate))
//                .forEach(e-> System.out.println(
//                        "Season: " + e.getSeason()
//                                + " Episode: " + e.getTitle()
//                                + " Release Date: " + e.getReleaseDate().format(formatter)
//                ));
        System.out.println("Insert Episode you wish to know the season for: ");
        var searchedTitle = reader.nextLine();
        Optional<Episode> searchedEpisode = episodes.stream()
                .filter(e-> e.getTitle().toUpperCase().contains(searchedTitle.toUpperCase()))
                .findFirst();
        System.out.println("Episode Title: " + searchedEpisode.get().getTitle()+" Season: " + searchedEpisode.get().getSeason());
    }
}
