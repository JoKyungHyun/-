package model;

import java.io.IOException;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

import model.dto.MovieDTO;

public class MovieDAO {
	
	// 영화 검색
	public static MovieDTO searchMovie(String title, String director) {

		Document doc1 = null;
		Document doc2 = null;
		MovieDTO list = null;

		try {

			doc1 = Jsoup.connect(
					"http://www.kobis.or.kr/kobisopenapi/webservice/rest/movie/searchMovieList.xml?key=7c143eb909d92fd93497473822f6c1fe&movieNm="
							+ title + "&directorNm=" + director)
					.get();

			Elements name = doc1.select("movieListResult > movieList > movie:nth-child(1)> movieNm");
			Elements Code = doc1.select("movieListResult > movieList > movie:nth-child(1)> movieCd");
			Elements genre = doc1.select("movieListResult > movieList > movie:nth-child(1)> repGenreNm");

			doc2 = Jsoup.connect(
					"http://www.kobis.or.kr/kobisopenapi/webservice/rest/movie/searchMovieInfo.xml?key=7c143eb909d92fd93497473822f6c1fe&movieCd="
							+ Code.text())
					.get();

			Elements nation = doc1.select("movieListResult > movieList > movie:nth-child(1)> repNationNm");
			Elements direct = doc2.select("movieInfoResult > movieInfo> directors > director > peopleNm");
			Elements actor1 = doc2.select("movieInfoResult > movieInfo> actors > actor:nth-child(1)> peopleNm");
			Elements actor2 = doc2.select("movieInfoResult > movieInfo> actors > actor:nth-child(2)> peopleNm");

			list = (new MovieDTO(name.text(), genre.text(), nation.text(), direct.text(), actor1.text(),
					actor2.text()));

		} catch (IOException e) {
			e.printStackTrace();
		}
		return list;
	}
}
