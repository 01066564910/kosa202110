package school.domain;

import org.apache.ibatis.type.Alias;

import lombok.Data;

@Data
@Alias("startEndPageDTO")
public class StartEndPageDTO {
	Long startRow;
	Long endRow;
	String searchName;
	String searchWord;
}
