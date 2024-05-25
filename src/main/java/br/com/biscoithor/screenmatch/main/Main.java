package br.com.biscoithor.screenmatch.main;

import br.com.biscoithor.screenmatch.model.EpisodeData;
import br.com.biscoithor.screenmatch.model.SeasonData;
import br.com.biscoithor.screenmatch.model.SeriesData;
import br.com.biscoithor.screenmatch.service.ConsumeApi;
import br.com.biscoithor.screenmatch.service.DataConverter;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {

    private final String ADDRESS="https://www.omdbapi.com/?t=";
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

    }
}
