package model.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor

public class MovieDTO {
	private String title;	// 영화 제목
	private String genre;	// 장르
	private String nation;	// 국가
	private String dirctor;	// 감독
	private String actor1;	// 배우 1
	private String actor2;	// 배우 2
}
