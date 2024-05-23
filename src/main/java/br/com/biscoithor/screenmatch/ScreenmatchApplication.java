package br.com.biscoithor.screenmatch;

import br.com.biscoithor.screenmatch.model.SeriesData;
import br.com.biscoithor.screenmatch.service.ConsumeApi;
import br.com.biscoithor.screenmatch.service.DataConverter;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class ScreenmatchApplication implements CommandLineRunner {

	public static void main(String[] args) {
		SpringApplication.run(ScreenmatchApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		var seriesLoader = new ConsumeApi();
		var json = seriesLoader.obterDados("https://www.omdbapi.com/?t=The+Office&apikey=f6243ad3");
		DataConverter converter = new DataConverter();
		SeriesData data = converter.getDados(json,SeriesData.class);
		System.out.println(data);
	}
}
