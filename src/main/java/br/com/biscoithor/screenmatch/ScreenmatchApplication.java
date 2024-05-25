package br.com.biscoithor.screenmatch;

import br.com.biscoithor.screenmatch.main.Main;
import br.com.biscoithor.screenmatch.model.EpisodeData;
import br.com.biscoithor.screenmatch.model.SeasonData;
import br.com.biscoithor.screenmatch.model.SeriesData;
import br.com.biscoithor.screenmatch.service.ConsumeApi;
import br.com.biscoithor.screenmatch.service.DataConverter;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.ArrayList;
import java.util.List;

@SpringBootApplication
public class ScreenmatchApplication implements CommandLineRunner {

	public static void main(String[] args) {
		SpringApplication.run(ScreenmatchApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		Main main = new Main();
		main.showMenu();
	}
}
