package kr.ac.kopo.kihwan.bookmarket.domain;

import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.web.multipart.MultipartFile;

import java.math.BigDecimal;


//@Setter
//@Getter
//@NoArgsConstructor
@Data
public class Book {
    private String bookID;  //도서 ID
    private String name;    //도서 제목
    private BigDecimal unitPrice;   //가격
    private String author;  //저자
    private String publisher;   //출판사
    private String description; //설명
    private String category;    //분류
    private long unitInStock;   //재고수
    private String releaseDate; //출판일(월/년)
    private String condition;   //신규,중고,전자책
    private String fileName; //도서 이미지 파일
    private MultipartFile bookImage; //업로드된 도서 이미지 파일
}
