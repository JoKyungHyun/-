package model.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor

public class MemberDTO {

	private String id;
	private String name;
	private String sex;
	private String address;
	private String pw;
	private String repw;
	@Override
	public String toString() {
		return "MemberDTO [id=" + id + ", name=" + name + ", sex=" + sex + ", address=" + address + ", pw=" + pw
				+ ", repw=" + repw + "]";
	}
	
	

	
}
